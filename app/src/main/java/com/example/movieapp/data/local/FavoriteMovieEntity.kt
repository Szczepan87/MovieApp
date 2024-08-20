package com.example.movieapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int
)
