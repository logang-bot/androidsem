package com.example.guifinalprojecto;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    private int PICK_IMAGE = 100;
    private Uri imagenUri;
    private String mediaPath, postPath;

    private FloatingActionButton reg_confirm;
    private Button addImage ;
    private CircleImageView img;

    ProgressDialog progressDialog;

    private register root =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    loadComponents();
    }
    private void loadComponents() {
        reg_confirm = this.findViewById(R.id.reg_confirm);
        addImage = this.findViewById(R.id.addImage);
        img = this.findViewById(R.id.menu_image);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalery();
            }
        });

        reg_confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                TextInputEditText regname = root.findViewById(R.id.reg_name);
                TextInputEditText regemail = root.findViewById(R.id.reg_email);
                TextInputEditText regpass = root.findViewById(R.id.reg_pass);
                TextInputEditText regcpass = root.findViewById(R.id.reg_cpass);

                progressDialog = new ProgressDialog(root);

                String name= regname.getText().toString().trim();
                String email = regemail.getText().toString().trim();
                String password = regpass.getText().toString().trim();
                String c_password = regcpass.getText().toString().trim();
                //CONVERT
                //name
                RequestBody bname = RequestBody.create(MediaType.parse("multipart/form-data"), name);
                //email
                RequestBody bemail = RequestBody.create(MediaType.parse("multipart/form-data"), email);
                //password
                RequestBody bpassword = RequestBody.create(MediaType.parse("multipart/form-data"), password);
                //cpassword
                RequestBody bcpassword = RequestBody.create(MediaType.parse("multipart/form-data"), c_password);

                //img
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
                            .getApi().signUp(bname,bemail,bpassword,bcpassword, body);
                    call.enqueue(new Callback<logInResponse>() {
                        @Override
                        public void onResponse(Call<logInResponse> call, Response<logInResponse> response) {
                            if(response.body().getToken()!=null){
                                UserDataServer.TOKEN = response.body().getToken();
                            }
                            Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            if(response.body().getMessage().equals("sugoi")){
                                progressDialog.show();
                                progressDialog.setContentView(R.layout.progress_dialog);
                                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                Intent intent = new Intent(root, MainDashboard.class);
                                root.startActivity(intent);
                            }
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
                img.setImageURI(imagenUri);
            }
            else
                Toast.makeText(getApplicationContext(), "cancelado", Toast.LENGTH_LONG).show();
        }
    }
}