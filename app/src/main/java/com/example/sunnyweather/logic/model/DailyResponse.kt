package com.example.sunnyweather.logic.model

import android.provider.ContactsContract.Data
import com.google.gson.annotations.SerializedName
import java.util.Date


class DailyResponse(val status: String, val result: Result) {

    class Result(val daily: Daily)

    //@SerializedName("life_index") 是一个注解，用于指定在序列化或反序列化过程中将属性映射到 JSON 或其他数据格式中的字段名称
    class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>, @SerializedName("life_index") val lifeIndex: LifeIndex)

    class Temperature(val max: Float, val min: Float)

    class Skycon(val value: String, val date: Date)

    class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing: List<LifeDescription>, val ultraviolet: List<LifeDescription>, val dressing: List<LifeDescription>)

    class LifeDescription(val desc: String)

}