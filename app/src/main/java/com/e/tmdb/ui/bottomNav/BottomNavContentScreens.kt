package com.e.tmdb.ui.bottomNav

import MovieCard
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.e.tmdb.R
import com.e.tmdb.models.movie.Movie
import com.e.tmdb.ui.components.MovieList
import com.e.tmdb.ui.components.PopularList
import com.e.tmdb.ui.components.SearchBar
import com.e.tmdb.viewModel.HomeViewModel
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    navigateToDetails: (Int) -> Unit,
) {
    val homeViewModel = getViewModel<HomeViewModel>()
    val popular = homeViewModel.popular.collectAsState()
    val nowPlaying = homeViewModel.nowPlaying.collectAsState()
    val upcoming = homeViewModel.upcoming.collectAsState()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        SearchBar(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        PopularList(
            movieList = popular.value,
            navigateToDetails = navigateToDetails
        )

        MovieCategory(
            categoryTitle = stringResource(id = R.string.category_now_playing),
            movieList = nowPlaying.value,
            navigateToDetails = navigateToDetails
        )

        MovieCategory(
            categoryTitle = stringResource(id = R.string.category_upcoming),
            movieList = upcoming.value,
            navigateToDetails = navigateToDetails
        )
    }
}

@Composable
fun MovieCategory(
    categoryTitle: String,
    movieList: List<Movie>,
    navigateToDetails: (Int) -> Unit
) {
    Text(
        text = categoryTitle,
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(start = 10.dp)
    )
    MovieList(list = movieList, navigateToDetails = navigateToDetails)
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun FavouritesScreen(
    navigateToDetails: (Int) -> Unit,
) {
    val homeViewModel = getViewModel<HomeViewModel>()
    val favouriteMovies = homeViewModel.favourites.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.label_favourites),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(3.dp)
        ) {
            items(favouriteMovies.value) { movie ->
                MovieCard(
                    item = movie,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(250.dp),
                    navigateToDetails = navigateToDetails
                )
            }
        }
    }
}
