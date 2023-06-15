package com.example.movieinfoapp.database.genre

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GenreDao {
    @Query("SELECT * FROM genre")
    fun getAllGenre(): LiveData<List<Genre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genres: List<Genre>)
}