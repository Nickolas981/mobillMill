package com.example.ngumeniuk.mobillmill.weatherFragment.data.dataRepositories

import com.example.ngumeniuk.mobillmill.weatherFragment.data.dao.WeatherDao
import com.example.ngumeniuk.mobillmill.weatherFragment.data.dataSource.DatabaseWeatherDataSource
import com.example.ngumeniuk.mobillmill.weatherFragment.data.models.databaseEnteties.WeatherModel
import io.reactivex.Flowable


class RoomWeatherRepository(private val weatherDao: WeatherDao) : DatabaseWeatherDataSource{
    override fun getAll(): Flowable<List<WeatherModel>> = weatherDao.getAll()
    override fun putWeatherModel(weatherModel: WeatherModel) = weatherDao.putWeatherModel(weatherModel)
    override fun dropTable() = weatherDao.dropTable()
}