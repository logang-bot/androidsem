package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.adapters.menusAdapter;
import com.example.guifinalprojecto.adapters.structMenu;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class dataMenu extends AppCompatActivity {
    public int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_menu);
        Bundle bundle = this.getIntent().getExtras();
        String idRes = bundle.getString("idRes");

        Toast.makeText(this," idRest = "+idRes.toString(), Toast.LENGTH_LONG).show();
        Call<structMenu> call = RetrofitClient
                .getInstance()
                .getApi().getDataMenu(idRes);


        call.enqueue(new Callback<structMenu>() {
            @Override
            public void onResponse(Call <structMenu> call, Response <structMenu> response) {
                structMenu data = response.body();
                ImageView image_food= findViewById(R.id.image_food);
                TextView TitleRest= findViewById(R.id.title_res_onmenu);
                TextView name_food= findViewById(R.id.name_food);
                TextView precio_food= findViewById(R.id.precio_food);
                TextView food_desc=findViewById(R.id.food_desc);
                TextView food_cant_num=findViewById(R.id.food_cant_num);
                Button plus = findViewById(R.id.plus_food); // boton para aumentar la cantidad
                Button dism= findViewById(R.id.dism_food);
                TitleRest.setText(data.getNombre());
                name_food.setText(data.getNombre());
                precio_food.setText(data.getPrecio());
                food_desc.setText(data.getDescripcion());
                food_cant_num.setText(data.getContador());
                FloatingActionButton add_to_cart = findViewById(R.id.añadir_pedido);
                add_to_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Añadido al carrito",Toast.LENGTH_LONG).show();
                    }
                });

                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        contador++;
                        food_cant_num.setText(contador);

                    }
                });

                dism.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        contador--;
                        food_cant_num.setText(contador);
                    }
                });

                Glide.with(getApplicationContext())
                        .load(RetrofitClient.BASE_URL + data.getFoto())
                        .centerCrop()
                        .into(image_food);
            }
            @Override
            public void onFailure(Call <structMenu> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}