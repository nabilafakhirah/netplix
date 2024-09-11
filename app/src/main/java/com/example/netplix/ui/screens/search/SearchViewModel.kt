package com.example.netplix.ui.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.netplix.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _state = mutableStateOf(SearchViewState())
    val state: State<SearchViewState> = _state

    fun getMovieListByQuery(movieName: String) {
        val movieResponse = movieRepository.searchPagingList(movieName)
        _state.value = SearchViewState(
            isLoading = false,
            movieItemResponse = movieResponse,
            error = null
        )
    }
}