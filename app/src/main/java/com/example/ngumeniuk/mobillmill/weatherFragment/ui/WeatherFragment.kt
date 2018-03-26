package com.example.ngumeniuk.mobillmill.weatherFragment.ui

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ngumeniuk.mobillmill.R
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.location.Location
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.viewModel.WeatherViewModel
import com.example.ngumeniuk.mobillmill.weatherFragment.ui.adapters.WeatherListAdapter
import com.example.ngumeniuk.mobillmill.widgets.enums.Status
import io.nlopez.smartlocation.SmartLocation
import kotlinx.android.synthetic.main.fragment_weather.*
import org.jetbrains.anko.support.v4.toast
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider




class WeatherFragment : Fragment() {
    private val weatherViewModel
            by lazy { ViewModelProviders.of(activity!!).get(WeatherViewModel::class.java) }
    private val adapter
            by lazy { WeatherListAdapter() }
    private val layoutManager
            by lazy { LinearLayoutManager(activity) }

    private val LOCATION_PERMISSION_ID = 1001
    private lateinit var provider: LocationGooglePlayServicesProvider


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_weather, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        weatherViewModel.loadWeather()
        subscribeToLiveData()
    }

    fun subscribeToLiveData() {
        weatherViewModel.getWeatherLiveData().observe(this, Observer { res ->
            if (res != null) {
                when (res.status) {
                    Status.LOADING -> {
                        loadingView.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        toast("error")
                    }
                    Status.SUCCESS -> {
                        loadingView.visibility = View.GONE
                        adapter.change(res.data!!)
                        recyclerView.scrollToPosition(0)
                    }
                }
            }
        })

        weatherViewModel.getLocationLiveData().observe(this, Observer { res ->
            if (res == null){
                checkPermission()
            } else {
                weatherViewModel.loadWeatherFromNetwork()
            }
        })
    }

    private fun checkPermission(){
        if (ContextCompat.checkSelfPermission(activity as Context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!,
                    arrayOf( Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_ID)
            return
        }
        startLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_ID && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startLocation()
        }
    }

    private fun startLocation() {
        provider = LocationGooglePlayServicesProvider()
        provider.setCheckLocationSettings(true)

        val smartLocation = SmartLocation.Builder(activity as Context).logging(true).build()

        smartLocation.location(provider).oneFix()
                .start { weatherViewModel.setLocation(Location(it.latitude, it.longitude)) }
    }

    companion object {
        fun newInstance(): WeatherFragment = WeatherFragment()
    }
}
