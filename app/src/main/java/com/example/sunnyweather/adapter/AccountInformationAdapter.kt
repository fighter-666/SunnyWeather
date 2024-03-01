package com.example.sunnyweather.adapter

import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import com.ct.base.ext.dp
import com.example.sunnyweather.base.binding.BaseBindingQuickAdapter
import com.example.sunnyweather.data.MyInformationPageData.AccountInformationFloorBean.AccountInformationListBean
import com.example.sunnyweather.databinding.ItemMineInformationAccountInfoBinding

/**
 * 说明：我的信息-我的账户信息列表适配器
 *
 * @作者 luohao
 * @创建时间 2024/2/21 09:54
 * @版本 11.2.0
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */

class AccountInformationAdapter : BaseBindingQuickAdapter<AccountInformationListBean, ItemMineInformationAccountInfoBinding>(){

    override fun convert(helper: BaseBindingHolder, item: AccountInformationListBean) {
        helper.getViewBinding<ItemMineInformationAccountInfoBinding>().apply {
            val position = data.indexOf(item)

            viewMineAccountInfo.setData(item.accountDetailList)
            val number = if (data.size % 2 == 0) {
                2
            } else {
                1
            }
            if (position < data.size - number && data.size > 2) {
                viewHorizontal.visibility = View.VISIBLE
            } else {
                viewHorizontal.visibility = View.GONE
            }

            if (position % 2 == 0) {
                viewVertical.visibility = View.VISIBLE
                viewHorizontal.layoutParams.apply {
                    this as MarginLayoutParams
                    leftMargin = 10.dp
                    rightMargin = 0
                }
            } else {
                viewVertical.visibility = View.GONE
                viewHorizontal.layoutParams.apply {
                    this as MarginLayoutParams
                    leftMargin = 0
                    rightMargin = 10.dp
                }
            }
        }
    }
}