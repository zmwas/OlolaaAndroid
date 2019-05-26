package com.ololaa.ololaa.common.requests;

public class CreateTripRequest {
    Long truckId;

    Long driverId;

    String firstAvailableDate;

    String lastAvailableDate;

    Double availableTonage;

    String tripStart;

    String tripDestination;

    public Long getTruckId() {
        return truckId;
    }

    public void setTruckId(Long truckId) {
        this.truckId = truckId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getFirstAvailableDate() {
        return firstAvailableDate;
    }

    public void setFirstAvailableDate(String firstAvailableDate) {
        this.firstAvailableDate = firstAvailableDate;
    }

    public String getLastAvailableDate() {
        return lastAvailableDate;
    }

    public void setLastAvailableDate(String lastAvailableDate) {
        this.lastAvailableDate = lastAvailableDate;
    }

    public Double getAvailableTonage() {
        return availableTonage;
    }

    public void setAvailableTonage(Double availableTonage) {
        this.availableTonage = availableTonage;
    }

    public String getTripStart() {
        return tripStart;
    }

    public void setTripStart(String tripStart) {
        this.tripStart = tripStart;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }
}
