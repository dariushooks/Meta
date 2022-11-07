package com.rookieandroid.meta.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifRequest
{
    companion object
    {
        const val BASE_URL = "https://api.giphy.com/v1/gifs/"
        const val API_KEY = "EEjeWKnay8eNwJ091mC2ffGuQe96tdBN"
    }

    @GET("trending?")
    suspend fun getGifs(
        @Query("api_key") api_key : String,
        @Query("offset") offset : Int) : Response<GifResponse>
}