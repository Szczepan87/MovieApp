package com.example.movieapp.data.remote

import com.example.movieapp.data.model.MovieDTO
import com.example.movieapp.data.model.MovieDetailsDTO
import com.example.movieapp.data.model.PageDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class TmdbServiceImpl @Inject constructor(): TmdbService {
    override val provider: HttpClientProvider
        get() = HttpClientProvider()

    override suspend fun getNowPlayingMovies(): PageDTO<MovieDTO> = provider.client
        .get("movie/now_playing")
        .body<PageDTO<MovieDTO>>()

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDTO = provider.client
        .get("movie/$movieId")
        .body<MovieDetailsDTO>()
}