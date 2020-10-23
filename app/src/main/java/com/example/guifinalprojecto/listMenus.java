package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class listMenus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menus);

        Bundle bundle = this.getIntent().getExtras();
        String idUser = bundle.getString("idUser");
        Toast.makeText(this," idUser = "+idUser.toString(), Toast.LENGTH_LONG).show();
    }
}