package com.Ololaa.ololaa.truck;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import java.util.List;

public class TruckViewModel extends ViewModel {
    public ObservableField<String> licensePlate = new ObservableField<>();
    public ObservableField<String> errorLicensePlate = new ObservableField<>();
    public ObservableField<String> availableTonage = new ObservableField<>();
    public ObservableField<String> errorAvailableTonage = new ObservableField<>();
    public ObservableField<String> driver = new ObservableField<>();
    public ObservableField<String> errorDriver = new ObservableField<>();
    public ObservableField<Boolean> trailer = new ObservableField<>();
    public ObservableField<String> ntsa = new ObservableField<>();
    public ObservableField<String> errorNtsa = new ObservableField<>();
    public MutableLiveData<Boolean> showTruckPhoto = new MutableLiveData<>();
    public MutableLiveData<Boolean> showInsuranceSticker = new MutableLiveData<>();



}
