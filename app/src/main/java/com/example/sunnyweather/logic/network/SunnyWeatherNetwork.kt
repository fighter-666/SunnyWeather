package com.example.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/*
* 定义一个统一的网络数据源访问接口，对所有网络请求的API 进行封装*/
object SunnyWeatherNetwork {
    //还用ServiceCreate 穿件另一个PlaceService 接口的动态代理对象
    private val placeService = ServiceCreator.create(PlaceService::class.java)

    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()

    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()

    //然后定义一个 searchPlaces()函数,并在这里调用刚刚在PlaceService接口中
    // 定义的searchPlaces()方法，以发起搜索城市数据请求
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    /*这是一个 Kotlin 语言编写的扩展函数，用于将 Retrofit 的 Call 对象转换为挂起函数。
    该函数的主要作用是通过 suspendCoroutine 函数将 Retrofit 的异步回调方式转换为挂起函数的方式，
    从而方便在协程中使用 Retrofit 进行网络请求。

函数的定义中使用了泛型 <T>，表示 Call 对象的响应类型。在函数体内部，首先使用 suspendCoroutine
函数创建一个挂起的协程。suspendCoroutine 函数需要传入一个 Lambda 表达式，该 Lambda 表达式中的
continuation 参数代表了当前的协程继续执行的上下文。

接着，通过调用 Call 对象的 enqueue 方法来执行异步网络请求。enqueue 方法需要传入一个 Callback 对象，
用于处理请求的响应结果。在 Callback 对象的 onResponse 方法中，首先获取到响应的 body 对象。
如果 body 不为 null，则通过 continuation.resume(body) 将响应结果恢复给协程。如果 body 为 null，
则通过 continuation.resumeWithException(RuntimeException("response body is null")) 抛出一个异常给协程。

在 Callback 对象的 onFailure 方法中，通过 continuation.resumeWithException(t) 将请求失败的异常抛给协程。

最后，函数返回挂起函数中的结果*/

    //await()仍然是一个挂起函数，然后我们给它声明了一个泛型T，并将await()函数定义成了Call<T>的扩展函数
    //这样所有返回值是Call类型的Retrofit网络请求接口就都可以直接调用await()函数了
    private suspend fun <T> Call<T>.await(): T {
        //await()函数中使用了suspendCoroutine函数来挂起当前协程，并且由于扩展函数的原因，我们现在拥有了
        //Call对象的上下文，那么这里就可以直接调用enqueue()方法让Retrofit发起网络请求了
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    //是否为空，手动抛出异常
                    if (body != null)
                        //通过 continuation.resume(body) 将响应结果恢复给协程
                        continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })

        }
    }
}