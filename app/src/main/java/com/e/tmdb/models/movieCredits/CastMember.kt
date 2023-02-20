package com.e.tmdb.models.movieCredits

import com.e.tmdb.database.entity.DbCastMember
import com.e.tmdb.networking.HttpRoutes

data class CastMember(
    val id: Int,
    val name: String,
    val characterName: String,
    val profilePath: String?,
)


fun CastMemberResponse.toCastMember() = CastMember(
    id,
    name,
    characterName,
    profilePath.let { "${HttpRoutes.BASE_IMAGE_URL}$it" },
)

fun CastMember.toDbCastMember() = DbCastMember(
    id,
    name,
    characterName,
    profilePath
)
