package com.example.sunnyweather.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.base.ext.dp
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication.Companion.context
import com.example.sunnyweather.adapter.DataAdapter
import com.example.sunnyweather.adapter.MessageMarketingDecoration
import com.example.sunnyweather.base.binding.BaseBindingActivity
import com.example.sunnyweather.data.RecommendData
import com.example.sunnyweather.databinding.ActivityIntentBinding
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar

class JavaActivity : BaseBindingActivity<ActivityIntentBinding>() {
    private val settingList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //沉浸式处理
        ImmersionBar.with(this).transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.titleBar)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()
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
            context.assets.open("getJavaData.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val data = gson.fromJson(json, RecommendData::class.java)

        binding.run {
            binding.rvData.layoutManager = LinearLayoutManager(context)
            val recommendAdapter = DataAdapter().apply { setNewData(data.MyRecommendList) }
            binding.rvData.adapter = recommendAdapter

            binding.rvData.addItemDecoration(
                MessageMarketingDecoration(
                    context,
                    LinearLayoutManager.VERTICAL,
                    0.5.dp.toDouble(),
                    68.dp,
                    18.dp,
                    context.resources.getColor(R.color.gray_eeeeee)
                )
            )
        }


    }
}