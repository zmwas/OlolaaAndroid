package com.ololaa.ololaa.common.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class AppUser {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")

    int _id;
    @ColumnInfo(name = "id")

    Long id;
    @ColumnInfo(name = "companyName")

    String companyName;
    @ColumnInfo(name = "kraPin")

    String kraPin;
    @ColumnInfo(name = "email")

    String email;
    @ColumnInfo(name = "phoneNumber")

    String phoneNumber;
    @ColumnInfo(name = "firebaseToken")

    String firebaseToken;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getKraPin() {
        return kraPin;
    }

    public void setKraPin(String kraPin) {
        this.kraPin = kraPin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
