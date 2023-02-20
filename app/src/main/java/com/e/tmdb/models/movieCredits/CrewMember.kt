package com.e.tmdb.models.movieCredits

import com.e.tmdb.database.entity.DbCrewMember

data class CrewMember(
    val id: Int,
    val name: String,
    val job: String,
    val department: String,
    val profilePath: String?,
)

fun CrewMemberResponse.toCrewMember() = CrewMember(
    id,
    name,
    job,
    department,
    profilePath
)

fun CrewMember.toDbCrewMember() = DbCrewMember(
    id,
    name,
    job,
    department,
)