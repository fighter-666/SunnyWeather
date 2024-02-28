package com.example.sunnyweather.base;


/**
 * 日志输出封装
 */
public class UtilLog {

    public static boolean isShowLog = false; // 日志开启总开关, 正常打包为false, 日志包true

    public static boolean isWebMonitorSaveLogcat = true; // 网页监控是否写入文件
    public static boolean isUtilSaveLogcat = true; // 本地信息是否写入文件
    public static boolean isLoginSaveLogcat = true; // 登录日志信息是否写入文件
    public static boolean isWidgetSaveLogcat = true; // 小组件信息是否写入文件
    public static boolean isNetCheckSaveLogcat = true; // 网络监测信息是否写入文件
    public static boolean isImportantSaveLogcat = true; // 主数据是否写入文件
    public static boolean isLitePalSaveLogcat = true; // 数据库是否写入文件
    public static boolean isAnnounceSaveLogcat = true; // 公告流程是否写入文件
    public static boolean isIntegrationCard = true; // 首页卡片区是否写入文件231
    public static boolean isStreamSaveLogcat = true;// 直播间是否开启LOG
    public static boolean isMessageCenterSaveLogcat = true;// 消息中心是否开启LOG
    public static boolean isRefreshSaveLogcat = true;// 局部刷新是否开启log
    public static boolean isFeedSaveLogcat = true;// 瀑布流是否开启log
    public static boolean isPushSaveLogcat = true;// 推送是否开启log
    public static boolean isRequestSaveLogcat = true;// 接口通讯是否开启log
    public static boolean isRechargeSaveLogcat = true;// 充值是否开启log
    public static boolean isFeedTipSaveLogcat = true;// feed引导条是否开启log
    public static boolean isMPaaSPushLogcat = true;// mPaaS推送是否开启log
    public static boolean isSpeedTestPingLogcat = true;// 测试ping功能是否开启log

    public static void saveLogcat(String write) {
        Log.i("UtilLog", write);
        if (isShowLog){
            if (isUtilSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("UtilLog", write);
            }
        }
    }

    /**
     * 网页监控日志
     * @param write
     */
    public static void saveWebLogcat(String write) {
        Log.i("WebMonitorWorkerLog", write);
        if (isShowLog){
            if (isWebMonitorSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("WebMonitorWorkerLog", write);
            }
        }
    }

    /**
     * 登录流程日志
     * @param write
     */
    public static void saveLoginLogcat(String write) {
        Log.i("LoginProcessLog", write);
        if (isShowLog){
            if (isLoginSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("LoginProcessLog", write);
            }
        }
    }

    /**
     * 小组件流程日志
     * @param write
     */
    public static void saveWidgetLogcat(String write) {
        Log.i("WidgetProcessLog", write);
        if (isShowLog) {
            if (isWidgetSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("WidgetProcessLog", write);
            }
        }
    }

    /**
     * 网络日志
     * @param write
     */
    public static void saveNetLogcat(String write) {
        Log.i("NetCheckLog", write);
        if (isShowLog) {
            if (isNetCheckSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("NetCheckLog",  write);
            }
        }
    }

    /**
     * 首页卡片区日志
     * @param write
     */
    public static void saveCardLogcat(String write) {
        Log.i("IntegrationCardLog", write);
        if (isShowLog) {
            if (isIntegrationCard || Log.dbgFlg){
                UtilBaseApp.saveLogcat("IntegrationCardLog",  write);
            }
        }
    }

    /**
     * 主数据区日志
     * @param write
     */
    public static void saveImportantDataLogcat(String write) {
        Log.i("ImportantDataLog", write);
        if (isShowLog) {
            if (isImportantSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("ImportantDataLog",  write);
            }
        }
    }

    /**
     * 数据库日志
     * @param write
     */
    public static void saveLitePalDataLogcat(String write) {
        Log.i("LitePalLog", write);
        if (isShowLog) {
            if (isLitePalSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("LitePalSaveLogcat",  write);
            }
        }
    }

    /**
     * 公告流程日志
     * @param write
     */
    public static void saveAnnounceLogcat(String write) {
        Log.i("AnnounceLogcat", write);
        if (isShowLog) {
            if (isAnnounceSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("AnnounceLogcat",  write);
            }
        }
    }

/*    public static void saveStreamLogcat(String write) {
        Log.d(StreamConstants.TAG, write);
        if (isShowLog) {
            if (isStreamSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat(StreamConstants.TAG,  write);
            }
        }
    }*/

    public static void saveMessageCenterLogcat(String write) {
        Log.d("MessageCenterLog", write);
        if (isShowLog) {
            if (isMessageCenterSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("MessageCenterLog",  write);
            }
        }
    }

    public static void saveRefreshLogcat(String write) {
        Log.d("RefreshSaveLogcat", write);
        if (isShowLog) {
            if (isRefreshSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("RefreshSaveLogcat",  write);
            }
        }
    }

    public static void saveFeedLogcat(String write) {
        Log.d("FeedSaveLogcat", write);
        if (isShowLog) {
            if (isFeedSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("FeedSaveLogcat",  write);
            }
        }
    }

    public static void savePushLogcat(String write) {
        Log.d("PushLogcat", write);
        if (isShowLog) {
            if (isPushSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("PushLogcat",  write);
            }
        }
    }

    public static void saveRequestLogcat(String write) {
        Log.d("PushLogcat", write);
        if (isShowLog) {
            if (isRequestSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("RequestLogcat",  write);
            }
        }
    }

    public static void saveRechargeLogcat(String write) {
        Log.d("RechargeLogcat", write);
        if (isShowLog) {
            if (isRechargeSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("RechargeLogcat",  write);
            }
        }
    }

    public static void saveFeedTipSaveLogcat(String write) {
        Log.d("FeedTipSaveLogcat", write);
        if (isShowLog) {
            if (isFeedTipSaveLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("FeedTipSaveLogcat",  write);
            }
        }
    }

    public static void saveMPaaSPushLogcat(String write) {
        Log.d("MPaaSPushLogcat", write);
        if (isShowLog) {
            if (isMPaaSPushLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("MPaaSPushLogcat",  write);
            }
        }
    }

    public static void saveSpeedTestPingLogcat(String write) {
        Log.d("SpeedTestPingLogcat", write);
        if (isShowLog) {
            if (isSpeedTestPingLogcat || Log.dbgFlg){
                UtilBaseApp.saveLogcat("SpeedTestPingLogcat",  write);
            }
        }
    }
}
