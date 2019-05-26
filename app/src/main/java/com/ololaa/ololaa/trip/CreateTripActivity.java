package com.ololaa.ololaa.trip;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.R;
import com.ololaa.ololaa.databinding.FragmentCreateTripBinding;

public class CreateTripActivity extends AppCompatActivity {
    FragmentCreateTripBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_create_trip);
    }

}
