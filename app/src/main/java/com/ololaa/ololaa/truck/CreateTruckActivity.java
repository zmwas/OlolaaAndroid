package com.ololaa.ololaa.truck;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.BaseActivity;
import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.databinding.FragmentTruckRegistrationBinding;
import com.ololaa.ololaa.driver.DriverListActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.DRIVER;

public class CreateTruckActivity extends BaseActivity {
    FragmentTruckRegistrationBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    TruckViewModel viewModel;
    Uri selectedImageUri;
    String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_truck_registration);
        viewModel = ViewModelProviders.of(this, factory).get(TruckViewModel.class);
        binding.setViewModel(viewModel);
        binding.uploadTruckPhoto.setOnClickListener(v -> {
            uploadTruckPhoto();
        });

        binding.uploadInsuranceSticker.setOnClickListener(v -> {
            uploadInsuranceSticker();
        });

        binding.driver.setOnClickListener(v -> fetchDriver());

        if (getIntent().getExtras()!= null) {
            Driver driver = (Driver) getIntent().getExtras().get(DRIVER);
            viewModel.driver.set(driver.getName());
            viewModel.driverId.set(driver.getId());
        }

        observeProgressDialog();
        observeSuccessDialog();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            uploadTruckPhoto();
        }

    }


    public void uploadInsuranceSticker() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);


        } else {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), 2);
        }
    }

    public void uploadTruckPhoto() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);


        } else {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), 14);
        }
    }

    public void fetchDriver() {

        Intent intent = new Intent(this, DriverListActivity.class);
        intent.putExtra("REQUEST_CODE", 1);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 14 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            viewModel.showTruckPhoto.setValue(true);
            final String docId = DocumentsContract.getDocumentId(selectedImageUri);
            final String[] split = docId.split(":");
            final String type = split[0];

            path ="file://" + Environment.getExternalStorageDirectory() + "/" + split[1];

            viewModel.truckPhoto.set(path);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            viewModel.showInsuranceSticker.setValue(true);
            final String docId = DocumentsContract.getDocumentId(selectedImageUri);
            final String[] split = docId.split(":");
            final String type = split[0];

            path = "file://" + Environment.getExternalStorageDirectory() + "/" + split[1];

            viewModel.insuranceSticker.set(path);
        } else if (requestCode ==3&& resultCode == 1) {
            Driver driver = (Driver) data.getSerializableExtra(DRIVER);
            viewModel.driver.set(driver.getName());
            viewModel.driverId.set(driver.getId());
        }

    }

    public void observeProgressDialog() {
        viewModel.showProgressDialog().observe(this, this::onChanged);
    }


    public void observeSuccessDialog() {
        viewModel.showSuccessDialog().observe(this, isSuccess -> {
            if (isSuccess != null) {
                if (isSuccess) {
                    hideProgressDialog();
                    showSuccessDialog(getString(R.string.success));
                } else {
                    hideProgressDialog();
                    showSuccessDialog(getString(R.string.error));
                }
            }
        });
    }

}
