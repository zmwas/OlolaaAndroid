package com.ololaa.ololaa.trip;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.databinding.FragmentCreateTripBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class CreateTripActivity extends AppCompatActivity {
    FragmentCreateTripBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    TripViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_create_trip);
        viewModel = ViewModelProviders.of(this, factory).get(TripViewModel.class);
        binding.setViewModel(viewModel);
    }

}
