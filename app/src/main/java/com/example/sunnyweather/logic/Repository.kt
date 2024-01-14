package com.example.sunnyweather.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.dao.PlaceDao
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.model.Weather
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/*仓库层的主要工作就是判断调用方请求的数据应该是充本地数据源中获取还是网络数据源中获取，
* 并将获得的数据返回給调用方。因此，仓库层有点像是一个数据获取和缓存的中间层，在本地没有缓存
* 的情况下就去网络层获取，如果本地已经有缓存了，就直接获取缓存数据返回
* */
object Repository {
    fun searchPlaces(query: String) = fire(Dispatchers.IO){
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        if(placeResponse.status == "ok") {
            val places = placeResponse.places
            Log.d("Repository","success")
            Result.success(places)

        }else{
            Log.d("Repository","failure")
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))

        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            /*
            * 只需要分别在两个 async 函数中发起网络请求，然后再分别调用他们的await()方法，
            * 就可以保证只有在两个网络请求都成功响应后，才会进一步执行程序
            *
            * 另外由于async 函数必须在协程作用域内才能调用，所以这里使用 coroutineScope 函数创建一个协程作用域*/
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }


    /*这是一个 Kotlin 语言编写的私有函数，用于在协程中执行一个挂起函数，并将执行结果作为 LiveData 对象返回。
    该函数的主要作用是简化在协程中执行挂起函数并处理异常的过程。
函数的定义中使用了泛型 <T>，表示挂起函数的返回类型。在函数体内部，首先通过 liveData 函数创建一个 LiveData 对象，
该对象使用了指定的 CoroutineContext 上下文。
然后，在 try-catch 结构中，尝试执行传入的挂起 Lambda 表达式 block，该表达式的类型是 suspend() -> Result<T>，
表示一个挂起函数，返回一个 Result<T> 对象。

如果执行成功，则将结果 result 使用 emit 函数发送给 LiveData 对象。如果执行过程中发生异常，
则通过 catch 块捕获异常，并使用 Result.failure<T>(e) 创建一个失败的 Result 对象，其中 e 是捕获到的异常。
然后，将失败的 Result 对象同样使用 emit 函数发送给 LiveData 对象。

最后，函数返回 LiveData 对象，可以通过观察该 LiveData 对象来获取挂起函数的执行结果。

需要注意的是，在调用该函数时，需要在协程中进行调用，以便正确地使用挂起函数和 LiveData 的特性*/
    private fun <T> fire(context: CoroutineContext, block: suspend() -> Result<T> )=
    liveData<Result<T>>(context) {
        val result = try {
            block()
        }catch (e:Exception){
            Result.failure<T>(e)
        }
        emit(result)
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

}