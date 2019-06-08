package com.ololaa.ololaa.fetchingTasks;

import com.levibostian.wendy.service.PendingTask;
import com.levibostian.wendy.service.Wendy;
import com.levibostian.wendy.types.PendingTaskResult;
import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TruckDao;
import com.ololaa.ololaa.common.models.Truck;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

import static com.ololaa.ololaa.Constants.FETCH_TRUCKS;

public class FetchTrucksTask extends PendingTask {
    public ApiService apiService;
    private TruckDao truckDao;
    public static FetchTrucksTask instance;
    private boolean successful;

    public FetchTrucksTask(boolean manuallyRun, String dataId, String groupId, String tag) {
        super(manuallyRun, dataId, groupId, tag);
    }

    public static FetchTrucksTask getInstance(ApiService apiService, TruckDao truckDao) {
        if (instance == null) {
            instance = new FetchTrucksTask(false, "1", "1", FETCH_TRUCKS);
            instance.apiService = apiService;
            instance.truckDao = truckDao;
        }
        return instance;
    }

    @Override
    public PendingTaskResult runTask() {
        successful = false;
        Wendy.sharedInstance().addTask(this, true);
        Call<List<Truck>> fetchTrucks = apiService.fetchTrucks();
        try {
            List<Truck> trucks = fetchTrucks.execute().body();
            if (trucks != null && trucks.size() > 0) {
                truckDao.insertAll(trucks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return successful ? PendingTaskResult.SUCCESSFUL: PendingTaskResult.FAILED;
    }
}
