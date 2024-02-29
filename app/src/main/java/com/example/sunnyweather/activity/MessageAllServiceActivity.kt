package com.example.sunnyweather.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.adapter.FragmentStateAdapter2
import com.example.sunnyweather.base.CommWebViewFragment
import com.example.sunnyweather.base.Constants
import com.example.sunnyweather.base.ExtraParams
import com.example.sunnyweather.base.binding.BaseBindingActivity
import com.example.sunnyweather.data.QueryServiceMessageData
import com.example.sunnyweather.databinding.ActivityMessageAllServiceBinding
import com.example.sunnyweather.fragment.MessageAllServiceFragment
import com.example.sunnyweather.helper.IRedDotListener
import com.example.sunnyweather.helper.RedDotHelper
import com.example.sunnyweather.util.RedDotNotification
import com.example.sunnyweather.util.UtilOther
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import com.scwang.smart.refresh.layout.listener.OnRefreshListener


/*
 *说明：全部服务消息
 *@作者 吴道满
 *@创建时间 2023/12/20
 */
class MessageAllServiceActivity : BaseBindingActivity<ActivityMessageAllServiceBinding>() {

    private var mReceiver: MyReceiver? = null
    private val tabTitles = ArrayList<String>()
    //private lateinit var loadService: LoadService<Any>
    private val fragmentsList = ArrayList<Fragment>()
    private var currentItem = 0
    private var mMessageClassifyList = ArrayList<QueryServiceMessageData.MessageClassifyListBean>()
    private var isRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initData()
        initListener()

      /*  // 注册广播
        mReceiver = MyReceiver()
        val filter = IntentFilter()
        filter.addAction(Constants.ACTION_LOGIN_SUCC)
        registerReceiver(mReceiver, filter)

        HgXxSy.pageMessageAllService()*/
    }

    private fun initViews() {
        ImmersionBar.with(this).titleBar(binding.titleBar)    //解决状态栏和布局重叠问题，任选其一
            .transparentStatusBar()  //透明状态栏，不写默认透明色
         //.statusBarDarkFont(true)
         .init()

        binding.run {

           /* val loadSir = LoadSir.Builder().addCallback(MsgServiceEmptyCallback())
                .addCallback(MsgFailCallback()).build()
            loadService = loadSir.register(binding.smartRefreshLayout)
            loadService.setCallBack(MsgFailCallback::class.java) { context, view ->
                val tvReload: TextView = view.findViewById(R.id.tv_reload)
                tvReload.setOnClickListener {
                    queryServiceMessage(false)
                }
            }*/

            //初始化viewpage2的适配器
            vp2Message.adapter =
                FragmentStateAdapter2(this@MessageAllServiceActivity, fragmentsList)
            val tab = TabLayoutMediator(
                tabMessage, vp2Message
            ) { tab, position ->
                tab.text = tabTitles[position]
            }
            tab.attach()
        }
    }

    private fun initData() {
        //queryServiceMessage(false)
        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            SunnyWeatherApplication.context.assets.open("queryServiceeMessage.json").bufferedReader().use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val data = gson.fromJson(json, QueryServiceMessageData::class.java)

        data.run {
            binding.run {

                if (!data.messageClassifyList.isNullOrEmpty()) {
                    //loadService.showSuccess()
                    tabTitles.clear()
                    for (item in data.messageClassifyList) {
                        tabTitles.add(item.tabTitle)
                    }
                    tabMessage.visibility = View.VISIBLE
                    // 更新数据列表
                    mMessageClassifyList.clear()
                    mMessageClassifyList.addAll(messageClassifyList)
                    fragmentsList.clear()


                    for (i in data.messageClassifyList.indices) {
                        val tabItem = messageClassifyList.get(i)
                        if (tabItem.link.isNullOrEmpty()) {
                            //原生
                            val bundle = Bundle()
                            val data =
                                messageClassifyList[i]
                            bundle.putSerializable("queryServiceMessageData", data)
                            bundle.putSerializable("bottomTip", bottomTip)
                            val messageAllServiceFragment = MessageAllServiceFragment()
                            messageAllServiceFragment.arguments = bundle
                            fragmentsList.add(messageAllServiceFragment)
                        } else if (tabItem.linkType == "1" && tabItem.link == "F000011") {
                            /*val notLoginFragment = NotLoginFragment()
                            //加载未登录页面
                            UtilLog.saveMessageCenterLogcat("add NotLoginFragment   " + tabItem.tabTitle)
                            fragmentsList.add(notLoginFragment)*/
                        } else {
                            // 网页
                            val cWebKitFragment = CommWebViewFragment()
                            val extraParams = ExtraParams()
                            extraParams.data = tabItem.link
                            cWebKitFragment.mExtraParams = extraParams
                            fragmentsList.add(cWebKitFragment)
                        }
                    }
                    vp2Message.adapter?.notifyDataSetChanged()
                    vp2Message.offscreenPageLimit = fragmentsList.size
                    setTabLable(messageClassifyList)

                    //设置点击跳转的默认界面
                   /* if (!isRefreshing) {
                        val tabName = intent.getStringExtra("tabName")
                        val tabIndex =
                            messageClassifyList.indexOfFirst { it.tabTitle == tabName }

                        if (tabIndex == -1 || tabIndex == 0) {
                            val tabAt = binding.tabMessage.getTabAt(0)
                            tabAt?.let { updateTabFont(it, true) }
                        } else {
                            vp2Message.setCurrentItem(
                                tabIndex, false
                            )
                        }
                        // 在数据刷新后，设置 ViewPager2 的当前项为原来的项
                    }else{
                        if (currentItem < mMessageClassifyList.size) {
                            val tabAt = binding.tabMessage.getTabAt(currentItem)
                            tabAt?.let { updateTabFont(it, true) }
                        }
                        smartRefreshLayout.finishRefresh()
                    }*/
                } else {
                    tabMessage.visibility = View.GONE
                    //loadService.showCallback(MsgServiceEmptyCallback::class.java)
                }
            }
        }

    }

    private fun initListener() {
        binding.run {
            titleBar.setBackClickListener {
                //HgXxSy.hitMessageAllServiceBack()
            }

            //选中监听
            tabMessage.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    updateTabFont(tab, true) // 设置选中标签字体加粗
                    currentItem = vp2Message.currentItem
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    updateTabFont(tab, false) // 取消选中标签字体加粗
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                }
            })

            // 下拉刷新
            smartRefreshLayout.setOnRefreshListener {
                isRefresh = true
                // 执行刷新逻辑...
                // 在刷新之前获取当前项的索引
                currentItem = vp2Message.currentItem

                // 执行数据刷新
                //queryServiceMessage()
                // 刷新完成后
                isRefresh = false

            }
            smartRefreshLayout.setOnRefreshListener(OnRefreshListener { refreshlayout ->
                refreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
            })
            //横向滑动到最后一个tab，再滑要回到第一个
            vp2Message.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                private var isAttemptingToSwipePastLastPage = false
                private var lastKnownPosition = -1

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int,
                ) {
                    val isLastPage = position == vp2Message.adapter?.itemCount?.minus(1)
                    isAttemptingToSwipePastLastPage =
                        isLastPage && positionOffset == 0f && lastKnownPosition == position
                    lastKnownPosition = position
                }

                override fun onPageSelected(position: Int) {
                    // 当页面被选中时，重置尝试滑动的标志
                    isAttemptingToSwipePastLastPage = false
                }

                override fun onPageScrollStateChanged(state: Int) {
                    if (fragmentsList[fragmentsList.size - 1] !is CommWebViewFragment && !isRefresh && state == ViewPager2.SCROLL_STATE_IDLE && isAttemptingToSwipePastLastPage) {
                        // 当滑动停止且用户尝试在最后一页继续滑动时，跳转到第一个页面
                        vp2Message.setCurrentItem(0, false)
                        isAttemptingToSwipePastLastPage = false
                    }
                }
            })

           /* supportFragmentManager.setFragmentResultListener(CommWebViewFragment.ON_OVER_SCROLLED,
                mContext
            ) { requestKey, result ->
                val isClampedY = result.getBoolean("isClampedY")
                smartRefreshLayout.setEnableRefresh(isClampedY)
                Log.i("hssOnFragmentResult", "isClampedY:$isClampedY")
            }*/
        }


        //wap页js红点
        RedDotHelper.getInstance().addRedDotListener(
            Constants.RedDotTabName.MSG_UPDATE_RED_DOT_NUM,
            object : IRedDotListener<RedDotNotification> {
                override fun readRedDotListener(entity: RedDotNotification) {
                    val tabIndex =
                        mMessageClassifyList.indexOfFirst { it.tabTitle == entity.titleName }
                    if (tabIndex != -1) {
                        val tabAt: TabLayout.Tab? = binding.tabMessage.getTabAt(tabIndex)
                        val tv = tabAt!!.customView!!.findViewById<TextView>(R.id.tvTabTitle)
                        val redFlag = tabAt.customView!!.findViewById<ImageView>(R.id.ivRedDot)

                        if (UtilOther.parseInt(entity.num) > 99) {
                            tv.text  = "${entity.titleName}(99)"
                            redFlag.visibility = View.VISIBLE
                        } else if (UtilOther.parseInt(entity.num) > 0) {
                            tv.text = "${entity.titleName}(${entity.num})"
                            redFlag.visibility = View.VISIBLE
                        } else {
                            tv.text = "${entity.titleName}"
                            redFlag.visibility = View.GONE
                        }

                        RedDotHelper.getInstance().onRedDotListener(
                            Constants.RedDotTabName.MSG_UPDATE_CLASSIFY, entity
                        )
                    }
                }
            })
    }

    /**
     * 11.1.0 全部服务类消息信息接口
     */
    /*private fun queryServiceMessage(isRefreshing: Boolean = true) {
        val task = QueryServiceMessageTask(mContext)
        task.setProgressVisiable(!isRefreshing)
        task.setOnTaskFinished(object : OnTaskFinished {
            override fun onSucc(obj: Any) {
                val response = obj as QueryServiceMessageResponse
                binding.run {
                    response.queryServiceMessageData.run {
                        //动态配置空页面副标题和底部提示语
                        loadService.setCallBack(MsgServiceEmptyCallback::class.java) { context, view ->
                            val tvSubTitle: TextView = view.findViewById(R.id.tvSubTitle)
                            tvSubTitle.text = "服务提醒消息将在此汇总展示"
                            val tvBottomTip: MessageBottomTip = view.findViewById(R.id.tvBottomTip)
                            if (bottomTip.isEmptyOrNull()) {
                                tvBottomTip.visibility = View.GONE
                            } else {
                                tvBottomTip.setData(bottomTip)
                                tvBottomTip.visibility = View.VISIBLE
                            }

                        }

                        if (!messageClassifyList.isNullOrEmpty()) {
                            loadService.showSuccess()
                            tabTitles.clear()
                            for (item in messageClassifyList) {
                                tabTitles.add(item.tabTitle)
                            }
                            tabMessage.visibility = View.VISIBLE
                            // 更新数据列表
                            mMessageClassifyList.clear()
                            mMessageClassifyList.addAll(messageClassifyList)
                            fragmentsList.clear()


                            for (i in messageClassifyList.indices) {
                                val tabItem = messageClassifyList.get(i)
                                if (tabItem.link.isNullOrEmpty()) {
                                    //原生
                                    val bundle = Bundle()
                                    val data =
                                        response.queryServiceMessageData.messageClassifyList[i]
                                    bundle.putSerializable("queryServiceMessageData", data)
                                    bundle.putSerializable("bottomTip", bottomTip)
                                    val messageAllServiceFragment = MessageAllServiceFragment()
                                    messageAllServiceFragment.arguments = bundle
                                    fragmentsList.add(messageAllServiceFragment)
                                } else if (tabItem.linkType == "1" && tabItem.link == "F000011") {
                                    val notLoginFragment = NotLoginFragment()
                                    //加载未登录页面
                                    UtilLog.saveMessageCenterLogcat("add NotLoginFragment   " + tabItem.tabTitle)
                                    fragmentsList.add(notLoginFragment)
                                } else {
                                    // 网页
                                    val cWebKitFragment = CommWebViewFragment()
                                    val extraParams = ExtraParams()
                                    extraParams.data = tabItem.link
                                    cWebKitFragment.mExtraParams = extraParams
                                    fragmentsList.add(cWebKitFragment)
                                }
                            }
                            vp2Message.adapter?.notifyDataSetChanged()
                            vp2Message.offscreenPageLimit = fragmentsList.size
                            setTabLable(messageClassifyList)

                            //设置点击跳转的默认界面
                            if (!isRefreshing) {
                                val tabName = intent.getStringExtra("tabName")
                                val tabIndex =
                                    messageClassifyList.indexOfFirst { it.tabTitle == tabName }

                                if (tabIndex == -1 || tabIndex == 0) {
                                    val tabAt = binding.tabMessage.getTabAt(0)
                                    tabAt?.let { updateTabFont(it, true) }
                                } else {
                                    vp2Message.setCurrentItem(
                                        tabIndex, false
                                    )
                                }
                                // 在数据刷新后，设置 ViewPager2 的当前项为原来的项
                            }else{
                                if (currentItem < mMessageClassifyList.size) {
                                    val tabAt = binding.tabMessage.getTabAt(currentItem)
                                    tabAt?.let { updateTabFont(it, true) }
                                }
                                smartRefreshLayout.finishRefresh()
                            }
                        } else {
                            tabMessage.visibility = View.GONE
                            loadService.showCallback(MsgServiceEmptyCallback::class.java)
                        }
                    }
                }
            }

            override fun onFail(obj: Any) {
                loadService.showCallback(MsgFailCallback::class.java)
                binding.smartRefreshLayout.finishRefresh()
            }
        })
        task.execute()
    }*/

    // 跳转到下一个非 Web 页面的 tab
    fun switchToNextNonWebTab() {
        val currentItem = binding.vp2Message.currentItem
        var nextItem = currentItem

        // 循环遍历 ViewPager2 中的所有项，直到找到一个非 Web 页面的 tab
        do {
            nextItem = (nextItem + 1) % (binding.vp2Message.adapter?.itemCount ?: 1)
            // 如果下一个 tab 是 CommWebViewFragment，则跳过
        } while (fragmentsList[nextItem] is CommWebViewFragment && nextItem != currentItem)

        // 如果找到了非 Web 页面的 tab，则切换到该 tab
        if (nextItem != currentItem) {
            binding.vp2Message.setCurrentItem(nextItem, true)
        }
    }

    /**
     * 广播接受处理
     */
    inner class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                // 手机登录成功
                Constants.ACTION_LOGIN_SUCC -> {
                    // 执行数据刷新
                    //queryServiceMessage()
                }
            }
        }
    }

    private fun setTabLable(firTabList: List<QueryServiceMessageData.MessageClassifyListBean>) {
        for (i in 0 until binding.tabMessage.tabCount) {
            //获取每一个tab对象
            val tabAt = binding.tabMessage.getTabAt(i)
            //将每一个条目设置我们自定义的视图
            tabAt?.run {
                setCustomView(R.layout.view_message_tab)
                //通过tab对象找到自定义视图的ID
                val bean = firTabList[i]

                val tvTabTitle = customView!!.findViewById<TextView>(R.id.tvTabTitle)
                val ivRedDot = customView!!.findViewById<ImageView>(R.id.ivRedDot)

                //红点显示
                val sum =
                    UtilOther.parseInt(bean.redDotMsgNum) /*+ MessageInfoRepository.getOneLevelUnReadMsg(
                        bean.oneLevelMsgId
                    )*/

                if (sum > 99) {
                    tvTabTitle.text = "${bean.tabTitle}(99)"
                    ivRedDot.visibility = View.VISIBLE
                } else if (sum > 0) {
                        tvTabTitle.text = "${bean.tabTitle}($sum)"
                        ivRedDot.visibility = View.VISIBLE
                    } else {
                        tvTabTitle.text = bean.tabTitle
                        ivRedDot.visibility = View.GONE
                    }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (mReceiver != null) {
            unregisterReceiver(mReceiver)
        }
    }

    // 辅助方法：更新标签字体样式
    private fun updateTabFont(tab: TabLayout.Tab, isSelected: Boolean) {
        val customView: View? = tab.customView
        if (customView != null) {
            val tvTabTitle: TextView =
                customView.findViewById(R.id.tvTabTitle) as TextView // 自定义布局中的 TextView
            val ivRedDot = customView.findViewById<ImageView>(R.id.ivRedDot)

            val item = mMessageClassifyList[tab.position]
            if (isSelected) {
                tvTabTitle.setTypeface(null, Typeface.BOLD) // 设置字体加粗
                //HgXxSy.hitMessageAllServiceTab(item, tab.position)
            } else {
                tvTabTitle.setTypeface(null, Typeface.NORMAL) // 取消字体加粗
            }
            // 消息已读操作
            if (isSelected && ivRedDot.visibility == View.VISIBLE && fragmentsList[tab.position] !is CommWebViewFragment) {
                ivRedDot.visibility = View.GONE
                tvTabTitle.text = item.tabTitle

               /* val mMessageCenterHelper = MessageCenterHelper()
                // 一级消息设置已读
                mMessageCenterHelper.markReadMsg(
                    mContext,
                    MarkReadMsgTask.MARK_READ_FLAG.ONELEVELMSG,
                    oneLevelMsgId = item.oneLevelMsgId
                ).onMessageRead = {
                    // 回消息再首页刷新红点数
                    Variable.isNeedRefreshMsgDot = true
                }*/
            }

            // 网页禁止左右滑动
            if (isSelected) {
                binding.vp2Message.isUserInputEnabled =
                    fragmentsList[tab.position] !is CommWebViewFragment
            }
        }
    }

    companion object {
        fun goThisActivity(context: Context, tabName: String) {
            val intent = Intent(context, MessageAllServiceActivity::class.java)
            intent.putExtra("tabName", tabName)
            context.startActivity(intent)
        }
    }
}