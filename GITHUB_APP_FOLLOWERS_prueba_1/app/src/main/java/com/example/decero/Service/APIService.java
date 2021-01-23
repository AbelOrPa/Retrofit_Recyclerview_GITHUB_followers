package com.example.decero.Service;

import com.example.decero.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("users/{username}/followers")
    Call<List<User>> getFollow(@Path(value = "username") String username);

    @GET("users/{username}")
    Call<User> getUser(@Path(value = "username") String username);

}
