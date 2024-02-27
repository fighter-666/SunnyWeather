package com.example.sunnyweather.widget

import android.app.Dialog
import android.content.Context
import com.example.sunnyweather.util.ActivityUtils

/**
 * 说明：
 *
 * @作者 luohao
 * @创建时间 2024/2/2 10:35
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
open class CtBaseDialog : Dialog {

    constructor(context: Context) : super(context)

    constructor(context: Context, theme: Int) : super(context, theme)

    override fun show() {
        if (ActivityUtils.isActivityAlive(context)) {
            super.show()
        }
    }
}