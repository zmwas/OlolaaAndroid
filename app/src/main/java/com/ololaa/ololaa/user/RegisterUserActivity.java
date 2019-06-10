package com.ololaa.ololaa.user;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.databinding.ActivityRegisterUserBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.Messages.SUCCESS;

public class RegisterUserActivity extends AppCompatActivity {
    ActivityRegisterUserBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    UserViewModel viewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_user);
        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
        binding.setViewModel(viewModel);

        observeSuccessDialog();
        observeProgressDialog();

    }

    private void goToLogin() {
        Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void observeProgressDialog() {
        viewModel.showProgressDialog().observe(this, this::onChanged);
    }

    public void observeSuccessDialog() {
        viewModel.showSuccessDialog().observe(this, isSuccess -> {
            if (isSuccess != null) {
                if (isSuccess.equals(SUCCESS)) {
                    goToLogin();
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
