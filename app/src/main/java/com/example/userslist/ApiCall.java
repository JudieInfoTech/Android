package com.example.userslist;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiCall {
    @GET("users")
    Observable<List<Users>> getUserDetails();
   // Call<String> getUserDetails();


}
