package com.ololaa.ololaa.common.api;

import com.ololaa.ololaa.common.models.AppUser;
import com.ololaa.ololaa.common.models.Driver;
import com.ololaa.ololaa.common.models.Trip;
import com.ololaa.ololaa.common.models.Truck;
import com.ololaa.ololaa.common.requests.AuthWrapper;
import com.ololaa.ololaa.common.requests.CreateTripRequest;
import com.ololaa.ololaa.common.requests.CreateUserRequest;
import com.ololaa.ololaa.common.requests.FilterTripsRequest;
import com.ololaa.ololaa.common.requests.LoginRequest;
import com.ololaa.ololaa.common.requests.UpdateFirebaseTokenRequest;
import com.ololaa.ololaa.common.requests.UpdatePriceRequest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    @POST("v1/auth/signup")
    Call<AppUser> signUp(@Body CreateUserRequest request);

    @POST("v1/auth/login")
    Call<AuthWrapper> login(@Body LoginRequest request);

    @POST("v1/trip")
    Call<Trip> createTrip(@Body CreateTripRequest request);
    @Multipart
    @POST("v1/booking")
    Call<Trip> createBooking(@Part MultipartBody.Part file,
                             @Part("cargoType") RequestBody cargoType,
                             @Part("collectionPoint") RequestBody collectionPoint,
                             @Part("dropOffPoint") RequestBody dropOffPoint,
                             @Part("units") RequestBody units,
                             @Part("weight") RequestBody weight,
                             @Part("tripId") RequestBody tripId,
                             @Part("firstCollectionDate") RequestBody firstCollectionDate,
                             @Part("lastCollectionDate") RequestBody lastCollectionDate
    );
    @Multipart
    @POST("v1/truck")
    Call<Truck> createTruck(@Part MultipartBody.Part photo,
                            @Part MultipartBody.Part sticker,
                            @Part("licensePlateNumber") RequestBody licensePlateNumber,
                            @Part("driverId") RequestBody driverId,
                            @Part("isTrailer") RequestBody isTrailer,
                            @Part("availableTonage") RequestBody availableTonage,
                            @Part("ntsaCertificateNumber") RequestBody ntsaCertificateNumber
    );
    @Multipart
    @POST("v1/driver")
    Call<Driver> createDriver(@Part MultipartBody.Part photo,
                              @Part("name") RequestBody name,
                              @Part("drivingLicense") RequestBody drivingLicense,
                              @Part("drivingLicenseType") RequestBody drivingLicenseType,
                              @Part("idNumber") RequestBody idNumber
    );

    @GET("v1/truck")
    Call<List<Truck>> fetchTrucks();

    @GET("v1/driver")
    Call<List<Driver>> fetchDrivers();

    @GET("v1/booking")
    Call<List<Trip>> fetchBookings();

    @POST("v1/filterTrip")
    Call<List<Trip>> fetchTripsForLocation(@Body FilterTripsRequest request);

    @PUT("v1/user")
    Call<AppUser> updateFirebaseToken(@Body UpdateFirebaseTokenRequest request);

    @PUT("v1/trip")
    Call<Trip> updateTripPrice(@Body UpdatePriceRequest request);

    @GET("v1/trip/{id}")
    Call<Trip> fetchBooking(@Path("id")Long id);

}
