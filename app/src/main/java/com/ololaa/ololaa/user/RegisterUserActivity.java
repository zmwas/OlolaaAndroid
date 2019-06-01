package com.ololaa.ololaa.user;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.databinding.ActivityRegisterUserBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RegisterUserActivity extends AppCompatActivity {
    ActivityRegisterUserBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    UserViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_register_user);
        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
        binding.setViewModel(viewModel);
    }

}
