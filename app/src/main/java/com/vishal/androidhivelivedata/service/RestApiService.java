package com.vishal.androidhivelivedata.service;

import com.vishal.androidhivelivedata.model.LoginModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApiService {

  @FormUrlEncoded
  @POST("api/login")
  Call<LoginModel> doLogin(@Field("email") String email, @Field("password") String password);
}
