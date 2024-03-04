package com.example.sunnyweather.adapter

import com.ct.base.ext.setContent
import com.example.sunnyweather.base.binding.BaseBindingQuickAdapter
import com.example.sunnyweather.data.RecommendData
import com.example.sunnyweather.databinding.ItemRecommendBinding
import com.example.sunnyweather.util.CommonLinkItem

/**
 * 说明：我的信息-我的勋章适配器
 *
 * @作者 luohao
 * @创建时间 2024/2/22 09:19
 * @版本 11.2.0
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
class RecommendAdapter : BaseBindingQuickAdapter<RecommendData.MyRecommendListBean, ItemRecommendBinding>(){

    override fun convert(helper: BaseBindingHolder, item: RecommendData.MyRecommendListBean) {
        helper.getViewBinding<ItemRecommendBinding>().apply {
            item.run {
                tvTitle.setContent(title)
                tvSubTitle.setContent(subTitle)
                llBase.setOnClickListener {
                    CommonLinkItem.goTarget(link, linkType, mContext)
                }
            }
        }
    }
}