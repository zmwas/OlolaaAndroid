package com.Ololaa.ololaa.dependencyInjection;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;

@Module
public class ExecutorModule {

    @Provides
    public Executor executor() {
        return Executors.newSingleThreadExecutor();
    }
}
