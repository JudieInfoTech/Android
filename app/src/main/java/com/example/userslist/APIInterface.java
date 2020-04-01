package com.example.userslist;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    String JSONURL = "https://jsonplaceholder.typicode.com";

    @GET("users")
    Call<String> getUsersList();
}