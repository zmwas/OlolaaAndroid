package com.ololaa.ololaa.booking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.databinding.BookingsListItemBinding;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingViewHolder> {
    private List<Trip> bookings;
    private Context context;
    private BookingCallBack callBack;

    public BookingsAdapter(List<Trip> bookings, Context context, BookingCallBack callBack) {
        this.bookings = bookings;
        this.context = context;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        BookingsListItemBinding binding = BookingsListItemBinding.inflate(inflater, viewGroup, false);
        return new BookingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder bookingViewHolder, int i) {
        Trip trip = bookings.get(i);
        bookingViewHolder.bind(trip);
        bookingViewHolder.binding.getRoot().setOnClickListener(v -> {
            callBack.onItemClick(i, trip, v);
        });
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }
}
