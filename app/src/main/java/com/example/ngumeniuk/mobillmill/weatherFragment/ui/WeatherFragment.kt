package com.example.ngumeniuk.mobillmill.weatherFragment.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ngumeniuk.mobillmill.R
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.databaseEnteties.WeatherModel
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.viewModel.WeatherViewModel
import com.example.ngumeniuk.mobillmill.weatherFragment.ui.adapters.WeatherListAdapter
import com.example.ngumeniuk.mobillmill.widgets.enums.Status
import com.example.ngumeniuk.mobillmill.widgets.utils.DataResource
import kotlinx.android.synthetic.main.fragment_weather.*
import org.jetbrains.anko.support.v4.toast

class WeatherFragment : Fragment() {


    private val weatherViewModel
            by lazy { ViewModelProviders.of(activity!!).get(WeatherViewModel::class.java) }
    private val adapter
            by lazy { WeatherListAdapter() }
    private val layoutManager
            by lazy { LinearLayoutManager(activity) }

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
    }

    companion object {
        fun newInstance(): WeatherFragment = WeatherFragment()
    }
}
