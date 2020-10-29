package com.example.guifinalprojecto;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editMyRest extends AppCompatActivity {

    private int PICK_IMAGE = 100;
    private Uri imagenUri;
    private String mediaPath, postPath;

    private EditText ed_nombreR, ed_nitR, ed_dirR,ed_telR,ed_latR, ed_logR;
    private Button confirmEdit, btnEdit_FotoR, btnEditRLocation;
    private FloatingActionButton btnEdit_LogoR;
    private CircleImageView logoRest;
    private ImageView fotoRest;
    private String idR;

    private editMyRest root= this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_rest);

        ed_nombreR = findViewById(R.id.ed_nombreR);
        ed_nitR = findViewById(R.id.ed_nitR);
        ed_dirR = findViewById(R.id.ed_dirR);
        //ed_latR = findViewById(R.id.ed_latR);
        //ed_logR = findViewById(R.id.ed_logR);
        ed_telR = findViewById(R.id.ed_telR);
        confirmEdit= findViewById(R.id.editRest);

        btnEdit_FotoR = findViewById(R.id.editRestCover);
        btnEdit_LogoR = findViewById(R.id.buttonEdit_logoR);
        btnEditRLocation = findViewById(R.id.editRestLocation);

        //fotoRest= findViewById(R.id.ed_fotoR);
        logoRest= findViewById(R.id.ed_logoR);
        Bundle bundle = this.getIntent().getExtras();

        idR = bundle.getString("idR");

        String nombre = bundle.getString("nombreR");
        String nit = bundle.getString("nitR");
        String dir = bundle.getString("dirR");
        String tel = bundle.getString("telR");
        String log = bundle.getString("logR");
        String lat = bundle.getString("latR");
        String idRest=bundle.getString("idRes");
        String foto =bundle.getString("fotoRes");
        String logo =bundle.getString("logoRes");
        ed_nombreR.setText(nombre);
        ed_nitR.setText(nit);
        ed_dirR.setText(dir);
        ed_telR.setText(tel);
        //ed_logR.setText(log);
        //ed_latR.setText(lat);
        /*Glide.with(getApplicationContext())
                .load(RetrofitClient.BASE_URL + foto)
                .centerCrop()
                .into(fotoRest);*/
        Glide.with(getApplicationContext())
                .load(RetrofitClient.BASE_URL + logo)
                .centerCrop()
                .into(logoRest);

        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre= ed_nombreR.getText().toString().trim();
                String nit = ed_nitR.getText().toString().trim();
                String dir = ed_dirR.getText().toString().trim();
                String tel =ed_telR.getText().toString().trim();
                //String log = ed_logR.getText().toString().trim();
                //String lat = ed_latR.getText().toString().trim();

                Call<structRests> call = RetrofitClient
                        .getInstance()
                        .getApi().edRest(UserDataServer.TOKEN,nombre, nit, dir, tel, log, lat, idRest);

                call.enqueue(new Callback<structRests>() {
                    @Override
                    public void onResponse(Call<structRests> call, Response<structRests> response) {
                        String msg = response.body().getMessage();
                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if(msg.equals("Fue actualizado correctamente")){
                            Intent intent = new Intent(root, dataMyRest.class);
                            /*intent.putExtra("idRest", idRest);
                            intent.putExtra("fotoRes",foto);
                            intent.putExtra("logoRes",logo);*/
                            root.startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<structRests> call, Throwable t) {
                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnEditRLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, editMyRestLocation.class);
                intent.putExtra("idR", idR);
                intent.putExtra("fotoRes", foto);
                intent.putExtra("logoRes",logo);
                intent.putExtra("log", log);
                intent.putExtra("lat",lat);
                root.startActivity(intent);
            }
        });

        btnEdit_FotoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, editMyRestCover.class);
                intent.putExtra("idR", idR);
                intent.putExtra("fotoRes", foto);
                intent.putExtra("logoRes",logo);
                root.startActivity(intent);
            }
        });

        btnEdit_LogoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalery();
            }
        });
    }
    public void openGalery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE){
            if(resultCode == RESULT_OK){
                ClipData clipdata = data.getClipData();
                if(clipdata == null){
                    imagenUri = data.getData();
                    Uri uri = data.getData();
                    File file = new File(uri.getPath());//create path from uri
                    final String[] split = file.getPath().split(":");//split the path.
                    postPath = file.getPath().substring(4);
                    Toast.makeText(getApplicationContext(), postPath, Toast.LENGTH_LONG).show();
                }
                logoRest.setImageURI(imagenUri);

                MultipartBody.Part body = null;
                if(postPath != null){
                    File file = new File(postPath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
                }
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "no permission", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
                } else{
                    Call<logInResponse> call = RetrofitClient
                            .getInstance()
                            .getApi().setlogo(UserDataServer.TOKEN, idR, body);
                    call.enqueue(new Callback<logInResponse>() {
                        @Override
                        public void onResponse(Call<logInResponse> call, Response<logInResponse> response) {
                            Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onFailure(Call<logInResponse> call, Throwable t) {
                            Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    Log.i("Mensaje", "Se tiene permiso para leer!");
                }
            }
            else
                Toast.makeText(getApplicationContext(), "cancelado", Toast.LENGTH_LONG).show();
        }
    }
}
