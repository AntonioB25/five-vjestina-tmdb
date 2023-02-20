package com.e.tmdb.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.e.tmdb.R
import com.e.tmdb.models.movieCredits.CastMember
import com.e.tmdb.models.movieCredits.MovieCredits
import com.e.tmdb.models.movieDetails.MovieDetails
import com.e.tmdb.viewModel.MovieDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieDetails(
    movieId: Int,
) {
    val detailsViewModel = getViewModel<MovieDetailsViewModel>()
    val movieDetails = detailsViewModel.getMovieDetails(movieId).collectAsState().value
    val movieCredits = detailsViewModel.getMovieCredits(movieId).collectAsState().value

    LazyColumn() {
        item {
            if (movieDetails != null && movieCredits != null) {
                ImageAndInfo(movieDetails)
                Spacer(Modifier.padding(10.dp))

                Overview(Modifier.padding(start = 10.dp), movieDetails, movieCredits)
                Spacer(Modifier.padding(10.dp))

                Cast(Modifier.padding(start = 10.dp), movieCredits)
                Spacer(Modifier.padding(10.dp))

                //Social(Modifier.padding(start = 10.dp))
                Spacer(Modifier.padding(10.dp))

                Recommendations(movieId, detailsViewModel)
            } else {
                Text(text = stringResource(R.string.error_message_details))
            }
        }
    }
}


@Composable
fun ImageAndInfo(
    movieDetails: MovieDetails
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Center
    ) {

        Image(
            painter = rememberAsyncImagePainter(movieDetails.backDropPath),
            contentDescription = stringResource(id = R.string.movie_cover),
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = size.height / 3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                },
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop
        )

        Column(
            Modifier
                .align(BottomStart)
                .fillMaxWidth()
                .padding(start = 30.dp)

        ) {
            Row(verticalAlignment = CenterVertically) {
                CircularProgressIndicator(color = Color.Green, progress = 0.74f)
                Text(
                    text = stringResource(R.string.label_user_score),
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(start = 10.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = movieDetails.title,
                fontSize = 20.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = movieDetails.releaseDate + " (" + movieDetails.productionCountries[0].iso + ")",
                color = Color.White
            )
            Text(
                text = movieDetails.genres.joinToString(",") { it.name },
                color = Color.White
            )
            Text(
                text = getFormattedTime(movieDetails.runtime),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            StarButton(modifier = Modifier.padding(top = 20.dp, bottom = 10.dp))
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Overview(
    modifier: Modifier,
    movieDetails: MovieDetails,
    movieCredits: MovieCredits
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.label_overview),
        style = MaterialTheme.typography.h1,
    )

    Spacer(Modifier.padding(10.dp))
    Text(
        modifier = modifier,
        text = movieDetails.overview
    )

    Spacer(Modifier.padding(10.dp))

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(movieCredits.crew) { member -> // should I show all crew members, or only some?
            StaffCard(
                name = member.name,
                job = member.job
            )
        }
    }
}

@Composable
fun StaffCard(name: String, job: String) {
    Column {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(text = job)
    }
}


@Composable
fun Cast(
    modifier: Modifier,
    movieCredits: MovieCredits
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = CenterVertically) {
            Text(
                text = stringResource(R.string.label_top_billed),
                style = MaterialTheme.typography.h1,
            )

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null,
                onClick = {
                    //TODO: show full cast & crew
                },
            ) {
                Text(
                    text = stringResource(R.string.label_full_cast),
                    style = MaterialTheme.typography.h2
                )
            }
        }

        CastList(list = movieCredits.cast)
    }
}


@Composable
fun Social(modifier: Modifier) {
    Column(modifier) {
        Text(
            text = "Social",
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Row {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null,
                onClick = {
                    //TODO: show reviews
                },
            ) {
                Text(text = "Reviews", style = MaterialTheme.typography.h6)
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null,
                onClick = {
                    //TODO: show discussions
                },
            ) {
                Text(text = "Discussions", style = MaterialTheme.typography.h6)
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))
        ReviewCard()
    }
}


@Composable
fun Recommendations(
    movieId: Int,
    detailsViewModel: MovieDetailsViewModel
) {
    val recommendedMovies = detailsViewModel.getRecommendedMovies(movieId).collectAsState()
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Text(
            text = stringResource(R.string.label_recommendtations),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.padding(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recommendedMovies.value) { movie ->
                RecommendationCard(movie = movie)
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CastList(list: List<CastMember>) {
    LazyRow(
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(list) { member ->
            CastMemberCard(modifier = Modifier, member = member)
        }
    }
}

@Composable
fun StarButton(
    modifier: Modifier,
    color: Color = Color.White
) {
    var isStar by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isStar,
        onCheckedChange = { isStar = !isStar },
        modifier = modifier
            .clip(CircleShape)
            .background(Color(0xFF757575))

    ) {
        Icon(
            tint = color,
            imageVector = if (isStar) {
                Icons.Outlined.Star
            } else {
                Icons.Outlined.Star
            },
            contentDescription = null
        )
    }
}


fun getFormattedTime(minutes: Int?): String {
    if (minutes == null) return "N.A"
    val hours: Int = minutes / 60
    val mins: Int = minutes % 60
    return ("%d h %02d m").format(hours, mins)
}

