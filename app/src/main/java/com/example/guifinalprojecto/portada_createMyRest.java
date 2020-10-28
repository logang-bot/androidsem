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
import com.example.guifinalprojecto.interfaces.RetrofitClient;

public class portada_createMyRest extends AppCompatActivity {
    private String NombreRest;
    private Button siguiente;
    private portada_createMyRest root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada_create_my_rest);
        Bundle bundle = this.getIntent().getExtras();
        NombreRest= bundle.getString("nombreR");
    //    String logo =bundle.getString("logoRes");
        Toast.makeText(this," NombreRest = "+NombreRest.toString(), Toast.LENGTH_LONG).show();
        TextView NombreRestaurant=findViewById(R.id.new_mostrarnombreRespo);
    //    ImageView logoRest= findViewById(R.id.mostrar_logoR);
        NombreRestaurant.setText(NombreRest);
     /*   Glide.with(getApplicationContext())
                .load(RetrofitClient.BASE_URL + logo)
                .centerCrop()
                .into(logoRest);*/

        siguiente= this.findViewById(R.id.new_SigPort);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, createMyRestUbicacion.class);
                root.startActivity(intent);
            }
        });

    }
}