package com.ololaa.ololaa.user;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.MainActivity;
import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.SharedPreferenceImpl;
import com.ololaa.ololaa.common.SharedPrefsWrapper;
import com.ololaa.ololaa.databinding.ActivityLoginUserBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.Messages.SUCCESS;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginUserBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    UserViewModel viewModel;
    SharedPrefsWrapper sharedPrefsWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        sharedPrefsWrapper = new SharedPreferenceImpl(getApplicationContext());
        if (sharedPrefsWrapper.getString("token") != null && !sharedPrefsWrapper.getBoolean("logged out")) {
            login();
        } else {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_login_user);
            viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
            binding.setViewModel(viewModel);
            binding.signInPrompt.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            });
            viewModel.showSuccessDialog().observe(this, response -> {
                if (response.equals(SUCCESS)) {
                    login();
                }
            });
        }
    }

    public void login() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }

}
