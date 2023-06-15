package com.example.movieinfoapp.network.genre

import com.example.movieinfoapp.data.movie.response.genre.DiscoverGenreResponse
import com.example.movieinfoapp.BuildConfig
import com.example.movieinfoapp.network.NetworkConnection
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApiService{
    @GET("/3/genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String = BuildConfig.API_KEY, @Query("language") lang: String): Response<DiscoverGenreResponse>
}

object GenreApi {
    private val retrofitService: GenreApiService by lazy {
        NetworkConnection.retrofit.create(GenreApiService::class.java)
    }

    suspend fun getGenres() = retrofitService.getGenres(lang = "en-US")
}