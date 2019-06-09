package com.ololaa.ololaa.booking;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.ololaa.ololaa.common.SingleLiveEvent;
import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TripDao;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.requests.FilterTripsRequest;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingRepository {

    ApiService apiService;
    TripDao tripDao;
    MutableLiveData<List<Trip>> trips = new MutableLiveData<>();
    MutableLiveData<Trip> trip = new MutableLiveData<>();
    public SingleLiveEvent<Boolean> showProgressDialog = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> showSuccessDialog = new SingleLiveEvent<>();

    @Inject
    public BookingRepository(ApiService apiService, TripDao tripDao) {
        this.apiService = apiService;
        this.tripDao = tripDao;
    }


    void createBooking(Trip trip, String cargo) {
        showProgressDialog.setValue(true);
        MediaType image = MediaType.parse("image/*");
        MediaType text = MediaType.parse("text/plain");

        File cargoPhoto = new File(cargo.replace("file://", ""));
        RequestBody requestBodyCargo = RequestBody.create(image, cargoPhoto);
        RequestBody cargoType = RequestBody.create(text, trip.getCargoType());
        RequestBody collectionPoint = RequestBody.create(text, trip.getCollectionPointName());
        RequestBody dropOffPoint = RequestBody.create(text, trip.getDropOffPointName());
        RequestBody units = RequestBody.create(text, String.valueOf(trip.getUnits()));
        RequestBody weight = RequestBody.create(text, String.valueOf(trip.getWeight()));
        RequestBody tripId = RequestBody.create(text, String.valueOf(trip.getId()));
        RequestBody firstCollectionDate = RequestBody.create(text, String.valueOf(trip.getFirstCollectionDate()));
        RequestBody lastCollectionDate = RequestBody.create(text, String.valueOf(trip.getLastCollectionDate()));

        MultipartBody.Part photo = MultipartBody.Part.createFormData("file", cargoPhoto.getName(), requestBodyCargo);

        Call<Trip> createBooking = apiService.createBooking(photo, cargoType, collectionPoint, dropOffPoint, units, weight, tripId, firstCollectionDate, lastCollectionDate);
        createBooking.enqueue(new Callback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {
                if (response.isSuccessful()) {
                    showProgressDialog.setValue(false);
                    showSuccessDialog.setValue(true);

                } else {
                    showSuccessDialog.setValue(false);
                    showProgressDialog.setValue(false);

                }
            }

            @Override
            public void onFailure(Call<Trip> call, Throwable t) {
                showProgressDialog.setValue(false);

            }
        });

    }

    LiveData<List<Trip>> filterTrips(FilterTripsRequest request) {
        showProgressDialog.setValue(true);

        Call<List<Trip>> fetchTripsForLocation = apiService.fetchTripsForLocation(request);
        fetchTripsForLocation.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.isSuccessful()) {
                    showProgressDialog.setValue(false);
                    trips.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                showProgressDialog.setValue(false);

            }
        });
        return trips;
    }

    LiveData<List<Trip>> fetchBookings() {
        showProgressDialog.setValue(true);
        Call<List<Trip>> fetchBookings = apiService.fetchBookings();

        fetchBookings.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.isSuccessful()) {
                    showProgressDialog.setValue(false);

                    trips.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                showProgressDialog.setValue(false);

            }
        });
        return trips;
    }


    public LiveData<Trip> fetchBooking(Long id) {
        showProgressDialog.setValue(true);

        Call<Trip> fetchBooking = apiService.fetchBooking(id);
        fetchBooking.enqueue(new Callback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {
                if (response.isSuccessful()) {
                    showProgressDialog.setValue(false);

                    trip.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Trip> call, Throwable t) {
                showProgressDialog.setValue(false);

            }
        });
        return trip;
    }
}
