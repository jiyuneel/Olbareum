package com.olbareum.olbareum.retrofit.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olbareum.olbareum.retrofit.RetrofitService
import com.olbareum.olbareum.retrofit.dto.user.UserCreateRequestDto
import com.olbareum.olbareum.retrofit.dto.user.UserCreateResponseDto
import com.olbareum.olbareum.retrofit.dto.user.UserLoginRequestDto
import com.olbareum.olbareum.retrofit.dto.user.UserLoginResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private val _accessToken = MutableLiveData<String>()
    val accessToken: LiveData<String> get() = _accessToken

    fun userSignUp(requestDto: UserCreateRequestDto) {
        RetrofitService.userApi.userSignUp(requestDto).enqueue(object : Callback<UserCreateResponseDto> {
            override fun onResponse(
                call: Call<UserCreateResponseDto>,
                response: Response<UserCreateResponseDto>
            ) {
                Log.i("testt", "${call.request()}")
                if (response.isSuccessful) {
                    Log.d("testt", "${response.code()} ${response.body()}")
                } else {
                    val errorBody = response.errorBody()?.source()?.buffer?.clone()?.readUtf8()
                    Log.e("testt", "${response.code()} $errorBody")
                }
            }

            override fun onFailure(call: Call<UserCreateResponseDto>, t: Throwable) {
                Log.i("testt", "${call.request()}")
                Log.e("testt", t.message.toString())
            }
        })
    }

    fun userLogin(requestDto: UserLoginRequestDto) {
        RetrofitService.userApi.userLogin(requestDto).enqueue(object : Callback<UserLoginResponseDto> {
            override fun onResponse(
                call: Call<UserLoginResponseDto>,
                response: Response<UserLoginResponseDto>
            ) {
                Log.i("testt", "${call.request()}")
                if (response.isSuccessful) {
                    Log.d("testt", "${response.code()} ${response.body()}")
                } else {
                    val errorBody = response.errorBody()?.source()?.buffer?.clone()?.readUtf8()
                    Log.e("testt", "${response.code()} $errorBody")
                }
            }

            override fun onFailure(call: Call<UserLoginResponseDto>, t: Throwable) {
                Log.i("testt", "${call.request()}")
                Log.e("testt", t.message.toString())
            }
        })
    }
}