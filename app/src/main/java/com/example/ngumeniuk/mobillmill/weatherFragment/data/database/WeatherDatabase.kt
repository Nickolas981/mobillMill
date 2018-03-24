package com.example.ngumeniuk.mobillmill.weatherFragment.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ngumeniuk.mobillmill.weatherFragment.data.dao.WeatherDao
import com.example.ngumeniuk.mobillmill.weatherFragment.data.models.databaseEnteties.WeatherModel

@Database(entities = arrayOf(WeatherModel::class), version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}