package com.example.sunnyweather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.sunnyweather.adapter.DynamicFragmentAdapter
import com.example.sunnyweather.databinding.ActivityMyBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()


        val tabs = arrayOf("首页", "知识", "消息", "我的")
        val pics = arrayOf(
            R.drawable.ic_home,
            R.drawable.ic_knowledge,
            R.drawable.ic_message,
            R.drawable.ic_mine
        )

        // offscreenPageLimit 离屏页面限制决定了在 ViewPager 的适配器中，当前页面两侧应该保留的页面数量
        // tabs.size 被用来动态地根据标签数量设置离屏页面限制
        binding.viewPager.offscreenPageLimit = tabs.size
        binding.viewPager.isUserInputEnabled = false; //true:滑动，false：禁止滑动
        val adapter =
            DynamicFragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        val mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabView =
                LayoutInflater.from(this@MyActivity).inflate(R.layout.view_custom_tab, null)
            val tabIcon = tabView.findViewById<ImageView>(R.id.tabIcon)
            val tabTitle = tabView.findViewById<TextView>(R.id.tabTitle)
            tabTitle.text = tabs[position]
            tabIcon.setImageResource(pics[position])
            tab.customView = tabView
        }
        mediator.attach()

        initListener()
    }

    private fun initListener() {
        binding.run {
            //选中监听
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    updateTabFont(tab, true) // 设置选中标签字体加粗
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    updateTabFont(tab, false) // 取消选中标签字体加粗
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                }
            })
        }
    }

    // 辅助方法：更新标签字体样式

    private fun updateTabFont(tab: TabLayout.Tab, isSelected: Boolean) {
        val customView: View? = tab.customView
        if (customView != null) {
            val tabName: TextView =
                customView.findViewById(R.id.tabTitle) as TextView // 自定义布局中的 TextView
            val tabIcon = customView.findViewById<ImageView>(R.id.tabIcon)
            CoroutineScope(Dispatchers.Main).launch {

                if (isSelected) {
                    tabName.setTypeface(null, Typeface.BOLD) // 设置字体加粗



                } else {
                    tabName.setTypeface(null, Typeface.NORMAL) // 取消字体加粗
                }
            }
        }
    }
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}