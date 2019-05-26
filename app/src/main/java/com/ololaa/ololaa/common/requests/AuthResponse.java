package com.ololaa.ololaa.common.requests;

import com.ololaa.ololaa.common.models.AppUser;

public class AuthResponse {
    private Boolean success;
    public String token;
    public AppUser appUser;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
