package com.example.movieapp.ui.moviedetails

data class MovieDetailsViewState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetailsItem? = null,
    val isMovieFavorite: Boolean = false,
)
