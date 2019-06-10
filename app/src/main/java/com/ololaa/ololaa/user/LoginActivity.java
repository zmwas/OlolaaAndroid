package com.ololaa.ololaa.user;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.Constants;
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
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        sharedPrefsWrapper = new SharedPreferenceImpl(getApplicationContext());
        if (sharedPrefsWrapper.getString(Constants.TOKEN) != null && !sharedPrefsWrapper.getBoolean(Constants.LOGGED_OUT)) {
            login();
        } else {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_login_user);
            viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
            binding.setViewModel(viewModel);
            binding.signInPrompt.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            });
            observeProgressDialog();
            observeSuccessDialog();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPrefsWrapper.getString(Constants.TOKEN) != null && !sharedPrefsWrapper.getBoolean(Constants.LOGGED_OUT)) {
            login();
        } else {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_login_user);
            viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
            binding.setViewModel(viewModel);
            binding.signInPrompt.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            });

        }
    }

    public void login() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }

    public void observeProgressDialog() {
        viewModel.showProgressDialog().observe(this, this::onChanged);
    }


    public void observeSuccessDialog() {
        viewModel.showSuccessDialog().observe(this, isSuccess -> {
            if (isSuccess != null) {
                if (isSuccess.equals(SUCCESS)) {
                    login();
                } else {
                    hideProgressDialog();
                    showSuccessDialog(getString(R.string.error));
                }
            }
        });
    }

    public void showSuccessDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setOnCancelListener(dialog -> onBackPressed());
        builder.setPositiveButton("OK", (dialog, which) -> {
            onBackPressed();
        });
        builder.setMessage(message);
        AlertDialog dialog = builder.create();
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
        dialog.show();


    }


    public void onChanged(Boolean showDialog) {
        if (showDialog != null) {
            if (showDialog) {
                showProgressDialog();
            } else {
                hideProgressDialog();
            }
        }
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage(getString(R.string.progress_message));
        progressDialog.show();
    }

    public void hideProgressDialog() {
        progressDialog.dismiss();
    }



}
