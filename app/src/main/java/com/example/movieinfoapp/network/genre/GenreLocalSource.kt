package com.example.movieinfoapp.network.genre

import com.example.movieinfoapp.database.genre.Genre
import com.example.movieinfoapp.database.genre.GenreDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GenreLocalSource constructor(private val database: GenreDataBase) {
    val genres = database.genreDao().getAllGenre()

    suspend fun insertAll(genres: List<Genre>) {
        withContext(Dispatchers.IO) {
            database.genreDao().insertGenres(genres)
        }
    }
}