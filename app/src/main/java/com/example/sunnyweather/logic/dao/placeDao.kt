package com.example.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.Place
import com.google.gson.Gson

object PlaceDao {

    //保存先通过GSON将Place对象转换为JSON格式的字符串，然后通过SharedPreferences来保存这个字符串
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    //读取先将保存的JSON格式的字符串从SharedPreferences中读取出来，然后通过GSON将JSON格式的字符串转换为Place对象并返回
    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    //用于判断是否有数据已被存储
    fun isPlaceSaved() = sharedPreferences().contains("place")

    /*调用SunnyWeatherApplication对象的context来获取Context对象，然后
    调用SharedPreferences的getSharedPreferences方法来获取一个SharedPreferences对象。
    它接收两个参数，第一个参数是文件名，第二个参数是访问模式。在这个例子中，文件名为"sunny_weather"，
    访问模式为Context.MODE_PRIVATE，表示只有当前应用程序可以访问这个SharedPreferences对象*/
    private fun sharedPreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

}