package com.olbareum.olbareum.retrofit.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olbareum.olbareum.retrofit.RetrofitService
import com.olbareum.olbareum.retrofit.dto.feedback.PronunciationFeedbackResponseDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackViewModel : ViewModel() {
    private val _feedback = MutableLiveData<PronunciationFeedbackResponseDto>()
    val feedback: LiveData<PronunciationFeedbackResponseDto> get() = _feedback

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun createFeedback(textSentence: String, audioFile: MultipartBody.Part) {
        RetrofitService.feedbackApi.createPronunciationFeedback(textSentence, audioFile)
            .enqueue(object : Callback<PronunciationFeedbackResponseDto> {
                override fun onResponse(
                    call: Call<PronunciationFeedbackResponseDto>,
                    response: Response<PronunciationFeedbackResponseDto>
                ) {
                    Log.i("testt", "${call.request()}")
                    if (response.isSuccessful) {
                        Log.d("testt", "${response.code()} ${response.body()}")
                        _feedback.value = response.body()
                    } else {
                        // response.errorBody()?.string()을 호출하면 스트림이 소모되어 다시 사용할 수 없음
//                    Log.e("testt", "${response.code()} ${response.errorBody()?.string()}")
                        try {
                            val errorResponse =
                                response.errorBody()?.let { RetrofitService.errorBody.convert(it) }
                            _errorMessage.value = errorResponse?.message
                        } catch (e: Exception) {
                            Log.e("testt", e.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<PronunciationFeedbackResponseDto>, t: Throwable) {
                    Log.i("testt", "${call.request()}")
                    Log.e("testt", t.message.toString())
                }
            })
    }
}