package com.example.sunnyweather.common

object Variable {
    @JvmField
    var redDotMsgSumNum = 0 // 消息红点数

    @JvmField
    var mAppVersion: String? = null

  /*  @JvmField
    var mSK: String? = Encrypt.getKey(Utils.getApp())*/

    @JvmField
    // 判断当前网络环境是否需要强制ipv4，默认不开启（业务逻辑标识，非开关）
    var isNeedForceIpv4 = false

    @JvmField
    var mUserAgent: String? = null
}