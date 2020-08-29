package com.example.retrofitlibrary.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofitlibrary.Interface.JSONPlaceholder;
import com.example.retrofitlibrary.Interface.Reqres;
import com.example.retrofitlibrary.Model.Posts;
import com.example.retrofitlibrary.Model.User;
import com.example.retrofitlibrary.R;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.xml.transform.Result;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Reqres reqres ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reqres = ApiClient.getReqresClient().create(Reqres.class);

        User user = new User("eve.holt@reqres.in"  , "cityslicka");
        Call<JsonElement> call = reqres.userSignIn(user);

//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Toast.makeText(LoginActivity.this, " Response " + response.code() + " \n Token :: " + response.body().getToken(), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });

       call.enqueue(new Callback<JsonElement>() {
           @Override
           public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
               Toast.makeText(LoginActivity.this, " Response " + response.code() + " \n Token :: " + response.body(), Toast.LENGTH_SHORT).show();
//
           }

           @Override
           public void onFailure(Call<JsonElement> call, Throwable t) {

           }
       });


    }
}