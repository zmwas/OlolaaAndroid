package com.ololaa.ololaa.booking;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.GPSLiveData;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.ActivityFilterTripBinding;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.TRIP;

public class FilterTripsActivity extends AppCompatActivity implements CreateBookingCallback {
    GPSLiveData gpsLiveData;
    @Inject
    OlolaaViewModelFactory factory;
    BookingViewModel viewModel;
    ActivityFilterTripBinding binding;
    RecyclerView recyclerView;
    FilteredTripsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter_trip);
        recyclerView = binding.filteredTripsList;
        gpsLiveData = new GPSLiveData(this);
        viewModel = ViewModelProviders.of(this, factory).get(BookingViewModel.class);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        binding.setViewModel(viewModel);
        gpsLiveData.observe(this, locationStatus -> {
            if (locationStatus.getErrorMessage() != null) {

            } else {
                viewModel.latitude.set(locationStatus.latitude);
                viewModel.longitude.set(locationStatus.longitude);
            }
        });

        binding.filter.setOnClickListener(v -> filterTrips());
    }

    public void filterTrips() {
        viewModel.filterTrips().observe(this, trips -> {
            if (trips != null && trips.size() > 0) {
                setUpRecyclerView(trips);
            }
        });
    }

    private void setUpRecyclerView(List<Trip> trips) {
        binding.listContainer.setVisibility(View.VISIBLE);
        adapter = new FilteredTripsAdapter(trips, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, Trip trip, View v) {
        Intent intent = new Intent(this, CreateBookingActivity.class);
        intent.putExtra(TRIP, (Serializable) trip);
        startActivity(intent);
    }


}
