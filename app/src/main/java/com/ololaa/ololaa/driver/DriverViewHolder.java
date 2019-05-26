package com.ololaa.ololaa.driver;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.databinding.DriverItemLayoutBinding;

class DriverViewHolder extends RecyclerView.ViewHolder {
    DriverItemLayoutBinding binding;
    public DriverViewHolder(@NonNull DriverItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void bind(Driver driver) {
        binding.setDriver(driver);
    }

}
