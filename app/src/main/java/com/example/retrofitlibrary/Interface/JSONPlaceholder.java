package com.example.retrofitlibrary.Interface;

import com.example.retrofitlibrary.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceholder {

    @GET("posts")
    Call<List<Posts>> getPosts(@Query("userId") Integer... id);


    @GET("posts/{id}")
    Call<Posts> getSinglePost(@Path("id") int id);

    @POST("posts")
    Call<Posts> createPost(@Body Posts posts);
}
