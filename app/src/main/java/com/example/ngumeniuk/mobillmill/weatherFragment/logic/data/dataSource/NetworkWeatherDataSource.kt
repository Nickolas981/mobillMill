package com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataSource

import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.retrofitResponse.WeatherResponse
import io.reactivex.Observable

interface NetworkWeatherDataSource {
    fun getWeather(lon: Double, lat : Double) : Observable<WeatherResponse>
}