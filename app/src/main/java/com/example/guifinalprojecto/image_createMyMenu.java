package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class image_createMyMenu extends AppCompatActivity {
    private String NombreRest, idRes, nameRest;
    private Button siguiente;
    private image_createMyMenu root=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_create_my_menu);

        Bundle bundle = this.getIntent().getExtras();
        NombreRest= bundle.getString("nombre");
        idRes=bundle.getString("idRes");
        nameRest=bundle.getString("nameRest");
        Toast.makeText(this," nameProduct = "+NombreRest.toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(this," idRes = "+idRes.toString(), Toast.LENGTH_LONG).show();

        TextView NombreRestaurant=findViewById(R.id.mostrarnombreRest);
        NombreRestaurant.setText(NombreRest);


        siguiente= this.findViewById(R.id.new_SigT);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, listMyMenus.class);
                intent.putExtra("nombreR", NombreRest);
                intent.putExtra("idRest",idRes);
                intent.putExtra("nameRest",nameRest);
                //intent.putExtra("logoRes",logoR);
                Toast.makeText(getApplicationContext(),"Producto creado",Toast.LENGTH_LONG).show(); // no vuelve a la actividad

                root.startActivity(intent);
            }
        });

    }
}