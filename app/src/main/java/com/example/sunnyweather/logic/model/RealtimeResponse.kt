package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

//数据类型定义在RealtimeResponse的内部，防止数据模型类有同名冲突的情况
data class RealtimeResponse(val status: String,val result:Result) {
    data class Result(val realtime: Realtime)

    //@SerializedName("air_quality") 是一个注解，用于指定在序列化或反序列化过程中将属性映射到 JSON 或其他数据格式中的字段名称
    data class Realtime(val skycon: String,val temperature:Float,
        @SerializedName("air_quality") val airQuality: AirQuality)

    data class AirQuality(val aqi:AQI)

    data class AQI(val chn:Float)
}