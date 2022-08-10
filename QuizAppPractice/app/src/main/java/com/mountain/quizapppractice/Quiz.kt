package com.mountain.quizapppractice

import android.content.Context
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

var mCurrentPosition = 0
var mCurrentSelected = 0
var mAnswerExpose = false
var mScore = 0
object Quiz {
    private val question1 = Question(
        1, "1. What country does below flag means?", R.drawable.img_flag_korea,
        "USA", "Korea", "China", "Japan", 2
    )
    private val question2 = Question(
        2, "2. What name of hero below image?", R.drawable.img_hero_ironman,
        "Captain America", "Black Panther",
        "Captain Marvel", "Iron Man", 4
    )
    private val question3 = Question(
        3, "3. What name of planet below image?", R.drawable.img_planet_saturn,
        "Saturn", "Earth", "Mars", "Jupiter", 1
    )
    private val question4 = Question(
        4, "4. What name of animal below image?", R.drawable.img_animal_penguin,
        "Lion", "Penguin", "Dog", "Cat", 2
    )
    private val questionList = arrayOf(question1, question2, question3, question4)

    fun setQuestion(
        question: TextView,
        img: ImageView,
        pg: ProgressBar,
        tvPg: TextView,
        opt1: TextView,
        opt2: TextView,
        opt3: TextView,
        opt4: TextView,
        btn: Button,
    ) {
        question.text = questionList[mCurrentPosition].question
            img.setImageResource(questionList[mCurrentPosition].image)
            pg.progress = mCurrentPosition + 1
            tvPg.text = "${mCurrentPosition+1}/${pg.max}"
            opt1.text = questionList[mCurrentPosition].option1
            opt2.text = questionList[mCurrentPosition].option2
            opt3.text = questionList[mCurrentPosition].option3
            opt4.text = questionList[mCurrentPosition].option4
            btn.text = "Check Answer"

            mCurrentSelected = 0
            mAnswerExpose = false
            opt1.setBackgroundResource(R.drawable.border_editbox)
            opt2.setBackgroundResource(R.drawable.border_editbox)
            opt3.setBackgroundResource(R.drawable.border_editbox)
            opt4.setBackgroundResource(R.drawable.border_editbox)
    }

    fun checkAnswer(
        opt1: TextView,
        opt2: TextView,
        opt3: TextView,
        opt4: TextView,
        btn: Button,
        pg: ProgressBar,
        context: Context
    ) {
        if (mCurrentSelected == questionList[mCurrentPosition].answer) {
            Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT).show()
            mScore++
            when (mCurrentSelected) {
                1 -> opt1.setBackgroundResource(R.drawable.background_correct)
                2 -> opt2.setBackgroundResource(R.drawable.background_correct)
                3 -> opt3.setBackgroundResource(R.drawable.background_correct)
                4 -> opt4.setBackgroundResource(R.drawable.background_correct)
            }
        } else {
            Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT).show()
            when (mCurrentSelected) {
                1 -> opt1.setBackgroundResource(R.drawable.background_wrong)
                2 -> opt2.setBackgroundResource(R.drawable.background_wrong)
                3 -> opt3.setBackgroundResource(R.drawable.background_wrong)
                4 -> opt4.setBackgroundResource(R.drawable.background_wrong)
            }
            when (questionList[mCurrentPosition].answer) {
                1 -> opt1.setBackgroundResource(R.drawable.background_correct)
                2 -> opt2.setBackgroundResource(R.drawable.background_correct)
                3 -> opt3.setBackgroundResource(R.drawable.background_correct)
                4 -> opt4.setBackgroundResource(R.drawable.background_correct)
            }
        }
        if (mCurrentPosition == pg.max-1) btn.text = "Finish Quiz"
        else btn.text = "Next Question"
        mAnswerExpose = true
        mCurrentPosition++
    }
}