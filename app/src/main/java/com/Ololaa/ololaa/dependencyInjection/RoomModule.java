package com.Ololaa.ololaa.dependencyInjection;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;


import com.Ololaa.ololaa.db.AppDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    @Provides
    public AppDatabase database(Application application) {
        RoomDatabase.Builder<AppDatabase> databaseBuilder = Room.databaseBuilder(application, AppDatabase.class, "bookmarks.db");
        return databaseBuilder.build();
    }

}
