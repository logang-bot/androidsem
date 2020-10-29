package com.example.guifinalprojecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.structMenu;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deleteMyMenu extends AppCompatActivity {
    private deleteMyMenu root = this;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_my_menu);

        confirmButton = findViewById(R.id.confirmDelete);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(root);
                builder1.setMessage("¿ESTAS SEGURO/A QUE DESEAS ELIMINAR ESTE PRODUCTO?");
                builder1.setCancelable(true);
/*
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Call<structMenu> call = RetrofitClient
                                        .getInstance()
                                        .getApi().delMenu(UserDataServer.TOKEN);
                                call.enqueue(new Callback<logInResponse>() {
                                    @Override
                                    public void onResponse(Call<logInResponse> call, Response<logInResponse> response) {
                                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(root, MainActivity.class);
                                        root.startActivity(intent);
                                    }
                                    @Override
                                    public void onFailure(Call<logInResponse> call, Throwable t) {
                                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                                //dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                /*Call<logInResponse> call = RetrofitClient
                        .getInstance()
                        .getApi().delUser(UserDataServer.TOKEN);
                call.enqueue(new Callback<logInResponse>() {
                    @Override
                    public void onResponse(Call<logInResponse> call, Response<logInResponse> response) {
                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(root, MainActivity.class);
                        root.startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<logInResponse> call, Throwable t) {
                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });*/
            }
        });
    }
}