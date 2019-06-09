package com.ololaa.ololaa.booking;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.FragmentBookingBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.TRIP;

public class CargoBookingDetailsActivity extends AppCompatActivity {
    FragmentBookingBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    BookingViewModel viewModel;
    //    private NotificationBroadcastReceiver mNotificationBroadcastReceiver = null;
//    private IntentFilter mIntentFilter = null;
    Trip trip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
//        this.mNotificationBroadcastReceiver = new NotificationBroadcastReceiver(this);
//        this.mIntentFilter = new IntentFilter(Constants.BROADCAST_MESSAGE_NOTIFICATION_RECEIVED);

        binding = DataBindingUtil.setContentView(this, R.layout.fragment_booking);
        viewModel = ViewModelProviders.of(this, factory).get(BookingViewModel.class);
        binding.setViewModel(viewModel);
        trip = (Trip) getIntent().getSerializableExtra(TRIP);
        viewModel.populateDetails(trip);
        fetchBooking();
        binding.call.setOnClickListener(v -> call());
    }

    public void fetchBooking() {
        viewModel.fetchBooking(trip.getId()).observe(CargoBookingDetailsActivity.this, trip1 -> {
            viewModel.populateDetails(trip1);
        });

    }

    public void call() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + viewModel.telephone.get()));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            call();
        }
    }

    //    @Override
//    protected void onResume() {
//        super.onResume();
//        LocalBroadcastManager.getInstance(this).registerReceiver(this.mNotificationBroadcastReceiver, this.mIntentFilter);
//    }
//
//    @Override
//    protected void onPause() {
//        if (this.mNotificationBroadcastReceiver != null) {
//            unregisterReceiver(mNotificationBroadcastReceiver);
//            this.mNotificationBroadcastReceiver = null;
//        }
//
//        super.onPause();
//    }
//
//

//    private class NotificationBroadcastReceiver extends BroadcastReceiver {
//
//        WeakReference<CargoBookingDetailsActivity> mMainActivity;
//
//        public NotificationBroadcastReceiver(CargoBookingDetailsActivity mainActivity) {
//            this.mMainActivity = new WeakReference<>(mainActivity);
//        }
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            CargoBookingDetailsActivity mainActivity = mMainActivity.get();
//            if (mainActivity != null) {
//                Bundle extras = intent.getExtras();
//                if (extras != null && extras.containsKey(Constants.PARAM_NOTIFICATION_INFO)) {
//                    NotificationInfo notificationInfo = (NotificationInfo) extras.getSerializable(Constants.PARAM_NOTIFICATION_INFO);
//                    assert notificationInfo != null;
//                    mainActivity.notificationReceived(notificationInfo);
//                }
//            }
//        }
//    }
//
//    public void notificationReceived(@NonNull final NotificationInfo notificationInfo)
//    {
//            fetchBooking();
//    }
//
}
