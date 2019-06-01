package com.ololaa.ololaa.common.dependencyInjection;


import com.ololaa.ololaa.BuildConfig;
import com.ololaa.ololaa.common.SharedPrefsWrapper;
import com.ololaa.ololaa.common.api.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public OkHttpClient client(SharedPrefsWrapper wrapper) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor authInterceptor = chain -> {
            String authToken = "";
            authToken = wrapper.getString("token");
            Request request = chain.request();
            Request newRequest = null;
            HttpUrl url = request.url();
            HttpUrl newUrl = url.newBuilder()
                    .build();

            if (!request.url().encodedPath().equalsIgnoreCase("v1/auth/login")) {
                Request.Builder builder = request.newBuilder()
                        .url(newUrl)
                        .addHeader("Authorization",
                                "Bearer " + authToken);
                newRequest = builder.build();

            } else {
                Request.Builder builder = request.newBuilder()
                        .url(newUrl);
                newRequest = builder.build();

            }
            return chain.proceed(newRequest);
        };

        return new OkHttpClient.Builder().connectTimeout(6, TimeUnit.MINUTES)
                .readTimeout(6, TimeUnit.MINUTES)
                .writeTimeout(6, TimeUnit.MINUTES).addInterceptor(authInterceptor).addInterceptor(loggingInterceptor).retryOnConnectionFailure(true).build();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Provides
    @Singleton
    public ApiService apiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
