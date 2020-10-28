package com.example.guifinalprojecto.interfaces;

import com.example.guifinalprojecto.adapters.structMenu;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.models.logInResponse;
import com.example.guifinalprojecto.models.user;
import com.example.guifinalprojecto.utils.UserDataServer;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("user/mydata")
    Call<user> getMydata(@Header("x-access-token") String auth);

    @Multipart
    @PUT("user/edAvatar")
    Call<logInResponse> edAvatar(@Header("x-access-token") String auth, @Part MultipartBody.Part part);

    @FormUrlEncoded
    @PUT("user/edit")
    Call<logInResponse> edUser(@Header("x-access-token") String auth,
                               @Field("name") String name,
                               @Field("email") String email,
                               @Field("password") String password,
                               @Field("confirm_password") String confirm_password);
    @DELETE("user/delete")
    Call<logInResponse> delUser(@Header("x-access-token") String auth);

    @GET("res/search")
    Call<ArrayList<structRests>> getSearchRest(@Query("word") String word);

    @GET("res")
    Call<ArrayList<structRests>> getRests();

    @GET("res/list")
    Call<ArrayList<structRests>> getMyRests(@Header("x-access-token") String auth);

    @GET("res/mydata")
    Call<structRests> getMydataRes(@Header("x-access-token") String auth,@Query("id") String idRes);

    @FormUrlEncoded
    @PUT("res/edit")
    Call<structRests> edRest(@Header("x-access-token") String auth,
                             @Field("nombre") String nombreR,
                               @Field("nit") String nitR,
                               @Field("calle") String dirR,
                               @Field("telefono") String telR,
                               @Field("log") String logR,
                                @Field("lat") String latR,
                                @Query("id") String idRest);
    @FormUrlEncoded
    @POST("res/create")
    Call<structRests> createRest(
            @Header("x-access-token") String auth,
            @Field("nombre") String nombreR,
            @Field("nit") String nitR,
            @Field("calle") String dirR,
            @Field("telefono") String telR
    );
    @DELETE("res/delete")
    Call<structRests> delRest(@Query("id") String idRes);

    @GET("menu/search")
    Call<ArrayList<structMenu>> getSearchMenu(@Query("word") String word);

    @GET("menu")
    Call<ArrayList<structMenu>> getMenu(@Query("idRes") String idRes);

    @GET("menu/data")
    Call<structMenu> getDataMenu(@Query("idMenu") String idMenu);


}