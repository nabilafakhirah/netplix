package com.example.netplix.ui.screens.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.netplix.ui.navigation.Destinations
import com.example.netplix.ui.screens.detail.MovieDetailDialogue
import com.example.netplix.ui.widgets.MovieItemCard
import com.example.netplix.ui.theme.Typography
import com.example.netplix.ui.widgets.SearchBarView

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var openDialogDetail by remember { mutableStateOf(false) }
    var currentMovieId by remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        viewModel.getMovies()
    }
    val state = viewModel.state
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Netplix") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            SearchBarView(
                onSearch = {
                    navController.navigate(
                        "${Destinations.MOVIE_SEARCH_ROUTE}/{movieName}".replace(
                            oldValue = "{movieName}",
                            newValue = it
                        )
                    )
                }
            )
            // popular section
            Text(
                text = "Popular Now",
                style = Typography.h5
            )
            if (state.value.isPopularLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .horizontalScroll(rememberScrollState()),
            ) {
                if (state.value.popularMovieList != null) {
                    state.value.popularMovieList?.results?.forEach {
                        MovieItemCard(
                            onClick = { movieId ->
                                openDialogDetail = !openDialogDetail
                                currentMovieId = movieId.toString()
                            },
                            movie = it
                        )
                    }
                }
            }

            // now playing section
            Text(
                text = "Now Playing",
                style = Typography.h5
            )
            if (state.value.isNowPlayingLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .horizontalScroll(rememberScrollState()),
            ) {
                if (state.value.nowPlayingMovieList != null) {
                    state.value.nowPlayingMovieList?.results?.forEach {
                        MovieItemCard(
                            onClick = { movieId ->
                                openDialogDetail = !openDialogDetail
                                currentMovieId = movieId.toString()
                            },
                            movie = it
                        )
                    }
                }
            }

            // top rated section
            Text(
                text = "Top Rated Movies",
                style = Typography.h5
            )
            if (state.value.isTopRatedLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .horizontalScroll(rememberScrollState()),
            ) {
                if (state.value.topRatedMovieList != null) {
                    state.value.topRatedMovieList?.results?.forEach {
                        MovieItemCard(
                            onClick = { movieId ->
                                openDialogDetail = !openDialogDetail
                                currentMovieId = movieId.toString()
                            },
                            movie = it
                        )
                    }
                }
            }

            // upcoming section
            Text(
                text = "Coming Soon",
                style = Typography.h5
            )
            if (state.value.isUpcomingLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .horizontalScroll(rememberScrollState()),
            ) {
                if (state.value.upcomingMovieList != null) {
                    state.value.upcomingMovieList?.results?.forEach {
                        MovieItemCard(
                            onClick = { movieId ->
                                openDialogDetail = !openDialogDetail
                                currentMovieId = movieId.toString()
                            },
                            movie = it
                        )
                    }
                }
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