package com.ololaa.ololaa.truck;

import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TruckDao;
import com.ololaa.ololaa.common.models.Truck;

import java.io.File;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TruckRepository {
    private ApiService apiService;
    private TruckDao truckDao;

    @Inject
    public TruckRepository(ApiService apiService, TruckDao truckDao) {
        this.apiService = apiService;
        this.truckDao = truckDao;
    }


    void createTruck(Truck truck, String truckImage, String insuranceImage) {
        MediaType image = MediaType.parse("image/*");
        MediaType text = MediaType.parse("text/plain");

        File truckPhoto = new File(truckImage);
        File insuranceSticker = new File(insuranceImage);
        Long driver = truck.getDriverId() == null ? Long.valueOf(0) : truck.getDriverId();

        RequestBody requestBodyTruck = RequestBody.create(image, truckPhoto);
        RequestBody requestBodySticker = RequestBody.create(image, insuranceSticker);
        RequestBody licensePlateNumber = RequestBody.create(text, truck.getLicensePlateNumber());
        RequestBody driverId = RequestBody.create(text, String.valueOf(driver));
        RequestBody isTrailer = RequestBody.create(text, String.valueOf(truck.isTrailer()));
        RequestBody availableTonage = RequestBody.create(text, String.valueOf(truck.getAvailableTonage()));
        RequestBody ntsaCertificateNumber = RequestBody.create(text, truck.getNtsaCertificateNumber());


        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", truckPhoto.getName(), requestBodyTruck);
        MultipartBody.Part sticker = MultipartBody.Part.createFormData("sticker", truckPhoto.getName(), requestBodySticker);

        Call<Truck> createTruck = apiService.
                createTruck(photo, sticker, licensePlateNumber, driverId, isTrailer,
                        availableTonage, ntsaCertificateNumber);

        createTruck.enqueue(new Callback<Truck>() {
            @Override
            public void onResponse(Call<Truck> call, Response<Truck> response) {
                if (response.isSuccessful()) {
                    truckDao.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<Truck> call, Throwable t) {

            }
        });
    }
}
