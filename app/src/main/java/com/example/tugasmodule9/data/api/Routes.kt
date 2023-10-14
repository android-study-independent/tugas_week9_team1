package com.example.tugasmodule9.data.api

import com.example.tugasmodule9.data.response.NowPlayingReponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface Routes {
    @GET("movie/now_playing")

    suspend fun getNowPlaying(
        @Header("Authorization") token: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): NowPlayingReponse


}