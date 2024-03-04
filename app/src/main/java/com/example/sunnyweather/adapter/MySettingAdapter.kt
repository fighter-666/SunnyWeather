package com.example.sunnyweather.adapter

import com.ct.base.ext.setColorString
import com.ct.base.ext.setContent
import com.example.sunnyweather.base.binding.BaseBindingQuickAdapter
import com.example.sunnyweather.data.MyInformationPageData.MedalFloorBean.MedalListBean
import com.example.sunnyweather.data.MySetting
import com.example.sunnyweather.data.MySettingData
import com.example.sunnyweather.data.Piggy
import com.example.sunnyweather.databinding.ItemMineInformationMedalBinding
import com.example.sunnyweather.databinding.ItemMineSettingBinding
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
class MySettingAdapter : BaseBindingQuickAdapter<MySettingData.MySettingListBean, ItemMineSettingBinding>(){

    override fun convert(helper: BaseBindingHolder, item: MySettingData.MySettingListBean) {
        helper.getViewBinding<ItemMineSettingBinding>().apply {
            item.run {
                tvTitle.setContent(title)
               /* llBase.setOnClickListener {
                    CommonLinkItem.goTarget(link, linkType, mContext)
                }*/
            }
        }
    }
}