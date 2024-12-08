package com.olbareum.olbareum.retrofit.api

import com.olbareum.olbareum.retrofit.dto.user.UserCreateRequestDto
import com.olbareum.olbareum.retrofit.dto.user.UserCreateResponseDto
import com.olbareum.olbareum.retrofit.dto.user.UserLoginRequestDto
import com.olbareum.olbareum.retrofit.dto.user.UserLoginResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/api/v1/users/sign-up")
    fun userSignUp(
        @Body body: UserCreateRequestDto
    ): Call<UserCreateResponseDto>

    @POST("/api/v1/users/login")
    fun userLogin(
        @Body body: UserLoginRequestDto
    ): Call<UserLoginResponseDto>
}