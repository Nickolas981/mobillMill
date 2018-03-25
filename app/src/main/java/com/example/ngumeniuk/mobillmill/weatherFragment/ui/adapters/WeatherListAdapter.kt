package com.example.ngumeniuk.mobillmill.weatherFragment.ui.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ngumeniuk.mobillmill.R
import com.example.ngumeniuk.mobillmill.weatherFragment.logic.data.models.databaseEnteties.WeatherModel
import com.example.ngumeniuk.mobillmill.widgets.adapters.ListAdapter
import kotlinx.android.synthetic.main.item_weather_list.view.*


class WeatherListAdapter : ListAdapter<WeatherModel, WeatherListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(list[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_weather_list))

    private fun ViewGroup.inflate(layoutRes: Int): View =
            LayoutInflater.from(context).inflate(layoutRes, this, false)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(model: WeatherModel) {
            with(itemView) {
                with(model) {
                    dateTV.text = dt
                    tempTV.text = temp.toString() + "°"
                    min_and_maxTV.text = "$minimal° - $maximal°"
                }
            }
        }
    }
}