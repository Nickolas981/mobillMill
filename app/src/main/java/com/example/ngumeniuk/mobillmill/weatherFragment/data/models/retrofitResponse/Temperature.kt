package com.example.ngumeniuk.mobilmill.weatherFragment.models.local

import com.google.gson.annotations.SerializedName

data class Temperature(
        @field:SerializedName("temp")
        val temp: Double,
        @field:SerializedName("temp_min")
        val tempMin: Double,
        @field:SerializedName("temp_max")
        val tempMax: Double
)