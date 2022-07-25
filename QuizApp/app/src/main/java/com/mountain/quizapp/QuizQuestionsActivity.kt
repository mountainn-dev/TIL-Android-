package com.mountain.quizapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.mountain.quizapp.databinding.ActivityQuizquestions1Binding
import kotlin.collections.ArrayList

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizquestions1Binding
    // 여러 메소드에서 쓰기 위해 전역변수 설정
    private var mCurrentPosition = 1   // TODO: lateinit을 왜 못 쓰는지? -> kotlin 문법상 primitive 타입(Bollean, Int 등)은 lateinit 불가
    private val mQuestionList = Constants.getQuestions()
    private var mSelectedOptionPosition = 1
    private var mbtnNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizquestions1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setQuestion()

        // TODO: 여기부분 강의에서 this가 어떻게 onClick 메서드로 연결되는지?
        binding.tvOption1.setOnClickListener { onClick(binding.tvOption1) }
        binding.tvOption2.setOnClickListener { onClick(binding.tvOption2) }
        binding.tvOption3.setOnClickListener { onClick(binding.tvOption3) }
        binding.tvOption4.setOnClickListener { onClick(binding.tvOption4) }
        // binding.tvQuestion.setOnClickListener(this) -> 소괄호 형식으로 인수 형태가 위 코드와 다름.

        binding.btnSubmit.setOnClickListener { clickAnswerSubmit() }
    }

    private fun setQuestion() {
        var question = mQuestionList[mCurrentPosition - 1]
        defaultOptionView()

        binding.tvQuestion.text = question.questions
        binding.imgQuestion.setImageResource(question.image)   // Question class 파일 생성 시 image 파라미터를 int 타입으로 지정했으므로 해당 메서드를 사용한다
        binding.pgbar.progress = mCurrentPosition - 1
        binding.tvpgbar.text = "${mCurrentPosition - 1}/${binding.pgbar.max}"
        binding.tvOption1.text = question.optionOne
        binding.tvOption2.text = question.optionTwo
        binding.tvOption3.text = question.optionThree
        binding.tvOption4.text = question.optionFour
        binding.btnSubmit.text = "Show Answer"
    }
// TODO: setBg와 seeAnswer메서드로 보완

//    private fun clickSeeAnswer1() {
//        var question = mQuestionList[mCurrentPosition - 1]
//        mbtnNum += 1
//        if (mSelectedOptionPosition == question.cor
//                1 -> binding.tvOption1.setBackgroundResource(R.drawable.correctanswer_bg)
//                2 -> binding.tvOption2.setBackgroundResource(R.drawable.correctanswer_bg)
//                3 -> binding.tvOption3.setBackgroundResource(R.drawable.correctanswer_bg)
//                4 -> binding.tvOption4.setBackgroundResource(R.drawable.correctanswer_bg)
//            }
//        }
//        else {
//            when (mSelectedOptionPosition) {
//                1 -> binding.tvOption1.setBackgroundResource(R.drawable.wronganswer_bg)
//                2 -> binding.tvOption2.setBackgroundResource(R.drawable.wronganswer_bg)
//                3 -> binding.tvOption3.setBackgroundResource(R.drawable.wronganswer_bg)
//                4 -> binding.tvOption4.setBackgroundResource(R.drawable.wronganswer_bg)
//            }
//
//            when (question.correctAnswer) {
//                1 -> binding.tvOption1.setBackgroundResource(R.drawable.correctanswer_bg)
//                2 -> binding.tvOption2.setBackgroundResource(R.drawable.correctanswer_bg)
//                3 -> binding.tvOption3.setBackgroundResource(R.drawable.correctanswer_bg)
//                4 -> binding.tvOption4.setBackgroundResource(R.drawable.correctanswer_bg)
//            }
//        }
//        binding.btnSubmit.text = "Submit"
//}
    private fun showAnswer(select: Int) {
    mbtnNum += 1
    var question = mQuestionList[mCurrentPosition-1]
    var answer = question.correctAnswer
    if (select == answer) setBg(select, R.drawable.correctanswer_bg)
    else {
        setBg(select, R.drawable.wronganswer_bg)
        setBg(answer, R.drawable.correctanswer_bg)
        }
    binding.btnSubmit.text = "Submit Answer"
    }
    private fun setBg(num: Int, bgID: Int) {
        when(num) {
            1 -> binding.tvOption1
            2 -> binding.tvOption2
            3 -> binding.tvOption3
            4 -> binding.tvOption4
            else -> null   // for safety
        }?.setBackgroundResource(bgID)   // TODO: 메서드 공통문구 활용
    }
    private fun submitAnswer() {
        mbtnNum += 1
        if (mCurrentPosition == mQuestionList.size) {   // 마지막 퀴즈 문제일 경우
            binding.btnSubmit.text = "Finish"
        }
        else {
            mCurrentPosition += 1
            setQuestion()
        }
    }
    private fun clickAnswerSubmit() {
        if (mbtnNum%2 == 0) {
            showAnswer(mSelectedOptionPosition)
            return   // TODO: callback hell 방지, if문 실행될 경우 밑으로 코드 더이상 진행x
        }
        submitAnswer()
    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        binding.tvOption1?.let {
            options.add(0, it)
        }
        binding.tvOption2?.let {
            options.add(1, it)
        }
        binding.tvOption3?.let {
            options.add(2, it)
        }
        binding.tvOption4?.let {
            options.add(3, it)
        }

        for (i in options) {
            // TODO: typeface default가 정확히 무슨뜻? -> 텍스트 스타일 초기화 용도
            i.typeface = Typeface.DEFAULT   //

            // xml에 설정한 이 배경을 굳이 한번 더 지정해줘야되나? -> 마찬가지로 선택됐을 경우, 다음 문제에서 초기화해주는 용도
            // i.background = this.getDrawable(R.drawable.editbox_bg)
            i.background = ContextCompat.getDrawable(this, R.drawable.editbox_bg)
            // TODO: this.get~, ContextCompat.~ 모두 draweble 를 입력받아 배경설정하는 메서드
            // this.get~을 이용하면 안드로이드 버전을 구분해서 코드를 작성해야하지만, context~ 메서드는 버전 구분필요x -> context로 통일 추천
            // i.setBackgroundResource(R.drawable.editbox_bg) 참고로, R.~ 값을 직접 입력하여 바로 파일 혹은 뷰 값을 가져올 수도 있다.

        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum
        tv.background = ContextCompat.getDrawable(this, R.drawable.selectedbox_bg)
        tv.setTypeface(tv.typeface, Typeface.BOLD)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.tvOption1.id -> selectedOptionView(binding.tvOption1, 1)
            binding.tvOption2.id -> selectedOptionView(binding.tvOption2, 2)
            binding.tvOption3.id -> selectedOptionView(binding.tvOption3, 3)
            binding.tvOption4.id -> selectedOptionView(binding.tvOption4, 4)
        }
    }


}