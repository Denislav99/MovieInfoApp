package com.example.movieinfoapp.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreferences {
    companion object {
        private const val APPLICATION_SHARED_PREFERENCES = "APPLICATION_SHARED_PREFERENCES"
        private const val LAST_GENRES_FETCHED_KEY = "LAST_GENRES_FETCHED_KEY"

        private lateinit var sharedPrefs: SharedPreferences

        fun init(context: Context) {
            sharedPrefs = context.getSharedPreferences(
                APPLICATION_SHARED_PREFERENCES,
                Context.MODE_PRIVATE)
        }

        var lastGenresFetchedMs: Long
            get() = sharedPrefs.getLong(LAST_GENRES_FETCHED_KEY, 0)
            set(value) {
                sharedPrefs.edit().putLong(LAST_GENRES_FETCHED_KEY, value).apply()
            }
    }
}