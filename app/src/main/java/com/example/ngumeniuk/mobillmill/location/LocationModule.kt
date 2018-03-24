package com.example.ngumeniuk.mobillmill.location

import android.content.Context
import com.yayandroid.locationmanager.LocationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideLocationBuilder() :
            LocationManager.Builder =
            LocationManager.Builder(context)

}