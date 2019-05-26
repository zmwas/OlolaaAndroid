package com.ololaa.ololaa.common.dependencyInjection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.ololaa.ololaa.OlolaaViewModelFactory;
import com.ololaa.ololaa.booking.BookingViewModel;
import com.ololaa.ololaa.driver.DriverViewModel;
import com.ololaa.ololaa.trip.TripViewModel;
import com.ololaa.ololaa.truck.TruckViewModel;
import com.ololaa.ololaa.user.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory factory(OlolaaViewModelFactory factory);


    @Binds
    @IntoMap
    @ViewModelKey(BookingViewModel.class)
    abstract ViewModel bindBookingViewModel(BookingViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DriverViewModel.class)
    abstract ViewModel bindDriverViewModel(DriverViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TripViewModel.class)
    abstract ViewModel bindTripViewModel(TripViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TruckViewModel.class)
    abstract ViewModel bindTruckViewModel(TruckViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel viewModel);


}
