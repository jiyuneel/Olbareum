package com.olbareum.olbareum.retrofit.dto


import com.google.gson.annotations.SerializedName

data class ErrorResponseDto(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: String?
)