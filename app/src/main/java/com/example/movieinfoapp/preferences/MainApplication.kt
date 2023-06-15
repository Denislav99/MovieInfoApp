package com.example.movieinfoapp.preferences

import android.app.Application

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}