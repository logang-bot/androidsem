package com.example.guifinalprojecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class dataRest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_rest);
    }
}

/*    Intent intent = new Intent(getContext(), listMenus.class);
                        intent.putExtra("idRest", data.get(i).get_id());
                                getContext().startActivity(intent); */