package com.ololaa.ololaa.common.dependencyInjection;

import com.ololaa.ololaa.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentsModule.class)
    abstract MainActivity mainActivity();
}
