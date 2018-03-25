package com.example.ngumeniuk.mobillmill.weatherFragment.logic.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.ngumeniuk.curogram.utils.BaseViewModel
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataSource.DatabaseWeatherDataSource
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataSource.NetworkWeatherDataSource
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.databaseEnteties.WeatherModel
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.retrofitResponse.WeatherItem
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.retrofitResponse.WeatherResponse
import com.example.ngumeniuk.mobillmill.widgets.utils.DataResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.InetAddress
import javax.inject.Inject


class WeatherViewModel @Inject constructor(private val databaseRepo: DatabaseWeatherDataSource,
                                           private val networkRepo: NetworkWeatherDataSource)
    : BaseViewModel() {

    private val liveDataWeather = MutableLiveData<DataResource<List<WeatherModel>>>()

    fun getWeather() {
        setWeatherValue(DataResource.loading(null))
        if (isInternetAvailable()) {
            addDisposable(
                    networkRepo
                            .getWeather(35.toDouble(), 35.toDouble())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map {
                                val res: MutableList<WeatherModel> = ArrayList()
                                var hourCounter = 0
                                for (weatherItem in it.list) {
                                    if (hourCounter++ == 0) {
                                        res.add(WeatherModel(weatherItem.main.temp,
                                                weatherItem.main.tempMin,
                                                weatherItem.main.tempMax,
                                                weatherItem.dt))
                                    }
                                    if (hourCounter == 8)
                                        hourCounter = 0
                                }
                                res.subList(0, 3).toList()
                            }
                            .subscribe({
                                databaseRepo.dropTable()
                                setWeatherValue(DataResource.success(it))
                            }, {
                                setWeatherValue(DataResource.error(it, null))
                            })
            )
        }
    }


    private fun setWeatherValue(value: DataResource<List<WeatherModel>>) =
            liveDataWeather.postValue(value)

    fun getWeatherLiveData(): LiveData<DataResource<List<WeatherModel>>> =
            liveDataWeather

    private fun isInternetAvailable(): Boolean = try {
        val ipAddr = InetAddress.getByName("google.com")
        !ipAddr.equals("")
    } catch (e: Exception) {
        false
    }
}