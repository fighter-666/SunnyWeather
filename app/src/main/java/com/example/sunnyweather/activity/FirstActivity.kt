package com.example.sunnyweather.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.example.sunnyweather.R
import com.example.sunnyweather.adapter.DynamicFragmentAdapter
import com.example.sunnyweather.databinding.ActivityFirstBinding
import com.example.sunnyweather.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init()


        val tabs = arrayOf("Weather", "Helper", "News", "My")
        val pics = arrayOf(
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
        )

        // offscreenPageLimit 离屏页面限制决定了在 ViewPager 的适配器中，当前页面两侧应该保留的页面数量
        // tabs.size 被用来动态地根据标签数量设置离屏页面限制
        binding.viewPager.offscreenPageLimit = tabs.size
        val adapter =
            DynamicFragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        val mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabView =
                LayoutInflater.from(this@FirstActivity).inflate(R.layout.view_custom_tab, null)
            val tabIcon = tabView.findViewById<ImageView>(R.id.tabIcon)
            val tabTitle = tabView.findViewById<TextView>(R.id.tabTitle)
            tabTitle.text = tabs[position]
            tabIcon.setImageResource(pics[position])
            tab.customView = tabView
        }
        mediator.attach()
    }
}