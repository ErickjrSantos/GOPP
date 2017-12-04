package com.example.admin.estoquescan.Service;

import com.example.admin.estoquescan.Classes.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;


/**
 * Created by user on 04/12/17.
 */

public interface LoginService {
    @GET("login.php")
    Call<User> existe(@Field("user") String user,
                      @Field("password") String password);
}
