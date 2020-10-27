package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editMyRest extends AppCompatActivity {
    private EditText ed_nombreR, ed_nitR, ed_dirR,ed_telR,ed_latR, ed_logR;
    private Button confirmEdit;
    private editMyRest root= this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_rest);

        ed_nombreR = findViewById(R.id.ed_nombreR);
        ed_nitR = findViewById(R.id.ed_nitR);
        ed_dirR = findViewById(R.id.ed_dirR);
        ed_latR = findViewById(R.id.ed_latR);
        ed_logR = findViewById(R.id.ed_logR);
        ed_telR = findViewById(R.id.ed_telR);
        confirmEdit= findViewById(R.id.editRest);
        ImageView fotoRest= findViewById(R.id.ed_fotoR);
        ImageView logoRest= findViewById(R.id.ed_logoR);
        Bundle bundle = this.getIntent().getExtras();
        String nombre = bundle.getString("nombreR");
        String nit = bundle.getString("nitR");
        String dir = bundle.getString("dirR");
        String tel = bundle.getString("telR");
        String log = bundle.getString("logR");
        String lat = bundle.getString("latR");
        String idRest=bundle.getString("idRes");
        String foto =bundle.getString("fotoRes");
        String logo =bundle.getString("logoRes");
        ed_nombreR.setText(nombre);
        ed_nitR.setText(nit);
        ed_dirR.setText(dir);
        ed_telR.setText(tel);
        ed_logR.setText(log);
        ed_latR.setText(lat);
        Glide.with(getApplicationContext())
                .load(RetrofitClient.BASE_URL + foto)
                .centerCrop()
                .into(fotoRest);
        Glide.with(getApplicationContext())
                .load(RetrofitClient.BASE_URL + logo)
                .centerCrop()
                .into(logoRest);

        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre= ed_nombreR.getText().toString().trim();
                String nit = ed_nitR.getText().toString().trim();
                String dir = ed_dirR.getText().toString().trim();
                String tel =ed_telR.getText().toString().trim();
                String log = ed_logR.getText().toString().trim();
                String lat = ed_latR.getText().toString().trim();

                Call<structRests> call = RetrofitClient
                        .getInstance()
                        .getApi().edRest(UserDataServer.TOKEN,nombre, nit, dir, tel, log, lat, idRest);

                call.enqueue(new Callback<structRests>() {
                    @Override
                    public void onResponse(Call<structRests> call, Response<structRests> response) {
                        String msg = response.body().getMessage();
                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if(msg.equals("Fue actualizado correctamente")){
                            Intent intent = new Intent(root, dataMyRest.class);
                            intent.putExtra("idRest", idRest);
                            root.startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<structRests> call, Throwable t) {
                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
