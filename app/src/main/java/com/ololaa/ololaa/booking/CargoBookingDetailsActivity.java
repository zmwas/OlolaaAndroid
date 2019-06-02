package com.ololaa.ololaa.booking;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.FragmentBookingBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.TRIP;

public class CargoBookingDetailsActivity extends AppCompatActivity {
    FragmentBookingBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    BookingViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_booking);
        viewModel = ViewModelProviders.of(this, factory).get(BookingViewModel.class);
        binding.setViewModel(viewModel);
        Trip trip = (Trip) getIntent().getSerializableExtra(TRIP);
        viewModel.populateDetails(trip);
    }
}
