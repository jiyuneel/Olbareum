package com.olbareum.olbareum.retrofit.dto.user


import com.google.gson.annotations.SerializedName

data class UserLoginResponseDto(
    @SerializedName("accessToken")
    val accessToken: String
)