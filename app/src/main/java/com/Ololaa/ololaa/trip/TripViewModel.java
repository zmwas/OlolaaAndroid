package com.ololaa.ololaa.trip;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ololaa.ololaa.common.SingleLiveEvent;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.requests.CreateTripRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.ololaa.ololaa.common.Utils.formatDate;

public class TripViewModel extends ViewModel {
    public ObservableField<String> truck = new ObservableField<>();
    public ObservableField<String> truckType = new ObservableField<>();
    public ObservableField<String> collectionPoint = new ObservableField<>();
    public ObservableField<String> collectionDate = new ObservableField<>();
    public ObservableField<String> collectionDate2 = new ObservableField<>();

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
    public ObservableField<Date> firstAvailableDate = new ObservableField<>();
    public ObservableField<String> errorFirstAvailableDate = new ObservableField<>();
    public ObservableField<Date> lastAvailableDate = new ObservableField<>();
    public ObservableField<String> errorLastAvailableDate = new ObservableField<>();
    public ObservableField<Long> driverId = new ObservableField<>();
    public ObservableField<Long> truckId = new ObservableField<>();
    public ObservableField<List<String>> pictures = new ObservableField<>();

    private TripRepository tripRepository;

    @Inject
    public TripViewModel(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public CreateTripRequest request() {
        CreateTripRequest request = new CreateTripRequest();
        request.setAvailableTonage(Double.valueOf(availableTonage.get()));
        request.setFirstAvailableDate(formatDate(firstAvailableDate.get()));
        request.setLastAvailableDate(formatDate(lastAvailableDate.get()));
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
        } else if (firstAvailableDate.get() == null) {
            errorFirstAvailableDate.set("Cannot be empty");
            return false;

        } else if (lastAvailableDate.get() == null) {
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

    void populateDetails(Trip trip) {
        List<String> urls = new ArrayList<>();
        transporter.set(trip.getTransporter().getCompanyName());
        truckType.set(trip.getTruck().getTruckType());
        tonage.set(String.valueOf(trip.getAvailableTonage()));
        collectionPoint.set(trip.getCollectionPoint().getName());
        collectionDate.set(trip.getFirstAvailableDate());
        collectionDate2.set(trip.getLastAvailableDate());
        driver.set(trip.getDriver().getName());
        kraPin.set(trip.getTransporter().getKraPin());
        telephone.set(trip.getTransporter().getPhoneNumber());
        email.set(trip.getTransporter().getEmail());
        callMade.set(false);
        urls.add(trip.getTruck().getPhotoUrl());
        urls.add(trip.getTruck().getInsuranceSticker());
        urls.add(trip.getDriver().getPassPortPhotoUrl());
        pictures.set(urls);
    }

    public SingleLiveEvent<Boolean> showSuccessDialog() {
        return tripRepository.showSuccessDialog;
    }

    public SingleLiveEvent<Boolean> showProgressDialog() {
        return tripRepository.showProgressDialog;
    }

}
