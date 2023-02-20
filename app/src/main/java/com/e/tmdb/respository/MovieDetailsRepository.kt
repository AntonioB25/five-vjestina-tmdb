package com.e.tmdb.respository

import com.e.tmdb.database.dao.MovieDao
import com.e.tmdb.database.entity.MovieCastCrossRef
import com.e.tmdb.database.entity.MovieCountryCrossRef
import com.e.tmdb.database.entity.MovieCrewCrossRef
import com.e.tmdb.database.entity.MovieGenreCrossRef
import com.e.tmdb.models.movie.Movie
import com.e.tmdb.models.movie.toMovie
import com.e.tmdb.models.movieCredits.MovieCredits
import com.e.tmdb.models.movieCredits.toDbCastMember
import com.e.tmdb.models.movieCredits.toDbCrewMember
import com.e.tmdb.models.movieCredits.toMovieCredits
import com.e.tmdb.models.movieDetails.*
import com.e.tmdb.networking.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface MovieDetailsRepository {
    fun fetchMovieDetails(movieId: Int): Flow<MovieDetails>
    fun fetchMovieCredits(movieId: Int): Flow<MovieCredits>
    fun fetchRecommendedMovies(movieId: Int): Flow<List<Movie>>
    fun addMovieToFavourites(movieDetails: MovieDetails, movieCredits: MovieCredits)
    fun getMovieDetailsFromDatabase(movieId: Int): Flow<MovieDetails>
}

internal class MovieDetailsRepositoryImpl(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) : MovieDetailsRepository {


    override fun fetchMovieDetails(movieId: Int): Flow<MovieDetails> =
        flow {
            emit(movieApi.fetchMovieDetails(movieId).toMovieDetails())
        }


    override fun fetchMovieCredits(movieId: Int): Flow<MovieCredits> =
        flow {
            emit(movieApi.fetchMovieCredits(movieId).toMovieCredits())
        }


    override fun fetchRecommendedMovies(movieId: Int): Flow<List<Movie>> =
        flow {
            emit(movieApi.fetchRecommendedMovies(movieId).movies.map { it.toMovie(false) })
        }


    override fun addMovieToFavourites(movieDetails: MovieDetails, movieCredits: MovieCredits) {
        val movieId = movieDetails.id
        val genre = movieDetails.genres
        val countries = movieDetails.productionCountries
        val cast = movieCredits.cast
        val crew = movieCredits.crew

        movieDao.insertMovie(movieDetails.toDbMovie())                      // insert movie

        cast.forEach {
            movieDao.insertCastMember(it.toDbCastMember())                  // insert cast member
            movieDao.insertMovieCastCrossRef(                               // insert movieCastCrossRef
                MovieCastCrossRef(
                    movieId,
                    it.id
                )
            )
        }

        crew.forEach {
            movieDao.insertCrewMember(it.toDbCrewMember())                  // insert crew member
            movieDao.insertMovieCrewCrossRef(                               // insert movieCrewCrossRef
                MovieCrewCrossRef(
                    movieId,
                    it.id
                )
            )
        }

        genre.forEach {
            movieDao.insertGenre(it.toDbMovieGenre())                       // insert genre
            movieDao.insertMovieGenreCrossRef(                              // insert movieGenreCrossRef
                MovieGenreCrossRef(
                    movieId,
                    it.id
                )
            )
        }

        countries.forEach {
            movieDao.insertProductionCountry(it.toDbProductionCountry())    // insert production country
            movieDao.insertMovieCountryCrossRef(                            // insert movieCountryCrossRef
                MovieCountryCrossRef(
                    movieId,
                    it.iso
                )
            )
        }
    }

    override fun getMovieDetailsFromDatabase(movieId: Int): Flow<MovieDetails> =
         movieDao.getMovieDetails(movieId).map { it.toMovieDetails() }

}
