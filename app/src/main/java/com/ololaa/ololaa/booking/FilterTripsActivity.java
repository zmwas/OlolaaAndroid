package com.ololaa.ololaa.booking;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.BaseActivity;
import com.ololaa.ololaa.common.GPSLiveData;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.ActivityFilterTripBinding;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.PLACES_API_KEY;
import static com.ololaa.ololaa.Constants.TRIP;

public class FilterTripsActivity extends BaseActivity implements CreateBookingCallback {
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
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        findAutoCompletedLocations();
        binding.filter.setOnClickListener(v -> filterTrips());
        observeProgressDialog();
        observeProgressDialog();
    }

    public void filterTrips() {
        viewModel.filterTrips().observe(this, trips -> {
            if (trips != null && trips.size() > 0) {
                setUpRecyclerView(trips);
            } else {
                binding.resultsEmpty.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        }

    }

    private void getLocation() {
        gpsLiveData.observe(this, locationStatus -> {
            if (locationStatus.getErrorMessage() != null) {

            } else {
                viewModel.latitude.set(locationStatus.latitude);
                viewModel.longitude.set(locationStatus.longitude);
            }
        });

    }

    private void setUpRecyclerView(List<Trip> trips) {
        binding.listContainer.setVisibility(View.VISIBLE);
        if (trips.size() > 0) {
            binding.resultsEmpty.setVisibility(View.GONE);
        } else {
            binding.resultsEmpty.setVisibility(View.VISIBLE);
        }
        adapter = new FilteredTripsAdapter(trips, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, Trip trip, View v) {
        Intent intent = new Intent(this, CreateBookingActivity.class);
        intent.putExtra(TRIP, (Serializable) trip);
        startActivity(intent);
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

    public void findAutoCompletedLocations() {
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), PLACES_API_KEY);
        }

        AutocompleteSupportFragment startingLocation = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.startingLocation);
        startingLocation.setHint(getString(R.string.starting_location));
        startingLocation.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        startingLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getLatLng());
                viewModel.collectionPoint.set(place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });

        AutocompleteSupportFragment destination = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.destination);

        destination.setHint(getString(R.string.delivery_point));
        destination.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        destination.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getLatLng());
                viewModel.dropOffPoint.set(place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });


    }


}
