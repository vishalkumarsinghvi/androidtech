package com.vishal.androidhivelivedata.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class RetrofitService {

  private static Retrofit retrofit = null;

  static RestApiService getApiService() {
    if (retrofit == null) {
      retrofit = new Retrofit
          .Builder()
          .baseUrl("https://reqres.in/")
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit.create(RestApiService.class);
  }
}
