package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.EndPoints;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.entity.mime.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    TextView tvTimeMsg;
    Button loginButton, signupButton;


    private MainActivity root = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout=findViewById(R.id.container);
        tvTimeMsg=findViewById(R.id.tv_time_msg);

        Calendar c = Calendar.getInstance();

        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            //morning
            constraintLayout.setBackground(getDrawable(R.drawable.good_morning_img));
            tvTimeMsg.setText("Good Morning");
        }
        else if (timeOfDay >= 12 && timeOfDay < 16) {
            //afternoon
        }
        else if (timeOfDay >= 16 && timeOfDay < 21) {
            //evening
        }
        else if (timeOfDay >= 21 && timeOfDay < 24) {
            //night
            constraintLayout.setBackground(getDrawable(R.drawable.good_night_img));
            tvTimeMsg.setText("Good Night");
        }
        loadComponents();
    }
    private void loadComponents() {
        loginButton = this.findViewById(R.id.btn_signIn);
        signupButton = this.findViewById(R.id.btn_signUp);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, register.class);
                root.startActivity(intent);
            }
        });

        loginButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Intent intent = new Intent(root, MainDashboard.class);
                //root.startActivity(intent);
                //login
                TextInputEditText et_email = root.findViewById(R.id.et_email);
                TextInputEditText et_password = root.findViewById(R.id.et_password);

                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                Call <logInResponse> call = RetrofitClient
                        .getInstance()
                        .getApi().userLogin(email, password);

                call.enqueue(new Callback<logInResponse>() {
                    @Override
                    public void onResponse(Call<logInResponse> call, Response<logInResponse> response) {

                        if(response.isSuccessful()){
                            if(response.body().getToken()!=null){
                                UserDataServer.MSN = response.body().getMessage();
                                UserDataServer.TOKEN = response.body().getToken();
                                Toast.makeText(root, "Sugoi!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(root, MainDashboard.class);
                                root.startActivity(intent);
                            }
                            else{
                                Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<logInResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                //login
                //call to api
                /*AsyncHttpClient client = new AsyncHttpClient();

                TextInputEditText email = root.findViewById(R.id.et_email);
                TextInputEditText password = root.findViewById(R.id.et_password);


                RequestParams params = new RequestParams();
                params.add("email", email.toString());
                params.add("password", password.toString());

                client.post(EndPoints.LOGIN_SERVICE, params, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                        try {
                            if(response.has("message")){
                                UserDataServer.MSN = response.getString("message");
                            }
                            if(response.has("token")){
                                UserDataServer.TOKEN = response.getString("token");
                            }
                            if(UserDataServer.TOKEN.length()>150){
                                Intent intent = new Intent(root, MainDashboard.class);
                                root.startActivity(intent);
                            }
                            else{
                                Toast.makeText(root, response.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                /*Intent intent = new Intent(root, MainDashboard.class);
                intent.putExtra("backupAgentName", root.getApplicationInfo().backupAgentName);
                intent.putExtra("data", "soy la informacion de la actividad MainActivity");
                intent.putExtra("number", 26);
                root.startActivity(intent);*/
            }
        }));
    }
}