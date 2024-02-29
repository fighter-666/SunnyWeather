package com.example.sunnyweather.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.base.ext.dp
import com.ct.client.message.adapter.ServiceMessageAdapter
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.activity.MessageAllServiceActivity
import com.example.sunnyweather.adapter.MarketMessageAdapter
import com.example.sunnyweather.adapter.MessageClassifyAdapter
import com.example.sunnyweather.adapter.MessageMarketingDecoration
import com.example.sunnyweather.base.binding.BaseBindingFragment
import com.example.sunnyweather.data.AdItem
import com.example.sunnyweather.data.QueryMessageChannelData
import com.example.sunnyweather.databinding.FragmentNewsBinding
import com.example.sunnyweather.widget.MsgDataHelper
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import java.lang.Math.abs


/**
 * 说明：1110 消息中心
 */
class NewsFragment : BaseBindingFragment<FragmentNewsBinding>(){
    private lateinit var messageClassifyAdapter: MessageClassifyAdapter // 营销消息分类
    private lateinit var serviceMessageAdapter: ServiceMessageAdapter // 服务消息
    private lateinit var marketMessageAdapter: MarketMessageAdapter // 营销消息
    private lateinit var outMarketMessageAdapter: MarketMessageAdapter // 过时营销消息
    override fun lazyInit() {
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.messageTitleBar)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initData()

    }

    private fun initView() {
        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            SunnyWeatherApplication.context.assets.open("queryMessageChannel.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val data = gson.fromJson(json, QueryMessageChannelData::class.java)

        val adItems = ArrayList<AdItem>()
            val adItem1 = AdItem()
            adItem1.title = "中国电信APP发现新版本，快来升级体验吧"
            adItem1.linkType = "1"
            adItem1.link = "1000021"
            adItems.add(adItem1)
        // 公告
            val adItem2 = AdItem()
            adItem2.title = "您有一条新的公告消息待查看"
            adItem2.linkType = "2"
            adItems.add(adItem2)

        binding.run {
            binding.messageInfoView.setNotices(adItems)
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
            if (serviceMessageAdapter.data.size > 3) {
                tvViewAll.visibility = View.VISIBLE
            }else{
                tvViewAll.visibility = View.GONE
            }

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
    private fun initListener() {
        binding.run {
            // 下拉刷新
            smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    // 把加载的动作传给当初的fragment，网页已禁止上拉所以不用判断类型
                    //feedViewPager.onLoadMore()
                    refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    initData()
                    refreshLayout.finishRefresh(2000/*,false*/);//传入false表示加载失败
                }
            })

            appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                // 当垂直偏移量大于20dp对应的像素值时，关闭侧滑菜单
                if (kotlin.math.abs(verticalOffset) > 20.dp) {
                    MsgDataHelper.currentSkidView?.smoothScrollTo(0, 0)
                }

                if (verticalOffset.toFloat() != ivTop.translationY) {
                    ivTop.translationY = verticalOffset.toFloat()
                    if (verticalOffset == 0) {
                        messageTitleBar.setBackgroundColor(
                            ContextCompat.getColor(
                                SunnyWeatherApplication.context,
                                R.color.transparent
                            )
                        )
                        ivSticky.visibility = View.GONE
                    } else {
                        messageTitleBar.setBackgroundColor(Color.WHITE)
                        ivSticky.visibility = View.VISIBLE
                    }
                }
                val offsetRatio = abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange

                /*if (offsetRatio == 1f) {
                    // 当吸顶时
                    binding.tabFeed.setBackgroundColor(resources.getColor(R.color.white))
                } else {
                    // 当未吸顶时，或者根据需要设置渐变效果
                    binding.tabFeed.setBackgroundColor(resources.getColor(R.color.gray_f8f8f8))
                }*/

                // 右下角回到顶部
                ivSticky.setOnClickListener {
                    appbar.setExpanded(true, true)
                    //feedViewPager.onTop()
                }

                // 查看全部
                tvViewAll.setOnClickListener {
                    val intent = Intent(context, MessageAllServiceActivity::class.java)
                    startActivity(intent)

                }
            }
        }

    }
    private fun initData() {


    }




}
