package com.example.ngumeniuk.mobillmill

import android.app.Application
import com.example.ngumeniuk.mobillmill.weatherFragment.di.WeatherComponent

class App : Application(){
    companion object {
        lateinit var weatherComponent: WeatherComponent
    }

    override fun onCreate() {
        super.onCreate()
//        weatherComponent = buildWeatherComponent()
    }

//    private fun buildWeatherComponent() = DaggerWeatherComponent
//            .builder()
//            .locationModule(LocationModule(applicationContext))
//            .build()
}