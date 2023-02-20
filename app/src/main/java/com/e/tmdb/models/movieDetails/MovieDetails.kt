package com.e.tmdb.models.movieDetails

import com.e.tmdb.database.entity.DbMovie
import com.e.tmdb.database.entity.DbMovieDetails
import com.e.tmdb.networking.HttpRoutes

data class MovieDetails(
    val id: Int,
    val title: String,
    val backDropPath: String?,
    val posterPath: String,
    val genres: List<MovieGenre>,
    val originalLanguage: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Int?,
    val productionCountries: List<ProductionCountry>
)

fun MovieDetailsResponse.toMovieDetails() = MovieDetails(
    id,
    title,
    backDropPath.let { "${HttpRoutes.BASE_IMAGE_URL}$it" },
    posterPath.let { "${HttpRoutes.BASE_IMAGE_URL}$it" },
    genres,
    originalLanguage,
    overview,
    releaseDate,
    runtime,
    productionCountries.map { it.toProductionCompany() }
)

fun MovieDetails.toDbMovie() = DbMovie(
    id,
    title,
    posterPath,
    backDropPath,
    originalLanguage,
    overview,
    releaseDate,
    runtime,
    true
)

fun DbMovieDetails.toMovieDetails() = MovieDetails(
    movie.movieId,
    movie.title,
    movie.backdropPath,
    movie.posterPath,
    genres.map { it.toMovieGenre() },
    movie.originalLanguage,
    movie.overview,
    movie.releaseDate,
    movie.runtime,
    productionCountries.map { it.toProductionCompany() }
)




