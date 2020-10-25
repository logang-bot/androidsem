package com.example.guifinalprojecto.interfaces;

import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.models.user;
import com.example.guifinalprojecto.utils.UserDataServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @FormUrlEncoded
    @POST("user/login")
    Call<logInResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("res")
    Call<ArrayList<structRests>> getRests();

    @GET("user/mydata")
    Call<user> getMydata(@Header("x-access-token") String auth);

    @GET("res/list")
    Call<ArrayList<structRests>> getMyRests(@Header("x-access-token") String auth);

    @GET("res/mydata")
    Call<structRests> getMydataRes(@Header("x-access-token") String auth,@Query("id") String idRes);

}