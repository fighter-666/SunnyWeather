package com.example.sunnyweather.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sunnyweather.base.binding.inflate
import com.example.sunnyweather.databinding.MessageBottomTipBinding

/**
 * 新版消息底部提示语
 */
class MessageBottomTip(content: Context, attrs: AttributeSet) : ConstraintLayout(content, attrs) {
    private val binding = inflate<MessageBottomTipBinding>()

    init {
        initListener()
    }

    private fun initListener() {
       /* binding.run {
            if (!tvBottomTip.text.isNullOrEmpty()){
                tvBottomTip.visibility = View.VISIBLE
                tvLeft.visibility = View.VISIBLE
                tvRight.visibility = View.VISIBLE
            }else{
                tvBottomTip.visibility = View.GONE
                tvLeft.visibility = View.GONE
                tvRight.visibility = View.GONE
            }
        }*/
    }

    fun setData(title: String) {
        binding.tvBottomTip.text = title
    }

}