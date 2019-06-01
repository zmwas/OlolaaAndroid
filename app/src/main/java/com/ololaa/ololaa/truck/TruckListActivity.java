package com.ololaa.ololaa.truck;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.Truck;
import com.ololaa.ololaa.databinding.TruckListBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class TruckListActivity extends AppCompatActivity {
    TruckListBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    TruckViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    TrucksAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.truck_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = binding.truckList;
        recyclerView.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(this, factory).get(TruckViewModel.class);
        viewModel.fetchTrucks().observe(this, trucks -> {
            if (trucks != null || trucks.size() > 0) {
                setUpRecyclerView(trucks);
            }
        });
    }

    private void setUpRecyclerView(List<Truck> trucks) {
        adapter = new TrucksAdapter(trucks, this);
        recyclerView.setAdapter(adapter);
    }

}
