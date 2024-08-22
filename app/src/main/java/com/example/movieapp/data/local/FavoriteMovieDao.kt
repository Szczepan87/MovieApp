package com.example.movieapp.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movie")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM favorite_movie WHERE movie_id = :movieId")
    suspend fun getFavoriteMovieById(movieId: Int): FavoriteMovieEntity?

    @Query("INSERT INTO favorite_movie (movie_id) VALUES (:movieId)")
    suspend fun addFavoriteMovie(movieId: Int)

    @Query("DELETE FROM favorite_movie WHERE movie_id = :movieId")
    suspend fun removeFavoriteMovie(movieId: Int)

    @Query("DELETE FROM favorite_movie")
    suspend fun removeAllFavoriteMovies()
}