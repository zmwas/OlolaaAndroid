package com.ololaa.ololaa.trip;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.FragmentAvailableTruckBinding;

import javax.inject.Inject;

import static com.ololaa.ololaa.Constants.TRIP;

public class TripDetailActivity extends AppCompatActivity {

    FragmentAvailableTruckBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    TripViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_available_truck);
        viewModel = ViewModelProviders.of(this, factory).get(TripViewModel.class);
        binding.setViewModel(viewModel);
        Trip trip = (Trip) getIntent().getSerializableExtra(TRIP);
        viewModel.populateDetails(trip);
    }

}
