package com.olbareum.olbareum.enums

enum class FeedbackStatus(val value: Int) {
    CORRECT(1),     // 정확한 발음
    INCORRECT(2),   // 발음에 틀린 부분 있음
    DIFFERENT(6);   // 단어 개수가 틀림

    companion object {
        fun fromValue(value: Int): FeedbackStatus {
            return entries.find { it.value == value }
                ?: throw IllegalArgumentException("Invalid status value: $value")
        }
    }
}