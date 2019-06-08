package com.ololaa.ololaa.common.requests;

import com.google.gson.annotations.SerializedName;

public class AuthWrapper {
    @SerializedName("auth")
    AuthResponse response;

    public AuthResponse getResponse() {
        return response;
    }

    public void setResponse(AuthResponse response) {
        this.response = response;
    }
}
