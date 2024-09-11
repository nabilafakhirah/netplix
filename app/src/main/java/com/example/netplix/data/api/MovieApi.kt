package com.example.netplix.data.api

import com.example.netplix.data.model.MovieDetailResponse
import com.example.netplix.data.model.MovieListResponse
import com.example.netplix.data.model.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int?,
        @Query("language") language: String = "en-US",
    ): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int?,
        @Query("language") language: String = "en-US",
    ): MovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int?,
        @Query("language") language: String = "en-US",
    ): MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int?,
        @Query("language") language: String = "en-US",
    ): MovieListResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("language") language: String = "en-US",
    ): MovieDetailResponse

    @GET("movie/{movieId}/videos")
    suspend fun getVideos(
        @Path("movieId") movieId: String,
        @Query("language") language: String = "en-US",
    ): VideosResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String?,
        @Query("page") page: Int?,
        @Query("language") language: String = "en-US",
    ): MovieListResponse
}