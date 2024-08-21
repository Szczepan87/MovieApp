package com.example.movieapp.domain

import com.example.movieapp.data.model.MovieDetails
import com.example.movieapp.data.repository.TmdbRepository
import com.example.movieapp.domain.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val tmdbRepository: TmdbRepository
) {
    suspend operator fun invoke(movieID: Int): Flow<Result<MovieDetails>> {
        return flow { emit(Result.success(tmdbRepository.getMovieDetails(movieID))) }
            .flowOn(dispatcher)
            .catch { error -> emit(Result.failure(error)) }
    }
}