package com.example.movieapp.ui.movielist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.R

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val viewState = viewModel.viewState.collectAsState()
        LaunchedEffect(key1 = true) {
            viewModel.loadMovies(1)
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewState.value.movies) { movie ->
                MovieListViewItem(
                    modifier = Modifier.padding(8.dp),
                    movie = movie,
                    onFavoriteClick = {
                        if (movie.isFavorite) {
                            viewModel.removeFavorite(movie.id)
                        } else {
                            viewModel.markAsFavorite(movie.id)
                        }
                    },
                    onClick = {
                        navController.navigate("movie/${movie.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun MovieListViewItem(
    modifier: Modifier = Modifier,
    movie: MovieListItem,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .clickable(onClick = onClick)
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            AsyncImage(model = movie.posterPath, contentDescription = "Movie poster")
            Image(painter = painterResource(
                id = if (movie.isFavorite) {
                    R.drawable.baseline_star_24
                } else {
                    R.drawable.baseline_star_border_24
                }
            ), contentDescription = "Favrite icon", modifier = Modifier
                .padding(8.dp)
                .align(
                    Alignment.TopEnd
                )
                .clickable { onFavoriteClick() })
        }
        Text(text = movie.title, modifier = Modifier.padding(vertical = 8.dp))
    }
}
