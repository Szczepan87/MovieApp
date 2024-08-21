package com.example.movieapp.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.AddMovieToFavoritesUseCase
import com.example.movieapp.domain.CheckIfMovieIsFavoriteUseCase
import com.example.movieapp.domain.GetNowPlayingMoviesUseCase
import com.example.movieapp.domain.RemoveMovieFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
    private val checkIfMovieIsFavoriteUseCase: CheckIfMovieIsFavoriteUseCase,
): ViewModel() {

    private val _viewState = MutableStateFlow<MovieListViewState>(MovieListViewState())
    val viewState: StateFlow<MovieListViewState> = _viewState

    fun markAsFavorite(movieId: Int) {
        viewModelScope.launch {

        }
    }

    fun removeFavorite(movieId: Int) {
        viewModelScope.launch {

        }
    }

    fun loadMovies(page: Int) {
        viewModelScope.launch {

        }
    }

}