package com.example.tugasmodule9.data.api

<<<<<<< HEAD
import com.example.tugasmodule9.data.response.NowPlayingResponse
=======
import com.example.tugasmodule9.data.response.ResultsItem
>>>>>>> b5cc72d19489ce240e89f80bd17f41c46d93cf48
import com.example.tugasmodule9.data.response.TopRatedResponse
import com.example.tugasmodule9.data.response.UpCommingResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface Routes {

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("language") lang: String,
        @Query("page") page: Int
    ): TopRatedResponse

<<<<<<< HEAD
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int
    ): NowPlayingResponse
=======
    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("language") lang: String = "en-EN",
        @Query("page") page: Int
    ): UpCommingResponse

    @GET("movie/populer")
    suspend fun getPopuler(
        @Query("poster_path") posterPath: String?,
        @Query("title") title : String?
    ): ResultsItem

>>>>>>> b5cc72d19489ce240e89f80bd17f41c46d93cf48

}