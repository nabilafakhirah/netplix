package com.example.netplix.ui.screens.home

import com.example.netplix.data.model.MovieListResponse

data class HomeViewState(
    val popularMovieList: MovieListResponse? = null,
    val upcomingMovieList: MovieListResponse? = null,
    val nowPlayingMovieList: MovieListResponse? = null,
    val topRatedMovieList: MovieListResponse? = null,
    val isPopularLoading: Boolean = false,
    val isUpcomingLoading: Boolean = false,
    val isNowPlayingLoading: Boolean = false,
    val isTopRatedLoading: Boolean = false,
    val isError: Boolean = false,
)
