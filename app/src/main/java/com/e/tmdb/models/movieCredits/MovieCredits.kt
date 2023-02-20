package com.e.tmdb.models.movieCredits

data class MovieCredits(
    val id: Int,
    val cast: List<CastMember>,
    val crew: List<CrewMember>
)

fun MovieCreditsResponse.toMovieCredits() = MovieCredits(
    id,
    cast.map { it.toCastMember() },
    crew.map { it.toCrewMember() }
)