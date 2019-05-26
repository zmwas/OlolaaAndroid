package com.ololaa.ololaa.driver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.databinding.DriverItemLayoutBinding;

import java.util.List;

public class DriversAdapter extends RecyclerView.Adapter<DriverViewHolder> {
    private List<Driver> drivers;
    private Context context;

    public DriversAdapter(List<Driver> drivers, Context context) {
        this.drivers = drivers;
        this.context = context;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        DriverItemLayoutBinding binding = DriverItemLayoutBinding.inflate(inflater, viewGroup, false);
        return new DriverViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder driverViewHolder, int i) {
        Driver driver = drivers.get(i);
        driverViewHolder.bind(driver);
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }
}
