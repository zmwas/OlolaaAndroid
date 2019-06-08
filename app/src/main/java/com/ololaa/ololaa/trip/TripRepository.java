package com.ololaa.ololaa.trip;

import com.ololaa.ololaa.common.SingleLiveEvent;
import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TripDao;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.requests.CreateTripRequest;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripRepository {
    private ApiService apiService;
    private TripDao tripDao;
    private ExecutorService executorService;
    public SingleLiveEvent<Boolean> showProgressDialog = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> showSuccessDialog = new SingleLiveEvent<>();

    @Inject
    public TripRepository(ApiService apiService, TripDao tripDao, ExecutorService executorService) {
        this.apiService = apiService;
        this.tripDao = tripDao;
        this.executorService = executorService;
    }


    void createTrip(CreateTripRequest tripRequest) {
        showProgressDialog.setValue(true);
        Call<Trip> createTrip = apiService.createTrip(tripRequest);
        createTrip.enqueue(new Callback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {
                showProgressDialog.setValue(false);
                if (response.isSuccessful()) {
                    showSuccessDialog.setValue(true);
                    executorService.execute(() -> tripDao.insert(response.body()));
                } else {
                    showSuccessDialog.setValue(false);

                }
            }

            @Override
            public void onFailure(Call<Trip> call, Throwable t) {

            }
        });
    }
}

