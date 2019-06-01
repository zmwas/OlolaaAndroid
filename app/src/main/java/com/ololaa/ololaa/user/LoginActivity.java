package com.ololaa.ololaa.user;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.databinding.ActivityLoginUserBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginUserBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    UserViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_user);
        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
        binding.setViewModel(viewModel);

    }

}
