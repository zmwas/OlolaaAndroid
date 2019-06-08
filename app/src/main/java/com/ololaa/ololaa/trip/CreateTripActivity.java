package com.ololaa.ololaa.trip;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import com.ololaa.ololaa.common.DatePickerFragment;
import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.common.models.Truck;
import com.ololaa.ololaa.databinding.FragmentCreateTripBinding;
import com.ololaa.ololaa.driver.DriverListActivity;
import com.ololaa.ololaa.truck.TruckListActivity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.google.android.libraries.places.api.model.Place.*;
import static com.ololaa.ololaa.Constants.DRIVER;
import static com.ololaa.ololaa.Constants.PLACES_API_KEY;
import static com.ololaa.ololaa.Constants.TRUCK;

public class CreateTripActivity extends BaseActivity implements DatePickerFragment.DatePickerDialogListener {
    FragmentCreateTripBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    TripViewModel viewModel;
    private DatePickerDialog d;
    private String strdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_create_trip);
        viewModel = ViewModelProviders.of(this, factory).get(TripViewModel.class);
        binding.setViewModel(viewModel);

        binding.truck.setOnClickListener(v -> fetchTruck());

        binding.driver.setOnClickListener(v -> fetchDriver());
        binding.firstDate.setOnClickListener(v -> showDatePickerDialog(v));

        binding.lastDate.setOnClickListener(v -> showDatePickerDialog(v));
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().get(DRIVER) != null) {
                Driver driver = (Driver) getIntent().getExtras().get(DRIVER);
                viewModel.driver.set(driver.getName());
                viewModel.driverId.set(driver.getId());
            } else if (getIntent().getExtras().get(TRUCK) != null) {
                Truck truck = (Truck) getIntent().getExtras().get(TRUCK);
                viewModel.truck.set(truck.getLicensePlateNumber());
                viewModel.truckId.set(truck.getId());
            }
        }
        observeProgressDialog();
        observeSuccessDialog();
        findAutoCompletedLocations();
    }


    public void fetchDriver() {

        Intent intent = new Intent(this, DriverListActivity.class);
        intent.putExtra("REQUEST_CODE", 2);
        startActivityForResult(intent, 3);
    }

    public void fetchTruck() {

        Intent intent = new Intent(this, TruckListActivity.class);
        intent.putExtra("REQUEST_CODE", 2);
        startActivityForResult(intent, 4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == RESULT_OK) {
            Driver driver = (Driver) data.getSerializableExtra(DRIVER);
            viewModel.driver.set(driver.getName());
            viewModel.driverId.set(driver.getId());
        } else if (requestCode == 4 && resultCode == RESULT_OK) {
            Truck truck = (Truck) data.getSerializableExtra(TRUCK);
            viewModel.truck.set(truck.getLicensePlateNumber());
            viewModel.truckId.set(truck.getId());
        }

    }

    protected void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setmListener(this);
        Bundle bundle = new Bundle();
        bundle.putInt("viewId", v.getId());
        newFragment.setArguments(bundle);
        newFragment.show(this.getSupportFragmentManager(), "");
    }

    @Override
    public void onDatePicked(DialogFragment dialog, Calendar c, int viewId, String dateFormat) {
        if (dateFormat == null) {
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        if (c != null) {
            strdate = sdf.format(c.getTime());
        }
        if (viewId == binding.firstDate.getId()) {
            binding.firstDate.setText(strdate);
            viewModel.firstAvailableDate.set(c.getTime());
        } else if (viewId == binding.lastDate.getId()) {
            binding.lastDate.setText(strdate);
            viewModel.lastAvailableDate.set(c.getTime());
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

    public void findAutoCompletedLocations() {
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), PLACES_API_KEY);
        }

        AutocompleteSupportFragment startingLocation = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.startingLocation);

        startingLocation.setPlaceFields(Arrays.asList(Field.ID, Field.NAME));
        startingLocation.setHint(getString(R.string.starting_location));
        startingLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getLatLng());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });

        AutocompleteSupportFragment destination = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.destination);

        destination.setPlaceFields(Arrays.asList(Field.ID, Field.NAME));
        destination.setHint(getString(R.string.destination));
        destination.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getLatLng());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });


    }


}
