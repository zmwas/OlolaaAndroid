package com.ololaa.ololaa.driver;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.R;
import com.ololaa.ololaa.databinding.FragmentDriverRegistrationBinding;

public class CreateDriverActivity extends AppCompatActivity {
    FragmentDriverRegistrationBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_driver_registration);
    }

}
