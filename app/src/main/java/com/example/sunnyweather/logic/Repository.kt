package com.example.sunnyweather.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


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

    private fun <T> fire(context: CoroutineContext, block: suspend() -> Result<T> )=
    liveData<Result<T>>(context) {
        val result = try {
            block()
        }catch (e:Exception){
            Result.failure<T>(e)
        }
        emit(result)
    }

}