package com.Ololaa.ololaa.trip;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

public class TripViewModel extends ViewModel {
    public ObservableField<String> truck = new ObservableField<>();
    public ObservableField<String> errorTruck = new ObservableField<>();
    public ObservableField<String> driver = new ObservableField<>();
    public ObservableField<String> errorDriver = new ObservableField<>();
    public ObservableField<String> startingLocation = new ObservableField<>();
    public ObservableField<String> errorStartingLocation = new ObservableField<>();
    public ObservableField<String> destination = new ObservableField<>();
    public ObservableField<String> errorDestination = new ObservableField<>();
    public ObservableField<String> availableTonage = new ObservableField<>();
    public ObservableField<String> errorAvailableTonage = new ObservableField<>();
    public ObservableField<String> firstAvailableDate = new ObservableField<>();
    public ObservableField<String> errorFirstAvailableDate = new ObservableField<>();
    public ObservableField<String> lastAvailableDate = new ObservableField<>();
    public ObservableField<String> errorLastAvailableDate = new ObservableField<>();


}