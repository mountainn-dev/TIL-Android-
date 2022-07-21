package com.mountain.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.mountain.quizapp.databinding.ActivityQuizquestions1Binding

class QuizQuestions1Activity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizquestions1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizquestions1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val questionList = Constants.getQuestions()
        Log.i("Question size is", "${questionList.size}")

        var currentPosition = 1
        val question1: Question = questionList[currentPosition-1]

        binding.tvQuestion1.text = question1.questions
        binding.imgQuiz1.setImageResource(question1.image)   // Question class 파일 생성 시 image 파라미터를 int 타입으로 지정했으므로 해당 메서드를 사용한다
        binding.pgbarQuiz1.progress = currentPosition-1
        binding.tvpgbarQuiz1.text = "${currentPosition-1}/${binding.pgbarQuiz1.max}"
        binding.tvQuiz1Option1.text = question1.optionOne
        binding.tvQuiz1Option2.text = question1.optionTwo
        binding.tvQuiz1Option3.text = question1.optionThree
        binding.tvQuiz1Option4.text = question1.optionFour
        binding.btnSubmit.setOnClickListener { clickSubmit() }
    }

    private fun clickSubmit() {
        val intent = Intent(this, QuizQuestions2Activity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.horizon_enter, R.anim.none)
        finish()
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}