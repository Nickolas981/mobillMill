package com.example.ngumeniuk.mobillmill

import android.app.Application
import com.example.ngumeniuk.mobillmill.location.LocationModule
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.di.DaggerWeatherComponent
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.di.RoomModule
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.di.WeatherComponent

class App : Application(){
    companion object {
        lateinit var weatherComponent: WeatherComponent
    }

    override fun onCreate() {
        super.onCreate()
        weatherComponent = buildWeatherComponent()
    }

    private fun buildWeatherComponent() = DaggerWeatherComponent
            .builder()
            .locationModule(LocationModule(applicationContext))
            .roomModule(RoomModule(this))
            .build()
}