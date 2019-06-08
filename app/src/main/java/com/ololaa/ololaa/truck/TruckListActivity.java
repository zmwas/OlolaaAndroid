package com.ololaa.ololaa.truck;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.BaseActivity;
import com.ololaa.ololaa.common.models.Truck;
import com.ololaa.ololaa.databinding.TruckListBinding;
import com.ololaa.ololaa.trip.CreateTripActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.TRUCK;

public class TruckListActivity extends BaseActivity implements TruckClickedCallback {
    TruckListBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    TruckViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    TrucksAdapter adapter;
    private int requestCode;

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
            if (trucks != null && trucks.size() > 0) {
                setUpRecyclerView(trucks);
            }
        });
        binding.fab.setOnClickListener(v -> {
            Intent intent = new Intent(TruckListActivity.this, CreateTruckActivity.class);
            startActivity(intent);

        });
        if (getIntent() != null) {
            requestCode = getIntent().getIntExtra("REQUEST_CODE", 0);
        }
        observeProgressDialog();
        observeSuccessDialog();
    }

    private void setUpRecyclerView(List<Truck> trucks) {
        binding.trucksEmpty.setVisibility(View.GONE);
        adapter = new TrucksAdapter(trucks, this, this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, Truck truck, View v) {
        if (requestCode == 4) {
            Intent intent = new Intent(this, CreateTruckActivity.class);
            intent.putExtra(TRUCK, truck);
            setResult(RESULT_OK, intent);
            finish();

        } else if (requestCode == 2) {
            Intent intent = new Intent(this, CreateTripActivity.class);
            intent.putExtra(TRUCK, truck);
            setResult(RESULT_OK, intent);
            finish();

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

}
