package com.example.tugasmodule9.data.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {

    private val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhN2RiMTE4YWU0OTJkMGNiMzZmOTM2Y2ZlZjc4OGU0ZCIsInN1YiI6IjY1MjRkMWZjZDM5OWU2MDBjNjc2M2Q5NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tmoke3RDcxtoMJZWBOun4v6PEr_7hInEvnq2tMqSXQE"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN").build()
        return chain.proceed(request)
    }
}