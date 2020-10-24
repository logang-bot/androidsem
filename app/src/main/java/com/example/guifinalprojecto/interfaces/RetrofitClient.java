package com.example.guifinalprojecto.interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
<<<<<<< HEAD
    public static final String BASE_URL = "http://192.168.1.11:8000/";
=======
    public static final String BASE_URL = "http://192.168.1.3:8000/";
>>>>>>> 70240d232fbd6720704839533067fcfaa056b43e
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
