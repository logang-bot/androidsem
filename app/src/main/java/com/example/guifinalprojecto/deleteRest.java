package com.example.guifinalprojecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deleteRest extends AppCompatActivity {
    private String idRest, nombreR;
    private deleteRest root = this;
    private Button borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_rest);
        Bundle bundle = this.getIntent().getExtras();
        idRest = bundle.getString("idRest");
        nombreR =bundle.getString("nombreR");
        Toast.makeText(this," idRest = "+idRest.toString(), Toast.LENGTH_LONG).show();
        TextView NombreRestaurant=findViewById(R.id.nomRest);
        NombreRestaurant.setText(nombreR +"?");
        borrar = findViewById(R.id.delR);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(root);
                builder1.setMessage("¿ESTAS SEGURO/A QUE DESEAS ELIMINAR ESTE RESTAURANT?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Call<structRests> call = RetrofitClient
                                        .getInstance()
                                        .getApi().delRest(idRest);
                                call.enqueue(new Callback<structRests>() {
                                    @Override
                                    public void onResponse(Call<structRests> call, Response<structRests> response) {
                                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(root, listMyRests.class);
                                        root.startActivity(intent);
                                    }
                                    @Override
                                    public void onFailure(Call<structRests> call, Throwable t) {
                                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
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
            }
        });
    }
}