package com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.retrofitResponse

import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.retrofitResponse.Temperature
import com.google.gson.annotations.SerializedName

data class WeatherItem(
        @field:SerializedName("main")
        val main: Temperature,
        @field:SerializedName("dt_txt")
        val dt: String
)