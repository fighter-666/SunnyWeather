package com.example.sunnyweather.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.sunnyweather.R
import com.example.sunnyweather.data.Piggy

class ComponentsAdapter(@LayoutRes layoutResId: Int, data: MutableList<Piggy>) :
    BaseQuickAdapter<Piggy, BaseViewHolder>(layoutResId, data) {
    //一个可为空的函数类型变量，用于保存点击事件的监听器
    private var onItemClickListener: ((Piggy) -> Unit)? = null

    override fun convert(holder: BaseViewHolder, item: Piggy) {
        holder.setImageResource(R.id.ivComponentsAdapter, item.image)
        holder.setText(R.id.tvComponentsAdapter, item.name)
    }
}