package com.ololaa.ololaa.common.db;

import android.arch.persistence.room.Dao;

import com.ololaa.ololaa.common.models.Trip;

@Dao
public interface TripDao extends BaseDao<Trip> {
}
