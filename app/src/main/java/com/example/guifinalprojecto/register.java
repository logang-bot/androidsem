package com.example.guifinalprojecto;

import android.content.Intent;
import android.os.Bundle;

import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {
    Button reg_confirm;
    private register root =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }
    private void loadComponents() {
        TextInputEditText reg_name = root.findViewById(R.id.reg_name);
        TextInputEditText reg_email = root.findViewById(R.id.reg_email);
        TextInputEditText reg_password = root.findViewById(R.id.reg_pass);
        TextInputEditText regc_password = root.findViewById(R.id.reg_cpass);

        String name= reg_name.getText().toString().trim();
        String email = reg_email.getText().toString().trim();
        String password = reg_password.getText().toString().trim();
        String c_password = regc_password.getText().toString().trim();
/*
        Call <signUp> call = RetrofitClient
                .getInstance()
                .getApi().userSignup(name,email, password,c_password);
*/

        reg_confirm = this.findViewById(R.id.reg_confirm);

        reg_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, MainDashboard.class);
                root.startActivity(intent);
            }
        });
    }


}