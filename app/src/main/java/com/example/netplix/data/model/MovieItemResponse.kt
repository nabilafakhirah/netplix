package com.example.netplix.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItemResponse(
    @Json(name="id")
    val id: Long,
    @field:Json(name="original_title")
    val originalTitle: String,
    @Json(name="overview")
    val overview: String,
    @field:Json(name="poster_path")
    val posterPath: String,
    @field:Json(name="release_date")
    val releaseDate: String,
    @Json(name="title")
    val title: String,
) : Parcelable