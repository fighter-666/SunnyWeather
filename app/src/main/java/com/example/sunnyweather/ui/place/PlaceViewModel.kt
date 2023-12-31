package com.example.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.sunnyweather.logic.Repository
import com.example.sunnyweather.logic.model.Place

/*
通过使用 PlaceViewModel，可以在 UI 层观察 placeLiveData 对象并获取搜索结果，
同时调用 searchPlaces 方法来执行搜索操作。这样可以将搜索的逻辑与界面分离，并且能够实时更新搜索结果
*/
class PlaceViewModel: ViewModel() {
    //searchLiveData 是一个 MutableLiveData 对象，用于保存搜索关键词。
    private val searchLiveData = MutableLiveData<String>()

    //placeList 是一个 ArrayList，用于保存搜索到的地点信息。
    val placeList =ArrayList<Place>()

    //placeLiveData 是一个 LiveData 对象，通过调用 switchMap
    // 方法将 searchLiveData 的值映射为对 Repository.searchPlaces(query) 方法的调用结果。
    val placeLiveData = searchLiveData.switchMap { query ->
        Repository.searchPlaces(query)
    }

    //searchPlaces 方法用于执行地点搜索操作。它接收一个搜索关键词 query，
    // 将其赋值给 searchLiveData 的值，从而触发 placeLiveData 的更新。
    fun searchPlaces(query: String){
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()
}