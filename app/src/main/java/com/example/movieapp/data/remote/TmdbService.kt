package com.example.movieapp.data.remote

import com.example.movieapp.data.model.MovieDTO
import com.example.movieapp.data.model.MovieDetailsDTO
import com.example.movieapp.data.model.PageDTO

interface TmdbService {
    val provider: HttpClientProvider

    suspend fun getNowPlayingMovies(): PageDTO<MovieDTO>

    suspend fun getMovieDetails(movieId: Int): MovieDetailsDTO
}