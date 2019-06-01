package com.ololaa.ololaa.booking;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.FragmentCreateBookingBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.TRIP;

public class CreateBookingActivity extends AppCompatActivity {
    FragmentCreateBookingBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    BookingViewModel viewModel;
    Uri selectedImageUri;
    String path;
    Trip trip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trip = (Trip) getIntent().getSerializableExtra(TRIP);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_create_booking);
        viewModel = ViewModelProviders.of(this, factory).get(BookingViewModel.class);
        if (trip != null)
            viewModel.tripObs.set(trip);
        binding.setViewModel(viewModel);
        binding.uploadCargoPhoto.setOnClickListener(v -> {

        });
    }

    public void uploadPhoto() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), 6);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6  && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            viewModel.showCargoPhoto.set(true);
            viewModel.photo.set(selectedImageUri.getPath());
            path = selectedImageUri.getPath();
        }
    }


}
