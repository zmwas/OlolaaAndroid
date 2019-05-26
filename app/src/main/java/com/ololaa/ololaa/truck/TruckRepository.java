package com.ololaa.ololaa.truck;

import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TruckDao;

import javax.inject.Inject;

public class TruckRepository {
    ApiService apiService;
    TruckDao truckDao;

    @Inject
    public TruckRepository(ApiService apiService, TruckDao truckDao) {
        this.apiService = apiService;
        this.truckDao = truckDao;
    }
}
