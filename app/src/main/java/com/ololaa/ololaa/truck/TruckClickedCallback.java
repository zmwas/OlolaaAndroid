package com.ololaa.ololaa.truck;

import android.view.View;

import com.ololaa.ololaa.common.models.Truck;

public interface TruckClickedCallback {
    void onItemClick(int position, Truck truck, View v);

}
