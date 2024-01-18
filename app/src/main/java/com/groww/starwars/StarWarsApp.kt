package com.groww.starwars

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StarWarsApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}