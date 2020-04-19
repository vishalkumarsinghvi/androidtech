package com.vishal.androidhivelivedata.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class LoginModel implements Serializable {

  private
  String token;
  @SerializedName("error")
  private
  String error;

  public String getError() {
    return error;
  }

  public String getToken() {
    return token;
  }

}
