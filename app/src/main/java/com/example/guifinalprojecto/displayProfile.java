package com.example.guifinalprojecto;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.user;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class displayProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        Call<user> call = RetrofitClient
                .getInstance()
                .getApi().getMydata(UserDataServer.TOKEN);

        call.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                user thedata = response.body();
                TextView username = findViewById(R.id.myname);
                TextView myemail = findViewById(R.id.myemail);
                CircleImageView prof = findViewById(R.id.myAvatar);
                username.setText(thedata.getName());
                myemail.setText(thedata.getEmail());
                Glide.with(getApplicationContext())
                        .load(RetrofitClient.BASE_URL + thedata.getAvatar())
                        .centerCrop()
                        .into(prof);
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}