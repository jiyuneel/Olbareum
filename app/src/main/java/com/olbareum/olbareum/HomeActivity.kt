package com.olbareum.olbareum

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.olbareum.olbareum.databinding.ActivityHomeBinding
import com.olbareum.olbareum.select.SentenceSelectActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pronunciationButton.setOnClickListener {
            startActivity(Intent(this, SentenceSelectActivity::class.java))
        }

        binding.intonationButton.setOnClickListener {}
    }
}