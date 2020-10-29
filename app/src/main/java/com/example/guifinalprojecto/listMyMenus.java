package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.menusAdapter;
import com.example.guifinalprojecto.adapters.structMenu;
import com.example.guifinalprojecto.interfaces.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listMyMenus extends AppCompatActivity {
    private String idRest , nameRest;

    private listMyMenus root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_menus);
        Bundle bundle = this.getIntent().getExtras();
        idRest = bundle.getString("idRest");
        nameRest = bundle.getString("nameRest");
        Toast.makeText(this," idRest = "+idRest.toString(), Toast.LENGTH_LONG).show();

        TextView titleRest = findViewById(R.id.title_my_menus);
        titleRest.setText(nameRest);

        GridView GridMyMenu = findViewById(R.id.g_my_menu);
        Call<ArrayList<structMenu>> call = RetrofitClient
                .getInstance()
                .getApi().getMenu(idRest);
        Button newMenu = findViewById(R.id.newMenu);
        newMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), createMyMenu.class);
                intent.putExtra("idRes",idRest);
                intent.putExtra("nameRest",nameRest);
                root.startActivity(intent);
            }
        });

        call.enqueue(new Callback<ArrayList<structMenu>>() {
            @Override
            public void onResponse(Call<ArrayList<structMenu>> call, Response<ArrayList<structMenu>> response) {

                ArrayList<structMenu> data = response.body();
                menusAdapter adapter = new menusAdapter(data, getApplicationContext());
                GridMyMenu.setAdapter(adapter);

                GridMyMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //animation
                        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                        animation1.setDuration(4000);
                        view.startAnimation(animation1);
                        //animation
                        Intent intent = new Intent(getApplicationContext(), dataMyMenu.class); //cambiar vista
                        intent.putExtra("idMenu", data.get(i).get_id());
                        intent.putExtra("idRest",idRest);
                        intent.putExtra("nameRest",nameRest);
                        root.startActivity(intent);
                        Toast.makeText(getApplicationContext(),data.get(i).getNombre() , Toast.LENGTH_LONG).show();
                    }
                });

            }
            @Override
            public void onFailure(Call<ArrayList<structMenu>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(root, dataMyRest.class);
        intent.putExtra("idRest",idRest);
        root.startActivity(intent);
    }

}