package com.ololaa.ololaa.common.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Trip {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")

    int _id;
    @ColumnInfo(name = "id")

    Long id;
    @ColumnInfo(name = "truck_id")

    Long truck_id;
    @ColumnInfo(name = "driver_id")

    Long driver_id;
    @ColumnInfo(name = "first_available_date")

    Date firstAvailableDate;
    @ColumnInfo(name = "last_available_date")

    Date lastAvailableDate;
    @ColumnInfo(name = "available_tonage")

    Double availableTonage;
    @Ignore

    AppUser cargoMover;
    @ColumnInfo(name = "cargo_mover_name")
    String cargoMoverName;
    @ColumnInfo(name = "cargo_mover_number")
    String cargoMoverNumber;

    @ColumnInfo(name = "cargo_type")
    String cargoType;
    @ColumnInfo(name = "transport_fees")
    Double transportFees;
    @ColumnInfo(name = "is_available")

    Boolean isAvailable = true;
    @ColumnInfo(name = "cargo_picture_url")

    String cargoPictureUrl;
    @ColumnInfo(name = "trip_start_name")

    String tripStartName;
    @ColumnInfo(name = "trip_end_name")

    String tripEndName;
    @ColumnInfo(name = "collection_point_name")

    String collectionPointName;
    @ColumnInfo(name = "drop_off_point_name")

    String dropOffPointName;

    @Ignore
    Location collectionPoint;
    @Ignore

    Location dropOffPoint;
    @Ignore

    Location tripStart;
    @Ignore

    Location tripDestination;

    @Ignore

    AppUser transporter;

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

    public Long getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(Long truck_id) {
        this.truck_id = truck_id;
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
    }

    public Date getFirstAvailableDate() {
        return firstAvailableDate;
    }

    public void setFirstAvailableDate(Date firstAvailableDate) {
        this.firstAvailableDate = firstAvailableDate;
    }

    public Date getLastAvailableDate() {
        return lastAvailableDate;
    }

    public void setLastAvailableDate(Date lastAvailableDate) {
        this.lastAvailableDate = lastAvailableDate;
    }

    public Double getAvailableTonage() {
        return availableTonage;
    }

    public void setAvailableTonage(Double availableTonage) {
        this.availableTonage = availableTonage;
    }

    public AppUser getCargoMover() {
        return cargoMover;
    }

    public void setCargoMover(AppUser cargoMover) {
        this.cargoMover = cargoMover;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public Double getTransportFees() {
        return transportFees;
    }

    public void setTransportFees(Double transportFees) {
        this.transportFees = transportFees;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getCargoPictureUrl() {
        return cargoPictureUrl;
    }

    public void setCargoPictureUrl(String cargoPictureUrl) {
        this.cargoPictureUrl = cargoPictureUrl;
    }

    public String getTripStartName() {
        return tripStartName;
    }

    public void setTripStartName(String tripStartName) {
        this.tripStartName = tripStartName;
    }

    public String getTripEndName() {
        return tripEndName;
    }

    public void setTripEndName(String tripEndName) {
        this.tripEndName = tripEndName;
    }

    public String getCollectionPointName() {
        return collectionPointName;
    }

    public void setCollectionPointName(String collectionPointName) {
        this.collectionPointName = collectionPointName;
    }

    public String getDropOffPointName() {
        return dropOffPointName;
    }

    public void setDropOffPointName(String dropOffPointName) {
        this.dropOffPointName = dropOffPointName;
    }

    public Location getCollectionPoint() {
        return collectionPoint;
    }

    public void setCollectionPoint(Location collectionPoint) {
        this.collectionPoint = collectionPoint;
    }

    public Location getDropOffPoint() {
        return dropOffPoint;
    }

    public void setDropOffPoint(Location dropOffPoint) {
        this.dropOffPoint = dropOffPoint;
    }

    public Location getTripStart() {
        return tripStart;
    }

    public void setTripStart(Location tripStart) {
        this.tripStart = tripStart;
    }

    public Location getTripDestination() {
        return tripDestination;
    }

    public void setTripDestination(Location tripDestination) {
        this.tripDestination = tripDestination;
    }

    public AppUser getTransporter() {
        return transporter;
    }

    public void setTransporter(AppUser transporter) {
        this.transporter = transporter;
    }
}
