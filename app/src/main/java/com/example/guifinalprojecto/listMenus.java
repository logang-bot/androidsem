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

import com.example.guifinalprojecto.adapters.ResAdapter;
import com.example.guifinalprojecto.adapters.menusAdapter;
import com.example.guifinalprojecto.adapters.structMenu;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.utils.UserDataServer;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listMenus extends AppCompatActivity {
    private listMenus root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menus);



        GridView GridM = findViewById(R.id.g_menu);
        Bundle bundle = this.getIntent().getExtras();
        String idRest = bundle.getString("idRest");
        Toast.makeText(this," idRest = "+idRest.toString(), Toast.LENGTH_LONG).show();

        String nameRest = bundle.getString("nameRest");


        Call<ArrayList<structMenu>> call = RetrofitClient
                .getInstance()
                .getApi().getMenu(idRest);

        TextView titleREst = findViewById(R.id.title_res_onmenu);
        titleREst.setText(nameRest);

        Button verinfo = findViewById(R.id.verInfoRest);
        verinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),dataRest.class);
                intent.putExtra("idRest",idRest);
                root.startActivity(intent);
            }
        });
        call.enqueue(new Callback<ArrayList<structMenu>>() {
            @Override
            public void onResponse(Call<ArrayList<structMenu>> call, Response<ArrayList<structMenu>> response) {

                ArrayList<structMenu> data = response.body();
                menusAdapter adapter = new menusAdapter(data, getApplicationContext());
                GridM.setAdapter(adapter);

                GridM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //animation
                        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                        animation1.setDuration(4000);
                        view.startAnimation(animation1);
                        //animation
                        Intent intent = new Intent(getApplicationContext(), dataMenu.class); //cambiar vista
                        intent.putExtra("idMenu", data.get(i).get_id());
                        intent.putExtra("nameRest",nameRest);
                        //intent.putExtra("name_Rest",
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
        Intent intent = new Intent(root, MainDashboard.class);
        root.startActivity(intent);
    }
}