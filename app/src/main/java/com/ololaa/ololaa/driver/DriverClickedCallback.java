package com.ololaa.ololaa.driver;

import android.view.View;

import com.ololaa.ololaa.common.models.Driver;

public interface DriverClickedCallback {
    void onItemClick(int position, Driver driver, View v);

}
