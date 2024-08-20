package com.example.movieapp.data.repository

import com.example.movieapp.data.local.FavoriteMovieDao
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieDetails
import com.example.movieapp.data.remote.TmdbService
import javax.inject.Inject

class TmdbRepositoryImpl @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao,
    private val tmdbApi: TmdbService
): TmdbRepository {
    override suspend fun getNowPlayingMovies(): List<Movie> {
        val nowPlayingMovies = tmdbApi.getNowPlayingMovies().resultList
        return nowPlayingMovies.map {
            it.toMovie()
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val movieDetailsDTO = tmdbApi.getMovieDetails(movieId)
        return movieDetailsDTO.toMovieDetails()
    }

    override suspend fun isFavoriteMovieById(movieId: Int): Boolean {
        return favoriteMovieDao.getFavoriteMovieById(movieId) != null
    }

    override suspend fun addFavoriteMovie(movieId: Int) {
        favoriteMovieDao.addFavoriteMovie(movieId)
    }

    override suspend fun removeFavoriteMovie(movieId: Int) {
        favoriteMovieDao.removeFavoriteMovie(movieId)
    }

    override suspend fun removeAllFavoriteMovies() {
        favoriteMovieDao.removeAllFavoriteMovies()
    }
}