package com.example.sunnyweather.adapter

import com.ct.base.ext.setColorString
import com.ct.base.ext.setContent
import com.example.sunnyweather.base.binding.BaseBindingQuickAdapter
import com.example.sunnyweather.data.MyInformationPageData.MedalFloorBean.MedalListBean
import com.example.sunnyweather.databinding.ItemMineInformationMedalBinding
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.GetScreenUtils
import com.example.sunnyweather.util.UtilGlide

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
class MedalAdapter : BaseBindingQuickAdapter<MedalListBean, ItemMineInformationMedalBinding>(){

    override fun convert(helper: BaseBindingHolder, item: MedalListBean) {
        helper.getViewBinding<ItemMineInformationMedalBinding>().apply {
            item.run {
                llBase.layoutParams.apply {
                    width = (GetScreenUtils.getScreenWidth(mContext) * 95f / 360).toInt()
                }

                ivMedal.layoutParams.apply {
                    width = (GetScreenUtils.getScreenWidth(mContext) * 45f / 360).toInt()
                    height = (GetScreenUtils.getScreenWidth(mContext) * 50f / 360).toInt()
                }
                UtilGlide.showImage(iconUrl, ivMedal)
                tvTitle.setContent(title)
                tvTitle.setColorString(titleColor)
                llBase.setOnClickListener {
                    CommonLinkItem.goTarget(link, linkType, mContext)
                }
            }
        }
    }
}