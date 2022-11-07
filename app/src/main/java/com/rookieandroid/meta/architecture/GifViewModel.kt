package com.rookieandroid.meta.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.rookieandroid.meta.api.GifRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(private val gifRequest: GifRequest) : ViewModel()
{
    val gifs = Pager(PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
    pagingSourceFactory = { GifPagingSource(gifRequest) }).flow.cachedIn(viewModelScope)
}