package com.example.movieapp.ui.moviedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.R

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    navController: NavController
) {
    val viewState by viewModel.viewState.collectAsState()
    MovieDetailsContent(viewState = viewState, onFavoriteClick = {
        if (viewState.isMovieFavorite) {
            viewState.movieDetails?.id?.let { viewModel.removeFavorite(it) }
        } else {
            viewState.movieDetails?.id?.let { viewModel.markAsFavorite(it) }
        }
    })

}

@Composable
fun MovieDetailsContent(viewState: MovieDetailsViewState, onFavoriteClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        MovieDetailsLoading(Modifier.wrapContentSize(align = Alignment.Center))
        Column(modifier = Modifier.fillMaxWidth()) {
            MovieDetailsHeader(
                movieDetails = viewState.movieDetails,
                isMovieFavorite = viewState.isMovieFavorite,
                onFavoriteClick = onFavoriteClick
            )
            MovieDetailsBody(movieDetails = viewState.movieDetails)
        }
    }
}

@Composable
fun MovieDetailsHeader(movieDetails: MovieDetailsItem?, isMovieFavorite: Boolean, onFavoriteClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = movieDetails?.posterPath,
            contentDescription = "Movie poster"
        )
        Image(
            modifier = Modifier
                .clickable { onFavoriteClick() }
                .align(Alignment.TopEnd)
                .padding(top = 32.dp, end = 32.dp),
            painter = painterResource(
                id = if (isMovieFavorite) {
                    R.drawable.baseline_star_24
                } else {
                    R.drawable.baseline_star_border_24
                }
            ),
            contentDescription = "Favorite icon"
        )
    }
}

@Composable
fun MovieDetailsBody(movieDetails: MovieDetailsItem?) {
    Column(modifier = Modifier.padding(16.dp)) {
        movieDetails?.let {
            MovieDetailsTextItem(label = "Title:", value = it.title)
            MovieDetailsTextItem(label = "Release date:", value = it.releaseDate)
            MovieDetailsTextItem(label = "Rating:", value = it.rating.toString())
            MovieDetailsTextItem(label = "Overview: ", value = it.overview)
        }
    }
}

@Composable
fun MovieDetailsTextItem(modifier: Modifier = Modifier, label: String, value: String) {
    Column(modifier = modifier) {
        Text(modifier = Modifier.padding(vertical = 16.dp), text = label)
        Text(text = value)
    }
}

@Composable
fun MovieDetailsLoading(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Loading..."
        )
    }
}