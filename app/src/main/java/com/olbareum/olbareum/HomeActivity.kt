package com.olbareum.olbareum

import android.content.Intent
import android.os.Bundle
import com.olbareum.olbareum.databinding.ActivityHomeBinding
import com.olbareum.olbareum.select.IntonationTypeSelectActivity
import com.olbareum.olbareum.select.PronunciationTypeSelectActivity

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pronunciationButton.setOnClickListener {
            startActivity(Intent(this, PronunciationTypeSelectActivity::class.java))
        }
        binding.intonationButton.setOnClickListener {
            startActivity(Intent(this, IntonationTypeSelectActivity::class.java))
        }
    }
}