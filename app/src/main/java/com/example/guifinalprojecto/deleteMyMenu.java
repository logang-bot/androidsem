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
    private Bundle bundle;
    private String idM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_my_menu);

        bundle = this.getIntent().getExtras();
        String idMenu = bundle.getString("idM");
        String nomMenuu = bundle.getString("nomMenu");
        String idRest=bundle.getString("idRest");
        String nameRest=bundle.getString("nameRest");
        TextView nomMenu =findViewById(R.id.nomMenu);
        nomMenu.setText(nomMenuu);

        confirmButton = findViewById(R.id.delM);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(root);
                builder1.setMessage("¿ESTAS SEGURO/A QUE DESEAS ELIMINAR ESTE PRODUCTO?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Call<structMenu> call = RetrofitClient
                                        .getInstance()
                                        .getApi().delMenu(idMenu);
                                call.enqueue(new Callback<structMenu>() {
                                    @Override
                                    public void onResponse(Call<structMenu> call, Response<structMenu> response) {
                                        //Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                        Toast.makeText(root, idMenu, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(root, listMyMenus.class);
                                        intent.putExtra("idRest",idRest);
                                        intent.putExtra("nameRest",nameRest);
                                        root.startActivity(intent);
                                    }
                                    @Override
                                    public void onFailure(Call<structMenu> call, Throwable t) {
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

            }
        });
    }
}