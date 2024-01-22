package com.example.sunnyweather

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.sunnyweather.databinding.ActivityMainBinding
import com.example.sunnyweather.ui.adapter.DynamicFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init()


        val tabs = arrayOf("Components", "Helper", "Lab", "Waterfall")
        val pics = arrayOf(
            R.mipmap.icon_tabbar_component_selected,
            R.mipmap.icon_tabbar_util_selected,
            R.mipmap.icon_tabbar_lab_selected,
            R.mipmap.icon_tabbar_lab_selected
        )

        // offscreenPageLimit 离屏页面限制决定了在 ViewPager 的适配器中，当前页面两侧应该保留的页面数量
        // tabs.size 被用来动态地根据标签数量设置离屏页面限制
        binding.viewPager.offscreenPageLimit = tabs.size
        val adapter =
            DynamicFragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        val mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabView =
                LayoutInflater.from(this@MainActivity).inflate(R.layout.view_custom_tab, null)
            val tabIcon = tabView.findViewById<ImageView>(R.id.tabIcon)
            val tabTitle = tabView.findViewById<TextView>(R.id.tabTitle)
            tabTitle.text = tabs[position]
            tabIcon.setImageResource(pics[position])
            tab.customView = tabView
        }
        mediator.attach()
    }
}