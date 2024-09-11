package com.example.netplix.data.model

import com.squareup.moshi.Json

data class VideosResponse(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "results")
    val results: List<VideoResultItems>,
)

data class VideoResultItems(
    @field:Json(name = "site")
    val site: String,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "type")
    val type: String,
    @field:Json(name = "key")
    val key: String,
)