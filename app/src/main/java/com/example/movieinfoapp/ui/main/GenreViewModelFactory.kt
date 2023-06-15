package com.example.movieinfoapp.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieinfoapp.repository.genre.GenreRepository

class GenreViewModelFactory(
    private val repository: GenreRepository,
    private val app: Application): ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GenreViewModel::class.java))
            GenreViewModel(repository, app) as T
        else
            throw IllegalArgumentException("ViewModel Not Found")
    }
}