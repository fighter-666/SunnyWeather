package com.example.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.sunnyweather.logic.Repository
import com.example.sunnyweather.logic.model.Location

//Class to create a WeatherViewModel
class WeatherViewModel:ViewModel() {
    //Declare a MutableLiveData object to store the location
    private val locationLiveData =MutableLiveData<Location>()

    //Declare variables to store the longitude and latitude
    var locationLng =""

    var locationLat = ""

    //Declare a variable to store the place name
    var placeName =""

    //Declare a LiveData object to store the weather
    val weatherLiveData = locationLiveData.switchMap { location ->
        //Call the repository to refresh the weather
        Repository.refreshWeather(location.lng,location.lat)
    }

    //Function to refresh the weather
    fun refreshWeather(lng:String,lat:String){
        //Set the value of the locationLiveData to the new location
        locationLiveData.value =Location(lng,lat)
    }
}