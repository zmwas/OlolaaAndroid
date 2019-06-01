package com.ololaa.ololaa.booking;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

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

    @Inject
    public BookingRepository(ApiService apiService, TripDao tripDao) {
        this.apiService = apiService;
        this.tripDao = tripDao;
    }


    void createBooking(Trip trip, String cargo) {
        MediaType image = MediaType.parse("image/*");
        MediaType text = MediaType.parse("text/plain");

        File cargoPhoto = new File(cargo);
        RequestBody requestBodyCargo = RequestBody.create(image, cargoPhoto);
        RequestBody cargoType = RequestBody.create(text, trip.getCargoType());
        RequestBody collectionPoint = RequestBody.create(text, trip.getCollectionPointName());
        RequestBody dropOffPoint = RequestBody.create(text, trip.getDropOffPointName());
        RequestBody units = RequestBody.create(text, String.valueOf(trip.getUnits()));
        RequestBody weight = RequestBody.create(text, String.valueOf(trip.getWeight()));
        RequestBody tripId = RequestBody.create(text, String.valueOf(trip.getId()));
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", cargoPhoto.getName(), requestBodyCargo);

        Call<Trip> createBooking = apiService.createBooking(photo, cargoType, collectionPoint, dropOffPoint, units, weight, tripId);
        createBooking.enqueue(new Callback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {
                if (response.isSuccessful()) {
                    tripDao.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<Trip> call, Throwable t) {

            }
        });

    }

    LiveData<List<Trip>> filterTrips(FilterTripsRequest request) {
        Call<List<Trip>> fetchTripsForLocation = apiService.fetchTripsForLocation(request);
        fetchTripsForLocation.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.isSuccessful()) {
                    trips.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

            }
        });
        return trips;
    }

    public LiveData<List<Trip>> fetchBookings() {
        Call<List<Trip>> fetchBookings = apiService.fetchBookings();

        fetchBookings.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.isSuccessful()) {
                    trips.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

            }
        });
        return trips;
    }
}
