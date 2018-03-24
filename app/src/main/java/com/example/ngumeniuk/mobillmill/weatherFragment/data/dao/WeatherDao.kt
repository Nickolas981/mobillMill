package com.example.ngumeniuk.mobillmill.weatherFragment.data.dao

import android.arch.persistence.room.*
import com.example.ngumeniuk.mobillmill.weatherFragment.data.models.databaseEnteties.WeatherModel
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface WeatherDao{
    @Insert
    fun putWeatherModel(weather : WeatherModel)

    @Delete
    fun delete(weatherModel: WeatherModel)

    @Query("SELECT * FROM weather")
    fun getAll(): Flowable<List<WeatherModel>>

    @Query("DELETE FROM weather WHERE id LIKE :id")
    fun deleteById(id: Int)

    @Query("DELETE FROM weather")
    fun dropTable()
}