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
    TruckClickedCallback clickedCallback;

    public TrucksAdapter(List<Truck> trucks, Context context, TruckClickedCallback clickedCallback) {
        this.trucks = trucks;
        this.context = context;
        this.clickedCallback = clickedCallback;
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
        truckViewHolder.binding.getRoot().setOnClickListener(v -> {
            clickedCallback.onItemClick(i, truck, v);
        });
    }

    @Override
    public int getItemCount() {
        return trucks.size();
    }
}
