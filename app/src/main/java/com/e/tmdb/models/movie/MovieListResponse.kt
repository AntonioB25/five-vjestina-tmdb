package com.e.tmdb.models.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    @SerialName("results")
    val movies: List<MovieResponse>
)