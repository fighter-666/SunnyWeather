package com.example.sunnyweather.adapter

import android.content.Intent
import android.view.View
import com.example.sunnyweather.widget.MsgDataHelper
import com.example.sunnyweather.R
import com.example.sunnyweather.base.Constants
import com.example.sunnyweather.base.binding.BaseBindingQuickAdapter
import com.example.sunnyweather.data.QueryMessageChannelData
import com.example.sunnyweather.databinding.AdapterMarketingMessagesBinding
import com.example.sunnyweather.helper.RedDotHelper
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.GetScreenUtils
import com.example.sunnyweather.util.UtilGlide
import com.example.sunnyweather.util.UtilOther
import com.example.sunnyweather.util.UtilPhoneParam
import com.example.sunnyweather.widget.CustomCleanMsgPopupView
import com.lxj.xpopup.XPopup

/**
 * 营销消息适配器
 */
class MarketMessageAdapter : BaseBindingQuickAdapter<QueryMessageChannelData.MarketingMessageListBean, AdapterMarketingMessagesBinding>(){

    var mBottomTip = ""
    var mSkidPosition: Int = -1 // 营销列表侧滑删除的位置
    lateinit var onMessageDelete: () -> Unit
    lateinit var onMessageTop: () -> Unit

    override fun convert(
        helper: BaseBindingHolder,
        item: QueryMessageChannelData.MarketingMessageListBean,
    ) {
        helper.getViewBinding<AdapterMarketingMessagesBinding>().apply {
            item.run {
                UtilGlide.showImage(iconUrl, ivLeft)
                tvTitle.text = title
                tvSubTitle.text = subtitle
                tvTime.text = showTime

                val realSum = if (msgType == QueryMessageChannelData.MarketingMessageListBean.MSG_TYPE.JUMP){
                    UtilOther.parseInt(redDotMsgNum) /*+ MessageInfoRepository.getThreeLevelUnReadMsg(threeLevelMsgId)*/
                }else{
                    UtilOther.parseInt(redDotMsgNum) /*+ MessageInfoRepository.getTwoLevelUnReadMsg(twoLevelMsgId)*/
                }
                item.realSum = realSum

                // 过时的消息非置顶红点置灰
                if (isOutOfTime == QueryMessageChannelData.MarketingMessageListBean.IS_OUT_OF_TIME.YES && !isTop){
                    tvRedDot.mBackground = R.drawable.bg_msg_gray_dot
                    tvRedDot.mBackgroundOmit = R.drawable.ic_msg_gray_point_omit
                }else{
                    tvRedDot.mBackground = R.drawable.bg_msg_red_dot
                    tvRedDot.mBackgroundOmit = R.drawable.ic_msg_red_point_omit
                }
                tvRedDot.setContent(realSum)

                // 置顶
                if (isTop){
                    ivTop.visibility = View.VISIBLE
                    tvTop.text = "取消置顶"
                }else{
                    ivTop.visibility = View.GONE
                    tvTop.text = "置顶"
                }

                clLeft.setOnClickListener {
                    // 跳转类(三级)
                    if (msgType == QueryMessageChannelData.MarketingMessageListBean.MSG_TYPE.JUMP){

                        val linkItem = CommonLinkItem()
                        linkItem.linkType = linkType
                        linkItem.link = link
                        linkItem.goTarget(mContext)
                        //val mMessageCenterHelper = MessageCenterHelper()

                        if (realSum > 0){
                            // 接口设置已读
                          /*  mMessageCenterHelper.markReadMsg(mContext, MarkReadMsgTask.MARK_READ_FLAG.THREELEVELMSG,
                                threeLevelMsgId = threeLevelMsgId).onMessageRead = {
                                redDotMsgNum = "0"
                                tvRedDot.setContent(0)

                                // 通知消息首页刷新红点数
                                RedDotHelper.getInstance()
                                    .onRedDotListener(Constants.RedDotTabName.MSG_READ_REFRESH)
                            }*/
                        }
                        // 通知类(二级)
                    }else{
                      /*  val intent = Intent(mContext, MsgThreeLevelActivity::class.java)
                        intent.putExtra("bottomTip", mBottomTip)
                        intent.putExtra("MarketingMessageListBean", item)
                        mContext.startActivity(intent)

                        if (realSum > 0){
                            // 接口设置已读
                            val mMessageCenterHelper = MessageCenterHelper()
                            mMessageCenterHelper.markReadMsg(mContext, MarkReadMsgTask.MARK_READ_FLAG.TWOLEVELMSG,
                                twoLevelMsgId = twoLevelMsgId).onMessageRead = {
                                redDotMsgNum = "0"
                                tvRedDot.setContent(0)

                                // 通知消息首页刷新红点数
                                RedDotHelper.getInstance()
                                    .onRedDotListener(Constants.RedDotTabName.MSG_READ_REFRESH)
                            }
                        }*/
                    }

                    if (isOutOfTime == QueryMessageChannelData.MarketingMessageListBean.IS_OUT_OF_TIME.YES && !isTop){
                        //HgXxSy.hitOutMsgMarketItem(item, helper.layoutPosition)
                    }else{
                       // HgXxSy.hitMsgMarketItem(item, helper.layoutPosition)
                    }
                }

                // 删除
                tvDelete.setOnClickListener {
                    val dialog = CustomCleanMsgPopupView(mContext).setData(item)
                    dialog.onMessageDelete = {
                        remove(helper.layoutPosition)

                        if (::onMessageDelete.isInitialized){
                            onMessageDelete()
                        }
                    }
                    //弹出清空消息记录询问框
                    XPopup.Builder(mContext)
                        .asCustom(dialog)
                        .show()

                    if (isOutOfTime == QueryMessageChannelData.MarketingMessageListBean.IS_OUT_OF_TIME.YES && !isTop){
                      //  HgXxSy.hitOutMsgMarketDelete(item)
                    }else{
                       // HgXxSy.hitMsgMarketDelete(item)
                    }
                }

               /* // 置顶
                tvTop.setOnClickListener {
                    val mMessageCenterHelper = MessageCenterHelper()
                    if (msgType == QueryMessageChannelData.MarketingMessageListBean.MSG_TYPE.JUMP){
                        if (isTop){
                            mMessageCenterHelper.setMessageCancelTop(mContext, threeLevelMsgId).onMessageTop = {
                                if (::onMessageTop.isInitialized){
                                    onMessageTop()
                                }
                                notifyItemChanged(helper.layoutPosition)
                            }
                        }else {
                            mMessageCenterHelper.setMessageTop(mContext, threeLevelMsgId).onMessageTop = {
                                if (::onMessageTop.isInitialized){
                                    onMessageTop()
                                }
                                notifyItemChanged(helper.layoutPosition)
                            }
                        }
                    }else{
                        if (isTop){
                            mMessageCenterHelper.setMessageCancelTop(mContext, twoLevelMsgId).onMessageTop = {
                                if (::onMessageTop.isInitialized){
                                    onMessageTop()
                                }
                                notifyItemChanged(helper.layoutPosition)
                            }
                        }else{
                            mMessageCenterHelper.setMessageTop(mContext, twoLevelMsgId).onMessageTop = {
                                if (::onMessageTop.isInitialized){
                                    onMessageTop()
                                }
                                notifyItemChanged(helper.layoutPosition)
                            }
                        }
                    }

                 *//*   if (isOutOfTime == QueryMessageChannelData.MarketingMessageListBean.IS_OUT_OF_TIME.YES){
                        if (isTop){
                            HgXxSy.hitOutMsgMarketTop("取消置顶", item)
                        }else{
                            HgXxSy.hitOutMsgMarketTop("置顶", item)
                        }
                    }else{
                        if (isTop){
                            HgXxSy.hitMsgMarketTop("取消置顶", item)
                        }else{
                            HgXxSy.hitMsgMarketTop("置顶", item)
                        }
                    }*//*
                }*/

                val params = clLeft.layoutParams
                params.width = GetScreenUtils.getScreenWidth(mContext)
                clLeft.layoutParams = params

                // 关闭展开的item
                if (intimateHorizontalScrollView.isExpand){
                    intimateHorizontalScrollView.smoothScrollTo(0,0)
                }

                // 侧滑关闭上一个滑出的列表
                intimateHorizontalScrollView.onSkidListener = { hasFocus: Boolean ->
                    //当 hasFocus 为 true 时，关闭 currentSkidView 并更新它
                    if (hasFocus) {
                        mSkidPosition = helper.layoutPosition
                        //避免在滑动自己的时候关闭自己
                        if (MsgDataHelper.currentSkidView != intimateHorizontalScrollView) {
                            MsgDataHelper.currentSkidView?.smoothScrollTo(0,0)
                        }
                        MsgDataHelper.currentSkidView = intimateHorizontalScrollView
                    }else{
                        //当 hasFocus 为 false 时，清除 currentSkidView
                        mSkidPosition = -1
                        //清除对当前滑动的 IntimateHorizontalScrollView 的引用
                        if (MsgDataHelper.currentSkidView == intimateHorizontalScrollView) {
                            MsgDataHelper.currentSkidView = null
                        }
                    }
                }
            }
        }
    }
}