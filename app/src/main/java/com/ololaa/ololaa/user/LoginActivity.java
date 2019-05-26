package com.ololaa.ololaa.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.R;
import com.ololaa.ololaa.databinding.ActivityLoginUserBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginUserBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_user);
    }

}
