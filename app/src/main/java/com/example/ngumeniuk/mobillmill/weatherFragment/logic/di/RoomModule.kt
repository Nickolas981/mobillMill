package com.example.ngumeniuk.mobillmill.weatherFragment.logic.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dao.WeatherDao
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataRepositories.RoomWeatherRepository
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataSource.DatabaseWeatherDataSource
import com.example.ngumeniuk.mobillmill.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(private val context: Context) {

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