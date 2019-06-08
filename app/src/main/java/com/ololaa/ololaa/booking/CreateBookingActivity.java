package com.ololaa.ololaa.booking;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.BaseActivity;
import com.ololaa.ololaa.common.DatePickerFragment;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.FragmentCreateBookingBinding;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.google.android.libraries.places.api.model.Place.*;
import static com.ololaa.ololaa.Constants.PLACES_API_KEY;
import static com.ololaa.ololaa.Constants.TRIP;

public class CreateBookingActivity extends BaseActivity implements DatePickerFragment.DatePickerDialogListener {
    FragmentCreateBookingBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    BookingViewModel viewModel;
    Uri selectedImageUri;
    String path;
    Trip trip;
    String strdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trip = (Trip) getIntent().getSerializableExtra(TRIP);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_create_booking);
        viewModel = ViewModelProviders.of(this, factory).get(BookingViewModel.class);
        if (trip != null)
            viewModel.tripObs.set(trip);
        binding.setViewModel(viewModel);
        binding.uploadCargoPhoto.setOnClickListener(v -> {
            uploadPhoto();
        });
        binding.firstDate.setOnClickListener(v -> showDatePickerDialog(v));

        binding.lastDate.setOnClickListener(v -> showDatePickerDialog(v));

        findAutoCompletedLocations();
        observeProgressDialog();
        observeSuccessDialog();
    }

    public void uploadPhoto() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);


        } else {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pick a picture"), 6);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            uploadPhoto();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            viewModel.showCargoPhoto.set(true);
            final String docId = DocumentsContract.getDocumentId(selectedImageUri);
            final String[] split = docId.split(":");
            final String type = split[0];

            path = "file://" + Environment.getExternalStorageDirectory() + "/" + split[1];

            viewModel.photo.set(path);
        }
    }

    protected void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setmListener(this);
        Bundle bundle = new Bundle();
        bundle.putInt("viewId", v.getId());
        newFragment.setArguments(bundle);
        newFragment.show(this.getSupportFragmentManager(), "");
    }

    @Override
    public void onDatePicked(DialogFragment dialog, Calendar c, int viewId, String dateFormat) {
        if (dateFormat == null) {
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        if (c != null) {
            strdate = sdf.format(c.getTime());
        }
        if (viewId == binding.firstDate.getId()) {
            binding.firstDate.setText(strdate);
            viewModel.firstAvailableDate.set(c.getTime());
        } else if (viewId == binding.lastDate.getId()) {
            binding.lastDate.setText(strdate);
            viewModel.lastAvailableDate.set(c.getTime());
        }
    }

    public void observeProgressDialog() {
        viewModel.showProgressDialog().observe(this, this::onChanged);
    }


    public void observeSuccessDialog() {
        viewModel.showSuccessDialog().observe(this, isSuccess -> {
            if (isSuccess != null) {
                if (isSuccess) {
                    hideProgressDialog();
                    showSuccessDialog(getString(R.string.success));
                } else {
                    hideProgressDialog();
                    showSuccessDialog(getString(R.string.error));
                }
            }
        });
    }


    public void findAutoCompletedLocations() {
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), PLACES_API_KEY);
        }

        AutocompleteSupportFragment startingLocation = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.collection_point);
        startingLocation.setHint(getString(R.string.starting_location));
        startingLocation.setPlaceFields(Arrays.asList(Field.ID, Field.NAME));

        startingLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getLatLng());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });

        AutocompleteSupportFragment destination = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.destination);

        destination.setHint(getString(R.string.delivery_point));
        destination.setPlaceFields(Arrays.asList(Field.ID, Field.NAME));

        destination.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getLatLng());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });


    }


}
