package com.mountain.DOBCalc

import android.content.Context
import android.text.TextPaint
import android.text.style.RelativeSizeSpan
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat

// Custom Span
class RelativeSizeColorSpan(
    size: Float,
    @ColorInt private val color: Int,) : RelativeSizeSpan(size) {
    override fun updateDrawState(textPaint: TextPaint) {
        // TODO: super 원리 -> 부모 클래스의 기존 메서드 내용 불러오는 목적, 그 이후 커스텀 코드를 작성한다.
        //  상속 메서드를 그대로 가져와야 하기 때문에 파라미터 변경도 불가능하다.
        super.updateDrawState(textPaint)
        textPaint.color = color
    }
}