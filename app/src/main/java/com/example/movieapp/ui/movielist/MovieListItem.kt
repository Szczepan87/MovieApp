package com.example.movieapp.ui.movielist

data class MovieListItem(
    val id: Int,
    val title: String,
    val posterPath: String,
    val isFavorite: Boolean,
)
