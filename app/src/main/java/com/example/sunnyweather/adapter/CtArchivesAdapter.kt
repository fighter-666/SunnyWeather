package com.example.sunnyweather.adapter

import android.os.Parcel
import android.os.Parcelable
import com.bumptech.glide.request.RequestOptions
import com.ct.base.ext.setColorString
import com.ct.base.ext.setContent
import com.example.sunnyweather.R
import com.example.sunnyweather.base.binding.BaseBindingQuickAdapter
import com.example.sunnyweather.data.MyInformationPageData.CtArchivesFloorBean.ArchivesListBean
import com.example.sunnyweather.databinding.ItemMineInformationCtArchivesBinding
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.UtilGlide

/**
 * 说明：我的信息-我的电信档案适配器
 *
 * @作者 luohao
 * @创建时间 2024/2/21 10:06
 * @版本 11.2.0
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */

class CtArchivesAdapter() :  BaseBindingQuickAdapter<ArchivesListBean, ItemMineInformationCtArchivesBinding>(),
    Parcelable {

    constructor(parcel: Parcel) : this() {
    }

    override fun convert(helper: BaseBindingHolder, item: ArchivesListBean) {
        helper.getViewBinding<ItemMineInformationCtArchivesBinding>().apply {
            item.run {
                tvTitle.setContent(title)
                tvTitle.setColorString(titleColor)
                tvSubTitle.setContent(subtitle)
                tvSubTitle.setColorString(subtitleColor)

                val options = RequestOptions()
                    .placeholder(R.drawable.ic_mine_info_folder)
                    .error(R.drawable.ic_mine_info_folder)
                    .fallback(R.drawable.ic_mine_info_folder)
                UtilGlide.showImageByOption(backgroundImage, ivBackground, options)

                clBase.setOnClickListener {
                    CommonLinkItem.goTarget(link, linkType, mContext)
                }
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CtArchivesAdapter> {
        override fun createFromParcel(parcel: Parcel): CtArchivesAdapter {
            return CtArchivesAdapter(parcel)
        }

        override fun newArray(size: Int): Array<CtArchivesAdapter?> {
            return arrayOfNulls(size)
        }
    }
}