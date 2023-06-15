package com.example.movieinfoapp.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieinfoapp.constants.DAY_MILLISECONDS
import com.example.movieinfoapp.preferences.AppPreferences
import com.example.movieinfoapp.repository.genre.GenreRepository
import kotlinx.coroutines.launch

class GenreViewModel(
    private val repository: GenreRepository,
    app: Application): AndroidViewModel(app) {

    val genres = repository.genres

    fun updateGenresIfNeeded(updateInterval: Long = DAY_MILLISECONDS) {
        val app = getApplication<Application>()
        val lastFetchedTime = (System.currentTimeMillis() - AppPreferences.lastGenresFetchedMs)

        if (lastFetchedTime >= updateInterval) {
            Log.v("Data", "database is being updated")

            viewModelScope.launch {
                repository.updateGenresFromApi(app)
            }
        } else
            Log.v("Data", "database is up to date")
    }
}