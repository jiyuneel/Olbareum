package com.olbareum.olbareum.retrofit.dto.feedback


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PronunciationFeedbackResponseDto(
    @SerializedName("id")
    val id: String,                             // 피드백 아이디
    @SerializedName("userId")
    val userId: String,                         // 유저 아이디
    @SerializedName("textSentence")
    val textSentence: String,                   // 문장
    @SerializedName("status")
    val status: Int,                            // 1: 정확한 발음 (피드백&입모양 사진 없음), 2: 발음에 틀린 부분 있음 (피드백&입모양 사진 있음)
    @SerializedName("pronounced_text")
    val pronouncedText: String,                 // 문장을 정확하게 발음했을 때 나와야 하는 문장
    @SerializedName("transcription")
    val transcription: String,                  // 문장을 발음한 대로 변환한 것
    @SerializedName("feedbackCount")
    val feedbackCount: Int,                     // 틀린 부분 수
    @SerializedName("wordIndex")
    val wordIndex: List<Int>,                   // 틀린 부분 인덱스 리스트
    @SerializedName("pronunciationFeedbacks")
    val pronunciationFeedbacks: List<String>,   // 발음 피드백 리스트
    @SerializedName("feedbackImageUrls")
    val feedbackImageUrls: List<String>,        // 발음 피드백 이미지 리스트
    @SerializedName("wrongSpellings")
    val wrongSpellings: List<String>,           // 틀린 철자들 리스트 ('X'는 없는 음소를 발음한 경우)
    @SerializedName("pronunciationScore")
    val pronunciationScore: Double              // 발음 점수
): Parcelable