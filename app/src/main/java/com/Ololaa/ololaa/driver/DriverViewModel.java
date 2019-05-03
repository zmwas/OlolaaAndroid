package com.ololaa.ololaa.driver;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

public class DriverViewModel extends ViewModel {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> errorName = new ObservableField<>();
    public ObservableField<String> truck = new ObservableField<>();
    public ObservableField<String> errorTruck = new ObservableField<>();
    public ObservableField<String> drivingLicenseNumber = new ObservableField<>();
    public ObservableField<String> errorDrivingLicenseNumber = new ObservableField<>();
    public ObservableField<String> drivingLicenseType = new ObservableField<>();
    public ObservableField<String> errorDrivingLicenseType = new ObservableField<>();
    public ObservableField<String> idNumber = new ObservableField<>();
    public ObservableField<String> errorIdNumber = new ObservableField<>();
    public MutableLiveData<Boolean> showPassportPhoto = new MutableLiveData<>();


}
