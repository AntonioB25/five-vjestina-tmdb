package com.e.tmdb.respository

import com.e.tmdb.database.dao.MovieDao
import com.e.tmdb.models.movie.Movie
import com.e.tmdb.models.movie.toMovie
import com.e.tmdb.networking.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface MovieRepository {
    fun fetchPopularMovies(): Flow<List<Movie>>
    fun fetchTopRatedMovies(): Flow<List<Movie>>
    fun fetchUpcomingMovies(): Flow<List<Movie>>
    fun fetchNowPlayingMovies(): Flow<List<Movie>>
    fun fetchSearchMovie(query: String): Flow<List<Movie>>
    fun fetchFavouriteMovies(): Flow<List<Movie>>
    fun removeMovieFromFavourites(movieId: Int)
}

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
) : MovieRepository {

    override fun fetchPopularMovies(): Flow<List<Movie>> =
        flow {
            emit(movieApi.fetchPopularMovies().movies.map {
                it.toMovie(false)
            })
        }


    override fun fetchTopRatedMovies(): Flow<List<Movie>> =
        flow {
            emit(movieApi.fetchTopRatedMovies().movies.map {
                it.toMovie(false)
            })
        }


    override
    fun fetchUpcomingMovies(): Flow<List<Movie>> =
        flow {
            emit(movieApi.fetchUpcomingMovies().movies.map {
                it.toMovie(false)
            })
        }


    override
    fun fetchNowPlayingMovies(): Flow<List<Movie>> =
        flow {
            emit(movieApi.fetchNowPlayingMovies().movies.map {
                it.toMovie(false)
            })
        }


    override
    fun fetchFavouriteMovies(): Flow<List<Movie>> {
        return movieDao.getFavouriteMovies().map { list -> list.map { it.toMovie() } }
    }


    override fun removeMovieFromFavourites(movieId: Int) {
        movieDao.deleteMovie(movieId)
    }


    override
    fun fetchSearchMovie(query: String): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }
}