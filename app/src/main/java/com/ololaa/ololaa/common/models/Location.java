package com.ololaa.ololaa.common.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
@Entity
public class Location {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")

    int _id;
    @ColumnInfo(name = "id")

    Long id;
    @ColumnInfo(name = "name")

    String name;
    @ColumnInfo(name = "latitude")

    Double latitude;
    @ColumnInfo(name = "longitude")

    Double longitude;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
