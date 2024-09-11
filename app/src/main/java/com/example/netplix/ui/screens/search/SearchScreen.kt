package com.example.netplix.ui.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.netplix.data.model.MovieItemResponse
import com.example.netplix.ui.screens.detail.MovieDetailDialogue
import com.example.netplix.ui.widgets.EmptyView
import com.example.netplix.ui.widgets.LoadingView
import com.example.netplix.ui.widgets.MovieItemCard
import com.example.netplix.ui.widgets.SearchBarView

@Composable
fun SearchScreen(
    navController: NavController,
    movieName: String,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        viewModel.getMovieListByQuery(movieName)
    }

    val state = viewModel.state
    val newsList = state.value.movieItemResponse?.collectAsLazyPagingItems()
    var openDialogDetail by remember { mutableStateOf(false) }
    var currentMovieId by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Netplix") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.navigateUp()
                            }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            SearchBarView(
                onSearch = {
                    viewModel.getMovieListByQuery(
                        movieName = it,
                    )
                }
            )
            if (newsList != null) {
                ListContent(
                    movies = newsList,
                    navController = navController,
                    onClickItem = {
                        openDialogDetail = !openDialogDetail
                        currentMovieId = it
                    }
                )
            }
            if (openDialogDetail) {
                MovieDetailDialogue(
                    onDismiss = {
                        openDialogDetail = false
                    },
                    movieId = currentMovieId,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListContent(
    movies: LazyPagingItems<MovieItemResponse>,
    onClickItem: (String) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val result = handlePagingResult(news = movies)
    if (result) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = modifier.padding(vertical = 16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                movies.itemCount
            ) { moviesItemScope ->
                movies[moviesItemScope]?.let {
                    MovieItemCard(
                        onClick = { movieId ->
                            onClickItem(movieId.toString())
                        },
                        movie = it
                    )
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(
    news: LazyPagingItems<MovieItemResponse>,
): Boolean {
    news.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                LoadingView()
                false
            }

            error != null -> {
                false
            }

            news.itemCount < 1 -> {
                EmptyView()
                false
            }

            else -> true
        }
    }
}