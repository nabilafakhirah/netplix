package com.example.netplix.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.netplix.ui.theme.Typography
import com.example.netplix.ui.widgets.YouTubePlayerView

@Composable
fun MovieDetailDialogue(
    onDismiss: () -> Unit,
    movieId: String,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel(),

    ) {
    Dialog(onDismissRequest = { onDismiss() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        LaunchedEffect(key1 = true) {
            viewModel.getDetails(movieId)
        }
        val state = viewModel.state
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (state.value.isLoading) {
                    CircularProgressIndicator()
                }
                if (state.value.movieVideos != null) {
                    val video = state.value.movieVideos?.results?.first {
                        it.type == "Trailer" && it.site == "YouTube"
                    }
                    YouTubePlayerView(
                        videoId = video?.key.orEmpty(),
                    )
                    Log.d("YOUTUBE_ID", "youtube id is ${video?.key}")
                }
                val movieDetail = state.value.movieDetails
                if (movieDetail != null) {
                    Text(
                        text = movieDetail.title,
                        style = Typography.h5
                    )
                    Text(
                        text = movieDetail.overview,
                        style = Typography.body1
                    )
                    Text(
                        modifier = Modifier.padding(top = 6.dp),
                        text = "Release Date",
                        style = Typography.body1
                    )
                    Text(
                        text = movieDetail.releaseDate,
                        style = Typography.body1
                    )
                }
            }
        }
    }
}
