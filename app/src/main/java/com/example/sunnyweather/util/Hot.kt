package com.ct.client.database.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * 热门精选
 */
@Entity(indices = [Index(value = ["cardId","phoneNumber"], unique = true)] )
data class Hot(
    // 使用@PrimaryKey注解将它设为了主键，再把autoGenerate参数指定成true，使得主键的值是自动生成的
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var cardId: String? = null,
    var type: String? = null, // 显示类型 0.本地显示 1.本地不显示
    var cardOrder: Int = 0,
    var isTakeDown: String? = null, // 下架标志 0.已上架 1.已下架
    var phoneNumber: String? = null
){
    @Ignore
    constructor():this(0)
}