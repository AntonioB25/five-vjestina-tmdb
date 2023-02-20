package com.e.tmdb.models.movieCredits

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrewMemberResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("job")
    val job: String,
    @SerialName("department")
    val department: String,
    @SerialName("profile_path")
    val profilePath: String?,
)
