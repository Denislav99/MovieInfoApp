package com.example.movieinfoapp.data.movie.response

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
    val page: Int?,
    val results: List<MovieResponse>?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
)