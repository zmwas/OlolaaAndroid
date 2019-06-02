package com.ololaa.ololaa;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ololaa.ololaa.common.SharedPrefsWrapper;
import com.ololaa.ololaa.databinding.ActivityMainCargoMoverBinding;
import com.ololaa.ololaa.databinding.ActivityMainTransporterBinding;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    ActivityMainCargoMoverBinding mainCargoMoverBinding;
    ActivityMainTransporterBinding mainTransporterBinding;

    @Inject
    SharedPrefsWrapper sharedPrefsWrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transporter);
       String role =  sharedPrefsWrapper.getString("role");
       if (role.equals("transporter")) {
           mainCargoMoverBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_cargo_mover);
       } else {
           mainTransporterBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_transporter);
       }
    }
}
