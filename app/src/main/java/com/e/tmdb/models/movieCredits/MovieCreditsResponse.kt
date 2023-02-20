package com.e.tmdb.models.movieCredits

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieCreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<CastMemberResponse>,
    @SerialName("crew")
    val crew: List<CrewMemberResponse>
)