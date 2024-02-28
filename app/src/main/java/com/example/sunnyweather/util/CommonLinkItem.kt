package com.example.sunnyweather.util

import android.content.Context
import android.content.Intent

class CommonLinkItem {
    var linkType: String = ""
    var link: String = ""

    fun goTarget(context: Context) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", link)
        context.startActivity(intent)
    }
}