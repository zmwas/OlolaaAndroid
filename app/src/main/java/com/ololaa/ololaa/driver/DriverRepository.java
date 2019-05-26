package com.ololaa.ololaa.driver;

import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.DriverDao;

import javax.inject.Inject;

public class DriverRepository {
    ApiService apiService;
    DriverDao driverDao;

    @Inject
    public DriverRepository(ApiService apiService, DriverDao driverDao) {
        this.apiService = apiService;
        this.driverDao = driverDao;
    }
}
