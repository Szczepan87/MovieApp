package com.example.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

}