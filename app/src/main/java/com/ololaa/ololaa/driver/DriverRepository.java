package com.ololaa.ololaa.driver;

import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.DriverDao;
import com.ololaa.ololaa.common.models.Driver;

import java.io.File;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverRepository {
    ApiService apiService;
    DriverDao driverDao;

    @Inject
    public DriverRepository(ApiService apiService, DriverDao driverDao) {
        this.apiService = apiService;
        this.driverDao = driverDao;
    }


    void createDriver(Driver driver, String passportPhoto) {
        MediaType image = MediaType.parse("image/*");
        MediaType text = MediaType.parse("text/plain");

        File passport = new File(passportPhoto);
        Long truck = driver.getTruckId() == null ? Long.valueOf(0) : driver.getTruckId();

        RequestBody requestBodyPassport = RequestBody.create(image, passport);
        RequestBody name = RequestBody.create(text, driver.getName());
        RequestBody drivingLicense = RequestBody.create(text, driver.getDrivingLicense());
        RequestBody drivingLicenseType = RequestBody.create(text, driver.getDrivingLicenseType());
        RequestBody idNumber = RequestBody.create(text, driver.getIdNumber());


        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", passport.getName(), requestBodyPassport);

        Call<Driver> createDriver = apiService.createDriver(photo, name, drivingLicense, drivingLicenseType, idNumber);
        createDriver.enqueue(new Callback<Driver>() {
            @Override
            public void onResponse(Call<Driver> call, Response<Driver> response) {
                if (response.isSuccessful()) {
                    driverDao.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<Driver> call, Throwable t) {

            }
        });
    }
}
