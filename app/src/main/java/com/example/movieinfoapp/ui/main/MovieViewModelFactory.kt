package com.example.movieinfoapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieinfoapp.repository.movie.MovieRepository

class MovieViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                MovieViewModel(this.repository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}