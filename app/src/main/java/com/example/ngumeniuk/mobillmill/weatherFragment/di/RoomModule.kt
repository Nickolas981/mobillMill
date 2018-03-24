package com.example.ngumeniuk.mobillmill.weatherFragment.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.ngumeniuk.mobillmill.weatherFragment.data.dao.WeatherDao
import com.example.ngumeniuk.mobillmill.weatherFragment.data.dataRepositories.RoomWeatherRepository
import com.example.ngumeniuk.mobillmill.weatherFragment.data.dataSource.DatabaseWeatherDataSource
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

    @Provides
    @Singleton
    fun provideWeatherDao(dataBase: WeatherDatabase): WeatherDao {
        return dataBase.weatherDao()
    }

    @Provides
    @Singleton
    fun provideRoomWeatherRepository(weatherDao: WeatherDao): DatabaseWeatherDataSource {
        return RoomWeatherRepository(weatherDao)
    }

}