package com.example.tugasmodule9.data.response

import com.google.gson.annotations.SerializedName

data class UpCommingResponse(
    val dates : dates?,
    val page: Int,
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPage: Int,
    @SerializedName("total_results")
    val totalResult: Int
)

data class dates(
    val maximum:String?,
    val minimum:String?,
)