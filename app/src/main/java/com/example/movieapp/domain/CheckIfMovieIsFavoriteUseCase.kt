package com.example.movieapp.domain

import com.example.movieapp.data.repository.TmdbRepository
import javax.inject.Inject

class CheckIfMovieIsFavoriteUseCase @Inject constructor(
    private val tmdbRepository: TmdbRepository
) {
    suspend operator fun invoke(movieId: String): Boolean {
        return tmdbRepository.isFavoriteMovieById(movieId)
    }
}