package com.example.movieapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.local.AppDatabase
import com.example.movieapp.data.local.FavoriteMovieDao
import com.example.movieapp.data.remote.TmdbService
import com.example.movieapp.data.remote.TmdbServiceImpl
import com.example.movieapp.data.repository.TmdbRepository
import com.example.movieapp.data.repository.TmdbRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "movie_app.db"
    ).build()

    @Provides
    @Singleton
    fun provideFavoriteMovieDao(database: AppDatabase): FavoriteMovieDao =
        database.favoriteMovieDao()

    @Binds
    @Singleton
    fun bindTmdbService(tmdbService: TmdbServiceImpl): TmdbService

    @Binds
    @Singleton
    fun bindTmdbRepository(tmdbRepository: TmdbRepositoryImpl): TmdbRepository
}