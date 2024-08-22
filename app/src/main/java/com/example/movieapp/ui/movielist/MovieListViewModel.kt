package com.example.movieapp.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.AddMovieToFavoritesUseCase
import com.example.movieapp.domain.GetNowPlayingMoviesUseCase
import com.example.movieapp.domain.RemoveMovieFromFavoritesUseCase
import com.example.movieapp.domain.GetAllFavoriteMoviesIdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
    getAllFavoriteMoviesIdsUseCase: GetAllFavoriteMoviesIdsUseCase,
): ViewModel() {

    private val _viewState = MutableStateFlow<MovieListViewState>(MovieListViewState())
    val viewState: StateFlow<MovieListViewState> = _viewState

    init {
        getAllFavoriteMoviesIdsUseCase().onEach { favoriteMoviesResult ->
            if (favoriteMoviesResult.isSuccess) {
                val favoriteMovies = favoriteMoviesResult.getOrDefault(emptyList())
                _viewState.value = _viewState.value.copy(favoriteMoviesIds = favoriteMovies)
            }
        }
        loadMovies(1)
    }

    fun markAsFavorite(movieId: Int) {
        viewModelScope.launch {
            addMovieToFavoritesUseCase(movieId)
        }
    }

    fun removeFavorite(movieId: Int) {
        viewModelScope.launch {
            removeMovieFromFavoritesUseCase(movieId)
        }
    }

    fun loadMovies(page: Int) {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase(page).onEach { moviesResult ->
                if (moviesResult.isSuccess) {
                    val movies = moviesResult.getOrDefault(emptyList())
                    _viewState.value = _viewState.value.copy(movies = movies.map{
                        MovieListItem(id = it.id, title = it.title, posterPath = it.imageUrl, isFavorite = isFavorite(it.id))
                    }, currentPage = page)
                }
            }
        }
    }

    private fun isFavorite(id: Int): Boolean =
        _viewState.value.favoriteMoviesIds.contains(id)
}