package com.olbareum.olbareum.retrofit

import com.olbareum.olbareum.MyApplication
import com.olbareum.olbareum.retrofit.api.FeedbackApi
import com.olbareum.olbareum.retrofit.dto.ErrorResponseDto
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://backend.allbareum.p-e.kr"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(MyApplication.preferences))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val errorBody = retrofit.responseBodyConverter<ErrorResponseDto>(
        ErrorResponseDto::class.java,
        ErrorResponseDto::class.java.annotations
    )
    val feedbackApi: FeedbackApi = retrofit.create(FeedbackApi::class.java)
}