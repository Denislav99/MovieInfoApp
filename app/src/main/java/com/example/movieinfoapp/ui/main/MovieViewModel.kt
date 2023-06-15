package com.example.movieinfoapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieinfoapp.database.genre.Genre
import com.example.movieinfoapp.repository.movie.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = repository.movies
    val movies: LiveData<List<Movie>> = _movies

    fun updateMoviesByGenres(genres: List<Genre>) {
        viewModelScope.launch {
            repository.updateMoviesByGenres(genres)
        }
    }
}


