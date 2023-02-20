package com.e.tmdb.models.movieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryResponse(
    @SerialName("iso_3166_1")
    val iso: String,
    @SerialName("name")
    val name: String
)