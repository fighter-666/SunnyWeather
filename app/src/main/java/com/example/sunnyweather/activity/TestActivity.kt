package com.example.sunnyweather.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.sunnyweather.R
import com.example.sunnyweather.adapter.WaterfallAdapter
import com.example.sunnyweather.data.GetFeedListData
import com.example.sunnyweather.databinding.ActivityTestBinding
import com.google.gson.Gson

class TestActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val json: String = application.assets.open("getFeedListData.json").bufferedReader()
            .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val feedList = gson.fromJson(json, GetFeedListData::class.java)
        val myAdapter = WaterfallAdapter(feedList.feedList)

        //掌厅
        /*myAdapter = FeedAdapter(false)
        myAdapter.setNewData(feedList.feedList)*/
        binding.rvComponentsWaterfall.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            (layoutManager as StaggeredGridLayoutManager).gapStrategy =
                StaggeredGridLayoutManager.GAP_HANDLING_NONE // 避免瀑布流跳动
            adapter = myAdapter
        }
    }
}