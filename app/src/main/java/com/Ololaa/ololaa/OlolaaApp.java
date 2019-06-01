package com.ololaa.ololaa;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.levibostian.wendy.service.Wendy;
import com.ololaa.ololaa.common.dependencyInjection.DaggerAppComponent;
import com.ololaa.ololaa.fetchingTasks.OlolaaTaskFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class OlolaaApp extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Inject
    OlolaaTaskFactory factory;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);
        Wendy.init(this, factory);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }

    public OlolaaTaskFactory getFactory() {
        return factory;
    }
}

