package com.example.tugasmodule9.data.response

import android.os.Bundle
import com.google.gson.annotations.SerializedName



data class NowPlayingResponse(
    val page: Int,
    val results: List<MovieResponse>,

    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
