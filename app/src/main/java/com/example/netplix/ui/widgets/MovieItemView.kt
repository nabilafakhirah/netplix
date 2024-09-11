package com.example.netplix.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.netplix.R
import com.example.netplix.data.model.MovieItemResponse
import com.example.netplix.ui.theme.Typography
import com.example.netplix.utils.BASE_POSTER_URL

@Composable
fun MovieItemCard(
    onClick: (Long) -> Unit,
    movie: MovieItemResponse,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .clickable {
                onClick(movie.id)
            }
            .width(140.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            AsyncImage(
                model = "${BASE_POSTER_URL}${movie.posterPath}",
                contentDescription = "Poster for ${movie.title}",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                placeholder = painterResource(id = R.drawable.img_load_failed),
                error = painterResource(id = R.drawable.img_load_failed),
            )
            Spacer(modifier = Modifier.height(10.dp))
            val lineHeight = MaterialTheme.typography.h6.fontSize * 4 / 3
            Text(
                text = movie.title,
                style = Typography.body1,
                modifier = Modifier.padding(horizontal = 10.dp),
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                lineHeight = lineHeight
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

    }

}