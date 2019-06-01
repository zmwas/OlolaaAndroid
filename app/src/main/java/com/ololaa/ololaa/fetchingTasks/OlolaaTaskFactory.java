package com.ololaa.ololaa.fetchingTasks;

import com.levibostian.wendy.service.PendingTask;
import com.levibostian.wendy.service.PendingTasksFactory;
import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.DriverDao;
import com.ololaa.ololaa.common.db.TripDao;
import com.ololaa.ololaa.common.db.TruckDao;

import static com.ololaa.ololaa.Constants.FETCH_DRIVERS;
import static com.ololaa.ololaa.Constants.FETCH_TRIPS;
import static com.ololaa.ololaa.Constants.FETCH_TRUCKS;

public class OlolaaTaskFactory implements PendingTasksFactory {
    public ApiService apiService;
    private TruckDao truckDao;
    private DriverDao driverDao;
    private TripDao tripDao;

    public OlolaaTaskFactory(ApiService apiService, TruckDao truckDao, DriverDao driverDao, TripDao tripDao) {
        this.apiService = apiService;
        this.truckDao = truckDao;
        this.driverDao = driverDao;
        this.tripDao = tripDao;
    }

    @Override
    public PendingTask getTask(String tag) {
        PendingTask task = null;
        switch (tag) {
            case FETCH_DRIVERS:
                task = FetchDriversTask.getInstance(apiService, driverDao);
                break;
            case FETCH_TRUCKS:
                task = FetchTrucksTask.getInstance(apiService, truckDao);
                break;
            case FETCH_TRIPS:
                task = FetchTripsTask.getInstance(apiService, tripDao);
        }
        return task;
    }
}
