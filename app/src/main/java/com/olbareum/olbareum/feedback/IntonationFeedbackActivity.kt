package com.olbareum.olbareum.feedback

import android.media.MediaPlayer
import android.os.Bundle
import com.bumptech.glide.Glide
import com.olbareum.olbareum.BaseActivity
import com.olbareum.olbareum.databinding.ActivityIntonationFeedbackBinding
import com.olbareum.olbareum.intonation.IntonationSentenceData
import com.olbareum.olbareum.retrofit.dto.feedback.IntonationFeedbackResponseDto

class IntonationFeedbackActivity : BaseActivity() {

    private lateinit var binding: ActivityIntonationFeedbackBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntonationFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val feedbackData =
            intent.getParcelableExtra<IntonationFeedbackResponseDto>("feedbackData")
        val sentence = intent.getStringExtra("sentence") ?: ""

        if (feedbackData != null) {
            val intonationScore = feedbackData.intonationScore.toInt()
            binding.score.text = "${intonationScore}점"
            binding.scoreBar.progress = intonationScore

            binding.sentence.text = IntonationSentenceData.getSentenceByCode(feedbackData.sentenceCode)
            binding.message.text = feedbackData.intonationFeedbacks

            Glide.with(this)
                .load(feedbackData.intonationImageUrls)
                .into(binding.image)

            Glide.with(this)
                .load(feedbackData.feedbackImageUrls)
                .into(binding.feedbackImage)
        } else {
            val url = "https://allbareum.s3.ap-northeast-2.amazonaws.com/images/image/0_0.jpg"
            Glide.with(this)
                .load(url)
                .into(binding.image)

            Glide.with(this)
                .load(url)
                .into(binding.feedbackImage)
        }

        val audioFile = IntonationSentenceData.getRawResourceIdBySentence(this, sentence)
        mediaPlayer = MediaPlayer.create(this, audioFile)
        binding.listenButton.setOnClickListener {
            mediaPlayer.start()
        }
    }
}