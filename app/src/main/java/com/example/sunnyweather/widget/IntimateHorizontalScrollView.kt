package com.example.sunnyweather.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.HorizontalScrollView
import com.ct.base.ext.dp
import com.example.sunnyweather.R
import kotlin.math.abs

/**
 * 说明：列表左滑布局
 *
 * @作者 luohao
 * @创建时间 2021/11/30 16:10
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
class IntimateHorizontalScrollView(context: Context, attrs: AttributeSet) : HorizontalScrollView(context, attrs) {
    var isExpand = false // 是否展开
    var position = -1 // 所属位置
    lateinit var onSkidListener: (Boolean) -> Unit // 侧滑状态回调

    private var scrollDistance = 0.0f // 触发展开和收起的距离

    init {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet) {
        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.IntimateHorizontalScrollView)
        scrollDistance = mTypedArray.getDimension(R.styleable.IntimateHorizontalScrollView_scrollDistance,
            30.dp.toFloat()
        )
        mTypedArray.recycle()
    }

    private var downX = 0
    private var downY = 0

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x.toInt()
                downY = ev.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                if(::onSkidListener.isInitialized){
                    this.onSkidListener(true)
                }
            }

            // 手势抬起或取消事件，根据滑动距离展开或者收起
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL -> {
                if (scrollX > scrollDistance) {
                    smoothScrollTo(2000, 0)
                    isExpand = true
                } else {
                    smoothScrollTo(0, 0)
                    if(::onSkidListener.isInitialized){
                        this.onSkidListener(false)
                    }
                    isExpand = false
                }

                // 判断如果是点击事件不消费，继续分发
                val deltaX = abs(ev.x - downX)
                if (deltaX > 10){
                    return true
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}