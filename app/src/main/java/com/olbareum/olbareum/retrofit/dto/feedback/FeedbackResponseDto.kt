package com.olbareum.olbareum.retrofit.dto.feedback


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedbackResponseDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("textSentence")
    val textSentence: String,
    @SerializedName("transcription")
    val transcription: String,
    @SerializedName("pronunciation_feedback")
    val pronunciationFeedback: String,
    @SerializedName("pronunciation_score")
    val pronunciationScore: Double,
    @SerializedName("pronunciation_feedback_image")
    val pronunciationFeedbackImage: String,
    @SerializedName("intonation_feedback")
    val intonationFeedback: String,
    @SerializedName("intonation_feedback_image")
    val intonationFeedbackImage: String
): Parcelable