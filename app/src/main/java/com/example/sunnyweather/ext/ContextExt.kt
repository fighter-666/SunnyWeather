package com.ct.base.ext

import android.content.Context
import androidx.core.content.ContextCompat


fun Context?.color(color:Int): Int {
    this?.run {
        return ContextCompat.getColor(this, color)
    }?:run {
        return 0
    }
}

