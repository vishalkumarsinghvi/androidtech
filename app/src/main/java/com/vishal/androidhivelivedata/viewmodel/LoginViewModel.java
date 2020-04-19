package com.vishal.androidhivelivedata.viewmodel;

import android.app.Application;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {

  private MutableLiveData<Integer> visibilityType = new MutableLiveData<>();
  private MutableLiveData<String> token = new MutableLiveData<>();

  public LoginViewModel(@NonNull Application application) {
    super(application);
    init();
  }

  private void init() {
    LoginRepository.getInstance().subscribeTokenFromRepo().observeForever(t ->
    {
      token.setValue(t);
      visibilityType.setValue(View.GONE);
    });
    visibilityType.setValue(View.GONE);
  }

  public void doLogin(String email, String password) {
    visibilityType.setValue(View.VISIBLE);
    LoginRepository.getInstance().validateUserPass(email, password);
  }

  public MutableLiveData<String> updateUi() {
    return token;
  }

  public LiveData<Integer> getVisibilityType() {
    return visibilityType;
  }
}
