package com.ololaa.ololaa.booking;

import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TripDao;
import com.ololaa.ololaa.common.models.Trip;

import java.io.File;

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

    @Inject
    public BookingRepository(ApiService apiService, TripDao tripDao) {
        this.apiService = apiService;
        this.tripDao = tripDao;
    }


    public void createBooking(Trip trip, String cargo) {
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
}
