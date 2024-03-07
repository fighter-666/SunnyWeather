package com.example.sunnyweather.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.base.ext.dp
import com.example.sunnyweather.MainActivity
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication.Companion.context
import com.example.sunnyweather.adapter.MessageMarketingDecoration
import com.example.sunnyweather.adapter.MySettingAdapter
import com.example.sunnyweather.base.binding.BaseBindingActivity
import com.example.sunnyweather.data.MySettingData
import com.example.sunnyweather.databinding.FragmentMyBinding
import com.example.sunnyweather.widget.Log
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar


class MyActivity : BaseBindingActivity<FragmentMyBinding>() {
    private val settingList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
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
            /*tvAccountSecurity.setOnClickListener {
                val intent = Intent(context, MyInformationActivity::class.java)
                startActivity(intent)
            }*/

        }

    }

    private fun initData() {
        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            this.assets.open("mySeting.json").bufferedReader()
                .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val data = gson.fromJson(json, MySettingData::class.java)

        Log.d("33333",data.MySettingList[0].title)

        binding.run {
            binding.rvSetting.layoutManager = LinearLayoutManager(this@MyActivity)
            val mySettingAdapter = MySettingAdapter().apply { setNewData(data.MySettingList) }
            binding.rvSetting.adapter = mySettingAdapter

            binding.rvSetting.addItemDecoration(
                MessageMarketingDecoration(
                    this@MyActivity,
                    LinearLayoutManager.VERTICAL,
                    0.5.dp.toDouble(),
                    68.dp,
                    18.dp,
                    this@MyActivity.resources.getColor(R.color.gray_eeeeee)
                )
            )

            mySettingAdapter.setOnItemClickListener { _, view, position ->
                when (position) {
                    0 -> {
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    }

                    1 -> {
                        val intent = Intent(context, TestActivity::class.java)
                        startActivity(intent)
                    }

                    2 -> {
                        val intent = Intent(context, HotListActivity::class.java)
                        startActivity(intent)
                    }

                    3 -> {
                        val intent = Intent(context, DataUsageActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}


