package com.ololaa.ololaa.common.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Truck {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;
    @ColumnInfo(name = "id")

    Long id;
    @ColumnInfo(name = "license_plate_number")

    String licensePlateNumber;

    @ColumnInfo(name = "driver_id")

    Long driverId;
    @ColumnInfo(name = "is_trailer")

    boolean isTrailer;
    @ColumnInfo(name = "available_tonage")

    Double availableTonage;
    @ColumnInfo(name = "photo_Url")

    String photoUrl;
    @ColumnInfo(name = "insurance_sticker")

    String insuranceSticker;
    @ColumnInfo(name = "ntsa_certificate_number")

    String ntsaCertificateNumber;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

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
}
