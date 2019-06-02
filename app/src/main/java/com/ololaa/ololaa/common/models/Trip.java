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

    public int _id;
    @ColumnInfo(name = "id")

    public Long id;
    @ColumnInfo(name = "truck_id")

    public Long truck_id;
    @ColumnInfo(name = "driver_id")

    public Long driver_id;
    @ColumnInfo(name = "first_available_date")

    public Date firstAvailableDate;
    @ColumnInfo(name = "last_available_date")

    public Date lastAvailableDate;
    @ColumnInfo(name = "available_tonage")

    public Double availableTonage;
    @Ignore

    public AppUser cargoMover;
    @ColumnInfo(name = "cargo_mover_name")
    public String cargoMoverName;
    @ColumnInfo(name = "cargo_mover_number")
    public String cargoMoverNumber;

    @ColumnInfo(name = "cargo_type")
    public String cargoType;
    @ColumnInfo(name = "transport_fees")
    public Double transportFees;
    @ColumnInfo(name = "is_available")

    public Boolean isAvailable = true;
    @ColumnInfo(name = "cargo_picture_url")

    public String cargoPictureUrl;
    @ColumnInfo(name = "trip_start_name")

    public String tripStartName;
    @ColumnInfo(name = "trip_end_name")

    public String tripEndName;
    @ColumnInfo(name = "collection_point_name")

    public String collectionPointName;
    @ColumnInfo(name = "drop_off_point_name")

    public String dropOffPointName;

    @ColumnInfo(name = "units")
    Double units;

    @ColumnInfo(name = "weight")
    Double weight;


    @Ignore
    public Location collectionPoint;
    @Ignore

    public Location dropOffPoint;
    @Ignore

    public Location tripStart;

    @Ignore
    Truck truck;

    @Ignore
    Driver driver;

    @Ignore

    Location tripDestination;

    @Ignore

    AppUser transporter;

    @Ignore
    Boolean isBooked;


    public String getCargoMoverName() {
        return cargoMoverName;
    }

    public void setCargoMoverName(String cargoMoverName) {
        this.cargoMoverName = cargoMoverName;
    }

    public String getCargoMoverNumber() {
        return cargoMoverNumber;
    }

    public void setCargoMoverNumber(String cargoMoverNumber) {
        this.cargoMoverNumber = cargoMoverNumber;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

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

    public String getTripDesc() {
        return getTripStartName() + "-" + getTripEndName();
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
