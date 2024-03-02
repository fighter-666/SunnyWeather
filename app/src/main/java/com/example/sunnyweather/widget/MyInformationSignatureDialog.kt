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
import com.ct.base.ext.dp
import com.example.sunnyweather.R
import com.example.sunnyweather.databinding.DialogMyInformationSignatureBinding
import com.example.sunnyweather.util.GetScreenUtils
import com.example.sunnyweather.util.MyToastD
import com.example.sunnyweather.util.UtilText
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * 说明：我的信息-个性签名弹窗
 *
 * @作者 luohao
 * @创建时间 2024/2/21 09:52
 * @版本 11.2.0
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
class MyInformationSignatureDialog(context: Context) : BottomSheetDialog(context, R.style.SheetDialog) {


    private val binding: DialogMyInformationSignatureBinding
    private var mList = ArrayList<String>()
    private var mCallback: Callback? = null

    fun onShow(electronicSignature: String) {
        UtilText.setText(binding.etSignature, electronicSignature, "")
        show()
    }

    override fun show() {
        super.show()
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_my_information_signature, null)
        setContentView(view)
        binding = DialogMyInformationSignatureBinding.bind(view)
        initView()
    }

    fun initView() {
        binding.run {
            llBase.layoutParams.run {
                height = (1080 * 435 / 720f).toInt()
            }
            etSignature.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    val str = etSignature.text.toString()
                    if (!UtilText.isEmptyOrNullString(str)) {
                        val count = 18 - str.length
                        if (count < 0) {
                            MyToastD.show("最多输入18个文字哦~")
                            etSignature.setText(str.substring(0, 18))
                            etSignature.setSelection(18)
                            return
                        }
                        tvCount.text = count.toString()
                        tvConfirm.alpha = 1f
                        tvConfirm.isEnabled = true
                        match(str)
                    } else {
                        tvCount.text = "18"
                        tvConfirm.alpha = 0.5f
                        tvConfirm.isEnabled = false
                    }
                }

            })
            tvConfirm.setOnClickListener {
                confirm(etSignature.text.toString())
            }
        }
    }

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

    fun match(string: String) {
        val index = mList.indexOf(string)
        binding.rvSignature.adapter?.run {
            if (this is SignatureAdapter) {
                currentSelectIndex = index
                notifyDataSetChanged()
            }
        }
    }

    fun confirm(electronicSignature: String) {
        dismiss()
        mCallback?.onSuccess(electronicSignature)
        /*val task = SetElectronicSignatureTask(context)
        task.setElectronicSignature(electronicSignature)
        task.setOnTaskFinished(object : OnTaskFinished {
            override fun onSucc(obj: Any?) {
                dismiss()
                mCallback?.onSuccess(electronicSignature)
            }

            override fun onFail(obj: Any?) {
                UtilToast.showTaskFailToast(context, obj)
            }

        })
        task.execute()*/
    }

    private inner class SignatureAdapter(list: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_my_information_signature, list) {

        var currentSelectIndex = -1

        override fun convert(helper: BaseViewHolder, item: String) {
            val position = data.indexOf(item)
            val tvSignature: TextView = helper.getView(R.id.tv_signature)
            UtilText.setText(tvSignature, item, "")

            if (position == currentSelectIndex) {
                tvSignature.setTextColor(context.resources.getColor(R.color.blue_5b82ff))
                tvSignature.background = context.resources.getDrawable(R.drawable.bg_white_corner_14dp_storke_5b82ff)
            } else {
                tvSignature.setTextColor(context.resources.getColor(R.color.black_333333))
                tvSignature.background = context.resources.getDrawable(R.drawable.bg_eeeeee_corner_14dp)
            }

            helper.itemView.setOnClickListener {
                binding.etSignature.setText(item)
                if (item.length < 19) {
                    binding.etSignature.setSelection(item.length)
                }
            }
        }

    }

    fun setCallback(callback: Callback) {
        mCallback = callback
    }

    interface Callback{
        fun onSuccess(string: String)
    }
}