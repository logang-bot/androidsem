package com.example.guifinalprojecto;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.adapters.structMenu;
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

public class editMyMenu extends AppCompatActivity {

    private int PICK_IMAGE = 100;
    private Uri imagenUri;
    private String mediaPath, postPath;

    private EditText edname, edprecio, eddes, edcant;
    private Button btnEdit;
    private FloatingActionButton btnEditFotoM;
    private ImageView imagefotoM;
    private String idM;

    private editMyMenu root = this;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_menu);

        edname = findViewById(R.id.ed_name);
        edprecio = findViewById(R.id.ed_precio);
        eddes = findViewById(R.id.ed_desc);
        edcant = findViewById(R.id.edit_cant);
        imagefotoM = findViewById(R.id.image_editFoodM);

        Bundle bundle = this.getIntent().getExtras();
        idM = bundle.getString("idM");
        String nombre = bundle.getString("nombre");
        String precio = bundle.getString("precio");
        String descripcion = bundle.getString("descripcion");
        String cantidad_dia = bundle.getString("cantidad");
        String fotoM = bundle.getString("fotoM");

        Glide.with(getApplicationContext())
                .load(RetrofitClient.BASE_URL + fotoM)
                .centerCrop()
                .into(imagefotoM);

        String nameRest=bundle.getString("nameRest");
        String idRest=bundle.getString("idRest");

        edname.setText(nombre);
        edprecio.setText(precio);
        eddes.setText(descripcion);
        edcant.setText(cantidad_dia);

        btnEdit = findViewById(R.id.buttonedit);
        btnEditFotoM = findViewById(R.id.buttonEdit_fotoM);

        btnEditFotoM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalery();
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(root);

                String name= edname.getText().toString().trim();
                String eprecio = edprecio.getText().toString().trim();
                String edesc = eddes.getText().toString().trim();
                String ecant = edcant.getText().toString().trim();

                Call<structMenu> call = RetrofitClient
                        .getInstance()
                        .getApi().edMenu(idM, name, eprecio, edesc, ecant);

                call.enqueue(new Callback<structMenu>() {
                    @Override
                    public void onResponse(Call<structMenu> call, Response<structMenu> response) {
                        String msg = response.body().getMessage();
                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if(msg.equals("Datos actualizados")){

                            progressDialog.show();
                            progressDialog.setContentView(R.layout.progress_dialog);
                            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                            Intent intent = new Intent(root, dataMyMenu.class);
                            intent.putExtra("idMenu",idM);
                            intent.putExtra("idRest",idRest);
                            intent.putExtra("nameRest",nameRest);
                            root.startActivity(intent);
                        }
                        else{
                            Toast.makeText(root,"no se pudo actualizar",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<structMenu> call, Throwable t) {
                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
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
                imagefotoM.setImageURI(imagenUri);

                MultipartBody.Part body = null;
                if(postPath != null){
                    File file = new File(postPath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
                }
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "no permission", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
                } else{
                    Call<logInResponse> call = RetrofitClient
                            .getInstance()
                            .getApi().setfotoMenu(UserDataServer.TOKEN,idM, body);

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