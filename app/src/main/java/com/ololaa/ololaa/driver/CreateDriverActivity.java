package com.ololaa.ololaa.driver;

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
import com.ololaa.ololaa.common.models.Truck;
import com.ololaa.ololaa.databinding.FragmentDriverRegistrationBinding;
import com.ololaa.ololaa.truck.TruckListActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.TRUCK;

public class CreateDriverActivity extends BaseActivity {
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

        observeProgressDialog();
        observeSuccessDialog();


    }

    public void uploadPassportPhoto() {
        if (ContextCompat.checkSelfPermission(CreateDriverActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || (ContextCompat.checkSelfPermission(CreateDriverActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(CreateDriverActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);


        } else {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), 4);
        }
    }

    public void fetchTruck() {

        Intent intent = new Intent(this, TruckListActivity.class);
        intent.putExtra("REQUEST_CODE",4);
        startActivityForResult(intent, 5);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            viewModel.showPassportPhoto.setValue(true);
            final String docId = DocumentsContract.getDocumentId(selectedImageUri);
            final String[] split = docId.split(":");
            final String type = split[0];

            path = "file://" + Environment.getExternalStorageDirectory() + "/" + split[1];

            viewModel.passportPhoto.set(path);
        } else if (requestCode == 5 && resultCode == RESULT_OK) {
            Truck truck = (Truck) data.getSerializableExtra(TRUCK);
            viewModel.truck.set(truck.getLicensePlateNumber());
            viewModel.truckId.set(truck.getId());

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            uploadPassportPhoto();
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
