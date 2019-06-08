package com.ololaa.ololaa.common.dependencyInjection;

import com.ololaa.ololaa.OlolaaApp;
import com.ololaa.ololaa.common.SharedPreferenceImpl;
import com.ololaa.ololaa.common.SharedPrefsWrapper;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefsModule {
    @Provides
    public SharedPreferenceImpl sharedPreference(OlolaaApp app) {
        return new SharedPreferenceImpl(app);
    }

    @Provides
    public SharedPrefsWrapper wrapper(SharedPreferenceImpl sharedPreference) {
        return sharedPreference;
    }
}


