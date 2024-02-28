package com.example.sunnyweather.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.sunnyweather.base.binding.inflate
import com.example.sunnyweather.data.AdItem
import com.example.sunnyweather.databinding.ViewMessageInfoBinding

/**
 * @说明: 消息中心-消息条
 */
class MessageInfoView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding = inflate<ViewMessageInfoBinding>()
    private var marqueeFactory: MessageInfoMF<*>
    private var mNotices = ArrayList<AdItem>()

    init {
        marqueeFactory = MessageInfoMF<Any?>(context)
        binding.marqueeView.setMarqueeFactory(marqueeFactory)
    }

    fun getNotice():ArrayList<AdItem>{
        return mNotices
    }

    fun setNotices(mData: List<AdItem>) {
        mNotices.clear()
        mNotices.addAll(mData)
        setAdList()
      /*  RedDotHelper.getInstance().addRedDotListener(Constants.RedDotTabName.MESSAGE_CLOSE) { entity: Any? ->
            mNotices.remove(entity)
            setAdList()
        }*/
    }

    private fun setAdList(){
        marqueeFactory.data = mNotices
        if (mNotices.isNotEmpty()){
            visibility = View.VISIBLE
            if (mNotices.size > 1){
                binding.marqueeView.startFlipping()
            }else{
                binding.marqueeView.stopFlipping()
            }
        }else{
            visibility = View.GONE
        }
        binding.marqueeView.setOnItemClickListener { mView, mData, mPosition ->
            val data = mData as AdItem
            data.run {
                when(data.linkType){
                  /*  // 公告
                    Constants.OtherLinkType.MSG_NOTICE_ANNOUNCE->{
                        XPopup.Builder(context)
                            .asCustom(
                                CusAnnounceDialog(context)
                                    .setData(MsgDataHelper.announceConfig)
                            )
                            .show()

                    }
                    else->{
                        val linkItem = CommonLinkItem()
                        linkItem.linkType = linkType
                        linkItem.link = link
                        linkItem.goTarget(context)

                    }*/
                }
            }
        }
    }

    fun startFlipping(){
        if (mNotices.size > 1){
            binding.marqueeView.startFlipping()
        }
    }

    fun stopFlipping(){
        binding.marqueeView.stopFlipping()
    }

}