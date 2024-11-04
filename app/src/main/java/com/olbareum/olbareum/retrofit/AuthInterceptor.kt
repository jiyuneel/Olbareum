package com.olbareum.olbareum.retrofit

import com.olbareum.olbareum.PreferenceUtil
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val preferences: PreferenceUtil) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = preferences.getAccessToken()
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(request)
    }
}