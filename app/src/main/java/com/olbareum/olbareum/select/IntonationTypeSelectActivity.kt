package com.olbareum.olbareum.select

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.olbareum.olbareum.BaseActivity
import com.olbareum.olbareum.R
import com.olbareum.olbareum.databinding.ActivityIntonationTypeSelectBinding

class IntonationTypeSelectActivity : BaseActivity() {

    private lateinit var binding: ActivityIntonationTypeSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntonationTypeSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerView = binding.typeList
        recyclerView.adapter = IntonationTypeRecyclerViewAdapter(
            listOf(
                Pair("평서문", R.drawable.img_declarative),
                Pair("의문문", R.drawable.img_interrogative),
                Pair("감탄문", R.drawable.img_exclamatory)
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}