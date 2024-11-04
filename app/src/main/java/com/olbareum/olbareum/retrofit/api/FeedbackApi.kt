package com.olbareum.olbareum.retrofit.api

import com.olbareum.olbareum.retrofit.dto.feedback.FeedbackResponseDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FeedbackApi {
    @Multipart
    @POST("/api/v1/feedback/create")
    fun createFeedback(
        @Header("Authorization") accessToken: String,
        @Part("textSentence") textSentence: String,
        @Part audioFile: MultipartBody.Part
    ): Call<FeedbackResponseDto>
}