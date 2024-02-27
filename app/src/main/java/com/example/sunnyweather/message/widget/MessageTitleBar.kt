package com.example.sunnyweather.message.widget

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sunnyweather.base.inflate
import com.example.sunnyweather.common.Variable
import com.example.sunnyweather.data.CompoundADSpaceData
import com.example.sunnyweather.data.QueryMessageChannelData
import com.example.sunnyweather.databinding.MessageTitleBarBinding
import com.example.sunnyweather.ext.isEmptyOrNull
import com.example.sunnyweather.message.help.MessageCenterHelper
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.UtilGlide

/**
 * 新版消息标题栏
 */
class MessageTitleBar(content: Context, attrs: AttributeSet) : ConstraintLayout(content, attrs) {
    private val binding = inflate<MessageTitleBarBinding>()
    private var mQueryMessageChannelData = QueryMessageChannelData()
    lateinit var onMessageReadAll: () -> Unit
    private var mNoReadSum = 0

    init {
        initListener()
    }

    fun initTitle(allSum: Int) {
        binding.run {
            Variable.redDotMsgSumNum = allSum
            mNoReadSum = allSum
            if (allSum > 99) {
                mNoReadSum = 99
            }

            if (mNoReadSum > 0) {
                tvIsRead.text = "(${mNoReadSum})"
                tvIsRead.visibility = View.VISIBLE
            } else {
                tvIsRead.visibility = View.GONE
            }

       /*     val instance = NavBadgeManager.getInstance()
            // 免打扰红点数变成1
            if (*//*UtilRefresh.isDistractionFree() && *//*mNoReadSum > 1){
                mNoReadSum = 1
            }
            if (mNoReadSum == 1){
                instance.commit(
                    NavBadge(
                        NavBadgeManager.Ori.MESSAGE,
                        NavBadgeManager.Ori.MESSAGE_1,
                        NavBadgeManager.Ori.NAN
                    )
                )
            }else if (mNoReadSum > 1) {
                val navBadge = NavBadge(
                    NavBadgeManager.Ori.MESSAGE,
                    NavBadgeManager.Ori.MESSAGE_1, mNoReadSum, context
                )
                // 红点拖拽设置已读
                navBadge.setOnBadgeDismissListener {
                    markAll()
                }
                instance.commit(navBadge)
            } else {
                instance.commit(
                    NavBadge(
                        NavBadgeManager.Ori.MESSAGE,
                        NavBadgeManager.Ori.MESSAGE_1,
                        NavBadgeManager.Ori.NON
                    )
                )
            }*/
        }
    }

    private fun initListener() {
        binding.run {
            // 消息设置
            ivMessageSetting.setOnClickListener {
                val intent = Intent(context, MessageSettingActivity::class.java)
                intent.putExtra("QueryMessageChannelData", mQueryMessageChannelData)
                context.startActivity(intent)

            }

            // 清除未读
            tvClear.setOnClickListener {
                val unReadSum = mNoReadSum
                if (unReadSum > 0) {
                    /*val dialogBlue = MyBaseDialogBlue(context)
                    dialogBlue.setMsg("确认清除所有未读消息")
                    dialogBlue.setNoBtn("取消")
                    dialogBlue.setYesBtn("确认")
                    dialogBlue.setOnYesButtonCallback {
                        markAll()
                    }
                    dialogBlue.show()*/
                } else {
                    //MyToastD.show("没有未读消息")
                }

            }
        }
    }

    private fun markAll() {
        val mMessageCenterHelper = MessageCenterHelper()
        mMessageCenterHelper.markReadMsg(
            context,
            MarkReadMsgTask.MARK_READ_FLAG.ALLMSG,
            isLoading = true
        ).onMessageRead = {
            if (::onMessageReadAll.isInitialized) {
                onMessageReadAll()
            }
        }
    }

    fun setData(queryMessageChannelData: QueryMessageChannelData) {
        mQueryMessageChannelData = queryMessageChannelData
    }

    fun setIcon(compoundADSpaceData: CompoundADSpaceData) {
        binding.run {
            compoundADSpaceData.run {
                // 是否有图标1
                var hasIcon1 = false
                // 是否有图标2
                var hasIcon2 = false
                if (!compoundADSpaceInfos.isNullOrEmpty()) {
                    compoundADSpaceInfos.forEachIndexed { i, compoundADSpaceInfoBean ->
                        if (compoundADSpaceInfoBean.type == CompoundADSpaceTask.TYPE.AD_MESSAGE_CHANNEL_TOP_FUNCTION) {
                            compoundADSpaceInfoBean.advertisingSpaceInfos.forEachIndexed { index, adItem ->
                                adItem.run {
                                    if (index == 0 && !iconUrl.isEmptyOrNull()) {
                                        ivCusButton1.visibility = View.VISIBLE
                                        UtilGlide.showSimpleImage(iconUrl, ivCusButton1)
                                        ivCusButton1.setOnClickListener {
                                            val linkItem = CommonLinkItem()
                                            linkItem.linkType = linkType
                                            linkItem.link = link
                                            linkItem.goTarget(context)

                                        }
                                        hasIcon1 = true
                                    } else if (index == 1 && !iconUrl.isEmptyOrNull()) {
                                        ivCusButton2.visibility = View.VISIBLE
                                        UtilGlide.showSimpleImage(iconUrl, ivCusButton2)
                                        ivCusButton2.setOnClickListener {
                                            val linkItem = CommonLinkItem()
                                            linkItem.linkType = linkType
                                            linkItem.link = link
                                            linkItem.goTarget(context)

                                        }
                                        hasIcon2 = true
                                    }
                                }
                            }
                        }
                    }
                }

                if (!hasIcon1) {
                    ivCusButton1.visibility = View.GONE
                }

                if (!hasIcon2) {
                    ivCusButton2.visibility = View.GONE
                }
            }
        }
    }
}