package com.example.sunnyweather.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ct.base.ext.isEmptyOrNull
import com.ct.base.ext.setContent
import com.ct.base.ext.setContent2
import com.ct.base.ext.setRightArrow
import com.example.sunnyweather.R
import com.example.sunnyweather.data.QueryServiceMessageData
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.UtilGlide
import com.example.sunnyweather.util.UtilText

/**
 * 说明：全部服务消息适配器
 */
class MessageAllServiceAdapter(
    layoutResId: Int,
    data: List<QueryServiceMessageData.MessageClassifyListBean.MessageCardListBean>?,
) : BaseQuickAdapter<QueryServiceMessageData.MessageClassifyListBean.MessageCardListBean, BaseViewHolder>(
    layoutResId,
    data as MutableList<QueryServiceMessageData.MessageClassifyListBean.MessageCardListBean>
) {

    private var tabTitle: String = ""

    fun setTabTitle(title: String) {
        tabTitle = title
    }

    // 消息删除
    lateinit var onMessageDelete: () -> Unit

    override fun convert(
        holder: BaseViewHolder,
        item: QueryServiceMessageData.MessageClassifyListBean.MessageCardListBean,
    ) {
        holder.run {
            val ivLeft = getView<ImageView>(R.id.ivLeft)
            val tvLeft = getView<TextView>(R.id.tvLeft)
            val tvShowTime = getView<TextView>(R.id.tvShowTime)
            val ivIcon = getView<ImageView>(R.id.ivCenter)
            val tvTitle = getView<TextView>(R.id.tvTitle)
            val tvSubtitle = getView<TextView>(R.id.tvSubTitle)
            val rvMessageAttribute = getView<RecyclerView>(R.id.rvMessageAttribute)
            val rvMessageOperate = getView<RecyclerView>(R.id.rvMessageOperate)
            val ivClose = getView<ImageView>(R.id.ivClose)
            val viewLine = getView<View>(R.id.viewLine)

            item.run {
                if (isScreenCapture) {
                    /*setOnClickListener(R.id.constraint) {
                        val intent = Intent(mContext, CalledOnActivity::class.java)
                        mContext.startActivity(intent)
                    }*/
                }
                else {
                    setOnClickListener(R.id.constraint) {
                        CommonLinkItem.goTarget(link, linkType, mContext)

                       /* HgXxSy.hitMessageAllServiceItem(
                            tabTitle,
                            cardTypeInfo?.title ?: "",
                            item
                        )*/
                    }
                }

                cardTypeInfo?.run {
                    ivLeft.visibility = View.VISIBLE
                    tvLeft.visibility = View.VISIBLE
                    // 左上角配置
                    if (!UtilText.isEmptyOrNull(iconUrl)){
                        UtilGlide.showImage(iconUrl, ivLeft)
                        UtilText.setTextAndView(tvLeft, title, ivLeft)
                        tvLeft.setRightArrow(linkType,R.drawable.ic_message_arrow)
                    }else{
                        if (iconId != -1) {
                            UtilGlide.showImage(iconId, ivLeft)
                            tvLeft.text = title
                        }else{
                            ivLeft.visibility = View.GONE
                            tvLeft.visibility = View.GONE
                        }
                    }

                    // 左上角跳转, 可以覆盖整体跳转
                    if (!linkType.isEmptyOrNull()){
                        tvLeft.setOnClickListener {
                            if (isPran) {
                               /* val intent = Intent(mContext, CalledOnActivity::class.java)
                                mContext.startActivity(intent)*/
                            }else{
                                CommonLinkItem.goTarget(link, linkType, mContext)
                                //HgXxSy.hitMessageAttributeTopLeft(tabTitle, item)
                            }
                        }
                    }
                } ?: run {
                    ivLeft.visibility = View.GONE
                    tvLeft.visibility = View.GONE
                }

                // 关闭
                ivClose.setOnClickListener {
                    // 快速点击不弹
                   /* if (UtilView.isFastClick())
                        return@setOnClickListener*/

                    if (msgId.isEmptyOrNull()) {
                        remove(holder.layoutPosition)
                        if (::onMessageDelete.isInitialized){
                            onMessageDelete()
                        }
                        //MessageInfoRepository.deleteThree(msgId)
                    } else {
                       /* val mMessageCenterHelper = MessageCenterHelper()
                        mMessageCenterHelper.deleteMessage(
                            mContext, DeleteMessageTask.DELETE_FLAG.THREELEVELMSG,
                            threeLevelMsgId = msgId
                        ).onMessageDelete = {
                            remove(holder.layoutPosition)

                            RedDotHelper.getInstance()
                                .onRedDotListener(Constants.RedDotTabName.MSG_DELETE)
                            Variable.isNeedGetMsgDot = true

                            if (::onMessageDelete.isInitialized){
                                onMessageDelete()
                            }
                        }*/
                    }

                    //HgXxSy.hitMsgAllServiceClose(tabTitle, cardTypeInfo?.title ?: "")
                }

                tvShowTime.setContent(showTime)
                UtilText.setText(tvTitle, title)
                UtilText.setText(tvSubtitle, subtitle)

                // 消息图标
                if (iconId == -1){
                    ivIcon.setContent2(iconUrl)
                }else{
                    UtilGlide.showImage(iconId, ivIcon)
                }

                // 属性区域
                if (!messageAttributeList.isNullOrEmpty()) {
                    val messageAttributeAdapter = MessageAttributeAdapter(
                        messageAttributeList
                    )
                    rvMessageAttribute.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = messageAttributeAdapter
                    }
                    messageAttributeAdapter.setBean(tabTitle, cardTypeInfo?.title ?: "",link,linkType,isScreenCapture)
                }

                // 底部引导区域
                if (!messageOperateList.isNullOrEmpty()) {
                    viewLine.visibility = View.VISIBLE
                    rvMessageOperate.visibility = View.VISIBLE

                    // 底部引导区域
                    val messageOperateAdapter =
                        MessageAttributeAdapter(messageOperateList)
                    rvMessageOperate.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = messageOperateAdapter
                    }
                    messageOperateAdapter.setBean(tabTitle, cardTypeInfo?.title ?: "",link,linkType,isScreenCapture)
                }else{
                    viewLine.visibility = View.GONE
                    rvMessageOperate.visibility = View.GONE
                }
            }
        }
    }

}