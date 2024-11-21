package com.olbareum.olbareum.select

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.olbareum.olbareum.databinding.ActivitySentenceSelectBinding

class SentenceSelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySentenceSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySentenceSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerView = binding.sentenceList
        recyclerView.adapter = SentenceRecyclerViewAdapter(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}