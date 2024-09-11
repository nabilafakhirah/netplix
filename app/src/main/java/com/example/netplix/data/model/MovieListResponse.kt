package com.example.netplix.data.model

import com.squareup.moshi.Json

data class MovieListResponse(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "results")
    val results: List<MovieItemResponse>,
    @field:Json(name = "total_results")
    val totalResults: Int
)
