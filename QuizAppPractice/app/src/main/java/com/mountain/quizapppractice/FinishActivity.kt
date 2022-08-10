package com.mountain.quizapppractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mountain.quizapppractice.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("Name")
        val score = intent.getIntExtra("Score", 0).toString()
        val count = intent.getIntExtra("QuestionCount", 0).toString()

        binding.tvScore.text = "${name}! your score is ${score}/${count}"
        binding.btnAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}