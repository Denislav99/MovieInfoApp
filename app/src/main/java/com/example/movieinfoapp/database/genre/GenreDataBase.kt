package com.example.movieinfoapp.database.genre

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieinfoapp.database.genre.Genre
import com.example.movieinfoapp.database.genre.GenreDao

@Database(entities = [Genre::class], version = 1)
abstract class GenreDataBase: RoomDatabase() {
    abstract fun genreDao(): GenreDao
}