package com.ololaa.ololaa.trip;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.booking.BookingCallBack;
import com.ololaa.ololaa.booking.BookingViewModel;
import com.ololaa.ololaa.booking.BookingsAdapter;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.FragmentBookingsListBinding;
import com.ololaa.ololaa.truck.TruckDetailActivity;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.ololaa.ololaa.Constants.TRIP;

public class BookedTripListActivity extends AppCompatActivity implements BookingCallBack {
    FragmentBookingsListBinding binding;
    @Inject
    OlolaaViewModelFactory factory;
    BookingViewModel viewModel;
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BookingsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_bookings_list);
        recyclerView = binding.bookingsList;
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(this, factory).get(BookingViewModel.class);

        viewModel.fetchBookings().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable List<Trip> trips) {
                if (trips != null && trips.size() > 0)
                    setUpRecyclerView(trips);
            }
        });
    }

    private void setUpRecyclerView(List<Trip> trips) {
        adapter = new BookingsAdapter(trips, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, Trip trip, View v) {
        Intent intent = new Intent(this, TruckDetailActivity.class);
        intent.putExtra(TRIP, (Serializable) trip);
        startActivity(intent);
    }

}
