package com.example.movieapp.ui.moviedetails

import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.AddMovieToFavoritesUseCase
import com.example.movieapp.domain.CheckIfMovieIsFavoriteUseCase
import com.example.movieapp.domain.GetMovieDetailsUseCase
import com.example.movieapp.domain.RemoveMovieFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
    private val checkIfMovieIsFavoriteUseCase: CheckIfMovieIsFavoriteUseCase,
): ViewModel() {

    private val _viewState = MutableStateFlow<MovieDetailsViewState>(MovieDetailsViewState())
    val viewState: StateFlow<MovieDetailsViewState> = _viewState

    fun markAsFavorite(movieId: Int) {

    }

    fun removeFavorite(movieId: Int) {

    }
}