package com.ololaa.ololaa.user;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

public class UserViewModel extends ViewModel {
    public ObservableField<String> companyName = new ObservableField<>();
    public ObservableField<String> errorCompanyName = new ObservableField<>();

    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> errorEmail = new ObservableField<>();

    public ObservableField<String> phoneNumber = new ObservableField<>();
    public ObservableField<String> errorPhoneNumber = new ObservableField<>();

    public ObservableField<String> kraPin = new ObservableField<>();
    public ObservableField<String> errorKraPin = new ObservableField<>();

    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> errorPassword = new ObservableField<>();

    public ObservableField<String> role = new ObservableField<>();
    public ObservableField<String> errorRole = new ObservableField<>();

    public ObservableField<Integer> rolePos = new ObservableField<>();


}
