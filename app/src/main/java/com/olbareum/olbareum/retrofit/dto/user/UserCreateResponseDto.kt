package com.olbareum.olbareum.retrofit.dto.user


import com.google.gson.annotations.SerializedName

data class UserCreateResponseDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("role")
    val role: String
)