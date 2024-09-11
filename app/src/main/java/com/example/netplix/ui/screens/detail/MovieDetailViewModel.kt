package com.example.netplix.ui.screens.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netplix.data.repository.MovieRepository
import com.example.netplix.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _state = mutableStateOf(MovieDetailViewState())
    val state: State<MovieDetailViewState> = _state

    fun getDetails(movieId: String) {
        getMovieDetails(movieId)
        getMovieVideos(movieId)
    }

    private fun getMovieVideos(movieId: String) {
        val videosResponse = movieRepository.getVideos(movieId)
        videosResponse.onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _state.value = MovieDetailViewState(
                        movieVideos = result.data,
                        movieDetails = state.value.movieDetails,
                        isError = false,
                        isLoading = false,
                    )
                }
                is DataResult.Error -> {
                    _state.value = MovieDetailViewState(
                        movieDetails = state.value.movieDetails,
                        isError = true,
                        isLoading = false,
                    )
                }
                is DataResult.Loading -> {
                    _state.value = MovieDetailViewState(
                        movieDetails = state.value.movieDetails,
                        isError = false,
                        isLoading = true,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getMovieDetails(movieId: String) {
        val videosResponse = movieRepository.movieDetails(movieId)
        videosResponse.onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _state.value = MovieDetailViewState(
                        movieDetails = result.data,
                        movieVideos = state.value.movieVideos,
                        isError = false,
                        isLoading = false,
                    )
                }
                is DataResult.Error -> {
                    _state.value = MovieDetailViewState(
                        movieVideos = state.value.movieVideos,
                        isError = true,
                        isLoading = false,
                    )
                }
                is DataResult.Loading -> {
                    _state.value = MovieDetailViewState(
                        movieVideos = state.value.movieVideos,
                        isError = false,
                        isLoading = true,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}