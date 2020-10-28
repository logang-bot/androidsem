package com.example.guifinalprojecto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createMyRest extends AppCompatActivity {
    private Button siguiente ;
    private createMyRest root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_rest);
        loadComponents();
    }
    private void loadComponents(){
        siguiente = this.findViewById(R.id.new_Sig);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText newNombreR = root.findViewById(R.id.new_nombreR);
                TextInputEditText newNitR = root.findViewById(R.id.new_nitR);
                TextInputEditText newDirR = root.findViewById(R.id.new_dirR);
                TextInputEditText newTelR = root.findViewById(R.id.new_teR);
                String nombre = newNombreR.getText().toString().trim();
                String nit = newNitR.getText().toString().trim();
                String calle = newDirR.getText().toString().trim();
                String tel = newTelR.getText().toString().trim();
                Call<structRests> call = RetrofitClient
                        .getInstance()
                        .getApi().createRest(UserDataServer.TOKEN, nombre, nit, calle, tel);
                call.enqueue(new Callback<structRests>() {
                    @Override
                    public void onResponse(Call<structRests> call, Response<structRests> response) {
                      //  Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if(response.body().getMessage().equals("El restaurant fue creado correctamente")){
                            Intent intent = new Intent(root, logo_createMyRest.class);
                            intent.putExtra("nombreR", nombre);
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
    }}