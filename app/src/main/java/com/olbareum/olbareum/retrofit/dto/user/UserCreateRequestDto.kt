package com.olbareum.olbareum.retrofit.dto.user


import com.google.gson.annotations.SerializedName

data class UserCreateRequestDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("nickname")
    val nickname: String
)