package com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.retrofitResponse

import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.retrofitResponse.WeatherItem
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
        @field:SerializedName("list")
        val list: List<WeatherItem>
)