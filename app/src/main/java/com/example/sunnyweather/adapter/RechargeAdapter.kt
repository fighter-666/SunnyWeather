package com.example.sunnyweather.adapter

import android.widget.TextView
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.sunnyweather.R
import com.example.sunnyweather.data.GetFeedListData

class RechargeAdapter(
    @LayoutRes layoutResId: Int,
    data: MutableList<GetFeedListData.FeedListBean.QuickRechargeBean.DenominationBean>,
) :
    BaseQuickAdapter<GetFeedListData.FeedListBean.QuickRechargeBean.DenominationBean, BaseViewHolder>(
        layoutResId,
        data
    ) {

    override fun convert(
        holder: BaseViewHolder,
        item: GetFeedListData.FeedListBean.QuickRechargeBean.DenominationBean,
    ) {
        holder.run {
            val tvMainTitle = getView<TextView>(R.id.tvMainTitle)
            val tvSubtitle = getView<TextView>(R.id.tvSubtitle)
            tvMainTitle.text = item.mainTitle
            tvSubtitle.text = item.subtitle
        }

    }
}