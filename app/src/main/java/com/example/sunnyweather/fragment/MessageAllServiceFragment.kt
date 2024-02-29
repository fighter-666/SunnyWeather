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
import com.example.sunnyweather.base.binding.BaseBindingFragment
import com.example.sunnyweather.data.QueryServiceMessageData
import com.example.sunnyweather.data.QueryServiceMessageData.MessageClassifyListBean.MessageCardListBean
import com.example.sunnyweather.databinding.FragmentMessageServiceBinding
import com.google.gson.Gson


/**
 * 说明：消息服务
 */
class MessageAllServiceFragment : BaseBindingFragment<FragmentMessageServiceBinding>() {

    //private lateinit var loadService: LoadService<Any>
    private var mMessageCardList = ArrayList<MessageCardListBean>()
    private lateinit var cardAdapter: MessageAllServiceAdapter

    override fun lazyInit() {
        initViews()
        initListener()
        initData()
    }

    private fun initViews() {
        /*val loadSir = LoadSir.Builder()
            .addCallback(MsgServiceEmptyCallback())
            .build()
        loadService = loadSir.register(binding.nestedScrollView)*/
        cardAdapter = MessageAllServiceAdapter(R.layout.adapter_message_all_service, mMessageCardList)


    }

    private fun initListener() {
        binding.run {
            cardAdapter.onMessageDelete = {
                if (cardAdapter.data.isEmpty() ) {
                    //loadService.showCallback(MsgServiceEmptyCallback::class.java)
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //置顶
                nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                    // 判断是否超过一屏高度
                    val isScrolled = scrollY > 0

                    // 根据滚动位置控制回到顶部按钮的可见性
                    if (isScrolled) {
                        ivSticky.visibility = View.VISIBLE
                        ivSticky.setOnClickListener {
                            nestedScrollView.smoothScrollTo(0, 0)
                            rvMessageCard.smoothScrollToPosition(0)
                        }
                    } else {
                        ivSticky.visibility = View.GONE
                    }
                }

                nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                    if (scrollY > oldScrollY) {
                        val bottom = v.getChildAt(0).height - v.measuredHeight
                        if (scrollY >= bottom) {
                            // 滚动到底部，通知 Activity 检查并切换到下一个非 Web 页面的 tab
                            (activity as? MessageAllServiceActivity)?.switchToNextNonWebTab()
                        }
                    }
                })
            }
        }
    }

    private fun initData() {
        arguments?.run {
            val data = getSerializable("queryServiceMessageData") as QueryServiceMessageData.MessageClassifyListBean
            val bottomTip = getSerializable("bottomTip") as String
            //动态配置空页面副标题和底部提示语
        /*    loadService.setCallBack(MsgServiceEmptyCallback::class.java) { context, view ->
                val tvSubTitle: TextView = view.findViewById(R.id.tvSubTitle)
                val tvBottomTip: MessageBottomTip = view.findViewById(R.id.tvBottomTip)
                tvSubTitle.text = "${data.tabTitle}消息将在此汇总展示"
                if (bottomTip.isEmptyOrNull()){
                    tvBottomTip.visibility = View.GONE
                }else{
                    tvBottomTip.setData(bottomTip)
                    tvBottomTip.visibility = View.VISIBLE
                }
            }*/

            //不为空页面的底部提示语
            binding.run {
                if (bottomTip.isEmptyOrNull()){
                    tvBottomTip.visibility = View.GONE
                }else{
                    tvBottomTip.setData(bottomTip)
                    tvBottomTip.visibility = View.VISIBLE
                }
            }

            //一级ID 7加入家庭圈和pran
         /*   if (data.oneLevelMsgId == "7"){
                val mScreenCaptureBean = DynamicMessagesView.mScreenCaptureBean
                val mPRANBean = DynamicMessagesView.mPRANBean

                val mPRANBeanList = MessageCardListBean()
                mPRANBean?.let {
                    mPRANBeanList.run {
                        isPran = it.isPran
                        msgId = it.msgId
                        isScreenCapture = it.isScreenCapture
                        cardTypeInfo = CompoundAdItem().apply {
                            iconId = R.drawable.ic_message_wing_connection
                            title = "翼相连"
                        }
                        iconUrl = null
                        iconId = it.headResId
                        link = it.link
                        linkType = it.linkType
                        messageOperateList = listOf(
                            MessageCardListBean.MessageAttributeListBean().apply {
                                link = it.link
                                linkType = it.linkType
                                title = "查看详情"
                                styleType = "4"
                            }
                        )
                        showTime = it.showTime
                        subtitle = it.subTitle
                        title = it.title

                    }
                    data.messageCardList.add(0, mPRANBeanList)
                }
                val mScreenCaptureBeanList = MessageCardListBean()
                mScreenCaptureBean?.let {
                    mScreenCaptureBeanList.run {
                        isPran = it.isPran
                        msgId = it.msgId
                        isScreenCapture = it.isScreenCapture
                        cardTypeInfo = CompoundAdItem().apply {
                            iconId = R.drawable.ic_message_family_circle
                            title = "家庭圈"
                        }
                        iconUrl = null
                        iconId = it.headResId
                        link = it.link
                        linkType = it.linkType
                        messageOperateList = listOf(
                            MessageCardListBean.MessageAttributeListBean().apply {
                                link = it.link
                                linkType = "abc"
                                title = "查看详情"
                                styleType = "4"
                            }
                        )
                        showTime = it.showTime
                        subtitle = it.subTitle
                        title = it.title

                    }
                    data.messageCardList.add(0, mScreenCaptureBeanList)
                }
            }


            // 移除被删除的消息
            val deleteList = MessageInfoRepository.getDeleteMsgList()
            deleteList.forEachIndexed { index, deleteItem ->
                val msgThreeLevelListIterator: MutableIterator<*> = data.messageCardList.iterator()
                while (msgThreeLevelListIterator.hasNext()) {
                    val item = msgThreeLevelListIterator.next() as MessageCardListBean
                    if (item.msgId == deleteItem.threeLevelMsgId) {
                        msgThreeLevelListIterator.remove()
                    }
                }
            }*/

            mMessageCardList.clear()
            mMessageCardList.addAll(data.messageCardList)
            cardAdapter.notifyDataSetChanged()
            cardAdapter.setTabTitle(data.tabTitle) // 设置 tabTitle

            if (cardAdapter.data.isEmpty() ) {
                //loadService.showCallback(MsgServiceEmptyCallback::class.java)
            } else {
                //loadService.showSuccess()

                binding.rvMessageCard.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = cardAdapter
                }
            }
        }
    }

}