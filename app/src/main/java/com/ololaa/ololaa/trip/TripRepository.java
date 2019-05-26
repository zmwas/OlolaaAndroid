package com.ololaa.ololaa.trip;

import com.ololaa.ololaa.common.api.ApiService;

import javax.inject.Inject;

public class TripRepository {
    ApiService apiService;

    @Inject
    public TripRepository(ApiService apiService) {
        this.apiService = apiService;
    }
}
