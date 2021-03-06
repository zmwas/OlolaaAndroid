package com.ololaa.ololaa.truck;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.ololaa.ololaa.common.SingleLiveEvent;
import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.db.TruckDao;
import com.ololaa.ololaa.common.models.Truck;

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

public class TruckRepository {
    private ApiService apiService;
    private TruckDao truckDao;
    private ExecutorService executorService;
    public MutableLiveData<List<Truck>> trucks = new MutableLiveData<>();
    private Truck truck;
    public SingleLiveEvent<Boolean> showProgressDialog = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> showSuccessDialog = new SingleLiveEvent<>();


    @Inject
    public TruckRepository(ApiService apiService, TruckDao truckDao, ExecutorService executorService) {
        this.apiService = apiService;
        this.truckDao = truckDao;
        this.executorService = executorService;
    }


    void createTruck(Truck truck, String truckImage, String insuranceImage) {
        showProgressDialog.setValue(true);

        MediaType image = MediaType.parse("image/*");
        MediaType text = MediaType.parse("text/plain");

        File truckPhoto = new File(truckImage.replace("file://", ""));
        File insuranceSticker = new File(insuranceImage.replace("file://", ""));
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
                    showProgressDialog.setValue(false);
                    showSuccessDialog.setValue(true);

                    executorService.execute(() -> {
                        truckDao.insert(response.body());
                    });
                } else {
                    showSuccessDialog.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Truck> call, Throwable t) {

            }
        });
    }

    public Truck getTruck(Long id) {
        executorService.execute(() -> truck = truckDao.getById(id));
        return truck;
    }

    LiveData<List<Truck>> fetchTrucks() {
        executorService.execute(() -> {
            trucks.postValue(truckDao.fetchTrucks());
        });
        return trucks;
    }
}
