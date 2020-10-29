package com.example.guifinalprojecto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editMyRestLocation extends AppCompatActivity {

    private String latitude="", longitude="";
    int PLACE_PICKER_REQUEST = 1;

    private String idR;
    private String NombreRest;
    private Uri logoUri;

    private TextView coord;
    private Button btnPicker, btnView;
    private Button finalizar, cancelar;

    private editMyRestLocation root = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_rest_location);

        Bundle bundle = this.getIntent().getExtras();
        NombreRest= bundle.getString("nombreR");
        //logoUri = Uri.parse(bundle.getString("logoUri"));
        idR = bundle.getString("idR");

        cancelar = this.findViewById(R.id.upLocationCancelar);
        finalizar= this.findViewById(R.id.upLocation_finalizar);
        btnPicker = this.findViewById(R.id.upLocationagregar_ubicacionR);
        btnView = this.findViewById(R.id.upLocationGooMaps);
        coord = this.findViewById(R.id.ubicacioninfo);

        finalizar.setEnabled(false);

        latitude = bundle.getString("lat");
        longitude = bundle.getString("log");

        if(latitude == null || longitude == null){
            btnView.setEnabled(false);
        }

        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try{
                    startActivityForResult(builder.build(editMyRestLocation.this),
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

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, dataMyRest.class);
                intent.putExtra("nombreR", NombreRest);
                intent.putExtra("idRest", idR);
                //intent.putExtra("logoUri", logoUri.toString());
                //intent.putExtra("logoRes",logoR);
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
                        Intent intent = new Intent(root, dataMyRest.class);
                        intent.putExtra("nombreR", NombreRest);
                        intent.putExtra("idRest", idR);
                        //intent.putExtra("logoUri", logoUri.toString());
                        //intent.putExtra("logoRes",logoR);
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