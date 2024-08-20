package com.example.movieapp.data.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val overview: String,
    val rating: Double,
    val releaseDate: String,
)
