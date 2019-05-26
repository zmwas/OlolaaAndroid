package com.ololaa.ololaa.driver;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.R;
import com.ololaa.ololaa.databinding.DriverListBinding;

public class DriverListActivity extends AppCompatActivity {
    DriverListBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.driver_list);
    }

}
