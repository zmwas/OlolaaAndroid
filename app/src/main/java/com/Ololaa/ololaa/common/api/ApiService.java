package com.ololaa.ololaa.common.api;

import com.ololaa.ololaa.common.models.AppUser;
import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.models.Truck;
import com.ololaa.ololaa.common.requests.CreateTripRequest;
import com.ololaa.ololaa.common.requests.CreateUserRequest;
import com.ololaa.ololaa.common.requests.FilterTripsRequest;
import com.ololaa.ololaa.common.requests.LoginRequest;
import com.ololaa.ololaa.common.requests.UpdateFirebaseTokenRequest;
import com.ololaa.ololaa.common.requests.UpdatePriceRequest;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiService {
    @POST("/v1/auth/signup")
    Call<AppUser> signUp(@Body CreateUserRequest request);

    @POST("/v1/auth/login")
    Call<AppUser> login(@Body LoginRequest request);

    @POST("/v1/trip")
    Call<Trip> createTrip(@Body CreateTripRequest request);

    @POST("/v1/booking")
    Call<Trip> createBooking(@Part("file") RequestBody file,
                             @Part("cargoType") RequestBody cargoType,
                             @Part("collectionPoint") RequestBody collectionPoint,
                             @Part("dropOffPoint") RequestBody dropOffPoint,
                             @Part("tripId") RequestBody tripId
    );

    @POST("/v1/truck")
    Call<Truck> createTruck(@Part("photo") RequestBody photo,
                            @Part("sticker") RequestBody sticker,
                            @Part("licensePlateNumber") RequestBody licensePlateNumber,
                            @Part("driverId") RequestBody driverId,
                            @Part("isTrailer") RequestBody isTrailer,
                            @Part("availableTonage") RequestBody availableTonage,
                            @Part("ntsaCertificateNumber") RequestBody ntsaCertificateNumber,
                            @Part("transporterId") RequestBody transporterId


    );

    @POST("/v1/truck")
    Call<Driver> createDriver(@Part("photo") RequestBody photo,
                              @Part("sticker") RequestBody sticker,
                              @Part("licensePlateNumber") RequestBody licensePlateNumber,
                              @Part("driverId") RequestBody driverId,
                              @Part("isTrailer") RequestBody isTrailer,
                              @Part("availableTonage") RequestBody availableTonage,
                              @Part("ntsaCertificateNumber") RequestBody ntsaCertificateNumber,
                              @Part("transporterId") RequestBody transporterId


    );

    @GET("/truck")
    Call<List<Truck>> fetchTrucks();

    @GET("/driver")
    Call<List<Driver>> fetchDrivers();

    @GET("/booking")
    Call<List<Trip>> fetchBookings();

    @POST("/filterTrip")
    Call<List<Trip>> fetchTripsForLocation(@Body FilterTripsRequest request);

    @PUT("/user")
    Call<AppUser> updateFirebaseToken(@Body UpdateFirebaseTokenRequest request);

    @PUT("/user")
    Call<Trip> updateTripPrice(@Body UpdatePriceRequest request);


}
