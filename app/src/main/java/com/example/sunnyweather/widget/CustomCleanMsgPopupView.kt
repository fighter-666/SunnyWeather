package com.example.sunnyweather.widget

import android.content.Context
import com.example.sunnyweather.R
import com.example.sunnyweather.data.QueryMessageChannelData
import com.example.sunnyweather.databinding.DialogCleanMsgRecordBinding
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.BottomPopupView

/*
*说明：底部确认删除消息记录弹窗-营销消息
*
*/class CustomCleanMsgPopupView(context: Context) : BottomPopupView(context) {
    lateinit var binding: DialogCleanMsgRecordBinding
    lateinit var onMessageDelete: () -> Unit
    private var mItem = QueryMessageChannelData.MarketingMessageListBean()

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_clean_msg_record
    }

    override fun onCreate() {
        super.onCreate()
        binding = DialogCleanMsgRecordBinding.bind(popupImplView)
        initView()
        initListener()
    }

    private fun initView() {
        binding.run {
            tvTitle.text = "删除后，将清空${mItem.title}消息，并直到有新消息后才展示"
        }
    }

    private fun initListener() {
        binding.run {
            tvCancelDelete.setOnClickListener {
                dismiss()
            }
            tvConfirmDelete.setOnClickListener {
                if (::onMessageDelete.isInitialized){
                    onMessageDelete()
                }
                dismiss()
                /*mItem.run {
                    val mMessageCenterHelper = MessageCenterHelper()
                    if (msgType == QueryMessageChannelData.MarketingMessageListBean.MSG_TYPE.JUMP) {
                        mMessageCenterHelper.deleteMessage(
                            context, DeleteMessageTask.DELETE_FLAG.THREELEVELMSG,
                            threeLevelMsgId = threeLevelMsgId
                        ).onMessageDelete = {
                            if (::onMessageDelete.isInitialized){
                                onMessageDelete()
                            }
                            dismiss()
                        }
                    } else {
                        mMessageCenterHelper.deleteMessage(
                            context, DeleteMessageTask.DELETE_FLAG.TWOLEVELMSG,
                            twoLevelMsgId = twoLevelMsgId,
                            threeLevelMsgId = threeLevelMsgId
                        ).onMessageDelete = {
                            if (::onMessageDelete.isInitialized){
                                onMessageDelete()
                            }
                            dismiss()
                        }
                    }
                }*/
            }
        }
    }

    fun setData(item: QueryMessageChannelData.MarketingMessageListBean): CustomCleanMsgPopupView {
        mItem = item
        return this
    }

}