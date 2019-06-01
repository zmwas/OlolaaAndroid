package com.ololaa.ololaa.booking;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.FilteredTripItemBinding;

import java.util.List;

public class FilteredTripsAdapter extends RecyclerView.Adapter<TripViewHolder> {
    List<Trip> trips;
    Context context;
    FilteredTripItemBinding binding;
    CreateBookingCallback callback;

    public FilteredTripsAdapter(List<Trip> trips, Context context, CreateBookingCallback callback) {
        this.trips = trips;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.filtered_trip_item, viewGroup, false);
        return new TripViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder tripViewHolder, int i) {
        Trip trip = trips.get(i);
        tripViewHolder.bind(trip);
        binding.createBooking.setOnClickListener(v -> {
            callback.onItemClick(i, trip, v);
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
