package com.vishal.androidhivelivedata.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.vishal.androidhivelivedata.service.LoginService;

//this class will talk to db or remote api depend on situations
class LoginRepository {

  private static LoginRepository instance;
  private MutableLiveData<String> token;

  private LoginRepository() {
    this.token = new MutableLiveData<>();
    init();
  }

  static LoginRepository getInstance() {
    if (instance == null) {
      instance = new LoginRepository();
    }
    return instance;
  }

  private void init() {
    LoginService.geInstance().subscribeTokenFromService().observeForever(t -> token.setValue(t));
  }


  MutableLiveData<String> subscribeTokenFromRepo() {
    return token;
  }

  void validateUserPass(String email, String password) {
    LoginService.geInstance().doLogin(email, password);

  }
}
