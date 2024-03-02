package com.ct.client.database.repository

/**
 * 消息数据库操作
 */
/*
object MessageInfoRepository {

    */
/**
     * 保存消息
     * 根据threeLevelMsgId不存在才插入，否则更新除了已读和置顶的所有字段
     *//*

    fun save(msgList: MutableList<MessageInfo>){
        msgList.forEachIndexed { index, messageInfo ->
            messageInfo.phoneNumber = KUtilData.getPhoneNumber()
            messageInfo.isTakeDown = Constants.DELETE_STATE.EXIST
            messageInfo.isRead = MessageInfo.ReadFlag.NO
            val data = AppRoomDataBase.getDatabase().messageInfoDao().getThreeLevelMsg(messageInfo.threeLevelMsgId, messageInfo.phoneNumber)
            if (data.isEmpty()){
                messageInfo.isDelete = MessageInfo.DeleteFlag.NO
                if (!messageInfo.threeLevelMsgId.isEmptyOrNull()){
                    AppRoomDataBase.getDatabase().messageInfoDao().insert(messageInfo)
                }
            }else{
                AppRoomDataBase.getDatabase().messageInfoDao().update(messageInfo.oneLevelMsgId, messageInfo.twoLevelMsgId, messageInfo.threeLevelMsgId, messageInfo.failureTime, messageInfo.phoneNumber)
            }
        }
    }

    private fun insertDeleteData(threeLevelMsgId: String) {
        val messageInfo = MessageInfo()
        messageInfo.phoneNumber = KUtilData.getPhoneNumber()
        messageInfo.isDelete = MessageInfo.DeleteFlag.YES
        messageInfo.threeLevelMsgId = threeLevelMsgId
        if (!messageInfo.threeLevelMsgId.isEmptyOrNull()){
            AppRoomDataBase.getDatabase().messageInfoDao().insert(messageInfo)
        }
    }

    */
/**
     * 获取所有被删除的消息
     *//*

    fun getDeleteMsgList():List<MessageInfo>{
        return AppRoomDataBase.getDatabase().messageInfoDao().getDeleteMsgList(KUtilData.getPhoneNumber())
    }

    */
/**
     * 获取所有未被删除的消息
     *//*

    fun getNoDeleteMsgList():List<MessageInfo>{
        return AppRoomDataBase.getDatabase().messageInfoDao().getNoDeleteMsgList(KUtilData.getPhoneNumber())
    }

    */
/**
     * 获取所有未被删除的二级消息下的三级消息
     *//*

    private fun getNoDeleteThirdMsgList(twoLevelMsgId: String):List<MessageInfo>{
        return AppRoomDataBase.getDatabase().messageInfoDao().getNoDeleteThirdMsgList(twoLevelMsgId, KUtilData.getPhoneNumber())
    }

    */
/**
     * 获取所有未读消息数量
     *//*

    fun getUnReadMsg():Int{
        return AppRoomDataBase.getDatabase().messageInfoDao().getUnReadMsg(KUtilData.getPhoneNumber()).size
    }

    */
/**
     * 获取所有一级未读消息
     *//*

    fun getOneLevelUnReadMsg(oneLevelMsgId: String):Int{
        return AppRoomDataBase.getDatabase().messageInfoDao().getOneLevelUnReadMsg(oneLevelMsgId, KUtilData.getPhoneNumber()).size
    }

    */
/**
     * 获取所有二级未读消息
     *//*

    fun getTwoLevelUnReadMsg(twoLevelMsgId: String):Int{
        return AppRoomDataBase.getDatabase().messageInfoDao().getTwoLevelUnReadMsg(twoLevelMsgId,KUtilData.getPhoneNumber()).size
    }

    */
/**
     * 获取所有三级未读消息
     *//*

    fun getThreeLevelUnReadMsg(threeLevelMsgId: String):Int{
        return AppRoomDataBase.getDatabase().messageInfoDao().getThreeLevelUnReadMsg(threeLevelMsgId, KUtilData.getPhoneNumber()).size
    }


    */
/**
     * 下架所有消息
     *//*

    fun takeDownAll(){
        AppRoomDataBase.getDatabase().messageInfoDao().updateTakeDown(KUtilData.getPhoneNumber())
    }

    */
/**
     * 删除所有消息
     *//*

    fun deleteAll(){
        AppRoomDataBase.getDatabase().messageInfoDao().deleteAll(KUtilData.getPhoneNumber())
    }

    */
/**
     * 删除所有二级消息
     *//*

    fun deleteTwo(twoLevelMsgId: String, threeLevelMsgId:String){
        val data = AppRoomDataBase.getDatabase().messageInfoDao().getThreeLevelMsg(threeLevelMsgId, KUtilData.getPhoneNumber())
        if (data.isEmpty()){
            insertDeleteData(threeLevelMsgId)
        }else{
            AppRoomDataBase.getDatabase().messageInfoDao().deleteTwo(twoLevelMsgId, KUtilData.getPhoneNumber())
        }
    }

    */
/**
     * 删除所有三级消息
     *//*

    fun deleteThree(threeLevelMsgId: String){
        val data = AppRoomDataBase.getDatabase().messageInfoDao().getThreeLevelMsg(threeLevelMsgId, KUtilData.getPhoneNumber())
        if (data.isEmpty()){
            insertDeleteData(threeLevelMsgId)
        }else{
            AppRoomDataBase.getDatabase().messageInfoDao().deleteThree(threeLevelMsgId, KUtilData.getPhoneNumber())
        }
    }

    */
/**
     * 更新全部消息置为已读
     *//*

    fun updateAllRead(){
        AppRoomDataBase.getDatabase().messageInfoDao().updateAllRead(KUtilData.getPhoneNumber())
    }

    */
/**
     * 更新一级消息置为已读
     *//*

    fun updateOneLevelRead(oneLevelMsgId: String){
        AppRoomDataBase.getDatabase().messageInfoDao().updateOneLevelRead(oneLevelMsgId, KUtilData.getPhoneNumber())
    }

    */
/**
     * 更新二级消息置为已读
     *//*

    fun updateTwoLevelRead(twoLevelMsgId: String){
        AppRoomDataBase.getDatabase().messageInfoDao().updateTwoLevelRead(twoLevelMsgId, KUtilData.getPhoneNumber())
    }

    */
/**
     * 更新三级消息置为已读
     *//*

    fun updateThreeLevelRead(threeLevelMsgId: String){
        AppRoomDataBase.getDatabase().messageInfoDao().updateThreeLevelRead(threeLevelMsgId, KUtilData.getPhoneNumber())
    }

}*/
