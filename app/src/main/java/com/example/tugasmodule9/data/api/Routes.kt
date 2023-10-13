package com.example.tugasmodule9.data.api

import com.example.tugasmodule9.data.response.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface Routes {

    @GET("movie/top_rated")
    @Headers("Authorization:")
    suspend fun getTopRated(
        @Query("language") lang: String,
        @Query("page") page: Int
    ): TopRatedResponse
}