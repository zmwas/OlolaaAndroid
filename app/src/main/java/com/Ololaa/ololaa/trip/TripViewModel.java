package com.ololaa.ololaa.trip;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ololaa.ololaa.common.requests.CreateTripRequest;

import javax.inject.Inject;

public class TripViewModel extends ViewModel {
    public ObservableField<String> truck = new ObservableField<>();
    public ObservableField<String> truckType = new ObservableField<>();
    public ObservableField<String> collectionPoint = new ObservableField<>();
    public ObservableField<String> collectionDate = new ObservableField<>();
    public ObservableField<String> transporter = new ObservableField<>();
    public ObservableField<String> tonage = new ObservableField<>();
    public ObservableField<String> telephone = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> agreedPrice = new ObservableField<>();
    public ObservableField<String> kraPin = new ObservableField<>();
    public ObservableField<Boolean> callMade = new ObservableField<>();
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
    public ObservableField<Long> driverId = new ObservableField<>();
    public ObservableField<Long> truckId = new ObservableField<>();

    private TripRepository tripRepository;

    @Inject
    public TripViewModel(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }


    public CreateTripRequest request() {
        CreateTripRequest request = new CreateTripRequest();
        request.setAvailableTonage(Double.valueOf(availableTonage.get()));
        request.setFirstAvailableDate(firstAvailableDate.get());
        request.setLastAvailableDate(lastAvailableDate.get());
        request.setTripDestination(destination.get());
        request.setTripStart(startingLocation.get());
        request.setDriverId(driverId.get());
        request.setTruckId(truckId.get());
        return request;
    }

    public void createTrip() {
        if (isDataValid())
            tripRepository.createTrip(request());
    }

    private boolean isDataValid() {
        if (availableTonage.get() == null || availableTonage.get().isEmpty()) {
            errorAvailableTonage.set("Cannot be empty");
            return false;
        } else if (firstAvailableDate.get() == null || firstAvailableDate.get().isEmpty()) {
            errorFirstAvailableDate.set("Cannot be empty");
            return false;

        } else if (lastAvailableDate.get() == null || lastAvailableDate.get().isEmpty()) {
            errorLastAvailableDate.set("Cannot be empty");
            return false;

        } else if (destination.get() == null || destination.get().isEmpty()) {
            errorDestination.set("Cannot be empty");
            return false;

        } else if (startingLocation.get() == null || startingLocation.get().isEmpty()) {
            errorStartingLocation.set("Cannot be empty");
            return false;

        } else if (driverId.get() == null || driverId.get() == 0) {
            errorDriver.set("Pick a driver by clicking on the box");
            return false;

        } else if (truckId.get() == null || truckId.get() == 0) {
            errorTruck.set("Pick a truck by clicking on the box");
            return false;

        }

        return true;
    }


}
