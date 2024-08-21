package com.example.movieapp.ui.moviedetails

data class MovieDetailsItem(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val rating: Double,
    val releaseDate: String,
    val isFavorite: Boolean,
)
