package com.example.retrofitlibrary.Interface;

import com.example.retrofitlibrary.Model.Posts;
import com.example.retrofitlibrary.Model.User;
import com.google.gson.JsonElement;

import java.util.List;

import javax.xml.transform.Result;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Reqres {

    @GET("users")
    Call<List<Posts>> getUsers();

    @POST("api/login")
    Call<JsonElement> userSignIn(@Body User user) ;

}
