package com.ololaa.ololaa.user;

import com.ololaa.ololaa.common.SharedPrefsWrapper;
import com.ololaa.ololaa.common.SingleLiveEvent;
import com.ololaa.ololaa.common.api.ApiService;
import com.ololaa.ololaa.common.models.AppUser;
import com.ololaa.ololaa.common.requests.AuthWrapper;
import com.ololaa.ololaa.common.requests.CreateUserRequest;
import com.ololaa.ololaa.common.requests.LoginRequest;
import com.ololaa.ololaa.common.requests.UpdateFirebaseTokenRequest;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ololaa.ololaa.Constants.Messages.GENERAL_ERROR;
import static com.ololaa.ololaa.Constants.Messages.SUCCESS;

public class UserRepository {
    private ApiService apiService;
    private SharedPrefsWrapper wrapper;
    public SingleLiveEvent<Boolean> showProgressDialog = new SingleLiveEvent<>();
    public SingleLiveEvent<Enum> showSuccessDialog = new SingleLiveEvent<>();
    public SingleLiveEvent<Enum> showError = new SingleLiveEvent<>();

    @Inject
    public UserRepository(ApiService apiService, SharedPrefsWrapper wrapper) {
        this.apiService = apiService;
        this.wrapper = wrapper;
    }

    void registerUser(CreateUserRequest request) {
        showProgressDialog.setValue(true);
        Call<AppUser> signUp = apiService.signUp(request);
        signUp.enqueue(new Callback<AppUser>() {
            @Override
            public void onResponse(Call<AppUser> call, Response<AppUser> response) {
                showProgressDialog.setValue(false);
                if (response.isSuccessful()) {
                    showSuccessDialog.postValue(SUCCESS);
                } else {
                    showSuccessDialog.postValue(GENERAL_ERROR);
                }
            }

            @Override
            public void onFailure(Call<AppUser> call, Throwable t) {
                showSuccessDialog.postValue(GENERAL_ERROR);
            }
        });
    }

    void login(LoginRequest request) {
        showProgressDialog.setValue(true);
        Call<AuthWrapper> login = apiService.login(request);
        login.enqueue(new Callback<AuthWrapper>() {
            @Override
            public void onResponse(Call<AuthWrapper> call, Response<AuthWrapper> response) {
                if (response.isSuccessful()) {
                    showProgressDialog.setValue(false);
                    showSuccessDialog.postValue(SUCCESS);
                    wrapper.putString("token", response.body().getResponse().getToken());
                    wrapper.putString("role", response.body().getResponse().getAppUser().getRoles().get(0));
                    wrapper.putBoolean("logged out", false);
                }
            }

            @Override
            public void onFailure(Call<AuthWrapper> call, Throwable t) {

            }
        });
    }

    public void updateFirebaseToken(UpdateFirebaseTokenRequest request) {
        Call<AppUser> updateFirebaseToken = apiService.updateFirebaseToken(request);
        updateFirebaseToken.enqueue(new Callback<AppUser>() {
            @Override
            public void onResponse(Call<AppUser> call, Response<AppUser> response) {

            }

            @Override
            public void onFailure(Call<AppUser> call, Throwable t) {

            }
        });
    }
}
