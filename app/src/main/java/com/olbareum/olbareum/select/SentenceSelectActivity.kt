package com.olbareum.olbareum.select

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.olbareum.olbareum.BaseActivity
import com.olbareum.olbareum.databinding.ActivitySentenceSelectBinding
import com.olbareum.olbareum.enums.FeedbackType
import com.olbareum.olbareum.pronunciation.PronunciationSentenceData

class SentenceSelectActivity : BaseActivity() {

    private lateinit var binding: ActivitySentenceSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySentenceSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val feedbackType = intent.getSerializableExtra("feedback_type") ?: FeedbackType.PRONUNCIATION
        val pronunciationType = intent.getStringExtra("pronunciation_type") ?: ""
        val intonationType = intent.getStringExtra("intonation_type") ?: ""

        val recyclerView = binding.sentenceList
        recyclerView.adapter =
            SentenceRecyclerViewAdapter(PronunciationSentenceData.data[pronunciationType] ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}