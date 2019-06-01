package com.ololaa.ololaa.driver;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.databinding.DriverListBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class DriverListActivity extends AppCompatActivity {
    DriverListBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    DriverViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    DriversAdapter adapter;

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
            if (drivers != null || drivers.size() > 0)
                setUpRecyclerView(drivers);
        });
    }

    private void setUpRecyclerView(List<Driver> drivers) {
        adapter = new DriversAdapter(drivers, this);
        recyclerView.setAdapter(adapter);
    }


}
