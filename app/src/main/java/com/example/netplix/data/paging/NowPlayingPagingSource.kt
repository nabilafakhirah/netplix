package com.example.netplix.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.netplix.data.api.MovieApi
import com.example.netplix.data.model.MovieItemResponse
import com.example.netplix.utils.PAGE_SIZE
import com.example.netplix.utils.START_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class NowPlayingPagingSource(private val movieApi: MovieApi) : PagingSource<Int, MovieItemResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemResponse> {
        val position = params.key ?: START_PAGE_INDEX
        return try {
            val data = movieApi.getNowPlaying(
                page = position,
            )
            val nextKey = if (data.results?.isEmpty() == true) {
                null
            } else {
                position + (params.loadSize / PAGE_SIZE)
            }
            val prevKey = if (position == START_PAGE_INDEX) null else position - 1
            LoadResult.Page(
                data = data.results!!,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItemResponse>): Int? {
        return state.anchorPosition
    }
}