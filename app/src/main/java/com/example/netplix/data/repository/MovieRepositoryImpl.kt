package com.example.netplix.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.netplix.data.api.MovieApi
import com.example.netplix.data.model.MovieDetailResponse
import com.example.netplix.data.model.MovieItemResponse
import com.example.netplix.data.model.MovieListResponse
import com.example.netplix.data.model.VideosResponse
import com.example.netplix.data.paging.NowPlayingPagingSource
import com.example.netplix.data.paging.PopularPagingSource
import com.example.netplix.data.paging.SearchPagingSource
import com.example.netplix.data.paging.TopRatedPagingSource
import com.example.netplix.data.paging.UpcomingPagingSource
import com.example.netplix.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
): MovieRepository {
    override fun getPopularPagingList(): Flow<PagingData<MovieItemResponse>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { PopularPagingSource(movieApi) }
    ).flow

    override fun getNowPlayingPagingList(): Flow<PagingData<MovieItemResponse>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { NowPlayingPagingSource(movieApi) }
    ).flow

    override fun getUpcomingPagingList(): Flow<PagingData<MovieItemResponse>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { UpcomingPagingSource(movieApi) }
    ).flow

    override fun getTopRatedPagingList(): Flow<PagingData<MovieItemResponse>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { TopRatedPagingSource(movieApi) }
    ).flow

    override fun searchPagingList(query: String): Flow<PagingData<MovieItemResponse>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { SearchPagingSource(movieApi, query) }
    ).flow

    override fun movieDetails(
        movieId: String
    ): Flow<DataResult<MovieDetailResponse>> = flow {
        try {
            emit(DataResult.Loading())
            val result = movieApi.getMovieDetails(movieId)
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(DataResult.Error(
                message = "Failed to retrieve movie details"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                DataResult.Error(
                    message = "Failed to retrieve movie details"
                ))
        }
    }

    override fun getVideos(
        movieId: String
    ): Flow<DataResult<VideosResponse>> = flow {
        try {
            emit(DataResult.Loading())
            val result = movieApi.getVideos(movieId)
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(DataResult.Error(
                message = "Failed to retrieve movie videos"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                DataResult.Error(
                    message = "Failed to retrieve movie videos"
                ))
        }
    }

    override fun getPopularFrontList(): Flow<DataResult<MovieListResponse>> = flow {
        try {
            emit(DataResult.Loading())
            val result = movieApi.getPopular(page = 1)
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(DataResult.Error(
                message = "Failed to retrieve movies"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                DataResult.Error(
                    message = "Failed to retrieve movies"
                ))
        }
    }

    override fun getNowPlayingFrontList(): Flow<DataResult<MovieListResponse>> = flow {
        try {
            emit(DataResult.Loading())
            val result = movieApi.getNowPlaying(page = 1)
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(DataResult.Error(
                message = "Failed to retrieve movies"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                DataResult.Error(
                    message = "Failed to retrieve movies"
                ))
        }
    }

    override fun getUpcomingFrontList(): Flow<DataResult<MovieListResponse>> = flow {
        try {
            emit(DataResult.Loading())
            val result = movieApi.getUpcoming(page = 1)
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(DataResult.Error(
                message = "Failed to retrieve movies"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                DataResult.Error(
                    message = "Failed to retrieve movies"
                ))
        }
    }

    override fun getTopRatedFrontList(): Flow<DataResult<MovieListResponse>> = flow {
        try {
            emit(DataResult.Loading())
            val result = movieApi.getTopRated(page = 1)
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(DataResult.Error(
                message = "Failed to retrieve movies"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                DataResult.Error(
                    message = "Failed to retrieve movies"
                ))
        }
    }
}