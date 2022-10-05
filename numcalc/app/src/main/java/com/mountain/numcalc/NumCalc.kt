package com.mountain.numcalc

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

object NumCalc {
    // TODO: 연산기능을 위해 저장될 숫자 공간을 메서드 안에서 선언할 경우
    //  연산자를 누를 때마다 이전 정보가 초기화되기 때문에 처음 선언은 메서드 바깥에서 해준다.
    var mFirstNum = 0
    var mSecondNum = 0
    var mSaveNum = 0
    var mResult = 0
    var mSymbol = ""
    var mPercent = false
    var mStatus = true

    fun selectNumber(button: Button, text: TextView) {
        if (text.text.toString() == "0") {
            text.text = button.text.toString()
        }
        else text.append(button.text)
    }
    fun selectDecimal(button: Button, text: TextView) {
        if (!text.text.contains(".")) text.append(button.text)
    }
    fun selectCLR(text: TextView) {
        text.text = "0"
        mFirstNum = 0
        mSecondNum = 0
        mSaveNum = 0
        mResult = 0
        mSymbol = ""
    }
    fun selectOperator(button: Button, text: TextView) {
        if (mFirstNum == 0) {
            mFirstNum = text.text.toString().toInt()
            mSymbol = button.text.toString()
        }
        else {
            mSecondNum = text.text.toString().toInt()
            when (mSymbol) {
                "+" -> mSaveNum = mFirstNum + mSecondNum
                "-" -> mSaveNum = mFirstNum - mSecondNum
                "*" -> mSaveNum = mFirstNum * mSecondNum
                "÷" -> mSaveNum = mFirstNum / mSecondNum
            }
            mFirstNum = mSaveNum
            // mSaveNum을 임시 저장공간으로만 활용하고 곧바로 mFirstNum으로 값을 재할당해주면서
            // 중간연산을 마무리지어준다.
            mSymbol = button.text.toString()
        }
        text.text = "0"
    }
    fun selectResult(text: TextView) {
        if (mSecondNum == 0) {
            when (mSymbol) {
                "+" -> mResult = mFirstNum + text.text.toString().toInt()
                "-" -> mResult = mFirstNum - text.text.toString().toInt()
                "*" -> mResult = mFirstNum * text.text.toString().toInt()
                "÷" -> mResult = mFirstNum / text.text.toString().toInt()
            }
            text.text = "$mResult"
        }
        else {
            when (mSymbol) {
                "+" -> mResult = mFirstNum + text.text.toString().toInt()
                "-" -> mResult = mFirstNum - text.text.toString().toInt()
                "*" -> mResult = mFirstNum * text.text.toString().toInt()
                "÷" -> mResult = mFirstNum / text.text.toString().toInt()
            }
            text.text = "$mResult"
        }
    }
    fun selectPercent(text: TextView, percent: TextView) {
        val number = text.text.toString().toInt()
        if (!mPercent) {
            text.text = (number * 100).toString()
            percent.text = "%"
            mPercent = true
        }
        else {
            text.text = (number / 100).toString()
            percent.text = ""
            mPercent = false
        }
    }
//    fun selectChange(text: TextView) {
//        if (mStatus) {
//            text.append("-")
//            mStatus = false
//        }
//        else {
//            mStatus = true
//        }
//    }
}
