package com.example.sunnyweather.widget

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.ct.base.ext.isEmptyOrNull
import com.ct.base.ext.setContent
import com.example.sunnyweather.R
import com.example.sunnyweather.base.binding.inflate
import com.example.sunnyweather.data.CompoundAdItem
import com.example.sunnyweather.databinding.ViewMineAccountInfoMarqueeBinding
import com.example.sunnyweather.util.CommonLinkItem

/**
 * 我的账户-布局
 */
class MineAccountInfoMarqueeView(context: Context) : LinearLayout(context) {

    private val binding = inflate<ViewMineAccountInfoMarqueeBinding>()

    fun setData(data: CompoundAdItem) {
        binding.run {
            data.run {
                ivArrow.setImageResource(R.drawable.ic_mine_account_arrow)
                tvTitle.setContent(title, "")
                tvSubTitle.setContent(subtitle, "")
                if (linkType.isEmptyOrNull()) {
                    ivArrow.visibility = View.GONE
                } else {
                    ivArrow.visibility = View.VISIBLE
                }
                setOnClickListener {
                    CommonLinkItem.goTarget(link, linkType, context)
                }
            }
        }
    }
}