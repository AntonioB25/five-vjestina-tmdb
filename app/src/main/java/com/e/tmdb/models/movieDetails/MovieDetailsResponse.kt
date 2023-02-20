package com.e.tmdb.models.movieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("backdrop_path")
    val backDropPath: String?,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("genres")
    val genres: List<MovieGenre>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("runtime")
    val runtime: Int?,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryResponse>
)
