package com.mountain.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.mountain.quizapp.databinding.ActivityQuizquestionsBinding

class QuizQuestionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizquestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizquestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val questionList = Constants.getQuestions()
        Log.i("Question size is", "${questionList.size}")
    }
}