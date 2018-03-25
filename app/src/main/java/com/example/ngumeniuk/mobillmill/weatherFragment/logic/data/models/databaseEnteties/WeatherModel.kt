package com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.databaseEnteties

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.ngumeniuk.curogram.utils.Differeble

@Entity(tableName = "weather")
data class WeatherModel(
        var temp: Double,
        var minimal: Double,
        var maximal: Double,
        var dt: String,
        @field:PrimaryKey(autoGenerate = true)
        var id: Int = 0
) : Differeble{
        override fun getIdToDiff(): Int = id
}
