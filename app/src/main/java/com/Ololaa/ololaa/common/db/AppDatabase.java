package com.ololaa.ololaa.common.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.ololaa.ololaa.common.models.AppUser;
import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.common.models.Location;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.models.Truck;

@Database(entities = {AppUser.class, Driver.class, Truck.class, Trip.class, Location.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TruckDao truckDao();
    public abstract TripDao tripDao();
    public abstract DriverDao driverDao();
    public abstract LocationDao locationDao();
}
