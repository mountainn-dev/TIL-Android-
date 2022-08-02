package com.mountain.DOBCalc

import androidx.annotation.StyleRes
import org.w3c.dom.Text

interface SetStyle {
    // TODO: 메서드 파라미터에는 val, var 키워드 입력x 오버라이딩(인수는 동일, 구현부 차이) 오버로딩(인수차이, 구현부차이) 모두 입력x
    //        println(3)
    //        println("Hello")
    //        println(3.0)   -> 오버로딩 ex println()
    //        인터페이스에는 생성자 구현 x
    fun setStyle(@StyleRes style: Int)
}