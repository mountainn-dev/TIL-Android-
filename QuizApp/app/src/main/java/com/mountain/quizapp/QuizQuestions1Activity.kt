package com.mountain.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import com.mountain.quizapp.databinding.ActivityQuizquestions1Binding
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class QuizQuestions1Activity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizquestions1Binding
    // 여러 메소드에서 쓰기 위해 전역변수 설정
    private var mCurrentPosition = 1
    private lateinit var mQuestionList: ArrayList<Question>
    private var mSelectedOptionPosition = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizquestions1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setQuestion()
        binding.btnSubmit.setOnClickListener { clickSubmit() }
    }

    private fun setQuestion() {
        mQuestionList = Constants.getQuestions()
        Log.i("Question size is", "${mQuestionList.size}")
        val question: Question = mQuestionList[mCurrentPosition - 1]

        binding.tvQuestion.text = question.questions
        binding.imgQuestion.setImageResource(question.image)   // Question class 파일 생성 시 image 파라미터를 int 타입으로 지정했으므로 해당 메서드를 사용한다
        binding.pgbar.progress = mCurrentPosition - 1
        binding.tvpgbar.text = "${mCurrentPosition - 1}/${binding.pgbar.max}"
        binding.tvOption1.text = question.optionOne
        binding.tvOption2.text = question.optionTwo
        binding.tvOption3.text = question.optionThree
        binding.tvOption4.text = question.optionFour
    }

    private fun clickSubmit() {
        if (mCurrentPosition == mQuestionList.size) {
            binding.btnSubmit.text = "Finish"
        }
        else {
            mCurrentPosition += 1
            setQuestion()
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}