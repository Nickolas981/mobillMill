package com.example.ngumeniuk.mobillmill

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ngumeniuk.mobillmill.weatherFragment.WeatherFragment
import com.example.ngumeniuk.mobilmill.widgets.utils.inTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.inTransaction { add(R.id.container, WeatherFragment.newInstance()) }
    }
}
