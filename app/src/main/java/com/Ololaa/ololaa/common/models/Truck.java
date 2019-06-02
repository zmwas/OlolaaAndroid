package com.ololaa.ololaa.common.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Truck {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int _id;

    public Long getId() {
        return id;
    }

    @ColumnInfo(name = "id")

    public Long id;
    @ColumnInfo(name = "license_plate_number")

    public String licensePlateNumber;

    @ColumnInfo(name = "driver_id")

    public Long driverId;
    @ColumnInfo(name = "is_trailer")

    public boolean isTrailer;
    @ColumnInfo(name = "available_tonage")

    public Double availableTonage;
    @ColumnInfo(name = "photo_Url")

    public String photoUrl;
    @ColumnInfo(name = "insurance_sticker")

    public String insuranceSticker;
    @ColumnInfo(name = "ntsa_certificate_number")

    public String ntsaCertificateNumber;

    @ColumnInfo(name = "truck_type")
    public String truckType;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public boolean isTrailer() {
        return isTrailer;
    }

    public void setTrailer(boolean trailer) {
        isTrailer = trailer;
    }

    public Double getAvailableTonage() {
        return availableTonage;
    }

    public void setAvailableTonage(Double availableTonage) {
        this.availableTonage = availableTonage;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getInsuranceSticker() {
        return insuranceSticker;
    }

    public void setInsuranceSticker(String insuranceSticker) {
        this.insuranceSticker = insuranceSticker;
    }

    public String getNtsaCertificateNumber() {
        return ntsaCertificateNumber;
    }

    public void setNtsaCertificateNumber(String ntsaCertificateNumber) {
        this.ntsaCertificateNumber = ntsaCertificateNumber;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }
}
