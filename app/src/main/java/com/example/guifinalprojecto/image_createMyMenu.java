package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class image_createMyMenu extends AppCompatActivity {
    private String NombreRest;
    private Button siguiente;
    private image_createMyMenu root=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_create_my_menu);

        Bundle bundle = this.getIntent().getExtras();
        NombreRest= bundle.getString("nombreR");
        Toast.makeText(this," idRest = "+NombreRest.toString(), Toast.LENGTH_LONG).show();

        TextView NombreRestaurant=findViewById(R.id.mostrarnombreRest);
        NombreRestaurant.setText(NombreRest);


        siguiente= this.findViewById(R.id.new_SigT);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, listMyMenus.class);
                intent.putExtra("nombreR", NombreRest);
                //intent.putExtra("logoRes",logoR);
                Toast.makeText(getApplicationContext(),"Producto creado",Toast.LENGTH_LONG).show();
                root.startActivity(intent);
            }
        });

    }
}