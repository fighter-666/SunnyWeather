package com.example.sunnyweather.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.sunnyweather.R
import com.example.sunnyweather.databinding.DialogMyInformationSignatureBinding
import com.example.sunnyweather.util.MyToastD
import com.example.sunnyweather.util.UtilText
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * 说明：我的信息-个性签名弹窗
 *
 */
class MyInformationSignatureDialog(context: Context) : BottomSheetDialog(context, R.style.SheetDialog) {

    private val binding: DialogMyInformationSignatureBinding // 绑定视图布局
    private var mList = ArrayList<String>() // 存储签名列表
    private var mCallback: Callback? = null // 回调接口

    // 显示对话框并设置签名
    fun onShow(electronicSignature: String) {
        UtilText.setText(binding.etSignature, electronicSignature, "")
        show()
    }

    // 重写show方法，展开BottomSheet
    /*这段代码是一个自定义的 show() 方法，用于在显示 BottomSheetDialog 时将其状态设置为展开状态。
    解释如下：
    show() 方法是继承自父类的方法，在调用 super.show() 之前，我们可以添加额外的逻辑进行处理。
    super.show() 是调用父类的 show() 方法，用于显示 BottomSheetDialog。
    behavior 是 BottomSheetDialog 的底部行为（Behavior）对象，通过该对象可以控制 BottomSheetDialog 的展开和折叠状态。
    behavior.state = BottomSheetBehavior.STATE_EXPANDED 将底部行为的状态设置为展开状态。
    STATE_EXPANDED 是 BottomSheetBehavior 类中定义的常量，表示展开状态。
    总体来说，这段代码在显示 BottomSheetDialog 时，先调用父类的 show() 方法显示对话框，然后将 BottomSheetDialog
    的底部行为状态设置为展开，使其以展开的形式显示在屏幕上。这样做可以保证 BottomSheetDialog 在显示时处于展开状态，而不是默认的折叠状态。
    */
    override fun show() {
        super.show()
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    // 初始化
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_my_information_signature, null)
        setContentView(view)
        binding = DialogMyInformationSignatureBinding.bind(view)
        initView()
    }

    // 初始化视图
    fun initView() {
        binding.run {
            llBase.layoutParams.run {
                height = (1080 * 435 / 720f).toInt() // 设置高度
            }
            etSignature.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                // 文本变化后的处理
                override fun afterTextChanged(s: Editable?) {
                    val str = etSignature.text.toString()
                    if (!UtilText.isEmptyOrNullString(str)) {
                        val count = 18 - str.length // 计算剩余可输入字符数
                        if (count < 0) {
                            MyToastD.show("最多输入18个文字哦~")
                            etSignature.setText(str.substring(0, 18))
                            etSignature.setSelection(18)
                            return
                        }
                        tvCount.text = count.toString() // 更新剩余字符数
                        tvConfirm.alpha = 1f // 设置确认按钮可见
                        tvConfirm.isEnabled = true // 启用确认按钮
                        match(str) // 匹配签名
                    } else {
                        tvCount.text = "18"
                        tvConfirm.alpha = 0.5f // 设置确认按钮半透明
                        tvConfirm.isEnabled = false // 禁用确认按钮
                    }
                }

            })
            tvConfirm.setOnClickListener {
                confirm(etSignature.text.toString()) // 点击确认
            }
        }
    }

    // 设置数据
    fun setData(list: List<String>) {
        mList = list as ArrayList<String>
        if(mList.isNullOrEmpty()) {
            binding.tvSignatureTitle.visibility = View.GONE
        } else {
            binding.tvSignatureTitle.visibility = View.VISIBLE
            binding.rvSignature.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvSignature.adapter = SignatureAdapter(list)
        }
    }

    // 匹配签名
    fun match(string: String) {
        val index = mList.indexOf(string)
        binding.rvSignature.adapter?.run {
            if (this is SignatureAdapter) {
                currentSelectIndex = index
                notifyDataSetChanged()
            }
        }
    }

    // 确认选择的签名
    fun confirm(electronicSignature: String) {
        dismiss()
        mCallback?.onSuccess(electronicSignature)
    }

    // 签名适配器
    private inner class SignatureAdapter(list: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_my_information_signature, list) {

        var currentSelectIndex = -1 // 当前选中的索引

        override fun convert(helper: BaseViewHolder, item: String) {
            val position = data.indexOf(item)
            val tvSignature: TextView = helper.getView(R.id.tv_signature)
            UtilText.setText(tvSignature, item, "")

            // 根据是否选中来设置文本颜色和背景
            if (position == currentSelectIndex) {
                tvSignature.setTextColor(context.resources.getColor(R.color.blue_5b82ff))
                tvSignature.background = context.resources.getDrawable(R.drawable.bg_white_corner_14dp_storke_5b82ff)
            } else {
                tvSignature.setTextColor(context.resources.getColor(R.color.black_333333))
                tvSignature.background = context.resources.getDrawable(R.drawable.bg_eeeeee_corner_14dp)
            }

            // 点击事件，选择签名
            helper.itemView.setOnClickListener {
                binding.etSignature.setText(item)
                if (item.length < 19) {
                    binding.etSignature.setSelection(item.length)
                }
            }
        }

    }

    // 设置回调接口
    fun setCallback(callback: Callback) {
        mCallback = callback
    }

    // 回调接口定义
    interface Callback{
        fun onSuccess(string: String) // 成功回调
    }
}