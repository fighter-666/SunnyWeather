package com.example.sunnyweather.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Setting {

    public static SharedPreferences sPref = null;
    public static final String KEY_FIRST_NEW_GUIDE = "FirstNewGuide";   // 是否首次显示新功能引导页
    public static final String KEY_FAVORITE_ICONS = "FavoriteIconNamesV2";   //根据需要更新
    public static final String KEY_POST_NOTIFICATION = "PostNotification";   //是否申请过通知权限
    public static final String KEY_USER_NAME = "UserName";   // 用户名
    public static final String KEY_IGNOREVER = "IgnoreVer";   //
    public static final String KEY_TOTAL_BALANCE = "TotalBalance";   // 桌面插件使用
    public static final String KEY_USED_AMOUNT = "UsedAmount";   // 已使用流量
    public static final String KEY_ACCAMOUNT = "AccAmount";   // 总流量
    public static final String KEY_LAST_UPDATE_TIME = "LastUpdateTime";   //
    public static final String KEY_LAST_LOGIN_PHONE_NBR = "LastLoginPhoneNbr";   //
    public static final String KEY_RECENT_PAYMENTS = "RecentPayments";   //
    public static final String KEY_SHOW_FLOW_NOTICE = "ShowFlowNotice";   //
    public static final String KEY_IS_OAUTH_LOGIN = "isOauthLogin"; //是否自动登录。默认为false
    public static final String KEY_LOGIN_SUCCESS_USER_PHOEN_AND_PWD_S = "loginSuccessUserPhonePwds";//登录成功的用户名和密码集合（旧版）
    public static final String KEY_LOGIN_MSG = "KEY_LOGIN_MSG";//登录成功的用户名和密码集合
    public static final String KEY_DELETE_DYNAMIC_MSG = "KEY_DELETE_DYNAMIC_MSG";//动态消息删除集合
    public static final String KEY_DELETE_SERVICE_MESSAGE = "KEY_DELETE_SERVICE_MESSAGE";// 服务消息删除集合
    public static final String KEY_DELETE_MARKETING_MESSAGE = "KEY_DELETE_MARKETING_MESSAGE";// 营销消息删除集合
    public static final String KEY_CLOSE_CATEGORY_MESSAGE = "KEY_CLOSE_CATEGORY_MESSAGE";// 消息分类关闭集合
    public static final String KEY_CLOSE_UPDATE = "KEY_CLOSE_UPDATE";// 升级消息关闭
    public static final String KEY_TOP_MARKETING_MESSAGE = "KEY_TOP_MARKETING_MESSAGE";// 营销消息置顶集合
    public static final String KEY_USER_INVOICE = "userInvoice";//登录成功的用户名和密码集合
    public static final String KEY_USER_WATERID = "userWaterId";// 用户的流水id
    public static final String KEY_USER_REALNAME = "userRealname";// 实名制通过的身份信息
    public static final String KEY_SPLASH_LINK = "splash_link";//闪屏页点击跳转的link
    public static final String KEY_SPLASH_LINKTYPE = "splash_linktype";//闪屏页点击跳转的linktype
    public static final String KEY_BROADBAND_LINK = "KEY_BROADBAND_LINK";// 单宽跳转的link
    public static final String KEY_BROADBAND_LINK_TYPE = "KEY_BROADBAND_LINK_TYPE";// 单宽跳转的linktype
    public static final String KEY_SPLASH_TITLE = "splash_title";//闪屏页点击跳转的title
    public static final String KEY_NEW_SALES_LINKTYPE = "KEY_NEW_SALES_LINKTYPE";// 新销售号点击跳转类型
    public static final String KEY_NEW_SALES_LINK = "KEY_NEW_SALES_LINK";// 新销售号点击跳转
    public static final String KEY_SOLAR_PIC = "solar_pic";// 节气动画标识
    public static final String KEY_ASTRICT = "Astrict";//IPV6网络提示方式
    public static final String KEY_URL_KMOCR_HTTPS_HOST = "KEY_URL_KMOCR_HTTPS_HOST_FORMAL";
    public static final String KEY_PORT_KMOCR_HTTPS = "KEY_PORT_KMOCR_HTTPS";
    public static final String KEY_URL_REPORT_HTTPS_HOST = "KEY_URL_REPORT_HTTPS_HOST";
    public static final String KEY_PORT_REPORT_HTTPS = "KEY_PORT_REPORT_HTTPS";
    public static final String MESSAGE_PROMPT_CLOSURE = "MESSAGE_PROMPT_CLOSURE";
    private static final String CRYPT_KEY = "chinatelecom2012";
    public static final String ANDROID_ID = "ANDROID_ID";
    public static final String KEY_ONE_KEY_LOGIN_AUTHORIZATION = "KEY_ONE_KEY_LOGIN_AUTHORIZATION";     // 是否走免密-一键登录
    public static final String KEY_IS_OPEN_NET_CHECK = "KEY_IS_OPEN_NET_CHECK";     // 是否开启网络监测
    public static final String KEY_IS_OPEN_CT_APM = "KEY_IS_OPEN_CT_APM";     // 是否开启监控sdk

    public static final String KEY_AUTO_LOGIN_IS_CARD = "KEY_AUTO_LOGIN_IS_CARD";     // 判断自动登录来源是否卡登陆（imsi和免密登录统称卡登陆，免密登录有闪屏页和登陆页两处）
    public static final String KEY_QUERY_TABLE_BAR_TIMESTAMP = "KEY_QUERY_TABLE_BAR_TIMESTAMP";
    public static final String KEY_CN_TOKEN = "KEY_CN_TOKEN";    // cntoken
    // 头像
    public static final String KEY_HEAD_URL = "KEY_HEAD_URL";
    public static final String KEY_BUSINESS_RECOMMONED_STATE = "KEY_BUSINESS_RECOMMONED_STATE"; // 业务推荐开关，默认开启，true
    public static final String KEY_PREMIUM_SWITCH = "KEY_PREMIUM_SWITCH"; // 尊享版开关，默认关闭，false
    public static final String KEY_PREMIUM_NO_MORE = "KEY_PREMIUM_NO_MORE"; // 尊享版弹窗是否不再提示
    public static final String KEY_PREMIUM_THIS_MONTH_NO_MORE = "KEY_PREMIUM_THIS_MONTH_NO_MORE"; // 尊享版弹窗本月是否需要显示
    public static final String KEY_PAY_NET_SALE_ID = "KEY_PAY_NET_SALEID";    // 网银支付id
    public static final String KEY_PAY_BALANCE_SALE_ID = "KEY_PAY_BALANCE_SALE_ID";    // 余额支付id
    public static final String KEY_ENVIRONMENT = "KEY_ENVIRONMENT";     // 当前环境
    public static final String KEY_DISASTER_TOLERANCE = "KEY_DISASTER_TOLERANCE";     // 是否开启容灾
    public static final String KEY_TEXT_FONT_SCALE = "KEY_TEXT_FONT_SCALE";    // 设置文本缩放比例
    public static final String KEY_CARE = "KEY_CARE";     // 是否开启关爱版
    public static final String KEY_INTERNATIONAL = "KEY_INTERNATIONAL";     // 是否开启国际版
    public static final String KEY_DISTRACTION_FREE = "KEY_DISTRACTION_FREE";     // 是否开启消息免打扰
    public static final String KEY_XH_DIAL_NO_TIP = "KEY_XH_DIAL_NO_TIP";    // 是否选择小号拨号弹窗不再提示
    public static final String KEY_ACTIVITY_NOMORE = "KEY_ACTIVITY_NOMORE";    // 首页异形不再弹出时间戳
    public static final String KEY_MOURNING_START_TIME = "KEY_MOURNING_START_TIME";    // 哀悼模式时间
    public static final String KEY_MOURNING_END_TIME = "KEY_MOURNING_END_TIME";
    public static final String KEY_IS_OPEN_VOICE_FLOAT = "KEY_IS_OPEN_VOICE_FLOAT"; // 是否开启语音播报浮窗
    public static final String KEY_FAMILY_NAME = "KEY_FAMILY_NAME";  // 家庭圈名称
    public static final String KEY_SIGNATURE_STRING = "KEY_SIGNATURE_STRING";  // 充值验签串
    public static final String KEY_FAMILY_STORE_TIME = "KEY_FAMILY_STORE_TIME";  // 家庭圈保存的时间
    public static final String KEY_HOME_TOP_TAB_LABEL = "KEY_HOME_TOP_TAB_LABEL";  // 首页顶部tab的label
    public static final String KEY_DISCLAIMER_TIMESTAMP = "KEY_DISCLAIMER_TIMESTAMP";  // 免责声明时间戳
    // 经纬度
    public static final String KEY_LONGITUDE = "KEY_LONGITUDE";
    public static final String KEY_LATITUDE = "KEY_LATITUDE";

    // 定位省市编码
    public static final String KEY_LOCATION_PROVINCE_CODE = "KEY_LOCATION_PROVINCE_CODE";
    public static final String KEY_LOCATION_CITY_CODE = "KEY_LOCATION_CITY_CODE";

    /**
     * 集群
     */
    public static class CLUSTER {
        // 容灾配置地址
        public static final String KEY_URL_JSON = "KEY_URL_JSON";
        // 环境域名和端口
        public static final String KEY_URL_HTTPS_HOST = "CTCLIENT_URL_HTTPS_HOST";
        public static final String KEY_PORT_HTTPS = "CTCLIENT_PORT_HTTPS";
        // 登录相关6个接口端口
        public static final String KEY_URL_LOGIN_HTTPS = "CTCLIENT_URL_LOGIN_HTTPS";
        public static final String KEY_PORT_LOGIN_HTTPS = "CTCLIENT_PORT_LOGIN_HTTPS";
        // 服务一致性
        public static final String KEY_URL_QUERY_HTTPS_HOST = "KEY_URL_QUERY_HTTPS_HOST";
        public static final String KEY_PORT_QUERY_HTTPS = "KEY_PORT_QUERY_HTTPS";
        // 客服相关接口
        public static final String KEY_URL_KEFU_HTTPS = "KEY_URL_KEFU_HTTPS";
        public static final String KEY_PORT_KEFU_HTTPS = "KEY_PORT_KEFU_HTTPS";
        // 广告位接口
        public static final String KEY_URL_AD_HTTPS_HOST = "KEY_URL_AD_HTTPS_HOST";
        public static final String KEY_PORT_AD_HTTPS = "KEY_PORT_AD_HTTPS";
    }


    public static SharedPreferences getSharedPreferences(Context context, String sharePreferencesName) {

        if (sPref != null) {
            return sPref;
        }

        if (sharePreferencesName == null || sharePreferencesName.length() == 0) {
            sPref = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            sPref = context.getSharedPreferences(sharePreferencesName, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        }
        return sPref;
    }

    public static String getStringDefault(String key, String defValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Utils.getApp());
        return prefs.getString(key, defValue);
    }

    /****************************** 旧版存储方式 ******************************/
    public static String getStringDefault(String key) {
        return getStringDefault(key, "");
    }

    public static void setStringDefault(String key, String value) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(Utils.getApp()).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 旧版带加密的存储
     */
    public static void setStringDefaultEncode(String key, String value) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(Utils.getApp()).edit();
        editor.putString(key, encode(value));
        editor.apply();
    }

    public static String getStringDefaultDecode(String key) {
        return decode(getStringDefault(key, ""));
    }

    public static boolean getBooleanDefault(String key, boolean defValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Utils.getApp());
        return prefs.getBoolean(key, defValue);
    }

    public static void setBooleanDefault(String key, boolean value) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(Utils.getApp()).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /****************************** 新版存储方式 ******************************/
    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences prefs = getNewSharedPreferences();
        return prefs.getBoolean(key, defValue);
    }

    public static void setBoolean(String key, boolean value) {
        Editor editor = getNewSharedPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getString(String key, String defValue) {
        SharedPreferences prefs = getNewSharedPreferences();
        return prefs.getString(key, defValue);
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static void setString(String key, String value) {
        Editor editor = getNewSharedPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 新版存储加密的方式保存String
     */
    public static void setStringEncode(String key, String value) {
        Editor editor = getNewSharedPreferences().edit();
        editor.putString(key, encode(value));
        editor.apply();
    }

    public static String getStringDecode(String key, String def) {
        return decode(getString(key, def));
    }

    /**
     * 新版存储key带号码加密的方式保存String
     * nbr 当前登录账号
     * @param key
     */
    public static String getStringByNbr(String key) {
        return getString(key + encode(MyApplication.mDataCache.UserPhoneNbr), "");
    }

    public static void setStringByNbr(String key, String value) {
        Editor editor = getNewSharedPreferences().edit();
        editor.putString(key + encode(MyApplication.mDataCache.UserPhoneNbr), value);
        editor.apply();
    }

    public static String getStringByNbr(String key, String nbr) {
        return getString(key + encode(nbr), "");
    }

    /**
     * 新版存储加密key的方式保存String
     */
    public static void setStringByEncodeKey(String key, String value) {
        Editor editor = getNewSharedPreferences().edit();
        editor.putString(encode(key), value);
        editor.apply();
    }

    /**
     * 新版存储加密key的方式获取String
     */
    public static String getStringByEncodeKey(String key) {
        return getString(encode(key), "");
    }

    /**
     * 新版存储key带号码加密的方式保存Boolean
     */
    public static boolean getBooleanByNbr(String key, boolean defValue) {
        SharedPreferences prefs = getNewSharedPreferences();
        return prefs.getBoolean(key + encode(MyApplication.mDataCache.UserPhoneNbr), defValue);
    }

    public static void setBooleanByNbr(String key, boolean value) {
        Editor editor = getNewSharedPreferences().edit();
        editor.putBoolean(key + encode(MyApplication.mDataCache.UserPhoneNbr), value);
        editor.apply();
    }

    public static boolean getBooleanByBroadbandAccount(String key, boolean defValue) {
        SharedPreferences prefs = getNewSharedPreferences();
        return prefs.getBoolean(key + encode(MyApplication.mBroadbandDataCache.getCurrentBroadband().broadbandAccount), defValue);
    }

    public static void setBooleanByBroadbandAccount(String key, boolean value) { 
        Editor editor = getNewSharedPreferences().edit();
        editor.putBoolean(key + encode(MyApplication.mBroadbandDataCache.getCurrentBroadband().broadbandAccount), value);
        editor.apply();
    }

    /**
     * 新版保存int数据
     *
     */
    public static int getInt(String key, int defValue) {
        SharedPreferences prefs = getNewSharedPreferences();
        return prefs.getInt(key, defValue);
    }

    public static void setInt(String key, int value) {
        Editor editor = getNewSharedPreferences().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private static SharedPreferences getNewSharedPreferences() {
        return getSharedPreferences(Utils.getApp(), Utils.getApp().getPackageName() + "_NewValue");
    }

    public static void setUserPwd(Context context, String value) {
        String dstValue;
        boolean cryptFlg;

        try {
            dstValue = UtilEncryption.desedeEncoder(value, CRYPT_KEY);
            cryptFlg = true;
        } catch (Exception e) {
            //重置密码时, 要设置为null, null会导致异常, 但又不想看到这个日常日志...
            if (value != null)
                e.printStackTrace();

            dstValue = value;
            cryptFlg = false;
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putString("UserPwd", dstValue);
        editor.putBoolean("PwdCrypt", cryptFlg);
        editor.apply();
    }

    public static String getUserPwd(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String pwd = prefs.getString("UserPwd", null);
        boolean crypt = prefs.getBoolean("PwdCrypt", false);
        if (!crypt || pwd == null) {
            return pwd;
        }

        try {
            return UtilEncryption.desedeDecoder(pwd, CRYPT_KEY);
        } catch (Exception e) {
            return pwd;
        }
    }

    private final static String DYNAMIC_IMAGE = "DYNAMIC_IMAGEv43";

    public static void setDynamicImageUrl(Context context, String imageTag, String url) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putString(DYNAMIC_IMAGE + imageTag, url);
        editor.apply();
    }

    public static String getDynamicImageUrl(String imageTag) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Utils.getApp());
        return prefs.getString(DYNAMIC_IMAGE + imageTag, null);
    }

    public static void setShowUserGuideSwitch(Context context, Boolean value) { 
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putBoolean("ShowUserGuideSwitch" + UtilBaseApp.getCurAppVer(context), value);
        editor.apply();
    }

    public static Boolean getShowUserGuideSwitch(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("ShowUserGuideSwitch" + UtilBaseApp.getCurAppVer(context), true);
    }

    public static void setFirstLunchTime(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putLong("FirstLunchTime", System.currentTimeMillis());
        editor.apply();
    }

    public static long getFirstLunchTime(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getLong("FirstLunchTime", 0);
    }

    public static boolean getShownInFaviroteYet(Context context, String name) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("ShownInFaviroteYet" + name, false);
    }

    public static void setShownInFaviroteYet(Context context, String name, boolean flg) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putBoolean("ShownInFaviroteYet" + name, flg);
        editor.apply();
    }

    public static boolean getHasUseFuncYet(Context context, String name) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("HasUseFuncYet" + name, false);
    }

    public static void setHasUseFuncYet(Context context, String name, boolean flg) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putBoolean("HasUseFuncYet" + name, flg);
        editor.apply();
    }

    public static void setMaskShownYet(Context context, String maskName, Boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putBoolean("MaskShownYet" + maskName + UtilBaseApp.getCurAppVer(context), value);
        editor.apply();
    }

    public static Boolean getMaskShownYet(Context context, String maskName) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("MaskShownYet" + maskName + UtilBaseApp.getCurAppVer(context), false);
    }

    public static void setUpdateFileSize(Context context, long size) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putLong("UpdateFileSize", size);
        editor.apply();
    }

    public static long getUpdateFileSize(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getLong("UpdateFileSize", 0);
    }

    public static void setRecordWheelCurrentPosition(Context context, int index) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putInt("topIndex", index);
        editor.apply();
    }

    public static int getRecordWheelCurrentPosition(Context context, int index) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt("topIndex", index);
    }

    public static void setSplashShareContent(HomePageUrlShareConfig value) {
        String spit = " ; ";
        String saveVal = value.comString + spit + value.shareImg + spit
                + value.shareLink + spit + value.shareTitle;
        SharedPreferences prefs = getNewSharedPreferences();
        Editor editor = prefs.edit();
        editor.putString("SplashShareContent", saveVal);
        editor.apply();
    }

    public static HomePageUrlShareConfig getSplashShareContent() {
        String spit = " ; ";
        HomePageUrlShareConfig config = new HomePageUrlShareConfig();
        SharedPreferences prefs = getNewSharedPreferences();
        String val = prefs.getString("SplashShareContent", "");
        try {
            String[] vals = val.split(spit);
            config.comString = vals[0];
            config.shareImg = vals[1];
            config.shareLink = vals[2];
            config.shareTitle = vals[3];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

    /**
     * 7.4.0 版本新闪屏接口新增
     */
    public static void setSplashShareContentNew(GetHomePagePicData.ShareConfigBean value) {
        String spit = " ; ";
        String saveVal = value.comString + spit + value.shareImg + spit
                + value.shareLink + spit + value.shareTitle;
        SharedPreferences prefs = getNewSharedPreferences();
        Editor editor = prefs.edit();
        editor.putString("SplashShareContentNew", saveVal);
        editor.apply();
    }

    public static void setSplashScreenConfig(GetHomePagePicData.SplashScreenConfigBean value) {
        String spit = " ; ";
        String saveVal = value.comString + spit + value.link + spit + value.linkType + spit + value.shareImg + spit
                + value.shareLink + spit + value.shareTitle + spit + value.title + spit + value.zipUrl;
        SharedPreferences prefs = getNewSharedPreferences();
        Editor editor = prefs.edit();
        editor.putString("SplashScreenConfig", saveVal);
        editor.apply();
    }

    /**
     * 标记 预警提示本日已经提示过了(分用户)
     *
     * @param type 用于存储的功能类型 Constants.ThisDayHasShowTipType
     */
    public static void setThisDayTipShowedByNbr(String type) {
        SharedPreferences prefs = getNewSharedPreferences();
        Editor editor = prefs.edit();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        editor.putString("thisDayTipShow:" + encode(MyApplication.mDataCache.UserPhoneNbr) + ":" + type, sdf.format(date));
        editor.apply();
    }

    /**
     * 获取本日是否需要显示(分用户)
     *
     * @param type 用于存储的功能类型 Constants.ThisDayHasShowTipType
     * @return true- 本日没有显示过 可以显示； false-本日已经显示过 不能再显示
     */
    public static boolean getThisDayTipShowedByNbr(String type) {
        SharedPreferences prefs = getNewSharedPreferences();
        String month = prefs.getString("thisDayTipShow:" + encode(MyApplication.mDataCache.UserPhoneNbr) + ":" + type, "");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return UtilText.isEmptyOrNull(month) || !month.equals(sdf.format(date)); 
    }

    /**
     * 标记 提示今天关闭了，不再展示
     */
    public static void setThisDayClose(String key) {
        SharedPreferences prefs = getNewSharedPreferences();
        Editor editor = prefs.edit();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        editor.putString("thisDayClose:" + key, sdf.format(date));
        editor.apply();
    }

    /**
     * 获取本日是否需要展示
     */
    public static boolean getThisDayClose(String key) {
        SharedPreferences prefs = getNewSharedPreferences();
        String month = prefs.getString("thisDayClose:" + key, "");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return UtilText.isEmptyOrNull(month) || !month.equals(sdf.format(date));
    }

    /**
     * 标记 预警提示本月已经提示过了（分用户）
     *
     * @param key 可能是时间戳拼接其他
     */
    public static void setThisMonthTipShowedByNbr(String key) {
        SharedPreferences prefs = getNewSharedPreferences();
        Editor editor = prefs.edit();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        editor.putString("thisMonthTipShow:" + encode(MyApplication.mDataCache.UserPhoneNbr) + ":" + key, sdf.format(date));
        editor.apply();
    }


    /**
     * 获取本月是否需要预警提示的显示（分用户）
     *
     * @param key 可能是时间戳拼接其他
     * @return true- 本月没有显示过 可以显示； false-本月已经显示过 不能再显示
     */
    public static boolean getThisMonthTipShowByNbr(String key) {
        SharedPreferences prefs = getNewSharedPreferences();
        String month = prefs.getString("thisMonthTipShow:" + encode(MyApplication.mDataCache.UserPhoneNbr) + ":" + key, "");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return UtilText.isEmptyOrNull(month) || !month.equals(sdf.format(date));
    }



    /**
     * 获取信息集合
     */
    public static Set<String> getValueSet(String key) {
        SharedPreferences prefs = getNewSharedPreferences();
        return prefs.getStringSet(key, null);
    }

    /**
     * 设置信息集合
     */
    public static void setValueSet(String key, Set<String> value) {
        Editor editor = getNewSharedPreferences().edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public static boolean getRedDot(Context context, String keyName) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Utils.getApp());
        return prefs.getBoolean(encode(keyName), true);
    }

    public static void setRedDot(Context context, String keyName, boolean flg) {
        if (context != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            Editor editor = prefs.edit();
            editor.putBoolean(encode(keyName), flg);
            editor.apply();
        }
    }

    /**
     * 保存已读时间
     */
    public static void setReadedTime(Context context, String keyName) {
        if (context != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String nowTime = format.format(date);
            Log.e("readedTime", nowTime);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            Editor editor = prefs.edit();
            editor.putString(keyName, nowTime);
            editor.apply();
        }
    }

    /**
     * 敏感信息加密
     */
    public static String encode(String value) {
        String dstValue;
        try {
            dstValue = UtilEncryption.desedeEncoder(value, CRYPT_KEY);
            return dstValue;
        } catch (Exception e) {
            e.printStackTrace();
            dstValue = value;
        }
        return dstValue;
    }

    /**
     * 敏感信息解密
     */
    public static String decode(String value) {
        try {
            return UtilEncryption.desedeDecoder(value, CRYPT_KEY);
        } catch (Exception e) {
            return value;
        }
    }

    /**
     * 保存List
     */
    public static void setDataList(String tag, List<ChargeHistoryItem> datalist) {
        if (null == datalist)
            return;
        SharedPreferences prefs = getNewSharedPreferences();
        Editor editor = prefs.edit();
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.putString(tag, strJson);
        editor.apply();
    }

    /**
     * 获取List
     */
    public static ArrayList<ChargeHistoryItem> getDataList(String tag) {
        SharedPreferences prefs = getNewSharedPreferences();
        ArrayList<ChargeHistoryItem> datalist = new ArrayList<>();
        String strJson = prefs.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<ChargeHistoryItem>>() {
        }.getType());
        return datalist;
    }

    /**
     * 将旧版的数据进行加密
     */
    public static void encodeUserMsg() {
        String jsonStr = Setting.getString(Setting.KEY_LOGIN_SUCCESS_USER_PHOEN_AND_PWD_S, null);
        if (!UtilText.isEmptyOrNull(jsonStr)) {
            // 加密
            Setting.setStringEncode(Setting.KEY_LOGIN_MSG, jsonStr);
            // 清空
            Setting.setString(Setting.KEY_LOGIN_SUCCESS_USER_PHOEN_AND_PWD_S, "");
        }
    }

    /**
     * 是否获取到隐私政策权限
     *
     * return true 已同意
     */
    public static Boolean isAgreePrivacyPolicy() {
        return !Setting.getBooleanDefault(KEY_SHOW_FLOW_NOTICE, true);
    }
}
