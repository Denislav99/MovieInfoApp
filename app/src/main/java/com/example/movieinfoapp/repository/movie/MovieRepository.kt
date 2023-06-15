package com.example.movieinfoapp.repository.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieinfoapp.network.movie.MovieApi
import com.example.movieinfoapp.ui.main.Movie
import com.example.movieinfoapp.database.genre.Genre
import com.example.movieinfoapp.data.movie.response.convertToMovie

class MovieRepository {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    suspend fun updateMoviesByGenres(genres: List<Genre>) {
        try {
            val response = MovieApi.getMovies()

            _movies.postValue(
                if (response.isSuccessful) {
                    response.body()?.results?.map {
                        it.convertToMovie(genres)
                    } ?: emptyList()
                } else
                    emptyList()
            )
        } catch (ex: Exception) {

        }
    }
}
