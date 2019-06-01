package com.ololaa.ololaa.truck;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.databinding.FragmentTruckRegistrationBinding;
import com.ololaa.ololaa.driver.DriverListActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.DRIVER;

public class CreateTruckActivity extends AppCompatActivity {
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
    }


    public void uploadInsuranceSticker() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), 2);
    }

    public void uploadTruckPhoto() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
    }

    public void fetchDriver() {

        Intent intent = new Intent(this, DriverListActivity.class);

        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            viewModel.showTruckPhoto.setValue(true);
            viewModel.truckPhoto.set(selectedImageUri.getPath());
            path = selectedImageUri.getPath();
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            viewModel.showInsuranceSticker.setValue(true);
            viewModel.insuranceSticker.set(selectedImageUri.getPath());
            path = selectedImageUri.getPath();
        } else if (requestCode == 3 && resultCode == RESULT_OK) {
            Driver driver = (Driver) data.getSerializableExtra(DRIVER);
            viewModel.driver.set(driver.getName());
            viewModel.driverId.set(driver.getId());
        }

    }
}
