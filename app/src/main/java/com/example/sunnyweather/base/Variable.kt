package com.example.sunnyweather.base


/**
 * 静态变量
 */
object Variable {
    @JvmField
    var mSID: String? = null

    @JvmField
    var mPhoneModelLong: String? = null

    @JvmField
    var isX201 = false //业务逻辑接口X201抢号
    @JvmField
    var isX104 = false //业务逻辑接口X104抢号，各页面设备下线弹窗（Activity形式）
    @JvmField
    var isX104Dialog = false //自动登录流程X104抢号，公告流程设备下线弹窗（Dialog形式）

    @JvmField
    var isX104Desc = "" //业务逻辑接口X104抢号文案
    var isForceUpdate = false // 是否强制升级


  /*  @JvmField
    var mSK: String? = Encrypt.getKey(Utils.getApp())*/

    @JvmField
    var mNormalStart = false // 软件正常启动与后台被系统杀死后重启两种

    @JvmField
    var mEnableUpdate = true

    @JvmField
    var mIsNeedRefresh = false

    @JvmField
    var mIsNorPhone = true // 分为普通手机和三星手机,因为三星手机要求较高,部分功能屏蔽

    @JvmField
    var mShowingUserGuide = false //当前版本第一次运行，特点是会显示新功能引导页

    @JvmField
    var mIsKillRefresh = false //是否系统杀死的刷;

    @JvmField
    var mIsCancelAutoLogin = false // 是否取消闪屏登录;

    @JvmField
    var mIsUseDefaultDb = true // 是否处于默认数据库;

    @JvmField
    var mLastActivity: String? = null//为了记录崩溃发生的页面，即最后一个打开的页面

    @JvmField
    var isFirstShowSecond = true // 是否第一次展示二楼

    @JvmField
    var isFirstShowAlien = true

    @JvmField
    // 判断当前网络环境是否需要强制ipv4，默认不开启（业务逻辑标识，非开关）
    var isNeedForceIpv4 = false

    @JvmField
    // 打开登录是否提示过
    var isLoginFailDialogOpen = false

    @JvmField
    // 是否弹过升级弹窗
    var isUpdateDialogOpen = false

    @JvmField
    // 是否弹过公告
    var isAnnounceDialogOpen = false

    @JvmField
    // 闪屏页是否网络异常
    var isSplashNetworkError = false

    @JvmField
    // 网络异常标识是否弹过
    var isNetworkErrorShow = false

    @JvmField
    // 手势密码是否开过
    var isUnLockStart = false

    @JvmField
    // 容灾
    var isFirst999 = true

    @JvmField
    // 是否获取到升级结果
    var isGetUpdateResult = false

    @JvmField
    var namemapIsLoadingDone = false

    @JvmField
    var nameMap = HashMap<String, String>()

    @JvmField
    var isKeyDataChange = false //我的频道：true,要刷新，false不需要刷新

    @JvmField
    var isCrashSaveLogcat = true //崩溃日志信息是否写入文件

    @JvmField
    var isWebContentsDebuggingEnabled = false //测试组自动化测试定制包开关(默认关闭)

    @JvmField
    var isLinkTestOpen = false //客户端测试跳转入口是否开启

    @JvmField
    var isBadgesEnabled = true //是否开启Tab、桌面小红点

    @JvmField
    var isWebMonitorEnabled = true //是否开启网页监控

    var isNeedRefreshMsg = false //是否需要刷新消息
    @JvmField
    var isNeedRefreshMsgDot = false //是否需要刷新消息红点
    var isNeedGetMsgDot = false //是否需要重新计算消息红点

    @JvmField
    var isFirstInstall = false //是否是首次安装（true 表示是首次安装，false表示不是首次安装）

    @JvmField
    var mAppVersion: String? = null

    @JvmField
    var mUserAgent: String? = null

   /* @JvmField
    //手机绑定省份信息列表
    var phoneBindProv: MutableList<AddressInfoData.ProvinceInfo> = mutableListOf()

    @JvmField
    //重置密码省份信息列表
    var resetPwdProv: MutableList<AddressInfoData.ProvinceInfo> = mutableListOf()

    @JvmField
    //账密登录省份信息列表
    var userPwdProv: MutableList<AddressInfoData.ProvinceInfo> = mutableListOf()

    @JvmField
    //宽带新装省份信息列表
    var bandNewProv: MutableList<AddressInfoData.ProvinceInfo> = mutableListOf()

    @JvmField
    //5G覆盖省份信息列表
    var fiveGProv: MutableList<AddressInfoData.ProvinceInfo> = mutableListOf()

    @JvmField
    //宽带身份证查询省份信息列表
    var broadBindProv: MutableList<AddressInfoData.ProvinceInfo> = mutableListOf()

    @JvmField
    //宽带地市配置列表
    var broadCityList: MutableList<AddressInfoData.BroadCityInfo> = mutableListOf()

    @JvmField
    //翼相助省市信息二级列表
    var assistanceProv: MutableList<AddressInfoData.ProvinceInfo> = mutableListOf()

    @JvmField
    //家庭圈头像列表
    var familyHeaderList: MutableList<DefaultMemberConfData.HeadPortraitsBean> = mutableListOf()

    @JvmField
    var familyNickNameList: MutableList<DefaultMemberConfData.NickNamesBean> = mutableListOf()

    @JvmField
    var mExceptionData: EReportData? = null

    @JvmField
    var mRefreshData: RefreshData? = null

    @JvmField
    var webViewInfoBeans = Vector<WebViewInfoBean>()*/

    @JvmField
    var isOnHome = false // 是否选中首页

    @JvmField
    var redDotMsgSumNum = 0 // 消息红点数
}