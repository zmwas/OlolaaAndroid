package com.ololaa.ololaa.booking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.FilteredTripItemBinding;

public class TripViewHolder extends RecyclerView.ViewHolder {
    FilteredTripItemBinding binding;

    public TripViewHolder(@NonNull FilteredTripItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Trip trip) {
        binding.collectionPoint.setText(trip.getCollectionPoint().getName());
        binding.dropoffPoint.setText(trip.getDropOffPoint().getName());
        binding.availableTonage.setText(String.valueOf(trip.getAvailableTonage()));
    }
}
