package com.example.guifinalprojecto;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.utils.UserDataServer;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createMyRestUbicacion extends AppCompatActivity {

    private String latitude="", longitude="";
    int PLACE_PICKER_REQUEST = 1;

    private String idR;
    private String NombreRest;
    private Uri logoUri;

    private TextView coord;
    private Button btnPicker, btnView;
    private Button finalizar, atras, omitir;

    private createMyRestUbicacion root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_rest_ubicacion);

        Bundle bundle = this.getIntent().getExtras();
        NombreRest= bundle.getString("nombreR");
        logoUri = Uri.parse(bundle.getString("logoUri"));
        idR = bundle.getString("idR");

        atras = this.findViewById(R.id.new_BackfromUbicacion);
        omitir = this.findViewById(R.id.new_OmitUbicacion);
        finalizar= this.findViewById(R.id.new_finalizar);
        btnPicker = this.findViewById(R.id.agregar_ubicacionR);
        btnView = this.findViewById(R.id.createR_mapView);
        finalizar.setEnabled(false);
        btnView.setEnabled(false);
        coord = this.findViewById(R.id.ubicacioninfo);

        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try{
                    startActivityForResult(builder.build(createMyRestUbicacion.this),
                            PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //"http://maps.google.com/maps?q=loc:37.7749,-122.4194" + destinationLatitude + "," + destinationLongitude;
                String uri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude;
                Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?q=loc:37.7749,-122.4194");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, portada_createMyRest.class);
                intent.putExtra("nombreR", NombreRest);
                intent.putExtra("idR", idR);
                intent.putExtra("logoUri", logoUri.toString());
                //intent.putExtra("logoRes",logoR);
                root.startActivity(intent);
            }
        });

        omitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, listMyRests.class);
                root.startActivity(intent);
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<logInResponse> call = RetrofitClient
                        .getInstance()
                        .getApi().setlocation(UserDataServer.TOKEN, idR, longitude, latitude);
                call.enqueue(new Callback<logInResponse>() {
                    @Override
                    public void onResponse(Call<logInResponse> call, Response<logInResponse> response) {
                        Toast.makeText(root, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(root, listMyRests.class);
                        root.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<logInResponse> call, Throwable t) {
                        Toast.makeText(root, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stringBuilder = new StringBuilder();
                latitude = String.valueOf(place.getLatLng().latitude);
                longitude = String.valueOf(place.getLatLng().longitude);
                stringBuilder.append("LATITUDE: ");
                stringBuilder.append(latitude);
                stringBuilder.append("\n");
                stringBuilder.append("LONGITUDE: ");
                stringBuilder.append(longitude);
                coord.setText(stringBuilder.toString());
                btnView.setEnabled(true);
                finalizar.setEnabled(true);
            }
        }
    }
}