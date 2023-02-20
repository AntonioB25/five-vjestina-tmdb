package com.e.tmdb.models.movieDetails

import com.e.tmdb.database.entity.DbGenre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

fun MovieGenre.toDbMovieGenre() = DbGenre(
    id,
    name
)

fun DbGenre.toMovieGenre() = MovieGenre(
    genreId,
    genreName
)
