package com.example.movieapp.data.di

import com.example.movieapp.data.remote.TmdbService
import com.example.movieapp.data.remote.TmdbServiceImpl
import com.example.movieapp.data.repository.TmdbRepository
import com.example.movieapp.data.repository.TmdbRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTmdbService(tmdbService: TmdbServiceImpl): TmdbService

    @Binds
    @Singleton
    fun bindTmdbRepository(tmdbRepository: TmdbRepositoryImpl): TmdbRepository
}