package com.example.sunnyweather.fragment

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ct.base.ext.isEmptyOrNull
import com.example.sunnyweather.R
import com.example.sunnyweather.activity.MessageAllServiceActivity
import com.example.sunnyweather.adapter.MessageAllServiceAdapter
import com.example.sunnyweather.adapter.WaterfallAdapter
import com.example.sunnyweather.base.binding.BaseBindingFragment
import com.example.sunnyweather.data.GetFeedListData
import com.example.sunnyweather.data.QueryServiceMessageData
import com.example.sunnyweather.data.QueryServiceMessageData.MessageClassifyListBean.MessageCardListBean
import com.example.sunnyweather.databinding.FragmentFeedBinding
import com.example.sunnyweather.databinding.FragmentMessageServiceBinding
import com.google.gson.Gson


/**
 * 说明：消息服务
 */
class FeedFragment : BaseBindingFragment<FragmentFeedBinding>() {


    override fun lazyInit() {
        initViews()
        initListener()
        initData()
    }

    private fun initViews() {


    }

    private fun initListener() {
        binding.run {

        }
    }

    private fun initData() {
        arguments?.run {
            val json: String = requireActivity().assets.open("getFeedListData.json").bufferedReader()
                .use { it.readText() }
            //使用了Gson库来将JSON数据转换为GetFeedTabData对象
            val gson = Gson()
            val feedList = gson.fromJson(json, GetFeedListData::class.java)
            val myAdapter = WaterfallAdapter(feedList.feedList)

            binding.rvFeed.apply {
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                (layoutManager as StaggeredGridLayoutManager).gapStrategy =
                    StaggeredGridLayoutManager.GAP_HANDLING_NONE // 避免瀑布流跳动
                adapter = myAdapter
            }
        }

    }

}