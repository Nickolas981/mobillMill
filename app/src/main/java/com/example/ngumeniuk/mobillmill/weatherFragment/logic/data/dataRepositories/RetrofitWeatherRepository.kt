package com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataRepositories

import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataSource.NetworkWeatherDataSource
import com.example.ngumeniuk.mobillmill.web.WeatherAPI


class RetrofitWeatherRepository(private val api: WeatherAPI) : NetworkWeatherDataSource {
    override fun getWeather(lon: Double, lat: Double) =
            api.getForecastByGeo(lon, lat)
}