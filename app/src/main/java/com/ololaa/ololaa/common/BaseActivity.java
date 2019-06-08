package com.ololaa.ololaa.common;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.R;

public class BaseActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
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
