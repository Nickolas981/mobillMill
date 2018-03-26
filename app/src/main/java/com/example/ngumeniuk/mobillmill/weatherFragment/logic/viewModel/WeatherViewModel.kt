package com.example.ngumeniuk.mobillmill.weatherFragment.logic.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Handler
import com.example.ngumeniuk.curogram.utils.BaseViewModel
import com.example.ngumeniuk.mobillmill.App
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataSource.DatabaseWeatherDataSource
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.dataSource.NetworkWeatherDataSource
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.databaseEnteties.WeatherModel
import com.example.ngumeniuk.mobillmill.widgets.utils.DataResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.net.InetAddress
import javax.inject.Inject


class WeatherViewModel : BaseViewModel() {

    @Inject
    lateinit var databaseRepo: DatabaseWeatherDataSource
    @Inject
    lateinit var networkRepo: NetworkWeatherDataSource

    private val liveDataWeather = MutableLiveData<DataResource<List<WeatherModel>>>()

    private val handler = Handler()
    private val waitCode = object : Runnable {
        override fun run() {
            async {
                val connection = bg { isInternetAvailable() }.await()
                if (connection)
                    getWeather()
                else
                    postDelayed()
            }
        }

        fun postDelayed(){
            handler.postDelayed(this, 2000)
        }
    }

    init {
        App.weatherComponent.inject(this)
    }

    fun loadWeather() {
        if (liveDataWeather.value == null) {
            getWeatherFromDatabase()
            handler.post(waitCode)
        }
    }


    private fun getWeatherFromDatabase() {
        addDisposable(
                databaseRepo.getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ setWeatherValue(DataResource.success(it)) },
                                { setWeatherValue(DataResource.error(it, null)) })
        )
    }


    fun getWeather() {
        setWeatherValue(DataResource.loading(null))
        addDisposable(
                networkRepo
                        .getWeather(35.toDouble(), 35.toDouble())
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
                            putWeatherToDatabase(it)
                        }, {
                            setWeatherValue(DataResource.error(it, null))
                        })
        )
    }

    private fun putWeatherToDatabase(list: List<WeatherModel>) {
        for (weather in list) {
            bg { databaseRepo.putWeatherModel(weather) }
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