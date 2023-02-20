package com.e.tmdb.ui.components

import MovieCard
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.e.tmdb.models.movie.Movie

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieList(
    list: List<Movie>,
    navigateToDetails: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(list) { movie ->
            MovieCard(
                modifier = Modifier
                    .height(180.dp)
                    .width(120.dp)
                    .padding(bottom = 10.dp),
                item = movie,
                navigateToDetails = navigateToDetails
            )
        }
    }
}

