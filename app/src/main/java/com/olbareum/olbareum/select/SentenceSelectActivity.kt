package com.olbareum.olbareum.select

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.olbareum.olbareum.BaseActivity
import com.olbareum.olbareum.databinding.ActivitySentenceSelectBinding

class SentenceSelectActivity : BaseActivity() {

    private lateinit var binding: ActivitySentenceSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySentenceSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("type") ?: ""

        val recyclerView = binding.sentenceList
        recyclerView.adapter =
            SentenceRecyclerViewAdapter(PronunciationSentenceData.data[type] ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}