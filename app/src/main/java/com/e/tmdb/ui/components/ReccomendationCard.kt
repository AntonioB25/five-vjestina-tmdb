package com.e.tmdb.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.e.tmdb.models.movie.Movie


@Composable
fun RecommendationCard(movie: Movie) {

    Card(
        elevation = 5.dp,
        modifier = Modifier
            .width(180.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column() {
            Image(
                painter = rememberAsyncImagePainter(movie.posterPath),
                contentDescription = "Movie cover",
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(85.dp)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h2
            )
        }
    }
}

