package com.olbareum.olbareum.util

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

class CustomTypefaceSpan(family: String, private val newType: Typeface) : TypefaceSpan(family) {
    override fun updateDrawState(tp: TextPaint) {
        applyCustomTypeFace(tp, newType)
    }

    override fun updateMeasureState(tp: TextPaint) {
        applyCustomTypeFace(tp, newType)
    }

    private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
        paint.typeface = tf
    }
}