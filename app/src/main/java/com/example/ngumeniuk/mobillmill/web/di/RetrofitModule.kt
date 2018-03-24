package com.example.ngumeniuk.mobillmill.web.di

import com.example.ngumeniuk.mobilmill.web.Endpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
            .baseUrl(Endpoints.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
/*
    @Singleton
    @Provides
    fun provideRetrofitWeatherDataSource(retrofit: Retrofit): NetworkWeatherDataSource =
            RetrofitWeatherRepository(retrofit.create(WeatherAPI::class.java))*/
}