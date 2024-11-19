package com.olbareum.olbareum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.olbareum.olbareum.databinding.ActivityFeedbackBinding
import com.olbareum.olbareum.retrofit.dto.feedback.PronunciationFeedbackResponseDto

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val feedbackData = intent.getParcelableExtra<PronunciationFeedbackResponseDto>("feedbackData")
        if (feedbackData != null) {
//            val pronunciationScore = feedbackData.pronunciationScore.toInt()
//            binding.score.text = "${pronunciationScore}점"
//            binding.scoreBar.progress = pronunciationScore
//            binding.pronunciationFeedback.text = feedbackData.pronunciationFeedback
//            binding.intonationFeedback.text = feedbackData.intonationFeedback
//            binding.sentence.text = feedbackData.textSentence
//            binding.transcription.text = feedbackData.transcription
//
//            Glide.with(binding.root.context)
//                .load(feedbackData.pronunciationFeedbackImage)
//                .into(binding.pronunciationFeedbackImage)
//
//            Glide.with(binding.root.context)
//                .load(feedbackData.intonationFeedbackImage)
//                .into(binding.intonationFeedbackImage)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}