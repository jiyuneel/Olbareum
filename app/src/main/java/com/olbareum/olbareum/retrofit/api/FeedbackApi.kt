package com.olbareum.olbareum.retrofit.api

import com.olbareum.olbareum.retrofit.dto.feedback.IntonationFeedbackResponseDto
import com.olbareum.olbareum.retrofit.dto.feedback.PronunciationFeedbackResponseDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FeedbackApi {
    @Multipart
    @POST("/api/v1/feedback/pronunciation/create")
    fun createPronunciationFeedback(
        @Part("textSentence") textSentence: String,
        @Part audioFile: MultipartBody.Part
    ): Call<PronunciationFeedbackResponseDto>

    @Multipart
    @POST("/api/v1/feedback/pronunciation/create-intonation")
    fun createIntonationFeedback(
        @Part("textSentence") textSentence: String,
        @Part audioFile: MultipartBody.Part
    ): Call<IntonationFeedbackResponseDto>
}