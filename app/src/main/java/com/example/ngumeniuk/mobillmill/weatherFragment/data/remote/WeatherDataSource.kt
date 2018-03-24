package com.example.ngumeniuk.mobillmill.weatherFragment.data.remote

import com.example.ngumeniuk.mobilmill.weatherFragment.models.local.WeatherResponse
import io.reactivex.Observable

interface WeatherDataSource {
    fun getWeather(lon: Double, lat : Double) : Observable<WeatherResponse>
}