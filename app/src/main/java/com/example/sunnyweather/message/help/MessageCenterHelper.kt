package com.example.sunnyweather.message.help

import android.content.Context
import android.util.Log
import com.ct.base.common.Constants.RedDotTabName
import com.ct.base.common.Log
import com.ct.base.common.Variable
import com.ct.base.helper.RedDotHelper
import com.ct.base.util.Setting
import com.ct.client.communication2.response.MarkReadMsgResponse
import com.ct.client.communication2.task.MarkReadMsgTask
import com.ct.client.database.repository.MessageInfoRepository
import com.ct.client.widget.DynamicMessagesView
import com.ct.net.communication.task.OnTaskFinished
import com.ct.net.communication2.response.DeleteMessageResponse
import com.ct.net.communication2.response.UpdateMessageTopResponse
import com.ct.net.communication2.response.bean.responseData.data.QueryMessageChannelData
import com.ct.net.communication2.task.DeleteMessageTask
import com.ct.net.communication2.task.UpdateMessageOpenTask
import com.ct.net.communication2.task.UpdateMessageTopTask
import com.ct.net.util.UtilToast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * 消息中心列表操作
 * 1. 已读
 * 2. 删除
 * 3. 置顶
 * 4. 分类开关
 */
class MessageCenterHelper {

    // 消息已读
    lateinit var onMessageRead: () -> Unit

    /**
     * 最新服务设为已读
     */
    fun setServiceMsgRead(mContext: Context?, msgListBean: QueryMessageChannelData.MsgListBean):MessageCenterHelper {
        // 不在缓存调已读接口
        if (MessageInfoRepository.getThreeLevelUnReadMsg(msgListBean.msgId) > 0){
            markReadMsg(mContext, MarkReadMsgTask.MARK_READ_FLAG.THREELEVELMSG, threeLevelMsgId = msgListBean.msgId)
            Log.i("setServiceMsgRead", "最新第三条设为已读")
        }else{
            Log.i("setServiceMsgRead", "最新第三条已读或者不在缓存")
        }
        return this
    }

    /**
     * 服务列表设为已读
     */
    fun setServiceMsgRead(mContext: Context?, serviceMessageList: MutableList<QueryMessageChannelData.MsgListBean>):MessageCenterHelper {
        serviceMessageList.forEachIndexed { index, msgListBean ->
            if (MessageInfoRepository.getThreeLevelUnReadMsg(msgListBean.msgId) > 0){
                markReadMsg(mContext, MarkReadMsgTask.MARK_READ_FLAG.THREELEVELMSG, threeLevelMsgId = msgListBean.msgId)
                Log.i("setServiceMsgRead", "最新第${index}条设为已读")
            }else{
                Log.i("setServiceMsgRead", "最新第${index}条已读或者不在缓存")
            }
        }
        return this
    }

    /**
     * 8.3.0  设置消息已读接口
     * 清除未读需要加载中
     */
    fun markReadMsg(
        mContext: Context?,
        markReadFlag: String,
        oneLevelMsgId: String = "",
        twoLevelMsgId: String = "",
        threeLevelMsgId: String = "",
        isLoading: Boolean = false,
    ) :MessageCenterHelper{
        val task = MarkReadMsgTask(mContext)
        task.setMarkReadFlag(markReadFlag)
        task.setOneLevelMsgIds(oneLevelMsgId)
        task.setTwoLevelMsgId(twoLevelMsgId)
        task.setThreeLevelMsgId(threeLevelMsgId)
        task.setProgressVisiable(isLoading)
        task.setOnTaskFinished(object : OnTaskFinished {
            override fun onSucc(obj: Any) {
                if (mContext == null) {
                    return
                }
                val response = obj as MarkReadMsgResponse
                // 本地缓存已读
                when(markReadFlag){
                    MarkReadMsgTask.MARK_READ_FLAG.ALLMSG->{
                        MessageInfoRepository.updateAllRead()
                    }
                    MarkReadMsgTask.MARK_READ_FLAG.ONELEVELMSG->{
                        MessageInfoRepository.updateOneLevelRead(oneLevelMsgId)
                    }
                    MarkReadMsgTask.MARK_READ_FLAG.TWOLEVELMSG->{
                        MessageInfoRepository.updateTwoLevelRead(twoLevelMsgId)
                    }
                    MarkReadMsgTask.MARK_READ_FLAG.THREELEVELMSG->{
                        MessageInfoRepository.updateThreeLevelRead(threeLevelMsgId)
                    }
                }
                if (::onMessageRead.isInitialized){
                    onMessageRead()
                }
            }

            override fun onFail(obj: Any) {
                if (isLoading){
                    UtilToast.showTaskFailToast(mContext, obj)
                }
            }
        })
        task.execute()

        return this
    }

    // 消息删除
    lateinit var onMessageDelete: () -> Unit

    /**
     * 11.1.0 删除消息接口
     */
    fun deleteMessage(
        mContext: Context?,
        deleteFlag: String,
        twoLevelMsgId: String = "",
        threeLevelMsgId: String = "",
    ) :MessageCenterHelper{
        val task = DeleteMessageTask(mContext)
        task.setTwoLevelMsgId(twoLevelMsgId)
        task.setThreeLevelMsgId(threeLevelMsgId)
        task.setDeleteFlag(deleteFlag)
        task.setProgressVisiable(true)
        task.setOnTaskFinished(object : OnTaskFinished {
            override fun onSucc(obj: Any) {
                if (mContext == null) {
                    return
                }
                val response = obj as DeleteMessageResponse
                // 本地缓存删除
                when(deleteFlag){
                    DeleteMessageTask.DELETE_FLAG.ALLMSG->{
                        MessageInfoRepository.deleteAll()
                        UtilToast.show("清除成功")

                        DynamicMessagesView.mScreenCaptureBean?.run {
                            MessageInfoRepository.deleteThree(msgId)
                        }
                        DynamicMessagesView.mPRANBean?.run {
                            MessageInfoRepository.deleteThree(msgId)
                        }
                    }
                    DeleteMessageTask.DELETE_FLAG.TWOLEVELMSG->{
                        MessageInfoRepository.deleteTwo(twoLevelMsgId, threeLevelMsgId)
                    }
                    DeleteMessageTask.DELETE_FLAG.THREELEVELMSG->{
                        MessageInfoRepository.deleteThree(threeLevelMsgId)
                    }
                }
                if (::onMessageDelete.isInitialized){
                    onMessageDelete()
                }

                // 通知首页动态消息刷新
                RedDotHelper.getInstance().onRedDotListener(RedDotTabName.REFRESH_HOME_PAGE_MSG)
            }

            override fun onFail(obj: Any) {
                if (mContext == null) {
                    return
                }
                UtilToast.showTaskFailToast(mContext, obj)
            }
        })
        task.execute()

        return this
    }

    // 消息置顶
    lateinit var onMessageTop: () -> Unit

    /**
     * 11.1.0 消息置顶
     */
    fun setMessageTop(mContext: Context?, msgId: String) :MessageCenterHelper{
        val categoryTopList = getAllTopMarketingMsgIdList()
        categoryTopList.add(msgId)
        val jsonStr = Gson().toJson(categoryTopList)

        val task = UpdateMessageTopTask(mContext)
        task.setTopList(jsonStr)
        task.setProgressVisiable(true)
        task.setOnTaskFinished(object : OnTaskFinished {
            override fun onSucc(obj: Any) {
                if (mContext == null) {
                    return
                }
                val response = obj as UpdateMessageTopResponse
                Setting.setStringByNbr(Setting.KEY_TOP_MARKETING_MESSAGE,jsonStr)
                if (::onMessageTop.isInitialized){
                    onMessageTop()
                }
            }

            override fun onFail(obj: Any) {
                if (mContext == null) {
                    return
                }
                UtilToast.showTaskFailToast(mContext, obj)
            }
        })
        task.execute()

        return this
    }

    /**
     * 11.1.0 消息取消置顶
     */
    fun setMessageCancelTop(mContext: Context?, msgId: String) :MessageCenterHelper{
        val categoryTopList = getAllTopMarketingMsgIdList()
        categoryTopList.remove(msgId)
        val jsonStr = Gson().toJson(categoryTopList)

        val task = UpdateMessageTopTask(mContext)
        task.setTopList(jsonStr)
        task.setProgressVisiable(true)
        task.setOnTaskFinished(object : OnTaskFinished {
            override fun onSucc(obj: Any) {
                if (mContext == null) {
                    return
                }
                val response = obj as UpdateMessageTopResponse
                Setting.setStringByNbr(Setting.KEY_TOP_MARKETING_MESSAGE,jsonStr)
                if (::onMessageTop.isInitialized){
                    onMessageTop()
                }
            }

            override fun onFail(obj: Any) {
                if (mContext == null) {
                    return
                }
                UtilToast.showTaskFailToast(mContext, obj)
            }
        })
        task.execute()

        return this
    }

    /**
     * 营销消息置顶-获取被置顶的消息id列表
     */
    fun getAllTopMarketingMsgIdList(): MutableList<String> {
        val strIds = Setting.getStringByNbr(Setting.KEY_TOP_MARKETING_MESSAGE)
        if (strIds == "") {
            return mutableListOf()
        } else {
            return Gson().fromJson(strIds, object : TypeToken<List<String>>() {}.type)
        }
    }

    /**
     * 营销消息置顶-获取被置顶的消息id的json串
     */
    fun getAllTopMarketingMsgIds(): String {
        return Setting.getStringByNbr(Setting.KEY_TOP_MARKETING_MESSAGE)
    }

    // 消息开关失败
    lateinit var onUpdateMessageOpenFail: () -> Unit

    /**
     * 11.1.0 消息分类开关打开
     */
    fun updateMessageOpen(mContext: Context?, twoLevelMsgId: String) :MessageCenterHelper{
        val categorySwitchList = getAllCloseCategoryMsgIdList()
        categorySwitchList.remove(twoLevelMsgId)
        val jsonStr = Gson().toJson(categorySwitchList)
        Setting.setStringByNbr(Setting.KEY_CLOSE_CATEGORY_MESSAGE,jsonStr)

        val task = UpdateMessageOpenTask(mContext)
        task.setCloseList(jsonStr)
        task.setOnTaskFinished(object : OnTaskFinished {
            override fun onSucc(obj: Any) {
                if (mContext == null) {
                    return
                }
                Variable.isNeedRefreshMsg = true
            }

            override fun onFail(obj: Any) {
                if (mContext == null) {
                    return
                }
                UtilToast.showTaskFailToast(mContext, obj)
                if (::onUpdateMessageOpenFail.isInitialized){
                    onUpdateMessageOpenFail()
                }
            }
        })
        task.execute()

        return this
    }

    /**
     * 11.1.0 消息分类开关关闭
     */
    fun updateMessageClose(mContext: Context?, twoLevelMsgId: String):MessageCenterHelper {
        val categorySwitchList = getAllCloseCategoryMsgIdList()
        categorySwitchList.add(twoLevelMsgId)
        val jsonStr = Gson().toJson(categorySwitchList)
        Setting.setStringByNbr(Setting.KEY_CLOSE_CATEGORY_MESSAGE,jsonStr)

        val task = UpdateMessageOpenTask(mContext)
        task.setCloseList(jsonStr)
        task.setOnTaskFinished(object : OnTaskFinished {
            override fun onSucc(obj: Any) {
                if (mContext == null) {
                    return
                }
                Variable.isNeedRefreshMsg = true
            }

            override fun onFail(obj: Any) {
                if (mContext == null) {
                    return
                }
                UtilToast.showTaskFailToast(mContext, obj)
                if (::onUpdateMessageOpenFail.isInitialized){
                    onUpdateMessageOpenFail()
                }
            }
        })
        task.execute()
        return this
    }

    /**
     * 消息分类开关-获取被关闭的消息分类id列表
     */
    fun getAllCloseCategoryMsgIdList(): MutableList<String> {
        val strIds = Setting.getStringByNbr(Setting.KEY_CLOSE_CATEGORY_MESSAGE)
        if (strIds == "") {
            return mutableListOf()
        } else {
            return Gson().fromJson(strIds, object : TypeToken<List<String>>() {}.type)
        }
    }

    /**
     * 消息分类开关-获取被关闭的消息分类id的json串
     */
    fun getAllCloseCategoryMsgIds(): String {
        return Setting.getStringByNbr(Setting.KEY_CLOSE_CATEGORY_MESSAGE)
    }
}