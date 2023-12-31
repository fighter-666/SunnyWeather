package com.example.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//单例类
object ServiceCreator {

    //常量，用于指定Retrofit的根路径
    private const val BASE_URL = "https://api.caiyunapp.com/"

    //用Retrofit.BUilder()创建Retrofit对象
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    //定义一个不带参数的create()方法，您使用inline关键字来修饰方法，
    // 使用reified 关键字来修饰泛型，这是泛型实例化的两大前提条件。
    // 接下来就可以使用T::class.java这种语法了，这里调用刚才定义的带有Class参数的create()方法即可
    inline fun <reified T> create(): T = create(T::class.java)

}