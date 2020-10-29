package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.HomeAdapter;
import com.example.guifinalprojecto.adapters.ResAdapter;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.utils.UserDataServer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listMyRests extends AppCompatActivity {
    private listMyRests root=this;
    private Button create_Rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_rests);
       ListView listR = findViewById(R.id.myrest);
  /*     ArrayList<String> datos= new ArrayList<>();
       datos.add("1");
        datos.add("2");
        datos.add("3");
        datos.add("4");
        datos.add("5");
       ArrayAdapter<String> adapter= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, datos);
       listR.setAdapter(adapter); */
       Call<ArrayList<structRests>> call = RetrofitClient
                .getInstance()
                .getApi().getMyRests(UserDataServer.TOKEN);

        call.enqueue(new Callback<ArrayList<structRests>>() {
            @Override
            public void onResponse(Call<ArrayList<structRests>> call, Response<ArrayList<structRests>> response) {

           /*    if(response.isSuccessful()){
                    if(response.body().get(0).getMessage()!=null){
                        TextView textRest=findViewById(R.id.callRest);
                        textRest.setText("No tiene Restaurants");
                    }
                    else{ */
                        ArrayList<structRests> data = response.body();
                        ResAdapter adapter = new ResAdapter(data, getApplicationContext());
                        listR.setAdapter(adapter);
             //      }}

                listR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //animation
                        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                        animation1.setDuration(4000);
                        view.startAnimation(animation1);
                        //animation
                        Intent intent = new Intent(getApplicationContext(), dataMyRest.class); //cambiar vista
                        intent.putExtra("idRest", data.get(i).get_id());
                        root.startActivity(intent);
                        Toast.makeText(getApplicationContext(),data.get(i).getNit() , Toast.LENGTH_LONG).show();
                    }
                });

            }
            @Override
            public void onFailure(Call<ArrayList<structRests>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        loadComponents();
    }
    private void loadComponents(){
        create_Rest = this.findViewById(R.id.newR);
        create_Rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, createMyRest.class);
                root.startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(root, MainDashboard.class);
        root.startActivity(intent);
    }

}

