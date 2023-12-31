package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/*
* 全局的context
* 彩云天气的令牌值TOKEN
* */
class SunnyWeatherApplication : Application() {

    companion object {

        const val TOKEN = "kI4inGvz4iiQA2fV" // 填入你申请到的令牌值

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}