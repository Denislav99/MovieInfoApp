package com.example.movieinfoapp.network.movie

import com.example.movieinfoapp.BuildConfig.API_KEY
import com.example.movieinfoapp.data.movie.response.DiscoverMoviesResponse
import com.example.movieinfoapp.network.NetworkConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String = API_KEY): Response<DiscoverMoviesResponse>
}

object MovieApi {
    private val retrofitService: MovieApiService by lazy {
        NetworkConnection.retrofit.create(MovieApiService::class.java)
    }

    suspend fun getMovies(): Response<DiscoverMoviesResponse> =
        withContext(Dispatchers.IO) {
            retrofitService.getMovies()
        }
}