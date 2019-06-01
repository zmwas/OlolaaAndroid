package com.ololaa.ololaa.booking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.BookingsListItemBinding;

class BookingViewHolder extends RecyclerView.ViewHolder {
    public BookingsListItemBinding binding;
    public BookingViewHolder(@NonNull BookingsListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Trip trip) {
        binding.setTrip(trip);
    }
}
