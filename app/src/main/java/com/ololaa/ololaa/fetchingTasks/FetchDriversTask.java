package com.ololaa.ololaa.fetchingTasks;

import com.levibostian.wendy.service.PendingTask;
import com.levibostian.wendy.types.PendingTaskResult;
import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.DriverDao;
import com.ololaa.ololaa.common.models.Driver;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

import static com.ololaa.ololaa.Constants.FETCH_DRIVERS;

public class FetchDriversTask extends PendingTask {
    public ApiService apiService;
    public DriverDao driverDao;
    public static FetchDriversTask instance;
    boolean successful;

    public FetchDriversTask(boolean manuallyRun, String dataId, String groupId, String tag) {
        super(manuallyRun, dataId, groupId, tag);
    }

    public static FetchDriversTask getInstance(ApiService apiService, DriverDao driverDao) {
        if (instance == null) {
            instance = new FetchDriversTask(false, "1", "1", FETCH_DRIVERS);
            instance.apiService = apiService;
            instance.driverDao = driverDao;
        }
        return instance;
    }

    @Override
    public PendingTaskResult runTask() {
        successful = false;
        Call<List<Driver>> fetchDrivers = apiService.fetchDrivers();
        try {
            List<Driver> drivers = fetchDrivers.execute().body();
            if (drivers != null && drivers.size() > 0) {
                driverDao.insertAll(drivers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return successful ? PendingTaskResult.SUCCESSFUL : PendingTaskResult.FAILED;
    }
}
