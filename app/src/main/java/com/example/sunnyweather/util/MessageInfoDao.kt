package com.ct.client.database.dao

import androidx.room.*
import com.ct.net.model.MessageInfo

@Dao
interface MessageInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg bean: MessageInfo): List<Long>

    // 获取所有被删除消息
    @Query("SELECT * FROM MessageInfo WHERE isDelete='1' and phoneNumber=:phoneNumber")
    fun getDeleteMsgList(phoneNumber: String): List<MessageInfo>

    // 获取所有未被删除消息
    @Query("SELECT * FROM MessageInfo WHERE isDelete='0' and phoneNumber=:phoneNumber")
    fun getNoDeleteMsgList(phoneNumber: String): List<MessageInfo>

    // 获取所有未被删除三级消息
    @Query("SELECT * FROM MessageInfo WHERE isDelete='0' and twoLevelMsgId=:twoLevelMsgId and phoneNumber=:phoneNumber")
    fun getNoDeleteThirdMsgList(twoLevelMsgId: String, phoneNumber: String): List<MessageInfo>

    // 获取所有未读消息
    @Query("SELECT * FROM MessageInfo WHERE isRead='0' and isTakeDown='0' and isDelete='0' and phoneNumber=:phoneNumber")
    fun getUnReadMsg(phoneNumber: String): List<MessageInfo>

    // 获取所有一级未读消息
    @Query("SELECT * FROM MessageInfo WHERE oneLevelMsgId=:oneLevelMsgId and isRead='0' and isTakeDown='0' and isDelete='0' and phoneNumber=:phoneNumber")
    fun getOneLevelUnReadMsg(oneLevelMsgId: String, phoneNumber: String): List<MessageInfo>

    // 获取所有二级未读消息
    @Query("SELECT * FROM MessageInfo WHERE twoLevelMsgId=:twoLevelMsgId and isRead='0' and isTakeDown='0' and isDelete='0' and phoneNumber=:phoneNumber")
    fun getTwoLevelUnReadMsg(twoLevelMsgId: String, phoneNumber: String): List<MessageInfo>

    // 获取所有三级未读消息
    @Query("SELECT * FROM MessageInfo WHERE threeLevelMsgId=:threeLevelMsgId and isRead='0' and isTakeDown='0' and isDelete='0' and phoneNumber=:phoneNumber")
    fun getThreeLevelUnReadMsg(threeLevelMsgId: String, phoneNumber: String): List<MessageInfo>

    // 获取所有三级消息
    @Query("SELECT * FROM MessageInfo WHERE threeLevelMsgId=:threeLevelMsgId and phoneNumber=:phoneNumber")
    fun getThreeLevelMsg(threeLevelMsgId: String, phoneNumber: String): List<MessageInfo>

    // 所有消息下架
    @Query("UPDATE MessageInfo SET isTakeDown='1' WHERE phoneNumber=:phoneNumber")
    fun updateTakeDown(phoneNumber: String): Int

    // 所有消息删除
    @Query("UPDATE MessageInfo SET isDelete='1' WHERE phoneNumber=:phoneNumber")
    fun deleteAll(phoneNumber: String): Int

    // 所有二级消息删除
    @Query("UPDATE MessageInfo SET isDelete='1' WHERE twoLevelMsgId=:twoLevelMsgId and phoneNumber=:phoneNumber")
    fun deleteTwo(twoLevelMsgId:String, phoneNumber: String): Int

    // 所有三级消息删除
    @Query("UPDATE MessageInfo SET isDelete='1' WHERE threeLevelMsgId=:threeLevelMsgId and phoneNumber=:phoneNumber")
    fun deleteThree(threeLevelMsgId:String, phoneNumber: String): Int

    // 更新除了已读的所有字段, 并上架
    @Query("UPDATE MessageInfo SET isTakeDown='0', oneLevelMsgId=:oneLevelMsgId , twoLevelMsgId=:twoLevelMsgId , failureTime=:failureTime WHERE phoneNumber=:phoneNumber and threeLevelMsgId=:threeLevelMsgId")
    fun update(oneLevelMsgId:String, twoLevelMsgId:String, threeLevelMsgId:String, failureTime: String?, phoneNumber: String): Int

    // 更新消息为已读
    @Query("UPDATE MessageInfo SET isRead='1' WHERE phoneNumber=:phoneNumber and isTakeDown='0'")
    fun updateAllRead(phoneNumber: String): Int

    // 更新一级消息为已读
    @Query("UPDATE MessageInfo SET isRead='1' WHERE phoneNumber=:phoneNumber and oneLevelMsgId=:oneLevelMsgId and isTakeDown='0'")
    fun updateOneLevelRead(oneLevelMsgId:String, phoneNumber: String): Int

    // 更新二级消息为已读
    @Query("UPDATE MessageInfo SET isRead='1' WHERE phoneNumber=:phoneNumber and twoLevelMsgId=:twoLevelMsgId and isTakeDown='0'")
    fun updateTwoLevelRead(twoLevelMsgId:String, phoneNumber: String): Int

    // 更新三级消息为已读
    @Query("UPDATE MessageInfo SET isRead='1' WHERE phoneNumber=:phoneNumber and threeLevelMsgId=:threeLevelMsgId and isTakeDown='0'")
    fun updateThreeLevelRead(threeLevelMsgId:String, phoneNumber: String): Int
}