package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.structMenu;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createMyMenu extends AppCompatActivity {
    private Button siguiente ;
    private createMyMenu root=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycreatemymenu);
        loadComponents();
    }
    private void loadComponents(){
        siguiente = this.findViewById(R.id.new_Sig);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText newNombreM = root.findViewById(R.id.new_nombreM);
                TextInputEditText new_precio = root.findViewById(R.id.new_precio);
                TextInputEditText new_desc = root.findViewById(R.id.new_desc);
                TextInputEditText new_cant = root.findViewById(R.id.new_cant);
                String nombre = newNombreM.getText().toString().trim();
                String precio = new_precio.getText().toString().trim();
                String desc = new_desc.getText().toString().trim();
                String cant = new_cant.getText().toString().trim();
                Call<structMenu> call = RetrofitClient
                        .getInstance()
                        .getApi().createMenu(nombre, precio, desc, cant);
                call.enqueue(new Callback<structMenu>() {
                    @Override
                    public void onResponse(Call<structMenu> call, Response<structMenu> response) {
                        //  Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if (response.body().getMessage().equals("menu creado satisfactoriamente")) {
                            Intent intent = new Intent(root, image_createMyMenu.class);
                            intent.putExtra("nombre", nombre);
                            root.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<structMenu> call, Throwable t) {
                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
