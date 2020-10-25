package com.example.guifinalprojecto.interfaces;

import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.models.user;
import com.example.guifinalprojecto.utils.UserDataServer;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {
    @FormUrlEncoded
    @POST("user/login")
    Call<logInResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @Multipart
    @POST("user/signUp")
    Call<logInResponse> signUp(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("confirm_password") RequestBody cpassword,
            @Part MultipartBody.Part part
    );


    @GET("res")
    Call<ArrayList<structRests>> getRests();

    @GET("user/mydata")
    Call<user> getMydata(@Header("x-access-token") String auth);

    @GET("res/list")
    Call<ArrayList<structRests>> getMyRests(@Header("x-access-token") String auth);

}