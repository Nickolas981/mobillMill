package com.example.ngumeniuk.mobillmill.weatherFragment.data.dataRepositories

import com.example.ngumeniuk.mobillmill.weatherFragment.data.dataSource.NetworkWeatherDataSource
import com.example.ngumeniuk.mobilmill.web.WeatherAPI


class RetrofitWeatherRepository(private val api: WeatherAPI) : NetworkWeatherDataSource {
    override fun getWeather(lon: Double, lat: Double) =
            api.getForecastByGeo(lon, lat)
}