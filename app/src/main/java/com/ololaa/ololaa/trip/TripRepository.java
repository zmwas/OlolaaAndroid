package com.ololaa.ololaa.trip;

import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TripDao;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.requests.CreateTripRequest;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripRepository {
    private ApiService apiService;
    private TripDao tripDao;

    @Inject
    public TripRepository(ApiService apiService, TripDao tripDao) {
        this.apiService = apiService;
        this.tripDao = tripDao;
    }


    void createTrip(CreateTripRequest tripRequest) {
        Call<Trip> createTrip = apiService.createTrip(tripRequest);
        createTrip.enqueue(new Callback<Trip>() {
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

