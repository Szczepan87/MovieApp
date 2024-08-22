package com.example.movieapp.domain

import com.example.movieapp.data.local.FavoriteMovieDao
import com.example.movieapp.domain.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllFavoriteMoviesIdsUseCase @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val movieDao: FavoriteMovieDao
) {
    operator fun invoke(): Flow<Result<List<Int>>> =
        movieDao.getAllFavoriteMovies()
            .map { movieEntity -> Result.success(movieEntity.map { it.movieId }) }
            .catch { error -> emit(Result.failure(error)) }
            .flowOn(coroutineDispatcher)
}