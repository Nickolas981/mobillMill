package com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dao

import android.arch.persistence.room.*
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.databaseEnteties.WeatherModel
import io.reactivex.Flowable


@Dao
interface WeatherDao{
    @Insert
    fun putWeatherModel(weather : WeatherModel)

    @Query("SELECT * FROM weather")
    fun getAll(): Flowable<List<WeatherModel>>

    @Query("DELETE FROM weather")
    fun dropTable()
}