package com.example.movieapp.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movie")
    suspend fun getAllFavoriteMovies(): List<FavoriteMovieEntity>

    @Query("SELECT * FROM favorite_movie WHERE movie_id = :movieId")
    suspend fun getFavoriteMovieById(movieId: Int): FavoriteMovieEntity?

    @Query("INSERT INTO favorite_movie (movie_id) VALUES (:movieId)")
    suspend fun addFavoriteMovie(movieId: Int)

    @Query("DELETE FROM favorite_movie WHERE movie_id = :movieId")
    suspend fun removeFavoriteMovie(movieId: Int)

    @Query("DELETE FROM favorite_movie")
    suspend fun removeAllFavoriteMovies()
}