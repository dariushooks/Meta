package com.rookieandroid.meta.api

import com.google.gson.annotations.SerializedName
import com.rookieandroid.meta.Models


data class GifResponse(@SerializedName("data") val gifs : List<Models.Giphy>)