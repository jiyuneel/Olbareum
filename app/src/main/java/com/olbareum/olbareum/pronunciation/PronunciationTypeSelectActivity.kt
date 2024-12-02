package com.olbareum.olbareum.pronunciation

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.olbareum.olbareum.BaseActivity
import com.olbareum.olbareum.databinding.ActivityPronunciationTypeSelectBinding

class PronunciationTypeSelectActivity : BaseActivity() {

    private lateinit var binding: ActivityPronunciationTypeSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPronunciationTypeSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.typeList
        recyclerView.adapter =
            PronunciationTypeRecyclerViewAdapter(PronunciationSentenceData.data.keys.toList())
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}