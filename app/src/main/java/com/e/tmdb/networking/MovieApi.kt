package com.e.tmdb.networking

import com.e.tmdb.models.MovieSearchResponse
import com.e.tmdb.models.movie.MovieListResponse
import com.e.tmdb.models.movieCredits.MovieCreditsResponse
import com.e.tmdb.models.movieDetails.MovieDetailsResponse
import io.ktor.client.*
import io.ktor.client.request.*

interface MovieApi {
    suspend fun fetchPopularMovies(): MovieListResponse
    suspend fun fetchNowPlayingMovies(): MovieListResponse
    suspend fun fetchUpcomingMovies(): MovieListResponse
    suspend fun fetchTopRatedMovies(): MovieListResponse
    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsResponse
    suspend fun fetchMovieCredits(movieId: Int): MovieCreditsResponse
    suspend fun fetchSearchMovie(query: String): MovieSearchResponse
    suspend fun fetchRecommendedMovies(movieId: Int): MovieListResponse
}

internal class MovieApiImpl(
    private val client: HttpClient
) : MovieApi {


    override suspend fun fetchPopularMovies(): MovieListResponse =
        client.get(
            "${HttpRoutes.POPULAR_MOVIES}?api_key=${HttpRoutes.API_KEY}"
        )


    override suspend fun fetchNowPlayingMovies(): MovieListResponse =
        client.get(
            "${HttpRoutes.NOW_PLAYING_MOVIES}?api_key=${HttpRoutes.API_KEY}"
        )

    override suspend fun fetchUpcomingMovies(): MovieListResponse =
        client.get(
            "${HttpRoutes.UPCOMING_MOVIES}?api_key=${HttpRoutes.API_KEY}"
        )

    override suspend fun fetchTopRatedMovies(): MovieListResponse =
        client.get(
            "${HttpRoutes.TOP_RATED_MOVIES}?api_key=${HttpRoutes.API_KEY}"
        )

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailsResponse =
        client.get(
            "${HttpRoutes.BASE_URL}/movie/$movieId?api_key=${HttpRoutes.API_KEY}"
        )

    override suspend fun fetchMovieCredits(movieId: Int): MovieCreditsResponse =
        client.get(
            "${HttpRoutes.BASE_URL}/movie/$movieId/credits?api_key=${HttpRoutes.API_KEY}"
        )

    override suspend fun fetchSearchMovie(query: String): MovieSearchResponse =
        client.get(
            "${HttpRoutes.BASE_URL}/search/movie/?query=$query?api_key=${HttpRoutes.API_KEY}"
        )

    override suspend fun fetchRecommendedMovies(movieId: Int): MovieListResponse =
        client.get(
            "${HttpRoutes.BASE_URL}/movie/$movieId/recommendations?api_key=${HttpRoutes.API_KEY}"
        )

}

