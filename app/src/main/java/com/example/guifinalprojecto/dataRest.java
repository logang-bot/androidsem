package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.utils.UserDataServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dataRest extends AppCompatActivity {
    private String idRest;
    private Button atras;
    private dataRest root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_rest);
        Bundle bundle = this.getIntent().getExtras();
        idRest = bundle.getString("idRest");
        Toast.makeText(this," idRest = "+idRest.toString(), Toast.LENGTH_LONG).show();

        Call<structRests> call = RetrofitClient
                .getInstance()
                .getApi().getMydataRes(UserDataServer.TOKEN,idRest);
        call.enqueue(new Callback<structRests>() {
            @Override
            public void onResponse(Call<structRests> call, Response<structRests> response) {
                structRests data = response.body();

                ImageView imgRest= findViewById(R.id.imgRestP);
                ImageView logoRest= findViewById(R.id.logoRest);
                TextView NomPropietario= findViewById(R.id.propietarioRestC);
                TextView TitleRest= findViewById(R.id.TitleRest);
                TextView nitRest= findViewById(R.id.nitRest);
                TextView dirRest=findViewById(R.id.dirRest);
                TextView telRest=findViewById(R.id.telRest);
                TextView logRest=findViewById(R.id.logRest);
                TextView latRest=findViewById(R.id.latRest);
                TextView fechRest=findViewById(R.id.fechaRest);
                TitleRest.setText(data.getNombre());
                //NomPropietario.setText(data.getNombrePropietario());
                nitRest.setText(data.getNit());
                dirRest.setText(data.getCalle());
                telRest.setText(data.getTelefono());
                logRest.setText(data.getLog());
                latRest.setText(data.getLat());
                fechRest.setText(data.getFechaReg());
                Glide.with(getApplicationContext())
                        .load(RetrofitClient.BASE_URL + data.getFoto())
                        .centerCrop()
                        .into(imgRest);
                Glide.with(getApplicationContext())
                        .load(RetrofitClient.BASE_URL + data.getLogo())
                        .centerCrop()
                        .into(logoRest);
            }
            @Override
            public void onFailure(Call<structRests> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        loadComponents();
    }

    private void loadComponents(){
        atras = this.findViewById(R.id.verMenus);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, listMyRests.class);
               // intent.putExtra("idRest", idRest);
                root.startActivity(intent);
            }
        });

    }
}