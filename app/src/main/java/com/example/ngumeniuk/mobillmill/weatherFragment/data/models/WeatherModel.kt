package com.example.ngumeniuk.mobillmill.weatherFragment.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class WeatherModel(
        val temp : Double,
        val tempMin : Double,
        val tempMax : Double,
        val dt : String,
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
)