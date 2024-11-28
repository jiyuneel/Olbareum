package com.olbareum.olbareum.retrofit.dto.feedback


import com.google.gson.annotations.SerializedName

data class IntonationFeedbackResponseDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("textSentence")
    val textSentence: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("feedbackCount")
    val feedbackCount: Int,
    @SerializedName("wordIndex")
    val wordIndex: String,
    @SerializedName("intonationFeedbacks")
    val intonationFeedbacks: String,
    @SerializedName("feedbackImageUrls")
    val feedbackImageUrls: String,
    @SerializedName("intonationScore")
    val intonationScore: Double
)