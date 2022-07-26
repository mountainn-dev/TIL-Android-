package com.mountain.quizapp

import androidx.annotation.DrawableRes

data class Question (
    val id: Int,
    val questions: String,
    // TODO: 리소스 어노테이션 -> 리소스 값을 입력받는 경우, 해당 어노테이션을 달아준다. 다른 리소스 ex)id, color 등 입력 방지
    @DrawableRes val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)
