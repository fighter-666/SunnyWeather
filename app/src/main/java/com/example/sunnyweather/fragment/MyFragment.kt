package com.example.sunnyweather.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.adapter.MedalAdapter
import com.example.sunnyweather.adapter.MySettingAdapter
import com.example.sunnyweather.base.binding.BaseBindingFragment
import com.example.sunnyweather.data.MyInformationPageData
import com.example.sunnyweather.data.MySetting
import com.example.sunnyweather.data.MySettingData
import com.example.sunnyweather.data.Piggy
import com.example.sunnyweather.databinding.FragmentMyBinding
import com.example.sunnyweather.widget.Log
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar


class MyFragment : BaseBindingFragment<FragmentMyBinding>() {
    private val settingList = ArrayList<Fragment>()
    override fun lazyInit() {
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            //.titleBar(binding.messageTitleBar)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initData()

    }

    private fun initView() {

    }

    private fun initListener() {
        binding.run {

        }

    }

    private fun initData() {
        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            SunnyWeatherApplication.context.assets.open("mySetting.json").bufferedReader()
                .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val data = gson.fromJson(json, MySettingData::class.java)

        Log.d("33333",data.MySettingList[0].title)

        binding.run {
            binding.rvSetting.layoutManager = LinearLayoutManager(requireContext())
            binding.rvSetting.adapter = MySettingAdapter().apply { setNewData(data.MySettingList) }
        }
    }
}


