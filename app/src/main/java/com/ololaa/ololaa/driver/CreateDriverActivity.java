package com.ololaa.ololaa.driver;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.Truck;
import com.ololaa.ololaa.databinding.FragmentDriverRegistrationBinding;
import com.ololaa.ololaa.truck.TruckListActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.TRUCK;

public class CreateDriverActivity extends AppCompatActivity {
    FragmentDriverRegistrationBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    DriverViewModel viewModel;
    Uri selectedImageUri;
    String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_driver_registration);
        viewModel = ViewModelProviders.of(this, factory).get(DriverViewModel.class);
        binding.setViewModel(viewModel);
        binding.uploadPassportPhoto.setOnClickListener(v -> {
            uploadPassportPhoto();
        });

        binding.truck.setOnClickListener(v -> {
            fetchTruck();
        });
    }

    public void uploadPassportPhoto() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), 4);
    }

    public void fetchTruck() {

        Intent intent = new Intent(this, TruckListActivity.class);

        startActivityForResult(intent, 5);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            viewModel.showPassportPhoto.setValue(true);
            viewModel.passportPhoto.set(selectedImageUri.getPath());
            path = selectedImageUri.getPath();
        } else if (requestCode == 5 && resultCode == RESULT_OK) {
            Truck truck = (Truck) data.getSerializableExtra(TRUCK);
            viewModel.truck.set(truck.getLicensePlateNumber());
            viewModel.truckId.set(truck.getId());

        }
    }
}
