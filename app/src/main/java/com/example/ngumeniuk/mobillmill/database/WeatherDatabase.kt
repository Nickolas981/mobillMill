package com.example.ngumeniuk.mobillmill.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dao.WeatherDao
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.databaseEnteties.WeatherModel

@Database(entities = arrayOf(WeatherModel::class), version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}