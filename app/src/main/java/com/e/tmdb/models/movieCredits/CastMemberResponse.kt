package com.e.tmdb.models.movieCredits

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastMemberResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("character")
    val characterName: String,
    @SerialName("profile_path")
    val profilePath: String?,
)
