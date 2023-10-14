package com.example.tugasmodule9.data.response

import com.google.gson.annotations.SerializedName


data class MovieResponse(
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    val id: Int?,
    val overview: String?,
    val popularity: Float?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val realeaseDate: String?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?
)
