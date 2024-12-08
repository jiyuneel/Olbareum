package com.olbareum.olbareum.select

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.olbareum.olbareum.BaseActivity
import com.olbareum.olbareum.databinding.ActivitySentenceSelectBinding
import com.olbareum.olbareum.enums.FeedbackType
import com.olbareum.olbareum.intonation.IntonationSentenceData
import com.olbareum.olbareum.pronunciation.PronunciationSentenceData

class SentenceSelectActivity : BaseActivity() {

    private lateinit var binding: ActivitySentenceSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySentenceSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val feedbackType = intent.getSerializableExtra("feedback_type") as FeedbackType?
        val pronunciationType = intent.getStringExtra("pronunciation_type") ?: ""
        val intonationType = intent.getStringExtra("intonation_type") ?: ""

        val sentenceData = mutableListOf<String>()
        when (feedbackType) {
            FeedbackType.PRONUNCIATION -> {
                PronunciationSentenceData.data[pronunciationType]?.forEach {
                    sentenceData.add(it.second)
                }
            }
            FeedbackType.INTONATION -> {
                IntonationSentenceData.data[intonationType]?.forEach {
                    sentenceData.add(it.second)
                }
            }
            null -> {}
        }

        val recyclerView = binding.sentenceList
        recyclerView.adapter = SentenceRecyclerViewAdapter(sentenceData, feedbackType)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}