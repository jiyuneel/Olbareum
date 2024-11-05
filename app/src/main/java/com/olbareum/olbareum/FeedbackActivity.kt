package com.olbareum.olbareum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.olbareum.olbareum.databinding.ActivityFeedbackBinding
import com.olbareum.olbareum.retrofit.dto.feedback.FeedbackResponseDto

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val feedbackData = intent.getParcelableExtra<FeedbackResponseDto>("feedbackData")
        if (feedbackData != null) {
            binding.score.text = "${feedbackData.pronunciationScore}점"
//            val pronunciationScore = feedbackData.pronunciationScore.toInt()
//            binding.scoreBar.progress = feedbackData.pronunciationScore.toInt()
            binding.pronunciationFeedback.text = feedbackData.pronunciationFeedback
//            binding.intonationFeedback.text = feedbackData.intonationFeedback
            binding.sentence.text = feedbackData.textSentence
            binding.transcription.text = feedbackData.transcription

            Glide.with(binding.root.context)
                .load(feedbackData.pronunciationFeedbackImage)
                .into(binding.pronunciationFeedbackImage)

//            Glide.with(binding.root.context)
//                .load(feedbackData.intonationFeedbackImage)
//                .into(binding.intonationFeedbackImage)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}