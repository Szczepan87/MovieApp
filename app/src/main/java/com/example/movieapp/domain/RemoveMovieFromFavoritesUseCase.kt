package com.example.movieapp.domain

import com.example.movieapp.data.repository.TmdbRepository
import com.example.movieapp.domain.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoveMovieFromFavoritesUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val tmdbRepository: TmdbRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<Result<Unit>> =
        flow { emit(Result.success(tmdbRepository.removeFavoriteMovie(movieId))) }
            .flowOn(dispatcher)
            .catch { error ->
                emit(Result.failure(error))
            }
}