package com.example.movieapp.data.repository

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieDetails

interface TmdbRepository {
    suspend fun getNowPlayingMovies(page: Int): List<Movie>
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun isFavoriteMovieById(movieId: Int): Boolean
    suspend fun addFavoriteMovie(movieId: Int)
    suspend fun removeFavoriteMovie(movieId: Int)
    suspend fun removeAllFavoriteMovies()
}