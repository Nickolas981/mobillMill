package com.example.ngumeniuk.mobillmill.weatherFragment.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.ngumeniuk.mobillmill.weatherFragment.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(val context: Context) {

    @Singleton
    @Provides
    fun provideWeatherRoomDataBase() = Room
            .databaseBuilder(context, WeatherDatabase::class.java, "weatherDatabase")
            .build()
            .weatherDao()

}