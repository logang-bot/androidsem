package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class createMyRestUbicacion extends AppCompatActivity {
    private Button finalizar;
    private createMyRestUbicacion root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_rest_ubicacion);


        finalizar= this.findViewById(R.id.new_finalizar);
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, listMyRests.class);
                root.startActivity(intent);
            }
        });

    }



}