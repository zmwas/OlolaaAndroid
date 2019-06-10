package com.ololaa.ololaa;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.iid.FirebaseInstanceId;
import com.levibostian.wendy.service.Wendy;
import com.ololaa.ololaa.booking.FilterTripsActivity;
import com.ololaa.ololaa.booking.ListBookingsActivity;
import com.ololaa.ololaa.common.BaseActivity;
import com.ololaa.ololaa.common.SharedPreferenceImpl;
import com.ololaa.ololaa.common.SharedPrefsWrapper;
import com.ololaa.ololaa.databinding.ActivityMainCargoMoverBinding;
import com.ololaa.ololaa.databinding.ActivityMainTransporterBinding;
import com.ololaa.ololaa.driver.DriverListActivity;
import com.ololaa.ololaa.fetchingTasks.FetchDriversTask;
import com.ololaa.ololaa.fetchingTasks.FetchTrucksTask;
import com.ololaa.ololaa.fetchingTasks.OlolaaTaskFactory;
import com.ololaa.ololaa.trip.CreateTripActivity;
import com.ololaa.ololaa.truck.TruckListActivity;
import com.ololaa.ololaa.user.LoginActivity;
import com.ololaa.ololaa.user.UserViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.FETCH_DRIVERS;
import static com.ololaa.ololaa.Constants.FETCH_TRUCKS;

public class MainActivity extends BaseActivity {
    ActivityMainCargoMoverBinding mainCargoMoverBinding;
    ActivityMainTransporterBinding mainTransporterBinding;
    @Inject
    OlolaaViewModelFactory factory;
    UserViewModel viewModel;
    SharedPrefsWrapper sharedPrefsWrapper;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transporter);
        AndroidInjection.inject(this);
        sharedPrefsWrapper = new SharedPreferenceImpl(getApplicationContext());
        role = sharedPrefsWrapper.getString("role");
        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            Log.e("newToken", newToken);
            viewModel.updateFirebase(newToken);
        });
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        OlolaaApp app = (OlolaaApp) getApplication();
        OlolaaTaskFactory ololaaTaskFactory = app.getFactory();
        if (role.equals("customer")) {
            mainCargoMoverBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_cargo_mover);
            bindCargoListeners();
        } else {
            mainTransporterBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_transporter);
            bindListeners();
            FetchTrucksTask fetchTrucksTask = (FetchTrucksTask) ololaaTaskFactory.getTask(FETCH_TRUCKS);
            FetchDriversTask fetchDriversTask = (FetchDriversTask) ololaaTaskFactory.getTask(FETCH_DRIVERS);
            Wendy.sharedInstance().addTask(fetchDriversTask,true);
            Wendy.sharedInstance().addTask(fetchTrucksTask, true);
            Wendy.sharedInstance().runTasks(null);
        }


    }

    public void bindListeners() {
        mainTransporterBinding.fleet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TruckListActivity.class);
            startActivity(intent);
        });

        mainTransporterBinding.drivers.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DriverListActivity.class);
            startActivity(intent);

        });

        mainTransporterBinding.booking.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListBookingsActivity.class);
            startActivity(intent);

        });

        mainTransporterBinding.trip.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateTripActivity.class);
            startActivity(intent);

        });

        mainTransporterBinding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.log_out) {
                sharedPrefsWrapper.putString("token", null);
                sharedPrefsWrapper.putBoolean("logged out", true);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            return true;
        });

    }


    public void bindCargoListeners() {
        mainCargoMoverBinding.bookTrip.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FilterTripsActivity.class);
            startActivity(intent);
        });

        mainCargoMoverBinding.viewBookings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListBookingsActivity.class);
            startActivity(intent);
        });

        mainCargoMoverBinding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.log_out) {
                sharedPrefsWrapper.putString("token", null);
                sharedPrefsWrapper.putBoolean("logged out", true);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            return true;
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                if (role.equals("transporter")) {
                    mainTransporterBinding.drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    mainCargoMoverBinding.drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
