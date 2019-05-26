package com.ololaa.ololaa.booking;

import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TripDao;

import javax.inject.Inject;

public class BookingRepository {

    ApiService apiService;
    TripDao tripDao;

    @Inject
    public BookingRepository(ApiService apiService, TripDao tripDao) {
        this.apiService = apiService;
        this.tripDao = tripDao;
    }
}
