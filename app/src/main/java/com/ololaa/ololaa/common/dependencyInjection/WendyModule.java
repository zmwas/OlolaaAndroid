package com.ololaa.ololaa.common.dependencyInjection;

import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.DriverDao;
import com.ololaa.ololaa.common.db.TripDao;
import com.ololaa.ololaa.common.db.TruckDao;
import com.ololaa.ololaa.fetchingTasks.OlolaaTaskFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class, RoomModule.class})
public class WendyModule {

    @Provides
    @Singleton
    public OlolaaTaskFactory factory(ApiService apiService, TripDao tripDao, TruckDao truckDao, DriverDao driverDao) {
        return new OlolaaTaskFactory(apiService, truckDao, driverDao, tripDao);
    }
}
