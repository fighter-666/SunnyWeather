package com.example.sunnyweather.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ct.base.ext.dp
import com.ct.base.ext.isEmptyOrNull
import com.ct.base.ext.maxWords
import com.ct.base.ext.setRightArrow
import com.example.sunnyweather.R
import com.example.sunnyweather.data.QueryServiceMessageData.MessageClassifyListBean.MessageCardListBean.MessageAttributeListBean
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.UtilText
import com.example.sunnyweather.util.UtilTime

/**
 * 说明：消息属性适配器
 *
 */
class MessageAttributeAdapter(data: List<MessageAttributeListBean>) :
    BaseMultiItemQuickAdapter<MessageAttributeListBean, BaseViewHolder>(data) {

    private var tabTitle: String = ""
    private var mainTitle: String = ""
    private var cardLink: String = ""
    private var cardLinkType: String = ""
    private var isScreenCapture: Boolean = false

    //传参-插码和整体点击事件
    fun setBean(
        title: String,
        mainTitle: String,
        cardLink: String,
        cardLinkType: String,
        isScreenCapture: Boolean,
    ) {
        this.tabTitle = title
        this.mainTitle = mainTitle
        this.cardLink = cardLink
        this.cardLinkType = cardLinkType
        this.isScreenCapture = isScreenCapture
    }

    init {
        addItemType(
            MessageAttributeListBean.STYLE_TYPE.BLUE.toInt(),
            R.layout.adapter_message_attribute_blue
        )
        addItemType(
            MessageAttributeListBean.STYLE_TYPE.ORANGE.toInt(),
            R.layout.adapter_message_attribute_orange
        )
        addItemType(
            MessageAttributeListBean.STYLE_TYPE.DETAIL.toInt(),
            R.layout.adapter_message_attribute_detail
        )
        addItemType(
            MessageAttributeListBean.STYLE_TYPE.DEFAULT.toInt(),
            R.layout.adapter_message_attribute_default
        )
    }

    override fun convert(holder: BaseViewHolder, item: MessageAttributeListBean) {
        item.run {
            when (holder.itemViewType) {
                //类型2,类型3
                MessageAttributeListBean.STYLE_TYPE.BLUE.toInt(), MessageAttributeListBean.STYLE_TYPE.ORANGE.toInt() -> {
                    val tvTitle = holder.getView<TextView>(R.id.tvTitle)
                    val tvContent = holder.getView<TextView>(R.id.tvContent)
                    val tvSubTitle = holder.getView<TextView>(R.id.tvSubTitle)
                    val constraint = holder.getView<ConstraintLayout>(R.id.constraint)
                    val llSubTitle = holder.getView<LinearLayout>(R.id.llSubTitle)
                    UtilText.setText(tvTitle, title.maxWords(4))
                    UtilText.setTextInvisible(tvContent, content)
                    tvSubTitle.visibility = View.VISIBLE
                    if (subtitle.isEmptyOrNull()) {
                        llSubTitle.visibility = View.GONE
                    } else {
                        llSubTitle.visibility = View.VISIBLE
                    }
                    UtilText.setTextInvisible(tvSubTitle, subtitle.maxWords(6))
                    //phoneNum：电话号码，subtitle不为空且type为类型2打电话时才需要处理该字段，没有返回或返回为空则不响应打电话操作
                    if (!subtitle.isEmptyOrNull() && type == MessageAttributeListBean.TYPE.CALL) {
                        if (!phoneNum.isEmptyOrNull()) {
                            tvSubTitle.setOnClickListener {
                                if (UtilTime.isAvailableTime(startTime, endTime)) {
                                    val intent = Intent()
                                    intent.action = Intent.ACTION_DIAL
                                    intent.data =
                                        Uri.parse("tel:" + phoneNum)
                                    mContext.startActivity(intent)
                                } else {
                                    if (!UtilText.isEmptyOrNull(linkType)) {
                                        val commonLinkItem = CommonLinkItem()
                                        commonLinkItem.link = link
                                        commonLinkItem.linkType = linkType
                                        commonLinkItem.goTarget(mContext)
                                    } else {
                                        //没有单独跳转时响应卡片的跳转
                                        CommonLinkItem.goTarget(cardLink, cardLinkType, mContext)
                                    }
                                }

                                /*HgXxSy.hitMessageAttributeItem(
                                    tabTitle, mainTitle, item, holder.layoutPosition
                                )*/
                            }
                        } else {
                            //没有返回或返回为空则不响应打电话操作
                            tvSubTitle.setOnClickListener {
                                if (!linkType.isEmptyOrNull()) {
                                    CommonLinkItem.goTarget(link, linkType, mContext)
                                } else {
                                    CommonLinkItem.goTarget(cardLink, cardLinkType, mContext)
                                }
                                /*HgXxSy.hitMessageAttributeItem(
                                    tabTitle, mainTitle, item, holder.layoutPosition
                                )*/
                            }
                        }
                    } else {
                        //没有返回或返回为空则不响应打电话操作
                        tvSubTitle.setOnClickListener {
                            if (!linkType.isEmptyOrNull()) {
                                CommonLinkItem.goTarget(link, linkType, mContext)
                            } else {
                                CommonLinkItem.goTarget(cardLink, cardLinkType, mContext)
                            }
                            /*HgXxSy.hitMessageAttributeItem(
                                tabTitle, mainTitle, item, holder.layoutPosition
                            )*/
                        }
                    }
                    //○点击非按钮区域响应卡片的跳转
                    if (!cardLinkType.isEmptyOrNull()) {
                        constraint.setOnClickListener {
                            CommonLinkItem.goTarget(cardLink, cardLinkType, mContext)
                        }
                    }
                }

                //类型4
                MessageAttributeListBean.STYLE_TYPE.DETAIL.toInt() -> {
                    val tvTitle = holder.getView<TextView>(R.id.tvTitle)
                    val tvSubTitle = holder.getView<TextView>(R.id.tvSubTitle)
                    val constraint = holder.getView<ConstraintLayout>(R.id.constraint)
                    UtilText.setText(tvTitle, title.maxWords(6))
                    //UtilText.setTextInvisible(tvSubTitle, subtitle.maxWords(10))
                    if (linkType.isEmptyOrNull() && subtitle.isEmptyOrNull()) {
                        tvSubTitle.visibility = View.GONE
                    } else {
                        tvSubTitle.visibility = View.VISIBLE
                        tvSubTitle.setRightArrow(linkType, R.drawable.ic_message_arrow)
                    }

                    if (isScreenCapture) {
                        constraint.setOnClickListener {
                            /*val intent = Intent(mContext, CalledOnActivity::class.java)
                            mContext.startActivity(intent)*/
                        }
                    } else {
                        if (!linkType.isEmptyOrNull()) {
                            constraint.setOnClickListener {
                                CommonLinkItem.goTarget(link, linkType, mContext)

                               /* HgXxSy.hitMessageOperateItem(
                                    tabTitle,
                                    mainTitle,
                                    item,
                                    holder.layoutPosition
                                )*/
                            }
                        } else {
                            constraint.setOnClickListener {
                                CommonLinkItem.goTarget(cardLink, cardLinkType, mContext)
                            }
                        }
                    }
                }

                //类型1,响应卡片的跳转
                else -> {
                    val tvTitle = holder.getView<TextView>(R.id.tvTitle)
                    val tvContent = holder.getView<TextView>(R.id.tvContent)
                    val constraint = holder.getView<ConstraintLayout>(R.id.constraint)
                    UtilText.setText(tvTitle, title.maxWords(4))
                    UtilText.setTextInvisible(tvContent, content)
                    constraint.setOnClickListener {
                        CommonLinkItem.goTarget(cardLink, cardLinkType, mContext)
                    }
                }
            }
        }
    }
}