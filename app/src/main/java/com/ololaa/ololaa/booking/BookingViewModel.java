package com.ololaa.ololaa.booking;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.requests.FilterTripsRequest;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.ololaa.ololaa.common.Utils.formatDate;

public class BookingViewModel extends ViewModel {
    public ObservableField<String> cargoType = new ObservableField<>();
    public ObservableField<String> cargoPictureUrl = new ObservableField<>();
    public ObservableField<String> errorCargoType = new ObservableField<>();
    public ObservableField<String> cargoMover = new ObservableField<>();
    public ObservableField<String> idNumber = new ObservableField<>();
    public ObservableField<String> telephone = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> weight = new ObservableField<>();
    public ObservableField<String> errorWeight = new ObservableField<>();
    public ObservableField<String> collectionPoint = new ObservableField<>();
    public ObservableField<String> errorCollectionPoint = new ObservableField<>();
    public ObservableField<String> dropOffPoint = new ObservableField<>();
    public ObservableField<String> errorDropOffPoint = new ObservableField<>();
    public ObservableField<String> availableTonage = new ObservableField<>();
    public ObservableField<String> errorAvailableTonage = new ObservableField<>();
    public ObservableField<Date> firstAvailableDate = new ObservableField<>();
    public ObservableField<String> errorFirstAvailableDate = new ObservableField<>();
    public ObservableField<Date> lastAvailableDate = new ObservableField<>();
    public ObservableField<String> errorLastAvailableDate = new ObservableField<>();
    public ObservableField<String> numberUnits = new ObservableField<>();
    public ObservableField<String> errorNumberUnits = new ObservableField<>();
    public ObservableField<Boolean> showCargoPhoto = new ObservableField<>();
    public ObservableField<String> tonage = new ObservableField<>();
    public ObservableField<String> agreedPrice = new ObservableField<>();
    public ObservableField<Boolean> callMade = new ObservableField<>();
    public ObservableField<Trip> tripObs = new ObservableField<>();
    public ObservableField<String> photo = new ObservableField<>();
    public ObservableField<Double> longitude = new ObservableField<>();
    public ObservableField<Double> latitude = new ObservableField<>();
    public ObservableField<String> firstCollectionDate = new ObservableField<>();
    public ObservableField<String> lastCollectionDate = new ObservableField<>();

    private BookingRepository bookingRepository;

    @Inject
    public BookingViewModel(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Trip trip() {
        Trip trip = new Trip();
        trip.setCargoType(cargoType.get());
        trip.setUnits(Double.valueOf(numberUnits.get()));
        trip.setWeight(Double.valueOf(weight.get()));
        trip.setCollectionPointName(collectionPoint.get());
        trip.setDropOffPointName(dropOffPoint.get());
        trip.setId(tripObs.get().getId());
        return trip;
    }

    public void createBooking() {
        bookingRepository.createBooking(trip(), photo.get());
    }

    private FilterTripsRequest request() {
        FilterTripsRequest request = new FilterTripsRequest();
        request.setCollectionPoint(collectionPoint.get());
        request.setDropOffPoint(dropOffPoint.get());
        request.setLatitude(latitude.get());
        request.setLongitude(longitude.get());
        return request;
    }

    public LiveData<List<Trip>> filterTrips() {
        return bookingRepository.filterTrips(request());
    }

    public LiveData<List<Trip>> fetchBookings() {
        return bookingRepository.fetchBookings();
    }

    public void populateDetails(Trip trip) {
        Double tonnes = trip.getUnits() * trip.getWeight();
        cargoPictureUrl.set(trip.getCargoPictureUrl());
        cargoType.set(trip.getCargoType());
        weight.set(String.valueOf(trip.getWeight()));
        numberUnits.set(String.valueOf(trip.getUnits()));
        tonage.set(String.valueOf(tonnes));
        collectionPoint.set(trip.getCollectionPoint().getName());
        dropOffPoint.set(trip.getDropOffPoint().getName());
        firstCollectionDate.set(formatDate(trip.getFirstAvailableDate()));
        lastCollectionDate.set(formatDate(trip.getLastAvailableDate()));
        cargoMover.set(trip.getCargoMover().getCompanyName());
        telephone.set(trip.getCargoMover().getPhoneNumber());
        email.set(trip.getCargoMover().getEmail());

    }
}
