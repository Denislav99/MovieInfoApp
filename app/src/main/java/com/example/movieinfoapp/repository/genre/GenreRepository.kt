package com.example.movieinfoapp.repository.genre

import android.content.Context
import android.widget.Toast
import com.example.movieinfoapp.database.genre.Genre
import com.example.movieinfoapp.network.genre.GenreLocalSource
import com.example.movieinfoapp.network.genre.GenreRemoteSource
import com.example.movieinfoapp.preferences.AppPreferences

class GenreRepository(
    private val localSource: GenreLocalSource,
    private val remoteSource: GenreRemoteSource
) {

    val genres = localSource.genres

    suspend fun updateGenresFromApi(context: Context) {
        try {
            val genresResponse = remoteSource.getGenres()
            if (genresResponse.isSuccessful) {
                val genres = genresResponse.body()?.genres?.map { Genre(it.id, it.name) }?.filter { it.isValid() }
                if (genres.isNullOrEmpty())
                    throw Exception("Empty genres response.")

                AppPreferences.lastGenresFetchedMs = System.currentTimeMillis()
                localSource.insertAll(genres)
            } else
                throw Exception("Unable to fetch genres.")
        } catch (ex: Exception) {
            Toast.makeText(context, "Unable to fetch genres from api.", Toast.LENGTH_LONG).show()
        }
    }
}