package com.example.sunnyweather.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sunnyweather.adapter.MyPagerAdapter
import com.example.sunnyweather.data.GetFeedTabData
import com.example.sunnyweather.databinding.ActivityDataUsageBinding
import com.example.sunnyweather.fragment.DataUsageFragment
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar

class DataUsageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataUsageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataUsageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸式
        ImmersionBar.with(this).transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.tvTitle)    //解决状态栏和布局重叠问题，任选其一
            .init()

        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            application.assets.open("getFeedTab.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val tabList = gson.fromJson(json, GetFeedTabData::class.java)

        //viewpage
        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(DataUsageFragment())
        fragments.add(DataUsageFragment())
        fragments.add(DataUsageFragment())

        val adapter = MyPagerAdapter(supportFragmentManager, fragments)
        binding.viewPager.offscreenPageLimit = fragments.size
        binding.viewPager.adapter = adapter              // 绑定adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)    // 绑定viewPager

        for (i in tabList.tabList.indices) {
            binding.tabLayout.getTabAt(i)?.text = tabList.tabList[i].tabName   // 设置标题
        }
    }

    fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int) {

    }
}