package com.varivoda.igor.signature

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

class SignatureView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val outlinePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 3f
    }

    private val paintSignature = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 8f
    }
    private val paintLine = Paint().apply {
        color = Color.GRAY
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }
    private var path = Path()
    private lateinit var frame: Rect
    private lateinit var frameFloat: RectF
    private var backgroundCol = Color.WHITE
    private var showLine: Boolean = true

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    private var currentX = 0f
    private var currentY = 0f

    private var fullPath = Path()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    private fun touchMove() {
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to cache it.
            extraCanvas.drawPath(path, paintSignature)
        }
        invalidate()
    }

    private fun touchUp() {
        fullPath.addPath(path)
        path.reset()
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(extraBitmap,0f,0f,null)
        canvas?.drawRoundRect(frameFloat, 10f, 10f, outlinePaint)

        if(showLine){
            canvas?.drawLine((inset*4).toFloat(), height - (inset*4).toFloat(),
                width - (inset*4).toFloat(), height - (inset*4).toFloat(), paintLine)
        }

    }

    private val inset = 20

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width - inset ,height - inset , Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundCol)
        frame = Rect(inset, inset, width - inset, height - inset)
        frameFloat = RectF(frame)
    }

    /**USER FUNCTIONS*/
    override fun setBackgroundColor(color: Int){
        backgroundCol = color
    }

    fun setSignatureColor(color: Int){
        paintSignature.color = color
    }

    fun setOutlineColor(color: Int){
        outlinePaint.color = color
    }

    fun getBitmapOrNullIfEmpty(): Bitmap?{
        return if (::extraBitmap.isInitialized) extraBitmap else null
    }

    fun clearSignature(){
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundCol)
        fullPath = Path()
    }

    fun isSignatureEmpty(): Boolean {
        return fullPath.isEmpty
    }

    fun setSignatureLineColor(color: Int){
        paintLine.color = color
    }

    fun hideSignatureLine(){
        showLine = false
    }

}