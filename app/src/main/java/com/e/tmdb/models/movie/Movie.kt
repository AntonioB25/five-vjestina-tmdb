package com.e.tmdb.models.movie

import com.e.tmdb.database.entity.DbMovieTuple
import com.e.tmdb.networking.HttpRoutes

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    var isFavorite: Boolean
)

fun MovieResponse.toMovie(isFavorite: Boolean) = Movie(
    id,
    title,
    overview,
    posterPath.let { "${HttpRoutes.BASE_IMAGE_URL}$it" },
    isFavorite
)

fun DbMovieTuple.toMovie() = Movie(
    id,
    title,
    overview,
    posterPath,
    isFavorite
)
