package com.example.movieapp.ui.movielist

data class MovieListViewState(
    val isLoading: Boolean = false,
    val movies: List<MovieListItem> = emptyList(),
    val currentPage: Int = 1,
    val favoriteMoviesIds: List<Int> = emptyList(),
)
