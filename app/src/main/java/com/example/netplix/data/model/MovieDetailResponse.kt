package com.example.netplix.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MovieDetailResponse(
    @Json(name="id")
    val id: Long,
    @field:Json(name="original_title")
    val originalTitle: String,
    @Json(name="overview")
    val overview: String,
    @Json(name="genres")
    val genres: @RawValue List<GenresItem>,
    @field:Json(name="poster_path")
    val posterPath: String,
    @field:Json(name="release_date")
    val releaseDate: String,
    @Json(name="title")
    val title: String,
) : Parcelable

data class GenresItem(
    @field:Json(name = "name")
    val genreName: String? = null,
    @field:Json(name = "id")
    val genreId: Int? = null
)