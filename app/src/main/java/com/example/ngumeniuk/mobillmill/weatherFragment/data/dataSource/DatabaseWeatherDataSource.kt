package com.example.ngumeniuk.mobillmill.weatherFragment.data.dataSource

import com.example.ngumeniuk.mobillmill.weatherFragment.data.models.databaseEnteties.WeatherModel
import io.reactivex.Flowable
import io.reactivex.Single


interface DatabaseWeatherDataSource {
    fun getAll(): Flowable<List<WeatherModel>>

    fun putNote(note: WeatherModel)

    fun dropTable()
}