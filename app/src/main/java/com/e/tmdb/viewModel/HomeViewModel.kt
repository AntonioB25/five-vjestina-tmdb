package com.e.tmdb.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.tmdb.models.movie.Movie
import com.e.tmdb.models.movieCredits.MovieCredits
import com.e.tmdb.models.movieDetails.MovieDetails
import com.e.tmdb.respository.MovieDetailsRepository
import com.e.tmdb.respository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    init {
        getPopularMovies()
        getTopRatedMovies()
        getNowPlayingMovies()
        getUpcomingMovies()
        getFavouriteMovies()
    }

    val favourites = MutableStateFlow<List<Movie>>(emptyList())
    val popular = MutableStateFlow<List<Movie>>(emptyList())
    val upcoming = MutableStateFlow<List<Movie>>(emptyList())
    val topRated = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlaying = MutableStateFlow<List<Movie>>(emptyList())


    private fun getPopularMovies() {
        viewModelScope.launch {
            movieRepository.fetchPopularMovies().collect { popular.emit(it) }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            movieRepository.fetchTopRatedMovies().collect { topRated.emit(it) }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            movieRepository.fetchNowPlayingMovies().collect { nowPlaying.emit(it) }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            movieRepository.fetchUpcomingMovies().collect { upcoming.emit(it) }
        }
    }


    private fun getFavouriteMovies() {
        viewModelScope.launch {
            movieRepository.fetchFavouriteMovies().collect {
                favourites.emit(it)
            }
        }
    }

    fun addMovieToFavourites(movieId: Int) {
        val movieCredits = MutableStateFlow<MovieCredits?>(null)
        val movieDetails = MutableStateFlow<MovieDetails?>(null)

        CoroutineScope(Dispatchers.IO).launch {
            movieDetailsRepository.fetchMovieCredits(movieId).collect { movieCredits.emit(it) }
            movieDetailsRepository.fetchMovieDetails(movieId).collect { movieDetails.emit(it) }

            movieDetails.value?.let { movieDetails ->
                movieCredits.value?.let { movieCredits ->
                    movieDetailsRepository.addMovieToFavourites(
                        movieDetails,
                        movieCredits
                    )
                }
            }
        }
    }

    fun removeMovieFromFavourite(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.removeMovieFromFavourites(movieId)
        }
    }
}
