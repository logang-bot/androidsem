package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editUser extends AppCompatActivity {

    private EditText edname, edemail, edpass, edcpass;
    private Button btnEdit;

    private editUser root = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        edname = findViewById(R.id.ed_name);
        edemail = findViewById(R.id.ed_email);
        edpass = findViewById(R.id.ed_pass);
        edcpass = findViewById(R.id.edcpass);

        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        String email = bundle.getString("email");
        //String pass = bundle.getString("pass");

        btnEdit = findViewById(R.id.buttonedit);

        edname.setText(name);
        edemail.setText(email);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= edname.getText().toString().trim();
                String email = edemail.getText().toString().trim();
                String password = edpass.getText().toString().trim();
                String c_password = edcpass.getText().toString().trim();

                Call<logInResponse> call = RetrofitClient
                        .getInstance()
                        .getApi().edUser(UserDataServer.TOKEN, name, email, password, c_password);

                call.enqueue(new Callback<logInResponse>() {
                    @Override
                    public void onResponse(Call<logInResponse> call, Response<logInResponse> response) {
                        String msg = response.body().getMessage();
                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if(msg.equals("sugoi")){
                            Intent intent = new Intent(root, displayProfile.class);
                            root.startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<logInResponse> call, Throwable t) {
                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}