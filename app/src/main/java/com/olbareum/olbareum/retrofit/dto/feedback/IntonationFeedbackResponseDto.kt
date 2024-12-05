package com.olbareum.olbareum.retrofit.dto.feedback


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IntonationFeedbackResponseDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("sentenceCode")
    val sentenceCode: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("intonationFeedbacks")
    val intonationFeedbacks: String,
    @SerializedName("feedbackImageUrls")
    val feedbackImageUrls: String,
    @SerializedName("intonationScore")
    val intonationScore: Double,
    @SerializedName("intonationImageUrls")
    val intonationImageUrls: String
): Parcelable