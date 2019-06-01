package com.ololaa.ololaa.booking;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.requests.FilterTripsRequest;

import java.util.List;

import javax.inject.Inject;

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
    public ObservableField<String> firstAvailableDate = new ObservableField<>();
    public ObservableField<String> errorFirstAvailableDate = new ObservableField<>();
    public ObservableField<String> lastAvailableDate = new ObservableField<>();
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

    public FilterTripsRequest request() {
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
}
