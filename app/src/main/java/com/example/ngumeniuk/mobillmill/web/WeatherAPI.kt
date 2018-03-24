package com.example.ngumeniuk.mobilmill.web

import com.example.ngumeniuk.mobillmill.web.Constants
import com.example.ngumeniuk.mobilmill.weatherFragment.models.local.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAPI {
    @GET(Endpoints.API_SEARCH_DAILY)
    fun getForecastByGeo(@Query("lon") lon: Double,
                         @Query("lat") lat: Double,
                         @Query("units") units: String = "metric",
                         @Query("appid") appid: String = Constants.APPID): Observable<WeatherResponse>
}