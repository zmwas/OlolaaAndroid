package com.Ololaa.ololaa.dependencyInjection;

import android.arch.lifecycle.ViewModelProvider;

import com.Ololaa.ololaa.OlolaaViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory factory(OlolaaViewModelFactory factory);


}
