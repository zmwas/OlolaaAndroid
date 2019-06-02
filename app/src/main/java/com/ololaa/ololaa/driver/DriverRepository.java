package com.ololaa.ololaa.driver;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.DriverDao;
import com.ololaa.ololaa.common.models.Driver;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverRepository {
    private ApiService apiService;
    private DriverDao driverDao;
    private ExecutorService executorService;
    public MutableLiveData<List<Driver>> drivers = new MutableLiveData<>();
    private Driver driver;

    @Inject
    public DriverRepository(ApiService apiService, DriverDao driverDao, ExecutorService executorService) {
        this.apiService = apiService;
        this.driverDao = driverDao;
        this.executorService = executorService;
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

    LiveData<List<Driver>> fetchDrivers() {
        executorService.execute(() -> drivers.postValue(driverDao.fetchDrivers()));
        return drivers;
    }

    public Driver getDriver(Long driverId) {
        executorService.execute(() -> driver = driverDao.getById(driverId));
        return driver;
    }
}
