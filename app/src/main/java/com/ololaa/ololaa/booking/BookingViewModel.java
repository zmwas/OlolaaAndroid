package com.ololaa.ololaa.booking;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

public class BookingViewModel extends ViewModel {
    public ObservableField<String> cargoType = new ObservableField<>();
    public ObservableField<String> cargoPictureUrl = new ObservableField<>();
    public ObservableField<String> errorCargoType = new ObservableField<>();
    public ObservableField<String> cargoMover = new ObservableField<>();
    public ObservableField<String> idNumber = new ObservableField<>();
    public ObservableField<String> telephone = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> weight = new ObservableField<>();
    public ObservableField<String> errorWeight = new ObservableField<>();
    public ObservableField<String> collectionPoint = new ObservableField<>();
    public ObservableField<String> errorCollectionPoint = new ObservableField<>();
    public ObservableField<String> dropOffPoint = new ObservableField<>();
    public ObservableField<String> errorDropOffPoint = new ObservableField<>();
    public ObservableField<String> availableTonage = new ObservableField<>();
    public ObservableField<String> errorAvailableTonage = new ObservableField<>();
    public ObservableField<String> firstAvailableDate = new ObservableField<>();
    public ObservableField<String> errorFirstAvailableDate = new ObservableField<>();
    public ObservableField<String> lastAvailableDate = new ObservableField<>();
    public ObservableField<String> errorLastAvailableDate = new ObservableField<>();
    public ObservableField<String> numberUnits = new ObservableField<>();
    public ObservableField<String> errorNumberUnits = new ObservableField<>();
    public ObservableField<Boolean> showCargoPhoto = new ObservableField<>();
    public ObservableField<String> tonage = new ObservableField<>();
    public ObservableField<String> agreedPrice = new ObservableField<>();
    public ObservableField<Boolean> callMade = new ObservableField<>();


}
