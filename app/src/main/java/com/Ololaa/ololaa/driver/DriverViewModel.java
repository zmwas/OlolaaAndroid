package com.ololaa.ololaa.driver;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ololaa.ololaa.common.SingleLiveEvent;
import com.ololaa.ololaa.common.models.Driver;

import java.util.List;

import javax.inject.Inject;

public class DriverViewModel extends ViewModel {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> errorName = new ObservableField<>();
    public ObservableField<String> truck = new ObservableField<>();
    public ObservableField<String> errorTruck = new ObservableField<>();
    public ObservableField<String> drivingLicenseNumber = new ObservableField<>();
    public ObservableField<String> errorDrivingLicenseNumber = new ObservableField<>();
    public ObservableField<String> drivingLicenseType = new ObservableField<>();
    public ObservableField<Integer> drivingLicenseTypePos = new ObservableField<>();
    public ObservableField<String> errorDrivingLicenseType = new ObservableField<>();
    public ObservableField<String> idNumber = new ObservableField<>();
    public ObservableField<String> errorIdNumber = new ObservableField<>();
    public ObservableField<String> passportPhoto = new ObservableField<>();
    public ObservableField<String> errorpassportPhoto = new ObservableField<>();

    public MutableLiveData<Boolean> showPassportPhoto = new MutableLiveData<>();
    public ObservableField<Long> truckId = new ObservableField<>();

    private DriverRepository driverRepository;

    @Inject
    public DriverViewModel(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }



    public Driver driver() {
        Driver driver = new Driver();
        driver.setDrivingLicense(drivingLicenseNumber.get());
        driver.setIdNumber(idNumber.get());
        driver.setName(name.get());
        driver.setDrivingLicenseType(drivingLicenseType.get());
        if (truckId.get() != null)
            driver.setTruckId(truckId.get());
        return driver;
    }

    private boolean isDataValid() {
        if (drivingLicenseNumber.get() == null || drivingLicenseNumber.get().isEmpty()) {
            errorDrivingLicenseNumber.set("Cannot be empty");
            return false;
        } else if (idNumber.get() == null || idNumber.get().isEmpty()) {
            errorIdNumber.set("Cannot be empty");
            return false;
        } else if (name.get() == null || name.get().isEmpty()) {
            errorName.set("Cannot be empty");
            return false;
        } else if (drivingLicenseType == null) {
            errorDrivingLicenseType.set("Cannot be empty");
            return false;
        } else if (passportPhoto.get()==null|| passportPhoto.get().isEmpty() ) {
            errorpassportPhoto.set("Image is missing");
            return false;
        }
        return true;
    }

    public void createDriver() {
        if (isDataValid())
            driverRepository.createDriver(driver(), passportPhoto.get());
    }

    public LiveData<List<Driver>> fetchDrivers() {
        return driverRepository.fetchDrivers();
    }

    public SingleLiveEvent<Boolean> showSuccessDialog() {
        return driverRepository.showSuccessDialog;
    }

    public SingleLiveEvent<Boolean> showProgressDialog() {
        return driverRepository.showProgressDialog;
    }

}
