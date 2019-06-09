package com.ololaa.ololaa.common;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.R;
import com.ololaa.ololaa.user.LoginActivity;

public class BaseActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    SharedPrefsWrapper sharedPrefsWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        sharedPrefsWrapper = new SharedPreferenceImpl(getApplicationContext());

        if (sharedPrefsWrapper.getLong("expired date") < System.currentTimeMillis()) {
            sharedPrefsWrapper.clear();
            sharedPrefsWrapper.putBoolean("logged out", true);
            logOut();
        }

    }

    public void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

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
