package com.e.tmdb.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "movie_genre")
data class DbGenre(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "genre_id")
    val genreId: Int,
    @SerialName("genre_name")
    val genreName: String
)