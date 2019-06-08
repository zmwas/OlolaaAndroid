package com.ololaa.ololaa.driver;

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
import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.databinding.DriverListBinding;
import com.ololaa.ololaa.trip.CreateTripActivity;
import com.ololaa.ololaa.truck.CreateTruckActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.DRIVER;

public class DriverListActivity extends AppCompatActivity implements DriverClickedCallback {
    DriverListBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    DriverViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    DriversAdapter adapter;
    int requestCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.driver_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = binding.recyclerview;
        recyclerView.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(this, factory).get(DriverViewModel.class);
        viewModel.fetchDrivers().observe(this, drivers -> {
            if (drivers != null && drivers.size() > 0)
                setUpRecyclerView(drivers);
        });
        binding.fab.setOnClickListener(v -> {
            Intent intent = new Intent(DriverListActivity.this, CreateDriverActivity.class);
            startActivity(intent);

        });
        if (getIntent()!=null) {
            requestCode = getIntent().getIntExtra("REQUEST_CODE", 0);
        }

    }

    private void setUpRecyclerView(List<Driver> drivers) {
        binding.driversEmpty.setVisibility(View.GONE);
        adapter = new DriversAdapter(drivers, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(int position, Driver driver, View v) {
        if (requestCode == 1) {
            Intent intent = new Intent(DriverListActivity.this, CreateTruckActivity.class);
            intent.putExtra(DRIVER, driver);
            setResult(1,intent);
            finish();//finishing activity

        } else if (requestCode == 2 ) {
            Intent intent = new Intent(DriverListActivity.this, CreateTripActivity.class);
            intent.putExtra(DRIVER, driver);
            setResult(RESULT_OK,intent);
            finish();//finishing activity

        }
    }
}
