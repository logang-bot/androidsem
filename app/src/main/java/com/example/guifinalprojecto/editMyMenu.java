package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.structMenu;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editMyMenu extends AppCompatActivity {

    private EditText edname, edprecio, eddes, edcant;
    private Button btnEdit;

    private editMyMenu root = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_menu);

        edname = findViewById(R.id.ed_name);
        edprecio = findViewById(R.id.ed_precio);
        eddes = findViewById(R.id.ed_desc);
        edcant = findViewById(R.id.edit_cant);

        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("idM");
        String nombre = bundle.getString("nombre");
        String precio = bundle.getString("precio");
        String descripcion = bundle.getString("descripcion");
        String cantidad_dia = bundle.getString("cantidad");

        String nameRest=bundle.getString("nameRest");

        edname.setText(nombre);
        edprecio.setText(precio);
        eddes.setText(descripcion);
        edcant.setText(cantidad_dia);

        btnEdit = findViewById(R.id.buttonedit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= edname.getText().toString().trim();
                String eprecio = edprecio.getText().toString().trim();
                String edesc = eddes.getText().toString().trim();
                String ecant = edcant.getText().toString().trim();

                Call<structMenu> call = RetrofitClient
                        .getInstance()
                        .getApi().edMenu(id, name, eprecio, edesc, ecant);

                call.enqueue(new Callback<structMenu>() {
                    @Override
                    public void onResponse(Call<structMenu> call, Response<structMenu> response) {
                        String msg = response.body().getMessage();
                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if(msg.equals("Datos actualizados")){
                            Intent intent = new Intent(root, dataMyMenu.class);
                            intent.putExtra("idMenu",id);
                            intent.putExtra("nameRest",nameRest);
                            root.startActivity(intent);
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
}