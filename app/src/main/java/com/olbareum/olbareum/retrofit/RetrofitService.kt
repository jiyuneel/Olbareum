package com.olbareum.olbareum.retrofit

import com.olbareum.olbareum.retrofit.api.FeedbackApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://backend.allbareum.p-e.kr"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val feedbackApi: FeedbackApi = retrofit.create(FeedbackApi::class.java)
}