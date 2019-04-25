package com.Ololaa.ololaa.dependencyInjection;

import com.Ololaa.ololaa.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentsModule.class)
    abstract MainActivity mainActivity();
}
