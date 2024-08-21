package com.example.movieapp.ui.moviedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    navController: NavController
) {
    val viewState by viewModel.viewState.collectAsState()
    MovieDetailsContent(viewState = viewState, onFavoriteClick = {
        if (viewState.movieDetails?.isFavorite == true) {
            viewModel.removeFavorite(viewState.movieDetails.id)
        } else {
            viewModel.markAsFavorite(viewState.movieDetails.id)
        }
    })

}

@Composable
fun MovieDetailsContent(viewState: MovieDetailsViewState, onFavoriteClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        MovieDetailsHeader(
            movieDetails = viewState.movieDetails,
            onFavoriteClick = onFavoriteClick
        )
        MovieDetailsBody(movieDetails = viewState.movieDetails)
    }
}

@Composable
fun MovieDetailsHeader(movieDetails: MovieDetailsItem?, onFavoriteClick: () -> Unit) {
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
            painter = painterResource(id =), // gwiazdka
            contentDescription = "Favorite icon"
        )
    }
}

@Composable
fun MovieDetailsBody(movieDetails: MovieDetailsItem?) {
    Column(modifier = Modifier.padding(16.dp)) {
        movieDetails?.let {
            MovieDetailsRow(label = "Title:", value = it.title)
            MovieDetailsRow(label = "Release date:", value = it.releaseDate)
            MovieDetailsRow(label = "Rating:", value = it.rating.toString())
            MovieDetailsRow(label = "Overview: ", value = it.overview)
        }
    }
}

@Composable
fun MovieDetailsRow(modifier: Modifier = Modifier, label: String, value: String) {
    Column(modifier = modifier) {
        Text(modifier = Modifier.padding(vertical = 16.dp), text = label)
        Text(text = value)
    }
}

@Composable
fun MovieDetailsLoading() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Loading..."
        )
    }
}