package com.olbareum.olbareum.record

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.olbareum.olbareum.R

class WaveformView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val ampList = mutableListOf<Float>()
    private val rectList = mutableListOf<RectF>()

    private val redPaint = Paint().apply {
        color = context.getColor(R.color.yellow5)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (rectF in rectList) {
            canvas.drawRect(rectF, redPaint)
        }
    }

    fun addAmplitude(maxAmplitude: Float) {
        val amplitude = (maxAmplitude / Short.MAX_VALUE) * this.height * 0.8f

        ampList.add(amplitude)
        rectList.clear()

        val rectWidth = 15f
        val maxRect = (this.width / rectWidth).toInt()

        val amps = ampList.takeLast(maxRect)

        for ((i, amp) in amps.withIndex()) {
            val rectF = RectF()
            rectF.top = (this.height / 2) - (amp / 2) - 3f
            rectF.bottom = rectF.top + amp + 3f
            rectF.left = i * rectWidth
            rectF.right = rectF.left + rectWidth - 5f   // 여백을 위해 5를 더 줌

            rectList.add(rectF)
        }

        invalidate()
    }

    fun clearData() {
        ampList.clear()
    }

    fun clearWave() {
        rectList.clear()
        invalidate()
    }
}