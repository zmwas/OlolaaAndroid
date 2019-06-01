package com.ololaa.ololaa.common.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Driver implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")

    public int _id;

    @ColumnInfo(name = "id")

    public Long id;
    @ColumnInfo(name = "name")

    public String name;
    @ColumnInfo(name = "driving_license")

    public String drivingLicense;
    @ColumnInfo(name = "driving_license_type")

    public String drivingLicenseType;
    @ColumnInfo(name = "id_number")

    public String idNumber;
    @ColumnInfo(name = "passport_photo_url")

    public String passPortPhotoUrl;
    @ColumnInfo(name = "truck_id")

    public Long truckId;

    @Ignore
    public AppUser transporter;


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

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getDrivingLicenseType() {
        return drivingLicenseType;
    }

    public void setDrivingLicenseType(String drivingLicenseType) {
        this.drivingLicenseType = drivingLicenseType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassPortPhotoUrl() {
        return passPortPhotoUrl;
    }

    public void setPassPortPhotoUrl(String passPortPhotoUrl) {
        this.passPortPhotoUrl = passPortPhotoUrl;
    }

    public Long getTruckId() {
        return truckId;
    }

    public void setTruckId(Long truckId) {
        this.truckId = truckId;
    }

    public AppUser getTransporter() {
        return transporter;
    }

    public void setTransporter(AppUser transporter) {
        this.transporter = transporter;
    }
}
