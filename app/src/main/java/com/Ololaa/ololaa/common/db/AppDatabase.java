package com.ololaa.ololaa.common.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ololaa.ololaa.common.models.Truck;

@Database(entities = {Truck.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
}
