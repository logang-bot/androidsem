package com.example.guifinalprojecto;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class MainDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*Bundle bundle = this.getIntent().getExtras();
        String backupAgentName = bundle.getString("backupAgentName");
        String data = bundle.getString("data");
        Integer number = bundle.getInt("number");
        Toast.makeText(this,
                "backupAgentName = " + backupAgentName +
                        " data = "+ data+
                        " number = "+number.toString(), Toast.LENGTH_LONG).show();*/
    }
}