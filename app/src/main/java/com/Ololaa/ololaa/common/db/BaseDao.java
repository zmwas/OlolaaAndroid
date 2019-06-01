package com.ololaa.ololaa.common.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertAll(List<T> objects);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(T object);

    @Update
    void update(T object);

    @Delete
    void delete(T object);

}
