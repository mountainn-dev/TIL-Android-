package com.mountain.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mountain.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener { clickStart() }
    }

    private fun clickStart() {
        if (binding.editName.text.isEmpty()) {
            Toast.makeText(this, "Enter your name", Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            startActivity(intent)
        }
    }
}
