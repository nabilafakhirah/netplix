package com.example.netplix.ui.screens.search

import androidx.paging.PagingData
import com.example.netplix.data.model.MovieItemResponse
import kotlinx.coroutines.flow.Flow

data class SearchViewState(
    var isLoading: Boolean = true,
    val movieItemResponse: Flow<PagingData<MovieItemResponse>>? = null,
    val error: String? = ""
)
