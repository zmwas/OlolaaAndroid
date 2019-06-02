package com.ololaa.ololaa.common.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.ololaa.ololaa.common.models.Truck;

import java.util.List;

@Dao
public interface TruckDao extends BaseDao<Truck> {
    @Query("SELECT * FROM truck")
    List<Truck> fetchTrucks();

    @Query("SELECT * FROM truck WHERE id=:id")
    Truck getById(Long id);
}
