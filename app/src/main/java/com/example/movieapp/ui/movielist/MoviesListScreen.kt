package com.example.movieapp.ui.movielist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val viewState = viewModel.viewState.collectAsState()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewState.value.movies) { movie ->
                MovieListViewItem(
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

}
