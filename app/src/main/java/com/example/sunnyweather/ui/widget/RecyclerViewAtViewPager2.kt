package com.example.sunnyweather.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


/**
 * @Description isCanSlide 设置为false时，viewpager不能横向滑动，用在横向滑动控件与viewpager冲突时
 * @Version 1.0
 */
class RecyclerViewAtViewPager2 : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
    ) : super(context, attrs, defStyleAttr)
    private var startX = 0
    private var startY = 0
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x.toInt()
                startY = ev.y.toInt()
                parent.requestDisallowInterceptTouchEvent(true)
            }

            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x.toInt()
                val endY = ev.y.toInt()
                val disX = abs(endX - startX)
                val disY = abs(endY - startY)
                /*LogUtils.d(
                    "DispatchTouchEvent disX=" + disX + "; disY" + disY + "; canScrollHorizontally(startX - endX) = " + canScrollHorizontally(
                        startX - endX
                    ) + "; canScrollVertically(startY - endY)" + canScrollVertically(startY - endY)
                )*/
                if (disX > disY) {
                    //如果是纵向滑动，告知父布局不进行时间拦截，交由子布局消费，　requestDisallowInterceptTouchEvent(true)
                    parent.requestDisallowInterceptTouchEvent(canScrollHorizontally(startX - endX))
                } else {
                    parent.requestDisallowInterceptTouchEvent(canScrollVertically(startX - endX))
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> parent.requestDisallowInterceptTouchEvent(
                false
            )
        }
        return super.dispatchTouchEvent(ev)
    }
}