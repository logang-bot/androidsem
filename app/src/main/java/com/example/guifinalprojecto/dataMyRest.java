package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.utils.UserDataServer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dataMyRest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_my_rest);
        Bundle bundle = this.getIntent().getExtras();
        String idRest = bundle.getString("idRest");
        Toast.makeText(this," idRest = "+idRest.toString(), Toast.LENGTH_LONG).show();

        Call<structRests> call = RetrofitClient
                .getInstance()
                .getApi().getMydataRes(UserDataServer.TOKEN,idRest);
        call.enqueue(new Callback<structRests>() {
            @Override
            public void onResponse(Call<structRests> call, Response<structRests> response) {
                structRests data = response.body();
                ImageView imgRest= findViewById(R.id.imgRestP);
                TextView TitleRest= findViewById(R.id.TitleRest);
                //TextView propRest= findViewById(R.id.propRest);
                TextView nitRest= findViewById(R.id.nitRest);
                TextView dirRest=findViewById(R.id.dirRest);
                TextView telRest=findViewById(R.id.telRest);
                TextView logRest=findViewById(R.id.logRest);
                TextView latRest=findViewById(R.id.latRest);
                TextView fechRest=findViewById(R.id.fechaRest);
                TitleRest.setText(data.getNombre());
               // propRest.setText(data.getPropietario());
                nitRest.setText(data.getNit());
                dirRest.setText(data.getCalle());
                telRest.setText(data.getTelefono());
                logRest.setText(data.getLog());
                latRest.setText(data.getLat());
                fechRest.setText(data.getFechaReg());
                Glide.with(getApplicationContext())
                        .load(RetrofitClient.BASE_URL + data.getLogo())
                        .centerCrop()
                        .into(imgRest);
            }
            @Override
            public void onFailure(Call<structRests> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}