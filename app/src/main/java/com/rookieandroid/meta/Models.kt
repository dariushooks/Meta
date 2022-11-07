package com.rookieandroid.meta

class Models
{
    data class Giphy(val id : String = "", val images : Original, val title : String = "")
    data class Original(val original : Gif)
    data class Gif(val url : String)
}