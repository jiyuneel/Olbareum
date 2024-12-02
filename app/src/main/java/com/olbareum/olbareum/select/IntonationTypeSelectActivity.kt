package com.olbareum.olbareum.select

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
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
                Pair("의문문", R.drawable.img_interrogative),
                Pair("평서문", R.drawable.img_declarative),
                Pair("감탄문", R.drawable.img_exclamatory),
                Pair("청유문", R.drawable.img_request)
            )
        )
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}