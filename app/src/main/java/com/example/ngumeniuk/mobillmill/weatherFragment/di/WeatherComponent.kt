package com.example.ngumeniuk.mobillmill.weatherFragment.di

import com.example.ngumeniuk.mobillmill.location.LocationModule
import com.example.ngumeniuk.mobillmill.weatherFragment.viewModel.WeatherViewModel
import com.example.ngumeniuk.mobillmill.web.di.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RetrofitModule::class, LocationModule::class, RoomModule::class))
interface WeatherComponent {
    fun inject(viewModel: WeatherViewModel)
}