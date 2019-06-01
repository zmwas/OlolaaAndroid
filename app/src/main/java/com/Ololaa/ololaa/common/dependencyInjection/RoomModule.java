package com.ololaa.ololaa.common.dependencyInjection;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.ololaa.ololaa.OlolaaApp;
import com.ololaa.ololaa.common.db.AppDatabase;
import com.ololaa.ololaa.common.db.DriverDao;
import com.ololaa.ololaa.common.db.LocationDao;
import com.ololaa.ololaa.common.db.TripDao;
import com.ololaa.ololaa.common.db.TruckDao;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    @Provides
    public AppDatabase database(OlolaaApp application) {
        RoomDatabase.Builder<AppDatabase> databaseBuilder = Room.databaseBuilder(application, AppDatabase.class, "oloola.db");
        return databaseBuilder.build();
    }

    @Provides
    public TruckDao truckDao(AppDatabase appDatabase) {
        return appDatabase.truckDao();
    }
    @Provides
    public TripDao tripDao(AppDatabase appDatabase) {
        return appDatabase.tripDao();
    }

    @Provides
    public DriverDao driverDao(AppDatabase appDatabase) {
        return appDatabase.driverDao();
    }

    @Provides
    public LocationDao locationDao(AppDatabase appDatabase) {
        return appDatabase.locationDao();
    }

}
