package com.mountain.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mountain.quizapp.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text = intent.getStringExtra(Constants.USER_NAME)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)   // Int는 디폴트값 필요
        val totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        binding.tvScore.text = "Your score is ${correctAnswer} out of ${totalQuestion}"

        binding.btnFinish.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}