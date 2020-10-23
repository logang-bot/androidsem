package com.example.guifinalprojecto;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.adapters.HomeAdapter;
import com.example.guifinalprojecto.adapters.profileAdapter;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.user;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDashboard extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private MainDashboard root = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //bottomnav
        bottomNavigationView=findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomnavmethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        //bottomnav

        //drawer
        mDrawerLayout = (DrawerLayout)findViewById(R.id.dashboard_main);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.profile_settings);
        navigationView.setNavigationItemSelectedListener(drawermethod);
            //profileload

        Call<user> call = RetrofitClient
                .getInstance()
                .getApi().getMydata(UserDataServer.TOKEN);

        call.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                user thedata = response.body();
                TextView username = findViewById(R.id.username);
                ImageView prof = findViewById(R.id.profile);
                username.setText(thedata.getName());
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

            //profileload*/
        //drawer

        /*Bundle bundle = this.getIntent().getExtras();
        String backupAgentName = bundle.getString("backupAgentName");
        String data = bundle.getString("data");
        Integer number = bundle.getInt("number");
        Toast.makeText(this,
                "backupAgentName = " + backupAgentName +
                        " data = "+ data+
                        " number = "+number.toString(), Toast.LENGTH_LONG).show();*/
    }

    //bottomnav
    private BottomNavigationView.OnNavigationItemSelectedListener bottomnavmethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment = null;

                    switch (item.getItemId()){
                        case R.id.home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.notifications:
                            fragment = new NotificationFragment();
                            break;
                        case R.id.more:
                            fragment = new MoreFragment();
                            break;
                    }
                    //fragment.setRetainInstance(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    return true;
                }
            };
    //bottomnav
    //drawer
    private  NavigationView.OnNavigationItemSelectedListener drawermethod = new
            NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.settings:
                            Intent intent = new Intent(root, displayProfile.class);
                            root.startActivity(intent);
                            break;
                        case R.id.myrests:
                            Intent intent2= new Intent(root, listMyRests.class);
                            root.startActivity(intent2);
                            break;
                    }
                    return true;
                }
            };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    //drawer
}