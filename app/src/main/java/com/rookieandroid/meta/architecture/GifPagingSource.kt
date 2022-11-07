package com.rookieandroid.meta.architecture

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rookieandroid.meta.Models
import com.rookieandroid.meta.api.GifRequest
import com.rookieandroid.meta.api.GifRequest.Companion.API_KEY
import retrofit2.HttpException
import java.io.IOException

private const val GIF_STARTING_PAGE = 0

class GifPagingSource(private val gifRequest: GifRequest) : PagingSource<Int, Models.Giphy>()
{
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Models.Giphy>
    {
        val page = params.key ?: GIF_STARTING_PAGE

        return try {
            val response = gifRequest.getGifs(API_KEY, page)
            val gifs = response.body()
            val data = gifs!!.gifs

            LoadResult.Page(data = data,
                prevKey = if(page == GIF_STARTING_PAGE) null else page - 1,
                nextKey = if(data.isEmpty()) null else page + 1)
        } catch (e : IOException){ LoadResult.Error(e) } catch (e : HttpException){ LoadResult.Error(e) }
    }

    override fun getRefreshKey(state: PagingState<Int, Models.Giphy>): Int?
    {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}