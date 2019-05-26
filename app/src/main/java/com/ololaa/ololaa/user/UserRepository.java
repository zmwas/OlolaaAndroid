package com.ololaa.ololaa.user;

import com.ololaa.ololaa.common.api.ApiService;

import javax.inject.Inject;

public class UserRepository {
    ApiService apiService;

    @Inject
    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }
}
