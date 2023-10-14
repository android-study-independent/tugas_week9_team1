package com.example.tugasmodule9.data.response

import com.google.gson.annotations.SerializedName

data class NowPlayingReponse(
    val page: Int,
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPage:Int,
    @SerializedName("total_results")
    val totalResult: Int
)
