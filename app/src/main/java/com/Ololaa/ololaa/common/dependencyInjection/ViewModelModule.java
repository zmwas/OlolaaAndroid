package com.ololaa.ololaa.common.dependencyInjection;

import android.arch.lifecycle.ViewModelProvider;

import com.ololaa.ololaa.OlolaaViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory factory(OlolaaViewModelFactory factory);


}
