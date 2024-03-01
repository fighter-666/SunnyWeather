package com.example.sunnyweather.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.sunnyweather.base.binding.inflate
import com.example.sunnyweather.data.CompoundAdItem
import com.example.sunnyweather.databinding.ViewMineAccountInfoBinding

/**
 * @说明: 我的账户-消息条
 */
class MineAccountInfoView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding = inflate<ViewMineAccountInfoBinding>()
    private var marqueeFactory: MineAccountInfoMF<*>
    private var mList = ArrayList<CompoundAdItem>()

    init {
        marqueeFactory = MineAccountInfoMF<Any?>(context)
        binding.marqueeView.setMarqueeFactory(marqueeFactory)
    }

    fun getData(): ArrayList<CompoundAdItem> {
        return mList
    }

    fun setData(mData: List<CompoundAdItem>) {
        mList.clear()
        mList.addAll(mData)
        marqueeFactory.data = mList
        if (mList.isNotEmpty()) {
            visibility = View.VISIBLE
            if (mList.size > 1) {
                binding.marqueeView.startFlipping()
            } else {
                binding.marqueeView.stopFlipping()
            }
        } else {
            visibility = View.GONE
        }
    }

    fun startFlipping() {
        if (mList.size > 1) {
            binding.marqueeView.startFlipping()
        }
    }

    fun stopFlipping() {
        binding.marqueeView.stopFlipping()
    }

}