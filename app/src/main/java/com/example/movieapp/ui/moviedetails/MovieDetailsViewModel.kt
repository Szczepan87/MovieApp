package com.example.movieapp.ui.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.AddMovieToFavoritesUseCase
import com.example.movieapp.domain.CheckIfMovieIsFavoriteUseCase
import com.example.movieapp.domain.GetMovieDetailsUseCase
import com.example.movieapp.domain.RemoveMovieFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieToFavoritesUseCase: AddMovieToFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
    private val checkIfMovieIsFavoriteUseCase: CheckIfMovieIsFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState = MutableStateFlow<MovieDetailsViewState>(MovieDetailsViewState())
    val viewState: StateFlow<MovieDetailsViewState> = _viewState

    private val movieId: Int = requireNotNull(savedStateHandle.get<Int>("movieId"))

    init {
        viewModelScope.launch {
            checkIfMovieIsFavoriteUseCase(movieId).onEach { isFavoriteResult ->
                if (isFavoriteResult.isSuccess) {
                    _viewState.update {
                        it.copy(isMovieFavorite = isFavoriteResult.getOrDefault(false))
                    }
                }
            }
            getMovieDetailsUseCase(movieId).onEach { movieDetailsResult ->
                if (movieDetailsResult.isSuccess) {
                    val movieDetails = movieDetailsResult.getOrNull()
                    _viewState.update {
                        it.copy(
                            movieDetails = MovieDetailsItem(
                                id = movieDetails?.id ?: 0,
                                title = movieDetails?.title ?: "",
                                overview = movieDetails?.overview ?: "",
                                posterPath = movieDetails?.imageUrl ?: "",
                                rating = movieDetails?.rating ?: 0.0,
                                releaseDate = movieDetails?.releaseDate ?: "",
                            )
                        )
                    }
                }
            }
        }
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
}