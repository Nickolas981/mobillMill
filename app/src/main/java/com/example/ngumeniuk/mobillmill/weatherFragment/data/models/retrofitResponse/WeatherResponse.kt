package com.example.ngumeniuk.mobilmill.weatherFragment.models.local

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
        @field:SerializedName("list")
        val list: List<WeatherItem>
)