package com.ct.base.ext

import android.view.View
import android.widget.ImageView
import com.example.sunnyweather.util.UtilGlide
import com.example.sunnyweather.util.UtilText

/**
 * 设置文本有内容可见(失败显示空白)，没内容不可见 (GONE)
 */
fun ImageView.setContent(iconUrl: String) {
    if (!UtilText.isEmptyOrNull(iconUrl)){
        visibility = View.VISIBLE
        UtilGlide.showSimpleImage(iconUrl, this)
    }else{
        visibility = View.GONE
    }
}

/**
 * 设置文本有内容可见(失败显示灰色加载中)，没内容不可见 (GONE)
 */
fun ImageView.setContent2(iconUrl: String) {
    if (!UtilText.isEmptyOrNull(iconUrl)){
        visibility = View.VISIBLE
        UtilGlide.showImage(iconUrl, this)
    }else{
        visibility = View.GONE
    }
}

