package com.example.ngumeniuk.mobillmill.weatherFragment.data.dataSource

import com.example.ngumeniuk.mobilmill.weatherFragment.models.local.WeatherResponse
import io.reactivex.Observable

interface NetworkWeatherDataSource {
    fun getWeather(lon: Double, lat : Double) : Observable<WeatherResponse>
}