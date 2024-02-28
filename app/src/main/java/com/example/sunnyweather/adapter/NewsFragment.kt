package com.example.sunnyweather.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.base.ext.dp
import com.ct.client.message.adapter.ServiceMessageAdapter
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.base.binding.BaseBindingFragment
import com.example.sunnyweather.data.GetFeedTabData
import com.example.sunnyweather.data.QueryMessageChannelData
import com.example.sunnyweather.databinding.FragmentNewsBinding
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar


/**
 * 说明：1110 消息中心
 */
class NewsFragment : BaseBindingFragment<FragmentNewsBinding>(){
    private lateinit var messageClassifyAdapter:MessageClassifyAdapter // 营销消息分类
    private lateinit var serviceMessageAdapter: ServiceMessageAdapter // 服务消息
    private lateinit var marketMessageAdapter:MarketMessageAdapter // 营销消息
    private lateinit var outMarketMessageAdapter:MarketMessageAdapter // 过时营销消息
    override fun lazyInit() {
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.messageTitleBar)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()

        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            SunnyWeatherApplication.context.assets.open("queryMessageChannel.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val data = gson.fromJson(json, QueryMessageChannelData::class.java)

        binding.run {
            // 消息分类列表
            messageClassifyAdapter = MessageClassifyAdapter()
            rvMessageClassify.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = messageClassifyAdapter
            }
            messageClassifyAdapter.setNewData(data.serviceMessageClassifyList)

            // 服务消息列表
            serviceMessageAdapter = ServiceMessageAdapter()
            rvServiceMessage.apply {
                layoutManager = object :LinearLayoutManager(context){
                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
                adapter = serviceMessageAdapter
            }
            serviceMessageAdapter.setNewData(data.serviceMessageList)
            llServiceList.visibility = View.VISIBLE

            // 营销消息
            marketMessageAdapter = MarketMessageAdapter()
            rvMarketingMessage.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = marketMessageAdapter
            }
            marketMessageAdapter.setNewData(data.marketingMessageList)
            rvMarketingMessage.addItemDecoration(
                MessageMarketingDecoration(
                    SunnyWeatherApplication.context,
                    LinearLayoutManager.VERTICAL,
                    0.5.dp.toDouble(),
                    68.dp,
                    18.dp,
                    SunnyWeatherApplication.context.resources.getColor(R.color.gray_eeeeee)
                )
            )

            // 营销消息（一周前的）
            outMarketMessageAdapter = MarketMessageAdapter()

            rvOutMarketMessage.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = outMarketMessageAdapter
            }
            rvOutMarketMessage.addItemDecoration(
                MessageMarketingDecoration(
                    SunnyWeatherApplication.context,
                    LinearLayoutManager.VERTICAL,
                    0.5.dp.toDouble(),
                    68.dp,
                    18.dp,
                    SunnyWeatherApplication.context.resources.getColor(R.color.gray_eeeeee)
                )
            )
        }
    }




}
