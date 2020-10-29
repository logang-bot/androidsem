package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dataMyRest extends AppCompatActivity {
    private String idR, Nombre, Nit, Direccion,Tel,Log,Lat,idRest,imageR,logoR;
    private FloatingActionButton edRest, delRest;
    private Button Lmenus;
    private dataMyRest root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_my_rest);
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
                idR = data.get_id();
                Nombre =data.getNombre();
                Nit =data.getNit();
                Direccion =data.getCalle();
                Log = data.getLog();
                Lat= data.getLat();
                Tel=data.getTelefono();
                imageR=data.getFoto();
                logoR=data.getLogo();
                ImageView imgRest= findViewById(R.id.imgRestP);
                ImageView logoRest= findViewById(R.id.logoRest);
                TextView TitleRest= findViewById(R.id.TitleRest);
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

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(root, listMyRests.class);
        root.startActivity(intent);
    }

    private void loadComponents(){
        edRest = this.findViewById(R.id.editRest);
        Lmenus = this.findViewById(R.id.verMenus);
        delRest= this.findViewById(R.id.elimRest);

        edRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, editMyRest.class);
                intent.putExtra("idR", idR);
                intent.putExtra("nombreR", Nombre);
                intent.putExtra("nitR", Nit);
                intent.putExtra("dirR", Direccion);
                intent.putExtra("telR", Tel);
                intent.putExtra("logR", Log);
                intent.putExtra("latR", Lat);
                intent.putExtra("idRes", idRest);
                intent.putExtra("fotoRes",imageR);
                intent.putExtra("logoRes",logoR);
                root.startActivity(intent);
            }
        });

        Lmenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, listMyMenus.class);
                intent.putExtra("idRest", idRest);
                root.startActivity(intent);
            }
        });

        delRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, deleteRest.class);
                intent.putExtra("idRest", idRest);
                intent.putExtra("nombreR", Nombre);
                root.startActivity(intent);
            }
        });

    }
}