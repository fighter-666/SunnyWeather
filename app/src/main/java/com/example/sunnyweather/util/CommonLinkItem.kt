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

    companion object {
        fun goTarget(link: String?, linkType: String?, context: Context?) {
            val commonLinkItem = CommonLinkItem()
            commonLinkItem.link = link!!
            commonLinkItem.linkType = linkType!!
            commonLinkItem.goTarget(context!!)
        }
    }
}