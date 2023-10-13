package com.example.tugasmodule9.data.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {

    private val TOKEN = "9530e1f4e74cb426f57cb45e84869d5d"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN").build()
        return chain.proceed(request)
    }
}