package com.example.retrofitlibrary.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitlibrary.Adapter.PostAdapter;
import com.example.retrofitlibrary.Interface.JSONPlaceholder;
import com.example.retrofitlibrary.Interface.Reqres;
import com.example.retrofitlibrary.Model.Posts;
import com.example.retrofitlibrary.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    private JSONPlaceholder jsonPlaceholder;
    private Reqres reqres ;
    private RecyclerView postRecyclerView ;
    private PostAdapter postAdapter ;
    private List<Posts> posts ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialization and Binding Views
        init();

      // GetPosts();

       // GetPost();

        CreatePost();
    }

    private void init() {

        // view Binding
        postRecyclerView = findViewById(R.id.postsRecyclerView);

        // Rertofit Binding
        jsonPlaceholder = ApiClient.getJsonClient().create(JSONPlaceholder.class);
        reqres = ApiClient.getReqresClient().create(Reqres.class);

        // ArrayList
        posts = new ArrayList<>();

        // Adapter
        postAdapter = new PostAdapter(posts , this);

        // Set Recycler View
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        postRecyclerView.setAdapter(postAdapter);


    }

    public void CreatePost() {
        Posts posts = new Posts("Hello" , "Hello" , 23 );
        Call<Posts> call = jsonPlaceholder.createPost(posts);

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Response + " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Posts posts1 = response.body() ;
                Toast.makeText(MainActivity.this, "Response + " + response.code() + " \n\n " + posts1.getId(), Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error  " +  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void GetPost() {
//        Call<Posts> call = jsonPlaceholder.getSinglePost(2);
//
//        call.enqueue(new Callback<Posts>() {
//            @Override
//            public void onResponse(Call<Posts> call, Response<Posts> response) {
//                if(!response.isSuccessful())
//                {
//                    textView.setText("Error " + response.code());
//                    return;
//                }
//
//                String data = " id :: " + response.body().getId() + "\n"
//                        + " Title " + response.body().getTitle()+ "\n"
//                        + " Description " + response.body().getBody()+ "\n" ;
//
//                textView.setText("Success " + "\n" + response.code() + "\n" + data);
//            }
//
//            @Override
//            public void onFailure(Call<Posts> call, Throwable t) {
//
//            }
//        });
//    }
//
    public void GetPosts() {
        Call<List<Posts>> call = jsonPlaceholder.getPosts(1 , 4 , 3);

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Error " + response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(response.body() != null){

                    posts.addAll(response.body());

                    postAdapter.notifyDataSetChanged();

                    //Toast.makeText(MainActivity.this, "Successfull " + response.body(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "OnFailure " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
