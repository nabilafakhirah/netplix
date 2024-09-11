package com.example.netplix.ui.screens.detail

import com.example.netplix.data.model.MovieDetailResponse
import com.example.netplix.data.model.VideosResponse

data class MovieDetailViewState(
    val movieDetails: MovieDetailResponse? = null,
    val movieVideos: VideosResponse? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
