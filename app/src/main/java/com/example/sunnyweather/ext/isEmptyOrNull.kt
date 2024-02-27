package com.example.sunnyweather.ext

import java.util.Locale

fun String?.isEmptyOrNull(): Boolean {
    if (!isNullOrBlank() && trim().toLowerCase(Locale.US) != "null")
        return false
    return true
}