package com.olbareum.olbareum.retrofit.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olbareum.olbareum.retrofit.RetrofitService
import com.olbareum.olbareum.retrofit.dto.feedback.FeedbackResponseDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackViewModel : ViewModel() {
    private val _feedback = MutableLiveData<FeedbackResponseDto>()
    val feedback: LiveData<FeedbackResponseDto> get() = _feedback

    fun createFeedback(textSentence: String, audioFile: MultipartBody.Part) {
        RetrofitService.feedbackApi.createFeedback(textSentence = textSentence, audioFile = audioFile).enqueue(object : Callback<FeedbackResponseDto> {
            override fun onResponse(
                call: Call<FeedbackResponseDto>,
                response: Response<FeedbackResponseDto>
            ) {
                Log.i("testt", "${call.request()}")
                if (response.isSuccessful) {
                    Log.d("testt", "${response.code()} ${response.body()}")
                    _feedback.value = response.body()
                } else {
                    Log.e("testt", "${response.code()} ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<FeedbackResponseDto>, t: Throwable) {
                Log.i("testt", "${call.request()}")
                Log.e("testt", t.message.toString())
            }
        })
    }
}