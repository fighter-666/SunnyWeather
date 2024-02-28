package com.example.sunnyweather.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import com.ct.base.ext.dp
import com.example.sunnyweather.R


/**
 * 消息自定义红点
 */
class MessageRedDot(content: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatTextView(content, attrs) {

    // 背景图
    var mBackground = 0
    // 省略背景图
    var mBackgroundOmit = 0
    init {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet) {
        // 从布局文件中读出控件设置
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MessageRedDotWidget)
        mBackground = a.getResourceId(R.styleable.MessageRedDotWidget_dot_bg, R.drawable.bg_msg_red_dot)
        mBackgroundOmit = a.getResourceId(R.styleable.MessageRedDotWidget_dot_bg_omit, R.drawable.ic_msg_red_point_omit)
        a.recycle()

        gravity = Gravity.CENTER
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
        setTextColor(Color.WHITE)
    }

    /**
     * 根据数量设置样式
     * @param sum
     */
    fun setContent(sum: Int) {
        visibility = View.VISIBLE

        // 免打扰
        if (/*UtilRefresh.isDistractionFree() &&*/ sum >= 1){
            height = 9.5.dp
            width = 9.5.dp
            text = ""
            setBackgroundResource(mBackground)
            // 百位显示省略号。。。
        }else if (sum > 99) {
            height = 13.dp
            width = 13.dp
            text = ""
            setBackgroundResource(mBackgroundOmit)
            // 两位数字
        } else if (sum > 9) {
            height = 13.dp
            width = 16.dp
            text = sum.toString()
            setBackgroundResource(mBackground)
            // 一位数字
        } else if (sum > 1) {
            height = 13.dp
            width = 13.dp
            text = sum.toString()
            setBackgroundResource(mBackground)
            // 红点
        } else if (sum == 1){
            height = 9.5.dp
            width = 9.5.dp
            text = ""
            setBackgroundResource(mBackground)
        } else{
            visibility = View.GONE
        }
    }

}