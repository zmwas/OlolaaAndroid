package com.ololaa.ololaa.common.dependencyInjection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;

@Module
public class ExecutorModule {

    @Provides
    public ExecutorService executor() {
        return Executors.newSingleThreadExecutor();
    }
}
