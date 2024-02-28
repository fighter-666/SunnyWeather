package com.ct.client.database.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * 说明：
 *
 * @作者 luohao
 * @创建时间 2023/5/25 09:56
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
@Entity
data class CustomerServiceMsg(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var sort: String? = null,
    var jsonString: String? = null,
    var phoneNumber: String? = null
) {
    @Ignore
    constructor():this(0)
}