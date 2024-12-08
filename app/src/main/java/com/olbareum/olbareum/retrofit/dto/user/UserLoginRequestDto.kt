package com.olbareum.olbareum.retrofit.dto.user


import com.google.gson.annotations.SerializedName

data class UserLoginRequestDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)