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
    val pronouncedText: String,                 // 문장을 정확하게 발음했을 때 나와야 하는 문장 ex) 나는 행보카게 끈나는 영화가 조타
    @SerializedName("transcription")
    val transcription: String,                  // 사용자의 발화를 발음 그대로 전사한 한글 텍스트 ex) 나는 행복하게 끝나는 용화가 좋다.
    @SerializedName("feedbackCount")
    val feedbackCount: Int,                     // 생성된 피드백 개수 (word_index, pronunciation_feedbacks, feedback_image_names, wrong_spellings의 길이와 같음)
    @SerializedName("wordIndex")
    val wordIndex: List<Int>,                   // 몇 번째 단어에서 틀렸는지 나타내는 인덱스를 포함하는 리스트
    @SerializedName("pronunciationFeedbacks")
    val pronunciationFeedbacks: List<String>,   // 발음 피드백 리스트
    @SerializedName("feedbackImageUrls")
    val feedbackImageUrls: List<String>,        // 발음 피드백 이미지 리스트
    @SerializedName("wrongSpellings")
    val wrongSpellings: List<String>,           // 틀린 철자들 리스트 ('X'는 없는 음소를 발음한 경우)
    @SerializedName("pronunciationScore")
    val pronunciationScore: Double              // 발음 점수
): Parcelable