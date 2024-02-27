package com.example.sunnyweather.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * 消息
 * 根据threeLevelMsgId和phoneNumber新增数据，确保唯一性
 */
@Entity(indices = [Index(value = ["threeLevelMsgId","phoneNumber"], unique = true)] )
class MessageInfo(
    // 使用@PrimaryKey注解将它设为了主键，再把autoGenerate参数指定成true，使得主键的值是自动生成的
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var oneLevelMsgId : String = "", // 一级消息id
    var twoLevelMsgId : String = "", // 二级消息id
    var threeLevelMsgId : String = "", // 三级消息id (具有唯一性)
    var isRead: String ?= null,  // 是否已读 0.未读 1.已读
    var isTakeDown: String = "", // 下架标志 0.已上架 1.已下架
    var isDelete: String ?= null, // 删除标志 0.未删除 1.已删除
    var failureTime: String ?= null, // 失效时间 string
    var phoneNumber: String = "" // 手机号
): Serializable{
    @Ignore
    constructor() : this(0)

    object ReadFlag {
        const val NO = "0" // 未读
        const val YES = "1" // 已读
    }

    object DeleteFlag {
        const val NO = "0" // 未删除
        const val YES = "1" // 已删除
    }
}
