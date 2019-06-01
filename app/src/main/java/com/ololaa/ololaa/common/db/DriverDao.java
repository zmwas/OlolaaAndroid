package com.ololaa.ololaa.common.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.ololaa.ololaa.common.models.Driver;

import java.util.List;

@Dao
public interface DriverDao extends BaseDao<Driver> {
    @Query("SELECT * FROM driver")
    List<Driver> fetchDrivers();
}
