package com.example.sunnyweather.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sunnyweather.fragment.MyInformationFragment
import com.example.sunnyweather.fragment.NewsFragment
import com.example.sunnyweather.fragment.KnowladgeFragment
import com.example.sunnyweather.fragment.SunnyFragment

class DynamicFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = listOf(
        SunnyFragment(),
        KnowladgeFragment(),
        NewsFragment(),
        MyInformationFragment(),
        //加载更多的 Fragment 实例
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}