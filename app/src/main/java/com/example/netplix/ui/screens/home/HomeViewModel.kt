package com.example.netplix.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netplix.data.model.VideosResponse
import com.example.netplix.data.repository.MovieRepository
import com.example.netplix.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _state = mutableStateOf(HomeViewState())
    val state: State<HomeViewState> = _state

    fun getMovies() {
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovieList()
        getNowPlayingMovies()
    }

    private fun getPopularMovies() {
        val popularMovieListResponse = movieRepository.getPopularFrontList()
        popularMovieListResponse.onEach { result ->
            when(result) {
                is DataResult.Success -> {
                    _state.value = HomeViewState(
                        popularMovieList = result.data,
                        upcomingMovieList = state.value.upcomingMovieList,
                        nowPlayingMovieList = state.value.nowPlayingMovieList,
                        topRatedMovieList = state.value.topRatedMovieList,
                        isError = false,
                        isPopularLoading = false,
                        isTopRatedLoading = state.value.isTopRatedLoading,
                        isNowPlayingLoading = state.value.isNowPlayingLoading,
                        isUpcomingLoading = state.value.isUpcomingLoading,
                    )
                }
                is DataResult.Error -> {
                    _state.value = HomeViewState(
                        upcomingMovieList = state.value.upcomingMovieList,
                        nowPlayingMovieList = state.value.nowPlayingMovieList,
                        topRatedMovieList = state.value.topRatedMovieList,
                        isError = true,
                        isPopularLoading = false,
                        isTopRatedLoading = state.value.isTopRatedLoading,
                        isNowPlayingLoading = state.value.isNowPlayingLoading,
                        isUpcomingLoading = state.value.isUpcomingLoading,
                    )
                }
                is DataResult.Loading -> {
                    _state.value = HomeViewState(
                        upcomingMovieList = state.value.upcomingMovieList,
                        nowPlayingMovieList = state.value.nowPlayingMovieList,
                        topRatedMovieList = state.value.topRatedMovieList,
                        isError = false,
                        isPopularLoading = true,
                        isTopRatedLoading = state.value.isTopRatedLoading,
                        isNowPlayingLoading = state.value.isNowPlayingLoading,
                        isUpcomingLoading = state.value.isUpcomingLoading,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRatedMovies() {
        val popularMovieListResponse = movieRepository.getTopRatedFrontList()
        popularMovieListResponse.onEach { result ->
            when(result) {
                is DataResult.Success -> {
                    _state.value = HomeViewState(
                        popularMovieList = state.value.popularMovieList,
                        upcomingMovieList = state.value.upcomingMovieList,
                        nowPlayingMovieList = state.value.nowPlayingMovieList,
                        topRatedMovieList = result.data,
                        isError = false,
                        isTopRatedLoading = false,
                        isNowPlayingLoading = state.value.isNowPlayingLoading,
                        isUpcomingLoading = state.value.isUpcomingLoading,
                        isPopularLoading = state.value.isPopularLoading,
                    )
                }
                is DataResult.Error -> {
                    _state.value = HomeViewState(
                        upcomingMovieList = state.value.upcomingMovieList,
                        nowPlayingMovieList = state.value.nowPlayingMovieList,
                        popularMovieList = state.value.popularMovieList,
                        isError = true,
                        isTopRatedLoading = false,
                        isNowPlayingLoading = state.value.isNowPlayingLoading,
                        isUpcomingLoading = state.value.isUpcomingLoading,
                        isPopularLoading = state.value.isPopularLoading,
                    )
                }
                is DataResult.Loading -> {
                    _state.value = HomeViewState(
                        upcomingMovieList = state.value.upcomingMovieList,
                        nowPlayingMovieList = state.value.nowPlayingMovieList,
                        popularMovieList = state.value.popularMovieList,
                        isError = false,
                        isTopRatedLoading = true,
                        isNowPlayingLoading = state.value.isNowPlayingLoading,
                        isUpcomingLoading = state.value.isUpcomingLoading,
                        isPopularLoading = state.value.isPopularLoading,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getNowPlayingMovies() {
        val popularMovieListResponse = movieRepository.getNowPlayingFrontList()
        popularMovieListResponse.onEach { result ->
            when(result) {
                is DataResult.Success -> {
                    _state.value = HomeViewState(
                        popularMovieList = state.value.popularMovieList,
                        upcomingMovieList = state.value.upcomingMovieList,
                        nowPlayingMovieList = result.data,
                        topRatedMovieList = state.value.topRatedMovieList,
                        isError = false,
                        isNowPlayingLoading = false,
                        isTopRatedLoading = state.value.isTopRatedLoading,
                        isUpcomingLoading = state.value.isUpcomingLoading,
                        isPopularLoading = state.value.isPopularLoading,
                    )
                }
                is DataResult.Error -> {
                    _state.value = HomeViewState(
                        upcomingMovieList = state.value.upcomingMovieList,
                        popularMovieList = state.value.popularMovieList,
                        topRatedMovieList = state.value.topRatedMovieList,
                        isError = true,
                        isNowPlayingLoading = false,
                        isTopRatedLoading = state.value.isTopRatedLoading,
                        isUpcomingLoading = state.value.isUpcomingLoading,
                        isPopularLoading = state.value.isPopularLoading,
                    )
                }
                is DataResult.Loading -> {
                    _state.value = HomeViewState(
                        upcomingMovieList = state.value.upcomingMovieList,
                        popularMovieList = state.value.popularMovieList,
                        topRatedMovieList = state.value.topRatedMovieList,
                        isError = false,
                        isNowPlayingLoading = true,
                        isTopRatedLoading = state.value.isTopRatedLoading,
                        isUpcomingLoading = state.value.isUpcomingLoading,
                        isPopularLoading = state.value.isPopularLoading,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUpcomingMovieList() {
        val popularMovieListResponse = movieRepository.getUpcomingFrontList()
        popularMovieListResponse.onEach { result ->
            when(result) {
                is DataResult.Success -> {
                    _state.value = HomeViewState(
                        popularMovieList = state.value.popularMovieList,
                        upcomingMovieList = result.data,
                        nowPlayingMovieList = state.value.nowPlayingMovieList,
                        topRatedMovieList = state.value.topRatedMovieList,
                        isError = false,
                        isUpcomingLoading = false,
                        isTopRatedLoading = state.value.isTopRatedLoading,
                        isNowPlayingLoading = state.value.isNowPlayingLoading,
                        isPopularLoading = state.value.isPopularLoading,
                    )
                }
                is DataResult.Error -> {
                    _state.value = HomeViewState(
                        popularMovieList = state.value.popularMovieList,
                        nowPlayingMovieList = state.value.nowPlayingMovieList,
                        topRatedMovieList = state.value.topRatedMovieList,
                        isError = true,
                        isUpcomingLoading = false,
                        isTopRatedLoading = state.value.isTopRatedLoading,
                        isNowPlayingLoading = state.value.isNowPlayingLoading,
                        isPopularLoading = state.value.isPopularLoading,
                    )
                }
                is DataResult.Loading -> {
                    _state.value = HomeViewState(
                        popularMovieList = state.value.popularMovieList,
                        nowPlayingMovieList = state.value.nowPlayingMovieList,
                        topRatedMovieList = state.value.topRatedMovieList,
                        isError = false,
                        isUpcomingLoading = true,
                        isTopRatedLoading = state.value.isTopRatedLoading,
                        isNowPlayingLoading = state.value.isNowPlayingLoading,
                        isPopularLoading = state.value.isPopularLoading,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}