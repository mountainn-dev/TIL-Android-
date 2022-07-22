package com.mountain.quizapp

object Constants {   // object는 객체 생성 과정을 생략하고 곧바로 사용할 수 있는 클래스와 비슷한 개념
    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val que1 = Question(1, "What country does this flag belong to?",
            R.drawable.flag_southkorea, "Argentina", "United States",
            "France", "Korea", 4)

        val que2 = Question(1, "Who is he?",
            R.drawable.char_obama, "george w bush", "barack obama",
            "mahatma gandhi", "george washington", 2)

        questionList.add(que1)
        questionList.add(que2)

        return questionList
    }
}