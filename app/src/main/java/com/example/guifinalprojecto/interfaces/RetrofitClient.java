package com.example.guifinalprojecto.interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
<<<<<<< HEAD
    public static final String BASE_URL = "http://192.168.100.186:8000/";
=======
    public static final String BASE_URL = "http://192.168.1.11:8000/";
>>>>>>> 78fb46dba7133f2714ea2165b17eb2fbd2dbb5a8
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
