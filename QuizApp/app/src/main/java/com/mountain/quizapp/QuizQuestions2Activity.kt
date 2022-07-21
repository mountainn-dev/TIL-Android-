package com.mountain.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.mountain.quizapp.databinding.ActivityQuizquestions2Binding

class QuizQuestions2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizquestions2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizquestions2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val questionList = Constants.getQuestions()

        var currentPosition = 1
        val question2: Question = questionList[currentPosition]

        binding.tvQuestion2.text = question2.questions
        binding.imgQuiz2.setImageResource(question2.image)   // Question class 파일 생성 시 image 파라미터를 int 타입으로 지정했으므로 해당 메서드를 사용한다
        binding.pgbarQuiz2.progress = currentPosition
        binding.tvpgbarQuiz2.text = "${currentPosition}/${binding.pgbarQuiz2.max}"
        binding.tvQuiz2Option1.text = question2.optionOne
        binding.tvQuiz2Option2.text = question2.optionTwo
        binding.tvQuiz2Option3.text = question2.optionThree
        binding.tvQuiz2Option4.text = question2.optionFour
        binding.btnSubmit.setOnClickListener { clickSubmit() }
    }

    private fun clickSubmit() {
        val intent = Intent(this, FinishActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.horizon_enter, R.anim.none)
        finish()
    }
}