package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class editUser extends AppCompatActivity {

    private EditText edname, edemail, edpass, edcpass;
    private editUser root = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        edname = findViewById(R.id.ed_name);
        edemail = findViewById(R.id.ed_email);
        edpass = findViewById(R.id.ed_pass);
        edcpass = findViewById(R.id.edcpass);


    }
}