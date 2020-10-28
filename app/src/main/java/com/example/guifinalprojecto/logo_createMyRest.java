package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class logo_createMyRest extends AppCompatActivity {
    private String NombreRest;
    private Button siguiente;
    private logo_createMyRest root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_create_my_rest);
        Bundle bundle = this.getIntent().getExtras();
        NombreRest= bundle.getString("nombreR");
        Toast.makeText(this," idRest = "+NombreRest.toString(), Toast.LENGTH_LONG).show();

        TextView NombreRestaurant=findViewById(R.id.mostrarnombreRest);
        NombreRestaurant.setText(NombreRest);


        siguiente= this.findViewById(R.id.new_Siglogo);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, portada_createMyRest.class);
                intent.putExtra("nombreR", NombreRest);
                //intent.putExtra("logoRes",logoR);
                root.startActivity(intent);
            }
        });

    }
}