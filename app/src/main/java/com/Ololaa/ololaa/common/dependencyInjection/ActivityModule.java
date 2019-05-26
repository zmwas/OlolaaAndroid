package com.ololaa.ololaa.common.dependencyInjection;

import com.ololaa.ololaa.MainActivity;
import com.ololaa.ololaa.booking.BookingDetailsActivity;
import com.ololaa.ololaa.booking.CreateBookingActivity;
import com.ololaa.ololaa.booking.FilterTripsActivity;
import com.ololaa.ololaa.booking.ListTripsActivity;
import com.ololaa.ololaa.driver.CreateDriverActivity;
import com.ololaa.ololaa.driver.DriverDetailActivity;
import com.ololaa.ololaa.driver.DriverListActivity;
import com.ololaa.ololaa.trip.CreateTripActivity;
import com.ololaa.ololaa.trip.TripDetailActivity;
import com.ololaa.ololaa.trip.TripListActivity;
import com.ololaa.ololaa.truck.CreateTruckActivity;
import com.ololaa.ololaa.truck.TruckDetailActivity;
import com.ololaa.ololaa.truck.TruckListActivity;
import com.ololaa.ololaa.user.LoginActivity;
import com.ololaa.ololaa.user.RegisterUserActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentsModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector()
    abstract BookingDetailsActivity bookingDetailsActivity();


    @ContributesAndroidInjector()
    abstract CreateBookingActivity createBookingActivity();


    @ContributesAndroidInjector()
    abstract FilterTripsActivity filterTripsActivity();


    @ContributesAndroidInjector()
    abstract ListTripsActivity listTripsActivity();


    @ContributesAndroidInjector()
    abstract CreateDriverActivity createDriverActivity();


    @ContributesAndroidInjector()
    abstract DriverDetailActivity driverDetailActivity();



    @ContributesAndroidInjector()
    abstract DriverListActivity driverListActivity();


    @ContributesAndroidInjector()
    abstract CreateTripActivity createTripActivity();


    @ContributesAndroidInjector()
    abstract TripDetailActivity tripDetailActivity();


    @ContributesAndroidInjector()
    abstract TripListActivity tripListActivity();


    @ContributesAndroidInjector()
    abstract CreateTruckActivity createTruckActivity();


    @ContributesAndroidInjector()
    abstract TruckDetailActivity truckDetailActivity();


    @ContributesAndroidInjector()
    abstract TruckListActivity truckListActivity();


    @ContributesAndroidInjector()
    abstract RegisterUserActivity registerUserActivity();


    @ContributesAndroidInjector()
    abstract LoginActivity loginActivity();
}
