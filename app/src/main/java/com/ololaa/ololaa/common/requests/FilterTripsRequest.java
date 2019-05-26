package com.ololaa.ololaa.common.requests;

public class FilterTripsRequest {
    String collectionPoint;
    String dropOffPoint;
    Double latitude;
    Double longitude;

    public String getCollectionPoint() {
        return collectionPoint;
    }

    public void setCollectionPoint(String collectionPoint) {
        this.collectionPoint = collectionPoint;
    }

    public String getDropOffPoint() {
        return dropOffPoint;
    }

    public void setDropOffPoint(String dropOffPoint) {
        this.dropOffPoint = dropOffPoint;
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
