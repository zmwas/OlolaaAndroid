package com.Ololaa.ololaa.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.Ololaa.ololaa.models.Truck;

@Database(entities = {Truck.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
}
