package com.ololaa.ololaa.truck;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ololaa.ololaa.common.models.Truck;
import com.ololaa.ololaa.databinding.TruckItemLayoutBinding;

import java.util.List;

public class TrucksAdapter extends RecyclerView.Adapter<TruckViewHolder> {
    private List<Truck> trucks;
    private Context context;

    public TrucksAdapter(List<Truck> trucks, Context context) {
        this.trucks = trucks;
        this.context = context;
    }

    @NonNull
    @Override
    public TruckViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        TruckItemLayoutBinding binding = TruckItemLayoutBinding.inflate(inflater, viewGroup, false);
        return new TruckViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TruckViewHolder truckViewHolder, int i) {
        Truck truck = trucks.get(i);
        truckViewHolder.bind(truck);
    }

    @Override
    public int getItemCount() {
        return trucks.size();
    }
}
