package com.ololaa.ololaa.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ololaa.ololaa.common.requests.CreateUserRequest;
import com.ololaa.ololaa.common.requests.LoginRequest;
import com.ololaa.ololaa.common.requests.UpdateFirebaseTokenRequest;

import javax.inject.Inject;

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


    private UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private CreateUserRequest createUserRequest() {
        CreateUserRequest request = new CreateUserRequest();
        request.setCompanyName(companyName.get());
        request.setEmail(email.get());
        request.setKraPin(kraPin.get());
        request.setPassword(password.get());
        request.setPhoneNumber(phoneNumber.get());
        request.setRole(role.get());
        return request;
    }

    private Boolean isDataValid() {
        if (companyName.get() == null || companyName.get().isEmpty()) {
            errorCompanyName.set("Cannot be empty");
            return false;
        } else if (email.get() == null || email.get().isEmpty()) {
            errorEmail.set("Cannot be empty");
            return false;
        } else if (kraPin.get() == null || kraPin.get().isEmpty()) {
            errorKraPin.set("Cannot be empty");
            return false;
        } else if (password.get() == null || password.get().isEmpty()) {
            errorPassword.set("Cannot be empty");
            return false;

        } else if (phoneNumber.get() == null || phoneNumber.get().isEmpty()) {
            errorPhoneNumber.set("Cannot be empty");
            return false;
        } else if (rolePos.get() == null || rolePos.get() < 1) {
            errorRole.set("Cannot be empty");
        }
        return true;
    }

    private LoginRequest loginRequest() {
        LoginRequest request = new LoginRequest();
        request.setEmail(email.get());
        request.setPassword(password.get());
        return request;
    }

    public boolean isLoginValid() {
        if (email.get() == null || email.get().isEmpty()) {
            errorEmail.set("Cannot be empty");
            return false;
        } else if (password.get() == null || password.get().isEmpty()) {
            errorPassword.set("Cannot be empty");
            return false;
        }
        return true;
    }

    public void signUp() {
        if (isDataValid())
            userRepository.registerUser(createUserRequest());
    }

    public void login() {
        if (isLoginValid())
            userRepository.login(loginRequest());
    }

    public void updateFirebase(String token) {
        UpdateFirebaseTokenRequest request = new UpdateFirebaseTokenRequest();
        request.setFirebaseToken(token);
        userRepository.updateFirebaseToken(request);
    }

    public LiveData<Enum> showSuccessDialog() {
        return userRepository.showSuccessDialog;
    }

}
