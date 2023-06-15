package com.example.movieinfoapp.data.movie.response

import com.example.movieinfoapp.database.genre.Genre
import com.example.movieinfoapp.ui.main.Movie
import com.example.movieinfoapp.constants.*
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Long,
    @SerializedName("backdrop_path") val backDropPath: String?,
    val title: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    val overview: String?,
)

fun MovieResponse.convertToMovie(genreMappings: List<Genre>) = Movie(
    id = this.id,
    imageUrl =  if (this.backDropPath != null) "$BASE_IMAGE_URL${this.backDropPath}" else EMPTY_STRING,
    title = this.title ?: EMPTY_STRING,
    genreNames = genreIds?.let { genreIds.map {id -> mapGenre(id, genreMappings) } } ?: emptyList(),
    description = this.overview ?: EMPTY_STRING,
)

private fun mapGenre(id: Int, genreMappings: List<Genre>): String {
    return genreMappings.find { genre -> genre.genreId == id }?.genreName ?: EMPTY_STRING
}

