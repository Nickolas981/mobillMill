package com.example.ngumeniuk.mobilmill.weatherFragment.models.local

import com.google.gson.annotations.SerializedName

data class WeatherItem(
        @field:SerializedName("main")
        val main: Temperature,
        @field:SerializedName("dt_txt")
        val dt: String
)