package com.example.sunnyweather.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * viewPager2的适配器
 */
class FragmentStateAdapter2 : FragmentStateAdapter {

    var fragmentList = ArrayList<Fragment>()

    constructor(fragmentActivity: FragmentActivity, fragmentList: ArrayList<Fragment>) : super(fragmentActivity) {
        this.fragmentList = fragmentList
    }

    constructor(fragment: Fragment, fragmentList: ArrayList<Fragment>) : super(fragment) {
        this.fragmentList = fragmentList
    }

    private val fragmentHashCodes = arrayListOf<Long>()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = fragmentList[position]
        fragmentHashCodes.add(fragment.hashCode().toLong())
        return fragment
    }//返回需要创建的fragment

    override fun containsItem(itemId: Long): Boolean {
        return fragmentHashCodes.contains(itemId)
    }//判断是否包含创建的ID（解决fragment数据为动态时无法刷新的问题）

    override fun getItemId(position: Int): Long {
        return fragmentList[position].hashCode().toLong()
    }
}