package com.ololaa.ololaa;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
        String role = sharedPrefsWrapper.getString("role");

//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, instanceIdResult -> {
//            String newToken = instanceIdResult.getToken();
//            Log.e("newToken", newToken);
//
//        });

        if (role.equals("transporter")) {
            mainCargoMoverBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_cargo_mover);
        } else {
            mainTransporterBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_transporter);
        }

    }
}
