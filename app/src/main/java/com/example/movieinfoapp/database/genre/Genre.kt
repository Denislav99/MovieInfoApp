package com.example.movieinfoapp.database.genre

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class Genre(
    @PrimaryKey @ColumnInfo(name = "genreId") val genreId: Int?,
    @ColumnInfo(name = "genreName") val genreName: String?) {

    fun isValid() = (genreId != null) && !genreName.isNullOrBlank()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Genre

        if (genreId != other.genreId) return false
        if (genreName != other.genreName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = genreId ?: 0
        result = 31 * result + (genreName?.hashCode() ?: 0)
        return result
    }
}