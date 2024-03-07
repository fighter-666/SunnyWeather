package com.example.sunnyweather.ui.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sunnyweather.MainActivity
import com.example.sunnyweather.R
import com.example.sunnyweather.activity.HotListActivity
import com.example.sunnyweather.databinding.ActivityWeatherBinding
import com.example.sunnyweather.logic.model.Weather
import com.example.sunnyweather.logic.model.getSky
import com.gyf.immersionbar.ImmersionBar
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import java.text.SimpleDateFormat
import java.util.Locale


class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding

    val viewModel by lazy {
        ViewModelProvider(this)[WeatherViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式
        ImmersionBar.with(this).transparentStatusBar()  //透明状态栏，不写默认透明色
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()

        //从Intent中获取数据,并赋值到viewModel中
        if (viewModel.locationLng.isEmpty()){
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        if (viewModel.locationLat.isEmpty()){
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()){
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }
        //对weatherLiveData 对象进行观察，当获取到服务器返回的天气数据时，就调用
        //showWeatherInfo()方法来更新UI
        viewModel.weatherLiveData.observe(this, Observer { result->
            val weather = result.getOrNull()
            if (weather != null){
                showWeatherInfo(weather)
            }else{
                Toast.makeText(this,"无法成功获取天气信息",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        //最后，调用 refreshWeather()方法来刷新天气信息
        refreshWeather()
        
        binding.refreshLayout.setOnRefreshListener{ refreshlayout ->
            refreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
            refreshWeather()
        }

        //调用DrawerLayout的openDrawer()方法来打开滑动菜单
        binding.now.navBtn.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.now.navHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //当滑动菜单被隐藏的时候，同时也要隐藏输入法
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
                //调用getSystemService方法并传入Context.INPUT_METHOD_SERVICE作为参数，
                // 获取到了一个InputMethodManager对象，并将其赋值给manager变量。
                //
                //然后，通过调用manager的hideSoftInputFromWindow方法来隐藏输入法键盘。该方法接收两个参数，
                // 第一个参数是一个View对象的windowToken，它表示当前窗口的令牌，用于指定隐藏键盘的目标窗口。
                // 在这里，drawerView是一个View对象，通过调用其getWindowToken方法获取到了窗口令牌。
                //
                //第二个参数是一个整型值，表示隐藏键盘的标志。在这里，传入的是InputMethodManager.HIDE_NOT_ALWAYS常量，
                // 表示如果输入法窗口当前是显示的，则隐藏它；如果输入法当前是隐藏的，则不进行任何操作。
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE)
                        as InputMethodManager
                manager.hideSoftInputFromWindow(drawerView.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)
            }

            override fun onDrawerStateChanged(newState: Int) {

            }

        })

    }

    fun refreshWeather(){
        viewModel.refreshWeather(viewModel.locationLng,viewModel.locationLat)
    }

    private fun showWeatherInfo(weather: Weather) {
        val realtime = weather.realtime
        val daily = weather.daily
        binding.now.run {
            placeName.text = viewModel.placeName
            //填充now.xml 布局中的数据
            val currentTempText = "${realtime.temperature.toInt()}℃"
            currentTemp.text = currentTempText
            currentSky.text = getSky(realtime.skycon).info
            val currentPM25Text = "空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
            currentAQI.text = currentPM25Text
            nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
        }

        //填充 forecast.xml 布局中的数据
        binding.forecast.forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days){
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(R.layout.forecast_item,binding.forecast.forecastLayout,false)
            val dateInfo = view.findViewById(R.id.dateInfo) as TextView
            val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
            val  skyInfo = view.findViewById(R.id.skyInfo) as TextView
            val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            //Log.d("skycon", skycon.data.toString())
            dateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
            temperatureInfo.text = tempText
            binding.forecast.forecastLayout.addView(view)
        }
        //填充 life_index.xml 布局中的数据
        val lifeIndex = daily.lifeIndex
        binding.lifeIndex.run {
            coldRiskText.text = lifeIndex.coldRisk[0].desc
            dressingText.text=lifeIndex.dressing[0].desc
            ultravioletText.text = lifeIndex.ultraviolet[0].desc
            carWashingText.text = lifeIndex.carWashing[0].desc
        }
        binding.weatherLayout.visibility = View.VISIBLE


    }
}