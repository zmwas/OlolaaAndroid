package com.ololaa.ololaa.fetchingTasks;

import com.levibostian.wendy.service.PendingTask;
import com.levibostian.wendy.types.PendingTaskResult;
import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TripDao;

import static com.ololaa.ololaa.Constants.FETCH_TRIPS;

public class FetchTripsTask extends PendingTask {
    public ApiService apiService;
    public TripDao tripDao;
    public static FetchTripsTask instance;
    public FetchTripsTask(boolean manuallyRun, String dataId, String groupId, String tag) {
        super(manuallyRun, dataId, groupId, tag);
    }

    public static FetchTripsTask getInstance(ApiService apiService, TripDao tripDao) {
        if (instance == null) {
            instance = new FetchTripsTask(false, "1", "1", FETCH_TRIPS);
            instance.apiService = apiService;
            instance.tripDao = tripDao;
        }
        return instance;
    }

    @Override
    public PendingTaskResult runTask() {
        return null;
    }
}
