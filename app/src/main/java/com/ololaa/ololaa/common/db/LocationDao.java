package com.ololaa.ololaa.common.db;

import android.arch.persistence.room.Dao;

import com.ololaa.ololaa.common.models.Location;

@Dao
public interface LocationDao extends BaseDao<Location> {
}
