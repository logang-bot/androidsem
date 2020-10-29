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
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class portada_createMyRest extends AppCompatActivity {

    private int PICK_IMAGE = 100;
    private Uri imagenUri;
    private String mediaPath, postPath;

    private String NombreRest, idR;
    private Uri logoUri;
    private Button siguiente, agregarPortR, atras, omitir;
    private CircleImageView logoR;
    private ImageView newPortR;

    private portada_createMyRest root=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada_create_my_rest);
        Bundle bundle = this.getIntent().getExtras();
        NombreRest= bundle.getString("nombreR");
        idR = bundle.getString("idR");
        logoUri = Uri.parse(bundle.getString("logoUri"));

        Toast.makeText(this," NombreRest = "+NombreRest.toString(), Toast.LENGTH_LONG).show();
        TextView NombreRestaurant=findViewById(R.id.new_mostrarnombreRespo);
        logoR = findViewById(R.id.mostrar_logoR);
        newPortR = findViewById(R.id.new_portadaR);

        NombreRestaurant.setText(NombreRest);
        logoR.setImageURI(logoUri);

        siguiente= this.findViewById(R.id.new_SigPort);
        agregarPortR = this.findViewById(R.id.newagregar_portadaR);
        atras = this.findViewById(R.id.new_BackfromPortada);
        omitir = this.findViewById(R.id.new_OmitPortada);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, logo_createMyRest.class);
                intent.putExtra("nombreR", NombreRest);
                intent.putExtra("idR", idR);
                root.startActivity(intent);
            }
        });

        omitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, createMyRestUbicacion.class);
                intent.putExtra("nombreR", NombreRest);
                intent.putExtra("idR", idR);
                intent.putExtra("logoUri", logoUri.toString());
                root.startActivity(intent);
            }
        });

        agregarPortR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalery();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
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
                            .getApi().setcover(UserDataServer.TOKEN, idR, body);
                    call.enqueue(new Callback<logInResponse>() {
                        @Override
                        public void onResponse(Call<logInResponse> call, Response<logInResponse> response) {
                            Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(root, createMyRestUbicacion.class);
                            intent.putExtra("nombreR", NombreRest);
                            intent.putExtra("idR", idR);
                            intent.putExtra("logoUri", logoUri.toString());
                            root.startActivity(intent);
                        }
                        @Override
                        public void onFailure(Call<logInResponse> call, Throwable t) {
                            Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    Log.i("Mensaje", "Se tiene permiso para leer!");
                }
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
                newPortR.setImageURI(imagenUri);
            }
            else
                Toast.makeText(getApplicationContext(), "cancelado", Toast.LENGTH_LONG).show();
        }
    }
}