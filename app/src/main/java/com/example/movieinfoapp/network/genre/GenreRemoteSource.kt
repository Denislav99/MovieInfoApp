package com.example.movieinfoapp.network.genre

import com.example.movieinfoapp.data.movie.response.genre.DiscoverGenreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GenreRemoteSource constructor(private val genreApiService: GenreApi) {
    suspend fun getGenres(): Response<DiscoverGenreResponse> =
        withContext(Dispatchers.IO) {
            genreApiService.getGenres()
        }
}
