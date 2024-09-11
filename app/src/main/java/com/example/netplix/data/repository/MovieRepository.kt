package com.example.netplix.data.repository

import androidx.paging.PagingData
import com.example.netplix.data.model.MovieDetailResponse
import com.example.netplix.data.model.MovieItemResponse
import com.example.netplix.data.model.MovieListResponse
import com.example.netplix.data.model.VideosResponse
import com.example.netplix.utils.DataResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    // paginated ones, not implemented for now other than search paging
    fun getPopularPagingList(): Flow<PagingData<MovieItemResponse>>
    fun getNowPlayingPagingList(): Flow<PagingData<MovieItemResponse>>
    fun getUpcomingPagingList(): Flow<PagingData<MovieItemResponse>>
    fun getTopRatedPagingList(): Flow<PagingData<MovieItemResponse>>
    fun searchPagingList(query: String): Flow<PagingData<MovieItemResponse>>


    fun movieDetails(
        movieId: String
    ): Flow<DataResult<MovieDetailResponse>>

    fun getVideos(
        movieId: String
    ): Flow<DataResult<VideosResponse>>

    fun getPopularFrontList(): Flow<DataResult<MovieListResponse>>
    fun getNowPlayingFrontList(): Flow<DataResult<MovieListResponse>>
    fun getUpcomingFrontList(): Flow<DataResult<MovieListResponse>>
    fun getTopRatedFrontList(): Flow<DataResult<MovieListResponse>>

}