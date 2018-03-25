package com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataRepositories

import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dao.WeatherDao
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataSource.DatabaseWeatherDataSource
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.databaseEnteties.WeatherModel
import io.reactivex.Flowable


class RoomWeatherRepository(private val weatherDao: WeatherDao) : DatabaseWeatherDataSource{
    override fun getAll(): Flowable<List<WeatherModel>> = weatherDao.getAll()
    override fun putWeatherModel(weatherModel: WeatherModel) = weatherDao.putWeatherModel(weatherModel)
    override fun dropTable() = weatherDao.dropTable()
}