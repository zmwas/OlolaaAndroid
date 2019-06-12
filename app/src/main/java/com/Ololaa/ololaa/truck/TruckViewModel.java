package com.ololaa.ololaa.truck;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ololaa.ololaa.common.SingleLiveEvent;
import com.ololaa.ololaa.common.models.Truck;

import java.util.List;

import javax.inject.Inject;

public class TruckViewModel extends ViewModel {
    public ObservableField<String> licensePlate = new ObservableField<>();
    public ObservableField<String> errorLicensePlate = new ObservableField<>();
    public ObservableField<String> availableTonage = new ObservableField<>();
    public ObservableField<String> errorAvailableTonage = new ObservableField<>();
    public ObservableField<String> driver = new ObservableField<>();
    public ObservableField<String> errorDrivers = new ObservableField<>();
    public ObservableField<Boolean> trailer = new ObservableField<>();
    public ObservableField<String> ntsa = new ObservableField<>();
    public ObservableField<String> insuranceSticker = new ObservableField<>();
    public ObservableField<String> errorinsuranceSticker = new ObservableField<>();
    public ObservableField<String> errortruckPhoto = new ObservableField<>();

    public ObservableField<String> truckPhoto = new ObservableField<>();
    public ObservableField<String> truckType = new ObservableField<>();
    public ObservableField<String> errorTruckType = new ObservableField<>();
    public ObservableField<String> errorNtsa = new ObservableField<>();
    public ObservableField<Long> driverId = new ObservableField<>();
    public MutableLiveData<Boolean> showTruckPhoto = new MutableLiveData<>();
    public MutableLiveData<Boolean> showInsuranceSticker = new MutableLiveData<>();

    private TruckRepository truckRepository;

    @Inject
    public TruckViewModel(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public Truck truck() {
        Truck truck = new Truck();
        truck.setAvailableTonage(Double.valueOf(availableTonage.get()));
        truck.setLicensePlateNumber(licensePlate.get());
        truck.setNtsaCertificateNumber(ntsa.get());
        truck.setTrailer(trailer.get());
        truck.setTruckType(truckType.get());
        if (driverId != null)
            truck.setDriverId(driverId.get());
        return truck;
    }


    private boolean isDataValid() {
        if (licensePlate.get() == null || licensePlate.get().isEmpty()) {
            errorLicensePlate.set("Cannot be empty");
            return false;
        } else if (availableTonage.get() == null || availableTonage.get().isEmpty()) {
            errorAvailableTonage.set("Cannot be empty");
            return false;
        } else if (ntsa.get() == null || ntsa.get().isEmpty()) {
            errorNtsa.set("Cannot be empty");
            return false;
        } else if (truckPhoto.get() == null || truckPhoto.get().isEmpty()) {
            errortruckPhoto.set("Image is missing");
            return false;
        } else if (insuranceSticker.get() == null || insuranceSticker.get().isEmpty()) {
            errorinsuranceSticker.set("Image is missing");
            return false;
        }
        return true;
    }

    public void createTruck() {
        if (isDataValid())
            truckRepository.createTruck(truck(), truckPhoto.get(), insuranceSticker.get());
    }


    public LiveData<List<Truck>> fetchTrucks() {
        return truckRepository.fetchTrucks();
    }

    public SingleLiveEvent<Boolean> showSuccessDialog() {
        return truckRepository.showSuccessDialog;
    }

    public SingleLiveEvent<Boolean> showProgressDialog() {
        return truckRepository.showProgressDialog;
    }

}
