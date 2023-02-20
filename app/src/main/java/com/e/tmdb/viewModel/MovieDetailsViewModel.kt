package com.e.tmdb.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.tmdb.models.movie.Movie
import com.e.tmdb.models.movieCredits.MovieCredits
import com.e.tmdb.models.movieDetails.MovieDetails
import com.e.tmdb.respository.MovieDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    private val details = MutableStateFlow<MovieDetails?>(null)
    private val credits = MutableStateFlow<MovieCredits?>(null)
    private val recommended = MutableStateFlow<List<Movie>>(emptyList())

    fun getMovieDetails(movieId: Int): MutableStateFlow<MovieDetails?> {
        viewModelScope.launch {
            movieDetailsRepository.fetchMovieDetails(movieId).collect {
                details.emit(it)
            }
        }
        return details
    }


    fun getMovieCredits(movieId: Int): MutableStateFlow<MovieCredits?> {
        viewModelScope.launch {
            movieDetailsRepository.fetchMovieCredits(movieId).collect {
                credits.emit(it)
            }
        }
        return credits
    }


    fun getRecommendedMovies(movieId: Int): MutableStateFlow<List<Movie>> {
        viewModelScope.launch {
            movieDetailsRepository.fetchRecommendedMovies(movieId).collect {
                recommended.emit(it)
            }
        }
        return recommended
    }
}
