package com.vishal.androidhivelivedata.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import com.vishal.androidhivelivedata.R;
import com.vishal.androidhivelivedata.databinding.ActivityLoginBinding;
import com.vishal.androidhivelivedata.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ActivityLoginBinding activityLoginBinding = DataBindingUtil
        .setContentView(this, R.layout.activity_login);
    activityLoginBinding.setLifecycleOwner(this);
    LoginViewModel loginViewModel = new ViewModelProvider(this,
        new AndroidViewModelFactory(getApplication())).get(LoginViewModel.class);
    activityLoginBinding.setViewmodel(loginViewModel);

    loginViewModel.updateUi().observe(this, t -> {
      if (t.isEmpty()) {
        Toast.makeText(this, "Please enter valid cred", Toast.LENGTH_SHORT).show();
      } else {
        startActivity(new Intent(this, HomeActivity.class));
      }
    });

  }
}
