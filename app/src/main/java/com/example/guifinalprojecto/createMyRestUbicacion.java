package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class createMyRestUbicacion extends AppCompatActivity {
    private createMyRestUbicacion root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_rest_ubicacion);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(root, MainActivity.class);
        root.startActivity(intent);
    }

}