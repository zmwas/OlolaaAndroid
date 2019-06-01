package com.ololaa.ololaa.booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.common.models.Trip;

import java.io.Serializable;

import javax.inject.Inject;

import static com.ololaa.ololaa.Constants.TRIP;

public class FilteredTripsActivity extends AppCompatActivity implements CreateBookingCallback {
    @Inject
    OlolaaViewModelFactory factory;
    BookingViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClick(int position, Trip trip, View v) {
        Intent intent = new Intent(this, CreateBookingActivity.class);
        intent.putExtra(TRIP, (Serializable) trip);
        startActivity(intent);
    }
}
