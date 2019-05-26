package com.ololaa.ololaa.truck;

import android.support.v7.widget.RecyclerView;

import com.ololaa.ololaa.common.models.Truck;
import com.ololaa.ololaa.databinding.TruckItemLayoutBinding;

class TruckViewHolder extends RecyclerView.ViewHolder {
    TruckItemLayoutBinding binding;

    public TruckViewHolder(TruckItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Truck truck) {
        binding.setTruck(truck);
    }
}
