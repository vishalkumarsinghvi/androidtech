package com.vishal.androidhivelivedata.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.vishal.androidhivelivedata.model.LoginModel;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

  private static LoginService loginService;
  private final MutableLiveData<String> validToken;

  private LoginService() {
    validToken = new MutableLiveData<>();
  }


  public static LoginService geInstance() {
    if (loginService == null) {
      loginService = new LoginService();
    }
    return loginService;
  }

  public void doLogin(String email, String password) {
    RetrofitService.getApiService().doLogin(email, password).enqueue(new Callback<LoginModel>() {
      @Override
      public void onResponse(@NonNull Call<LoginModel> call,
          @NonNull Response<LoginModel> response) {
        if (response.code() == 200) {
//          callback.onResponse(call, response);
          if (response.body() != null) {
            validToken.postValue(response.body().getToken());
          }
        } else {
          JsonParser parser = new JsonParser();
          JsonElement mJson = null;
          try {
            if (response.errorBody() != null) {
              mJson = parser.parse(response.errorBody().string());
            }
            Gson gson = new Gson();
            LoginModel errorResponse = gson.fromJson(mJson, LoginModel.class);
//            callback.onFailure(call,
//            new Throwable(errorResponse.getError()))
          } catch (IOException ex) {
            ex.printStackTrace();
//            callback.onFailure(call, new Throwable("Something went wrong"));
          }

          validToken.postValue("");
        }
      }

      @Override
      public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
        validToken.postValue("");
//        callback.onFailure(call, t);
      }
    });
  }

  public MutableLiveData<String> subscribeTokenFromService() {
    return validToken;
  }
}
