package com.ololaa.ololaa.trip;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.booking.ViewPagerAdapter;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.FragmentAvailableTruckBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.TRIP;

public class TripDetailActivity extends AppCompatActivity {

    FragmentAvailableTruckBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    TripViewModel viewModel;
    private FragmentManager fragmentManager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_available_truck);
        viewModel = ViewModelProviders.of(this, factory).get(TripViewModel.class);
        binding.setViewModel(viewModel);
        Trip trip = (Trip) getIntent().getSerializableExtra(TRIP);
        viewModel.populateDetails(trip);
        fragmentManager = getSupportFragmentManager();
        loadImages();

    }

    public void loadImages() {
        List<String> urls = viewModel.pictures.get();
        viewPagerAdapter = new ViewPagerAdapter(fragmentManager, urls);
        binding.images.setAdapter(viewPagerAdapter);
        binding.tabDots.setupWithViewPager(binding.images, true);

    }

}
