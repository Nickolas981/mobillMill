package com.example.ngumeniuk.mobillmill.weatherFragment.data.remote

import com.example.ngumeniuk.mobilmill.web.WeatherAPI


class RetrofitWeatherDataSource(private val api: WeatherAPI) : WeatherDataSource {
    override fun getWeather(lon: Double, lat: Double) =
            api.getForecastByGeo(lon, lat)
}