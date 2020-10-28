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
import com.example.guifinalprojecto.adapters.structMenu;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dataMyMenu extends AppCompatActivity {
    private dataMyMenu root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_my_menu);

        Bundle bundle = this.getIntent().getExtras();
        String idMenu = bundle.getString("idMenu");
        String nameRest = bundle.getString("nameRest");

        TextView title_res = findViewById(R.id.title_res_onMyMenu);
        title_res.setText(nameRest);




        Toast.makeText(this," idMenu = "+idMenu.toString(), Toast.LENGTH_LONG).show();
        Call<structMenu> call = RetrofitClient
                .getInstance()
                .getApi().getDataMenu(idMenu);


        call.enqueue(new Callback<structMenu>() {
            @Override
            public void onResponse(Call <structMenu> call, Response<structMenu> response) {
                structMenu data = response.body();
                ImageView image_Myfood= findViewById(R.id.image_Myfood);


                TextView precio_Myfood= findViewById(R.id.precio_Myfood);
                TextView food_Mydesc=findViewById(R.id.food_Mydesc);
                TextView name_Myfood= findViewById(R.id.name_Myfood);
                name_Myfood.setText(data.getNombre());

                precio_Myfood.setText(data.getPrecio());
                food_Mydesc.setText(data.getDescripcion());
                name_Myfood.setText(data.getNombre());

                FloatingActionButton delete_menu = findViewById(R.id.elimMenu);
                FloatingActionButton edit_menu = findViewById(R.id.editMenu);

                delete_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                edit_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(root, editMyMenu.class);
                        root.startActivity(intent);
                        Toast.makeText(getApplicationContext(),data.getNombre() , Toast.LENGTH_LONG).show();
                    }
                });

                Glide.with(getApplicationContext())
                        .load(RetrofitClient.BASE_URL + data.getFoto())
                        .centerCrop()
                        .into(image_Myfood);
            }
            @Override
            public void onFailure(Call <structMenu> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}