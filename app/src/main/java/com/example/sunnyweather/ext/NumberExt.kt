package com.ct.base.ext

import android.content.res.Resources

/**
 * converts dp value into px
 */
val Number.dp
    get() = (this.toFloat() * Resources.getSystem().displayMetrics.density + 0.5f).toInt()