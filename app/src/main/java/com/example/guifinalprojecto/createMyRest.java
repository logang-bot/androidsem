package com.example.guifinalprojecto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createMyRest extends AppCompatActivity {
    private Button siguiente ;
    private createMyRest root=this;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_rest);
        loadComponents();
    }
    private void loadComponents(){
        siguiente = this.findViewById(R.id.new_Sig);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(root);

                TextInputEditText newNombreR = root.findViewById(R.id.new_nombreR);
                TextInputEditText newNitR = root.findViewById(R.id.new_nitR);
                TextInputEditText newDirR = root.findViewById(R.id.new_dirR);
                TextInputEditText newTelR = root.findViewById(R.id.new_teR);
                String nombre = newNombreR.getText().toString().trim();
                String nit = newNitR.getText().toString().trim();
                String calle = newDirR.getText().toString().trim();
                String tel = newTelR.getText().toString().trim();
                Call<logInResponse> call = RetrofitClient
                        .getInstance()
                        .getApi().createRest(UserDataServer.TOKEN, nombre, nit, calle, tel);
                call.enqueue(new Callback<logInResponse>() {
                    @Override
                    public void onResponse(Call<logInResponse> call, Response<logInResponse> response) {
                      //  Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if(response.body().getMessage().equals("El restaurant fue creado correctamente")){

                            progressDialog.show();
                            progressDialog.setContentView(R.layout.progress_dialog);
                            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                            Intent intent = new Intent(root, logo_createMyRest.class);
                            intent.putExtra("nombreR", nombre);
                            intent.putExtra("idR", response.body().get_id());
                            root.startActivity(intent);
                        }
                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<logInResponse> call, Throwable t) {
                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
        });
    }
});
    }
}