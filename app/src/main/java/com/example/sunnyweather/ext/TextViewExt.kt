package com.ct.base.ext

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.util.UtilText

/**
 * 设置文本有内容可见，没内容不可见 (GONE)
 */
fun TextView.setContent(content: String) {
    if (!UtilText.isEmptyOrNull(content)){
        visibility = View.VISIBLE
        text = content
    }else{
        visibility = View.GONE
    }
}

fun TextView.setContent(content: String, elseContent: String) {
    text = if (UtilText.isEmptyOrNull(content)){
        elseContent
    }else{
        content
    }
}

fun TextView.setColor(color: Int) {
    setTextColor(SunnyWeatherApplication.context.resources.getColor(color))
}

fun TextView.setColorString(color: String) {
    try {
        setTextColor(Color.parseColor(color))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 设置文本有跳转才显示右侧跳转箭头
 */
fun TextView.setRightArrow(linkType: String, drawable: Int) {
    if (!UtilText.isEmptyOrNull(linkType)){
        setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            drawable,
            0
        )
    }else{
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
    }
}

/**
 * 最多显示字数
 */
fun String.maxWords(maxWords: Int): String {
    return if (length > maxWords) {
        substring(0, maxWords)
    } else {
        this
    }
}


