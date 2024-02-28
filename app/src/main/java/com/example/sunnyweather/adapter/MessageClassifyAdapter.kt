package com.example.sunnyweather.adapter

import com.ct.base.ext.isEmptyOrNull
import com.example.sunnyweather.base.binding.BaseBindingQuickAdapter
import com.example.sunnyweather.data.QueryMessageChannelData
import com.example.sunnyweather.databinding.AdapterMessageClassifyBinding
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.UtilGlide
import com.example.sunnyweather.util.UtilOther


/**
 * 消息分类适配器
 */
class MessageClassifyAdapter:
    BaseBindingQuickAdapter<QueryMessageChannelData.ServiceMessageClassifyListBean, AdapterMessageClassifyBinding>() {

    override fun convert(
        helper: BaseBindingHolder,
        item: QueryMessageChannelData.ServiceMessageClassifyListBean,
    ) {
        helper.getViewBinding<AdapterMessageClassifyBinding>().apply {
            item.run {
                val realSum = UtilOther.parseInt(redDotMsgNum) /*+ MessageInfoRepository.getOneLevelUnReadMsg(oneLevelMsgId)*/
                if (UtilOther.parseInt(h5RedDotNum) > 0){
                    item.realSum = UtilOther.parseInt(h5RedDotNum)
                }else{
                    item.realSum = realSum
                }
                tvRedDot.setContent(item.realSum)

                tvTitle.text = title
                if (iconUrl.isEmptyOrNull()){
                    UtilGlide.showImage(resId, ivTop)
                }else{
                    UtilGlide.showImage(iconUrl, ivTop)
                }

                clItem.setOnClickListener {
                    val linkItem = CommonLinkItem()
                    linkItem.linkType = linkType
                    linkItem.link = link
                    linkItem.goTarget(mContext)

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (data.size > 4) 4 else data.size
    }

}