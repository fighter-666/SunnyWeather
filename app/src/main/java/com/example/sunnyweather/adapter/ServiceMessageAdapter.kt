package com.ct.client.message.adapter

import com.ct.base.ext.dp
import com.ct.base.ext.isEmptyOrNull
import com.ct.base.ext.setContent
import com.example.sunnyweather.widget.MsgDataHelper
import com.example.sunnyweather.base.binding.BaseBindingQuickAdapter
import com.example.sunnyweather.data.QueryMessageChannelData
import com.example.sunnyweather.databinding.AdapterServiceMessageBinding
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.GetScreenUtils
import com.example.sunnyweather.util.UtilGlide
import com.example.sunnyweather.util.UtilPhoneParam

/**
 * 服务消息适配器
 */
class ServiceMessageAdapter :
    BaseBindingQuickAdapter<QueryMessageChannelData.MsgListBean, AdapterServiceMessageBinding>() {

    lateinit var onServiceMessageDelete: () -> Unit
    var mSkidPosition: Int = -1 // 服务列表侧滑删除的位置


    override fun convert(helper: BaseBindingHolder, item: QueryMessageChannelData.MsgListBean) {
        helper.getViewBinding<AdapterServiceMessageBinding>().apply {
            item.run {
                UtilGlide.showImage(headImage, ivLeft)

                tvTitle.text = title
                tvSubTitle.text = subTitle
                tvLabel.setContent(label)
                tvTime.text = showTime

                // 消息跳转
                cvLeft.setOnClickListener {
                    // 屏幕共享
                    if (isScreenCapture){
                       /* val intent = Intent(mContext, CalledOnActivity::class.java)
                        mContext.startActivity(intent)*/
                    }else{
                        val linkItem = CommonLinkItem()
                        linkItem.linkType = linkType
                        linkItem.link = link
                        linkItem.goTarget(mContext)
                    }

                    //HgXxSy.hitMsgServiceItem(item, helper.layoutPosition)
                }

                // 删除
                tvDelete.setOnClickListener {
                    if (msgId.isEmptyOrNull()){
                        remove(helper.layoutPosition)

                        if (::onServiceMessageDelete.isInitialized){
                            onServiceMessageDelete()
                        }
                        //MessageInfoRepository.deleteThree(msgId)
                    }else{
                        /*val mMessageCenterHelper = MessageCenterHelper()
                        mMessageCenterHelper.deleteMessage(mContext, DeleteMessageTask.DELETE_FLAG.THREELEVELMSG,
                            threeLevelMsgId = msgId).onMessageDelete = {
                            remove(helper.layoutPosition)

                            if (::onServiceMessageDelete.isInitialized){
                                onServiceMessageDelete()
                            }
                        }*/
                    }

                    //HgXxSy.hitMsgServiceDelete(item)
                }

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

            val params = cvLeft.layoutParams
            params.width = GetScreenUtils.getScreenWidth(mContext) - 36.dp
            cvLeft.layoutParams = params
        }
    }

}