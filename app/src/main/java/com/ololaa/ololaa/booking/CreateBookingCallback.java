package com.ololaa.ololaa.booking;

import android.view.View;

import com.ololaa.ololaa.common.models.Trip;

public interface CreateBookingCallback {
    void onItemClick(int position, Trip trip, View v);

}
