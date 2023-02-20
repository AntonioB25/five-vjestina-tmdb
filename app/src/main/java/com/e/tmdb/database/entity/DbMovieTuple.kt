package com.e.tmdb.database.entity

import androidx.room.ColumnInfo

/**
 * Object that stores data needed for movie card
 * Used in MovieDao
 */
data class DbMovieTuple(
    @ColumnInfo(name = "movie_id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "is_favourite") var isFavorite: Boolean,
    @ColumnInfo(name = "poster_path") val posterPath: String
)
