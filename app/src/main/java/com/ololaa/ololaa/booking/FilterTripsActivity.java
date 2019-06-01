package com.ololaa.ololaa.booking;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.GPSLiveData;
import com.ololaa.ololaa.databinding.ActivityFilterTripBinding;

import javax.inject.Inject;

public class FilterTripsActivity extends AppCompatActivity {
    GPSLiveData gpsLiveData;
    @Inject
    OlolaaViewModelFactory factory;
    BookingViewModel viewModel;
    ActivityFilterTripBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter_trip);
        gpsLiveData = new GPSLiveData(this);
        viewModel = ViewModelProviders.of(this, factory).get(BookingViewModel.class);
        binding.setViewModel(viewModel);
        gpsLiveData.observe(this, locationStatus -> {
            if (locationStatus.getErrorMessage() != null) {

            } else {
                viewModel.latitude.set(locationStatus.latitude);
                viewModel.longitude.set(locationStatus.longitude);
            }
        });


    }

}
