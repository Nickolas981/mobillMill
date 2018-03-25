package com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataSource

import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.databaseEnteties.WeatherModel
import io.reactivex.Flowable


interface DatabaseWeatherDataSource {
    fun getAll(): Flowable<List<WeatherModel>>

    fun putWeatherModel(weatherModel: WeatherModel)

    fun dropTable()
}