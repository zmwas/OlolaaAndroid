package com.ololaa.ololaa.common.dependencyInjection;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;


import com.ololaa.ololaa.common.db.AppDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    @Provides
    public AppDatabase database(Application application) {
        RoomDatabase.Builder<AppDatabase> databaseBuilder = Room.databaseBuilder(application, AppDatabase.class, "oloola.db");
        return databaseBuilder.build();
    }

}
