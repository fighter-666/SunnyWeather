package com.example.sunnyweather.widget

import android.content.Context
import android.widget.LinearLayout
import com.example.sunnyweather.R
import com.example.sunnyweather.base.binding.inflate
import com.example.sunnyweather.data.AdItem
import com.example.sunnyweather.databinding.ViewMessageInfoMarqueeBinding

/**
 * 消息中心-布局
 */
class MessageInfoMarquee(context: Context) : LinearLayout(context) {

    private val binding = inflate<ViewMessageInfoMarqueeBinding>()

    fun setData(data: AdItem) {
        binding.run {
            data.run {
                ivClose.setImageResource(R.drawable.ic_message_close)
                tvTitle.text = title
//                llContent.setOnClickListener {
//                    when(linkType){
//                        // 公告
//                        Constants.OtherLinkType.MSG_NOTICE_ANNOUNCE->{
//                            XPopup.Builder(context)
//                                .asCustom(
//                                    CusAnnounceDialog(context)
//                                        .setData(MsgDataHelper.announceConfig)
//                                )
//                                .show()
//                        }
//                        else->{
//                            val linkItem = CommonLinkItem()
//                            linkItem.linkType = linkType
//                            linkItem.link = link
//                            linkItem.goTarget(context)
//                        }
//                    }
//                }

                // 消息和公告分用户缓存关闭，升级不分用户
                /*ivClose.setOnClickListener {
                    RedDotHelper.getInstance()
                        .onRedDotListener(Constants.RedDotTabName.MESSAGE_CLOSE, data)

                    data.run {
                        when(linkType){
                            // 公告
                            Constants.OtherLinkType.MSG_NOTICE_ANNOUNCE->{
                                UtilRefresh.setRedDot(MsgDataHelper.announceConfig.timeStamp, MsgDataHelper.announceConfig.detail,
                                    CusAnnounceDialog::class.java.name)
                            }
                            else->{
                                // 升级
                                if (link == "1000021"){
                                    Setting.setString(KEY_CLOSE_UPDATE, UtilApp.getCurAppVerName())
                                }else{
                                    UtilRefresh.setRedDot(timeStamp, title,
                                        MessageInfoMarquee::class.java.name)
                                }
                            }
                        }
                    }

                }*/
            }
        }
    }


}