package com.ololaa.ololaa.common.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ololaa.ololaa.common.models.AppUser;
import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.common.models.Location;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.models.Truck;

@Database(entities = {AppUser.class, Driver.class, Truck.class, Trip.class, Location.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
}
