package com.example.sunnyweather.logic.network

import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.DailyResponse
import com.example.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    /*
    * 用于获取实时天气的信息
    * 使用@GET 注解来声明要访问的API接口，并且使用@Path 注解来向请求接口中动态传入经纬度的坐标
    * 返回值被声明成 Call<RealtimeResponse>，对应刚刚定义好的数据模型类*/
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String,@Path("lat") lat: String):
            Call<RealtimeResponse>

    /*
    * 用于获取未来天气的信息
    * */
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String,@Path("lat") lat: String):
            Call<DailyResponse>
}