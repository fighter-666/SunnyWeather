package com.example.sunnyweather.base;

import android.app.Activity;
import android.graphics.Color;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Constants {

   // 正式环境、测试环境切换开关 true：正式环境 false：测试环境
   public static boolean IS_URL_FORMAL = true;
   public static final List<Activity> mActivityList = new LinkedList<>();// 页面堆栈管理

   public static final String CHANNEL_UPDATE = "CHANNEL_UPDATE";
   public static final String CHANNEL_MSG = "CHANNEL_MSG";
   public static final String CHANNEL_RUNNING = "CHANNEL_RUNNING";

   /**
    * 推送消息分类
    */
   public static final String CHANNEL_MARKETING = "MARKETING";//公信通道：运营活动
   public static final String CHANNEL_CONTENT = "CONTENT";//公信通道：内容推荐
   public static final String CHANNEL_ACCOUNT = "ACCOUNT";//私信通道：账号动态
   public static final String CHANNEL_ORDER = "ORDER";//私信通道：订单物流
   public static final String CHANNEL_FINANCE = "FINANCE";//私信通道：账户动态
   public static final String CHANNEL_SUBSCRIPTION = "SUBSCRIPTION";//私信通道：订阅消息
   public static final String GROUP_ACTIVITY = "ACTIVITY_NOTIFICATION";//消息分组：活动通知
   public static final String GROUP_SERVICE = "SERVICE_NOTIFICATION";//消息分组：服务通知

   public static final int THRESHOLD_FLOW_LOW = 50; // 流量剩余阈值，该值以下说明流量不足
   public static final int THRESHOLD_FLOW_HIG = 80; // 流量剩余阈值，该值以上说明流量充足
   public static final int FLOWALARM_NOTIFICATION_ID = 20000; // 流量闹钟Notification的ID
   public static final int NOTIFICATION_ID_PRAN = 30000; // pran通知栏ID
   public static final int NOTIFICATION_ID_PRAN_RUNNING = 30001; // pran运行中通知栏ID
   public static final int NOTIFICATION_ID_SCREEN_CAPTURE_0 = 30002; // 屏幕分享运行中通知栏ID(呼叫)
   public static final int NOTIFICATION_ID_SCREEN_CAPTURE_1 = 30003; // 屏幕分享运行中通知栏ID(被叫)

   public final static String ACTION_LOGIN_SUCC = "ACTION_LOGIN_SUCC"; // 登录成功
   public final static String ACTION_SWITCH_PREMIUM = "ACTION_SWITCH_PREMIUM"; // 切换到尊享版
   public final static String ACTION_HOME_SORT_SUCC = "ACTION_HOME_SORT_SUCC"; // 首页排序成功
   public final static String ACTION_EXIT = "ACTION_EXIT"; // 退出登录
   public final static String ACTION_BROADBAND_LOGIN_SUCC = "ACTION_BROADBAND_LOGIN_SUCC"; // 账密，随机码宽带登录成功
   public final static String ACTION_ALL_BROADBAND_LOGIN_SUCC = "ACTION_OTHER_BROADBAND_LOGIN_SUCC"; // 任意宽带登录成功
   public final static String ACTION_UPDATE_NAME = "ACTION_UPDATE_NAME"; // 用户名查询结束
   public final static String ACTION_UPDATE_4GLEVEL = "ACTION_UPDATE_4GLEVEL";// 4G等级查询结束
   public final static String ACTION_UPDATE_FLOW = "ACTION_UPDATE_FLOW"; // 流量查询结束
   public final static String ACTION_ORDER_SUCC = "ACTION_ORDER_SUCC"; // 话费或者卡密充值成功
   public final static String ACTION_IMPORTANT_DATA_FINISH = "ACTION_IMPORTANT_DATA_FINISH"; // 登录流程中的主数据调用结束
   public final static String ACTION_ORDER_BROADBAND_SUCC = "ACTION_ORDER_BROADBAND_SUCC"; // 宽带充值成功
   public final static String ACTION_GUESTRUE_PASSWORD = "ACTION_GUESTRUE_PASSWORD";//手势密码状态更新
   public final static String ACTION_UPDATE_ICON = "ACTION_UPDATE_ICON";
   public final static String ACTION_GOLAST_ICON = "ACTION_GOLAST_ICON";
   public final static String ACTION_APP_UPDATE = "ACTION_APP_UPDATE";
   public final static String ACTION_PUSH_MSG = "ACTION_PUSH_MSG";
   public final static String ACTION_ANNOUNCE_MENT = "ACTION_ANNOUNCE_MENT";
   public final static String ACTION_MSG_CENTER = "ACTION_MSG_CENTER";
   public final static String ACTION_MAIN_HOME = "ACTION_MAIN_HOME";
   public final static String ACTION_SLIDING_TOGGLE = "ACTION_SLIDING_TOGGLE";
   public final static String ACTION_SHOW_RECOMMENDDIALOG = "ACTION_SHOW_RECOMMENDDIALOG";
   public final static String ACTION_RUN_UPDATE = "ACTION_RUN_UPDATE";
   public final static String ACTION_RUN_HOMEHUAN = "ACTION_RUN_HOMEHUAN";
   public final static String ACTION_RUN_MASK = "ACTION_RUN_MASK"; //引导页和遮罩显示过后申请通知权限
   public final static String ACTION_REFRESH_TEXT = "ACTION_REFRESH_TEXT";
   public final static String ACTION_PRENT_FRAGMENT_ONHIDDENCHANGED = "ACTION_PRENT_FRAGMENT_ONHIDDENCHANGED";
   public final static String ACTION_GET_USER_IMAGE_URL_SUCCESS = "ACTION_GET_USER_IMAGE_URL_SUCCESS"; // 获取用户头像url成功
   public final static String ACTION_UP_LOAD_USER_ICON = "ACTION_UP_LOAD_USER_ICON";
   public final static String ACTION_UPDATE_USER_NICK = "ACTION_UPDATE_USER_NICK";
   public final static String ACTION_UPDATE_USER_CUSTIDINFO = "ACTION_UPDATE_USER_CUSTIDINFO";
   public final static String ACTION_ONCLICK_MAIN_TAB_QUERY = "ACTION_ONCLICK_MAIN_TAB_QUERY";// 点击首页查询tab的广播
   public final static String ACTION_UPLOAD_LOCATION = "ACTION_UPLOAD_LOCATION"; // 通知接口上传定位信息
   public final static String ACTION_REFRESH_TEXT_INTEGRAL = "ACTION_REFRESH_TEXT_INTEGRAL";
   public final static String ACTION_SWITCH_TO_CALLLOG = "ACTION_SWITCH_TO_CALLLOG";
   public final static String ACTION_FUNCGUIDE_FINISH = "ACTION_FUNCGUIDE_FINISH"; // 通知首页lottie显示结束
   public final static String ACTION_BROADBAND_LOTTIE_FINISH = "ACTION_BROADBAND_LOTTIE_FINISH";//通知首页宽带lottie显示结束
   public final static String ACTION_START_TWOLEVEL = "ACTION_START_TWOLEVEL";//通知开启二楼
   public final static String ACTION_START_GRAY_MODEL = "ACTION_START_GRAY_MODEL";//通知开启哀悼
   public final static String ACTION_REFRESH_TRUST_EQUIP = "ACTION_START_TWOLEVEL";//刷新可信设备数量
   public final static int ACTIVITY_FINISH = 9999;// activityresult code
   public final static String PACKAGE_NAME = "com.ct.client"; // 应用包名
   public final static int CHECK_TTS = 6672; // 检查语音播报权限

   /* 老版本废弃域名和端口 ---start---*/
   //旧通讯框架使用
   // 9101为测试环境  8004为正式环境
//    public final static String URL_ENC = "http://cservice.client.189.cn:9101/map/clientXML?encrypted=true"; // 主服务器(加密)

   //新通讯框架使用 HTTP请求参数
   public static final String URL_HTTP_HOST = "http://cservice.client.189.cn";
   public static final int PORT_HTTP = 8004;//  8004 为正式环境  9101 为测试环境

   // 各功能独立连接,未来会很多
   public final static String URL_RCM = "http://cservice.client.189.cn:8008/v2/api?encrypted=true"; // 推荐功能(加密)
   // 推送功能(加密)—测试地址:"http://118.85.207.85:9104/map/clientXML"
   // 推送功能(加密)—正式地址:"http://cservice.client.189.cn:8081/map/clientXML"
   public final static String URL_PUSH = "http://cservice.client.189.cn:8081/map/clientXML"; // 推送功能(加密)
   /* 老版本废弃域名和端口 ---end---*/

   // ipv6环境不通切换ipv4开关，默认开启(功能逻辑开关)
   public static boolean IS_IPV4_CHANGE = true;
   // 是否开启首页随机模拟插码上报开关 true：开启  false：关闭（默认关闭）
   public static boolean IS_ENABLE_SIMULATION_REPORT = true;
   //实名制插件环境  true为测试环境  false为正式环境
   public static boolean URL_REALNAMESDK = !IS_URL_FORMAL;
   //宽带实名制流程绕过开关  true为进入SDK  false为绕过SDK
   public static boolean OPEN_KD_REALNAME = true;
   //宽带实名制验签串
   public static String KD_SIGNATURE = "42224a2287;;76;ed53233gdd2586cde;7352439";

   // 是否开启容灾开关
   public static boolean IS_URL_CLUSTER = true;
   // 是否开启qq分享入口开关
   public static boolean IS_OPEN_SHARE_QQ = false;

   /**
    * 集群接口url:
    * iOS10.4.1及以后版本：https://appupdates.189.cn/actives/iosclient.json
    * 安卓10.4.1及以后版本：https://appupdates.189.cn/actives/androidclient.json
    *
    * 安卓及iOS10.4.1以前版本：https://appupdates.189.cn:443/actives/goclient.json
    *
    * 测试地址：https://appg.189.cn:9443/client/goclient.json
    */

   public static String URL_LUSTER = "https://appupdates.189.cn/actives/androidclient.json";


   //通用集群域名和端口
   public static String URL_HTTPS_HOST = "https://appgo.189.cn";// https://appgo.189.cn 为正式环境 https://appg.189.cn 为测试环境
   public static int PORT_HTTPS = 9200;// 9200 为正式环境  9443 为测试环境
   public static String URL_ENC_HTTPS = URL_HTTPS_HOST + ":" + PORT_HTTPS; //新地址 json的https
   public static String URL_ENC = URL_ENC_HTTPS + "/map/clientXML";//老地址 xml的https

   //登录集群相关6个接口对应新域名及端口（loginNormal、loginAccountPlatform、ywSmsSend、getRandomV2、getSingle、custidinfo）
   public static String URL_LOGIN_HTTPS_HOST = "https://appgologin.189.cn";// https://appgologin.189.cn 为正式环境 https://appg.189.cn 为测试环境
   public static int PORT_LOGIN_HTTPS = 9031;// 9031 为正式环境  9443 为测试环境
   public static String URL_LOGIN_ENC_HTTPS = URL_LOGIN_HTTPS_HOST + ":" + PORT_LOGIN_HTTPS; //登录相关6个接口对应地址 json的https
   public static String URL_LOGIN_ENC = URL_LOGIN_ENC_HTTPS + "/map/clientXML";//登录相关6个接口对应地址 xml的https

   //计费域集群查询类接口地址
   //生产环境：https://appfuwu.189.cn:9021  测试环境：https://appg.189.cn:9443
   public static String URL_QUERY_HTTPS_HOST = "https://appfuwu.189.cn";
   public static int PORT_QUERY_HTTPS = 9021;
   public static String URL_QUERY = URL_QUERY_HTTPS_HOST + ":" + PORT_QUERY_HTTPS;

   //客服集群接口地址
   //生产环境：https://appkefu.189.cn:8301 测试环境：https://appg.189.cn:9443
   public static String URL_ONLINECUSTOMER_HTTPS_HOST = "https://appkefu.189.cn";
   public static int PORT_ONLINECUSTOMER_HTTPS = 8301;
   public static String URL_ONLINECUSTOMER = URL_ONLINECUSTOMER_HTTPS_HOST + ":" + PORT_ONLINECUSTOMER_HTTPS;

   //广告位集群接口地址
   //生产环境：https://appgoad.189.cn:7021
   //测试环境：https://appg.189.cn:9443
   public static String URL_AD_HTTPS_HOST = "https://appgoad.189.cn";
   public static int PORT_AD_HTTPS = 7021;
   public static String URL_AD = URL_AD_HTTPS_HOST + ":" + PORT_AD_HTTPS;

   //卡密OCR识别接口地址
   public static String URL_KMOCR_HTTPS_HOST = "http://appkm.189.cn";
   public static int PORT_KMOCR_HTTPS = 9021;
   public static String URL_KMOCR = URL_KMOCR_HTTPS_HOST + ":" + PORT_KMOCR_HTTPS;

   //统计上报集群接口地址
   //生产环境：https://reportappgo.189.cn:8443   暂时不用：http://reportappgo.189.cn:8081
   //测试环境：http://cservice.client.189.cn:9101
   public static String URL_REPORT_HTTPS_HOST = "https://reportappgo.189.cn";
   public static int PORT_REPORT_HTTPS = 8443;
   public static String URL_REPORT = URL_REPORT_HTTPS_HOST + ":" + PORT_REPORT_HTTPS;

   /* 各种域名用于环境切换的常量---start---*/
   // 正式环境（如需打准2环境包单独这边调整即可）
   public static final String URL_HTTPS_HOST_FORMAL = "https://appgo.189.cn";// https://appgo.189.cn 为正式环境 https://ctrl.189.cn为准2环境
   public static final int PORT_HTTPS_FORMAL = 9200;// 9200 为正式环境 9021为准2环境
   public static final String URL_LOGIN_HTTPS_HOST_FORMAL = "https://appgologin.189.cn";// https://appgologin.189.cn 登录相关6个接口正式环境
   public static final int PORT_LOGIN_HTTPS_FORMAL = 9031;// 登录相关6个接口对应及端口正式环境 9021为准2环境
   public static final String URL_ONLINECUSTOMER_HTTPS_HOST_FORMAL = "https://appkefu.189.cn";//统一客服8个接口地址正式环境
   public static final int PORT_ONLINECUSTOMER_HTTPS_FORMAL = 8301;//统一客服8个接口端口正式环境
   public static final String URL_KMOCR_HTTPS_HOST_FORMAL = "http://appkm.189.cn";//卡密OCR识别接口地址正式环境
   public static final int PORT_KMOCR_HTTPS_FORMAL = 9021;//卡密OCR识别接口端口正式环境
   public static final String URL_QUERY_HTTPS_HOST_FORMAL = "https://appfuwu.189.cn";//服务一致性查询类接口地址正式环境
   public static final int PORT_QUERY_HTTPS_FORMAL = 9021;//服务一致性查询类接口端口正式环境
   public static final String URL_AD_HTTPS_HOST_FORMAL = "https://appgoad.189.cn";//广告位接口地址正式环境
   public static final int PORT_AD_HTTPS_FORMAL = 7021;//广告位接口端口正式环境
   public static final String URL_REPORT_HTTPS_HOST_FORMAL = "https://reportappgo.189.cn";//登录统计上报接口地址正式环境
   public static final int PORT_REPORT_HTTPS_FORMAL = 8443;//登录统计上报接口端口正式环境
   public static final boolean URL_REALNAMESDK_FORMAL = false;//实名制插件环境正式环境

   // 测试环境
   public static final String URL_HTTPS_HOST_TEST = "https://appg.189.cn";// 测试环境
   public static final int PORT_HTTPS_TEST = 9443;// 测试环境端口
   public static final String URL_LOGIN_HTTPS_HOST_TEST = "https://appg.189.cn";// https://appg.189.cn 登录相关6个接口测试环境
   public static final int PORT_LOGIN_HTTPS_TEST = 9443; // 登录相关6个接口对应及端口测试环境
   public static final String URL_ONLINECUSTOMER_HTTPS_HOST_TEST = "https://appg.189.cn";//统一客服8个接口地址测试环境
   public static final int PORT_ONLINECUSTOMER_HTTPS_TEST = 9443;//统一客服8个接口端口测试环境
   public static final String URL_KMOCR_HTTPS_HOST_TEST = "http://appkm.189.cn";//卡密OCR识别接口地址测试环境
   public static final int PORT_KMOCR_HTTPS_TEST = 9021;//卡密OCR识别接口端口测试环境
   public static final String URL_QUERY_HTTPS_HOST_TEST = "https://appg.189.cn";//服务一致性查询类接口地址测试环境
   public static final int PORT_QUERY_HTTPS_TEST = 9443;//服务一致性查询类接口端口测试环境
   public static final String URL_AD_HTTPS_HOST_TEST = "https://appg.189.cn";//广告位接口地址测试环境
   public static final int PORT_AD_HTTPS_TEST = 9443;//广告位接口端口测试环境
   public static final String URL_REPORT_HTTPS_HOST_TEST = "http://cservice.client.189.cn";//登录统计上报接口地址正式环境
   public static final int PORT_REPORT_HTTPS_TEST = 9101;//登录统计上报接口端口正式环境
   public static final boolean URL_REALNAMESDK_TEST = true;//实名制插件环境测试环境
   /* 各种域名用于环境切换的常量---end---*/

   //通讯录助手通讯URL
   //正式环境：  http://118.85.200.203/UabSyncService/uabconfig.uab
   //测试环境： http://219.143.33.51:8001/uabconfig.uab
   public static String GETURL_URL = "http://118.85.200.203/UabSyncService/uabconfig.uab";

   /*本地写死URL的静态页面---start---*/
   public final static String PA_AGREEMENT = "http://cservice.client.189.cn:8004/cs/pa/agreement?orderId=";// 后激活查看订单协议
   public final static String TOPUP_ORDER = "http%3a%2f%2fcservice.client.189.cn%3a9092%2frechargeorder%2frechargeorder_list.html%3fct%3d1%26ticket%3d%24ticket%24";// 充值订单WAP页面URL
   public final static String SHOPPING_MALL_ORDER = "https%3a%2f%2flm.189.cn%2forderQuery%2forderQuery_list.html%3fordercategory%3d1%26ct%3d1%26shopid%3d20002%26ticket%3d%24ticket%24%26version%3d%24version%24";// 商城订单WAP页面URL
   public final static String BUSINESS_TRANSACTION_ORDER = "https%3a%2f%2flm.189.cn%2forderQuery%2forderQuery_list.html%3fordercategory%3d3%26ct%3d1%26shopid%3d20002%26ticket%3d%24ticket%24%26version%3d%24version%24";// 业务办理订单WAP页面URL
   public final static String URL_ARROUND_HALLS = "http://118.85.207.68/vieasy_account/web/index.php?c=map&a=near&fromid=5&type=3&lat=$latitude$&lng=$longitude$";// 周边营业厅信息
   public final static String XIAOHAO_SHOPPING_MALL_ORDER = "http%3A%2F%2Fcservice.client.189.cn%3A9092%2Fmap%2Fsinglepoint.do%3Fordercategory%3D4%26ticket%3D%24ticket%24%26clientType%3D2%26shopid%3D20002%26version%3D%24version%24";// 小号商城订单WAP页面URL
   public final static String XIAOHAO_SHOPPING_MALL_ORDER_TEST = "http%3A%2F%2Fcservice.client.189.cn%3A9112%2Fmap%2Fsinglepoint.do%3Fordercategory%3D4%26ticket%3D%24ticket%24%26clientType%3D2%26shopid%3D20002%26version%3D%24version%24";// 小号商城订单WAP页面URL(测试环境)

   public final static String CONS_HAPPYBEANS_SHARE_WEB_URL = "http://cservice.client.189.cn:9092/happybeans/happyBeans_makebean.html";//赚豆秘籍
   public final static String HAPPYBEANS_ANSWER_WEB_URL = "http://cservice.client.189.cn:9092/happybeans/happyBeans_anwser.html";//欢豆问答
   public final static String ZIFEISHUOMING_WEB_URL = "http://cservice.client.189.cn:9092/tariffDesc/tariff_index.html?shopid=20002";//资费说明
   public final static String GERENDDINGZHI_WEB_URL = "http://go.a.189.cn:9011/personal/personaldz.html";//个人定制
   public final static String XIAOHAO_CHEATS_WEB_URL = "http://www.189.cn/wapzt/xiaohao/cheats.html?type=2";//小号秘籍
   public final static String XIAOHAO_AGREEMENT_WEB_URL = "http://www.189.cn/wapzt/xiaohao/agreement.html?type=2";//使用协议
   public final static String PHONENUM_AGREEMENT_WEB_URL = "http://wapzt.189.cn/activity/clientJump/agreement.html";//号卡类入网协议
   public final static String VIDEO_AREA_WEB_URL = "http://wapact.189.cn:9000/videoSection/video_index.html?shopid=20001";//视频专区
   public final static String CARE_OPEN_SHARE_URL = "https://a.189.cn/wapportalservice/loadApp.do?id=62da6445cbc91b09600d20b3";//关爱版开启页分享

   public static final String BROWSE_WEB_URL = "https://wapact.189.cn:9001/browseModePage/index.html";//浏览模式H5页面

   public final static String DEFAULT_SHARE_URL = "http://a.app.qq.com/o/simple.jsp?pkgname=com.ct.client"; // 微下载链接（默认分享链接）
   public final static String DEFAULT_SHARE_URL_PRAN = "https://www.189.cn/wapportalweb/clientjump/clientjump.html?linkType=29&link="; // pran分享链接
   //Pran测试环境
//    public final static String DEFAULT_SHARE_URL_PRAN2 = "http://wapapp31050.zsc.189.cn/wapportalweb/vue/pages/wingconnect/index.html#/loading?version=$version$&linkType=100&link="; // pran分享链接
   public final static String DEFAULT_SHARE_URL_PRAN2_TEST = "http://wapapp31050.zsc.189.cn/wapportalweb/vue/pages/wingconnect/index.html#/loading?version=$version$&linkType=100&link="; // pran分享链接
   //Pran生产环境
   public final static String DEFAULT_SHARE_URL_PRAN2 = "https://www.189.cn/wapportalweb/vue/pages/wingconnect/index.html#/loading?version=$version$&linkType=100&link="; // pran分享链接
   public final static String DEFAULT_SHARE_URL_PRAN3 = "https://www.189.cn/wapportalweb/vue/pages/wingconnect/index.html#/?linkType=100"; // pran分享链接

   public final static String SPEEDTEST_SHARE_URL = "https://a.189.cn/wapportalservice/loadApp.do?id=65430e44b579b44248a59163";//测速页面分享链接
   public static final String SIGNAL_OVERLAY_SHARE_URL = "https://www.189.cn/client/wap/wapproject/newwap/clientJump/clientJump.html?id=AE4C1C4799D43F09E053531410ACDA94";//5G热力图分享链接
   public final static String INTEGRAL_RULE = "https://www.189.cn/client/jfgz/jfgz.html";//积分规则页面
   public final static String INVOICE_RULE_URL = "http://www.189.cn/client/client920/invoiceRule.html";// 发票须知

   public static final String MY_QR_CODE = "https%3A%2F%2Fwapact.189.cn%3A9001%2FInvitationCode_newEdition%2FqrCode.html%3Fticket%3D%24ticket%24%26utm_ch%3Dhg_app%26utm_sch%3Dhg_wd_wdxx";//我的二维码

   public static final String TODAY_WELFARE = "https://wapmkt.189.cn:7443/grepfun/external/redirect.do?scope=pro&url=/grepfun/groupWelfare/index.html&cmpid=khd-czfl-myjhnew-jt&code=$ticket$";//今日福利
   public static final String UPGRADE_5G = "https://www.189.cn/client/wap/wapproject/wapclient/login/login_index.html?ticket=$ticket$&activcode=3&cmpid=wdpd";//升级5G
   public static final String GOLD_BEAN = "https://wapside.189.cn:9001/resources/dist/signInActivity.html?ticket=$ticket$&version=$version$&cmpid=signin-khd-myjh-jt";//金豆商城
   public static final String BROADBAND = "https://w.189.cn/mpa/entry.action?ct=1&hdid=10007&cmpid=dqkdh5zq_khd-khd-all&ticket=$ticket$";//宽带新装
   public static final String GIFT = "https://wapact.189.cn:9001/InvitationCode/invitation.html?ticket=$ticket$&cmpid=khd-yrymtg-myjhnew-jt";//邀请有礼
   public static final String NUMBER_PORTBILITY = "https://wapact.189.cn:9001/NoPortability/portability_index.html?shopid=20002&provinceCode=600304";//携号入网

   public static final String CHECK_ALL_ORDER = "https://www.189.cn/client/wap/wapproject/wapclient/order/order_list.html?ticket=$ticket$";//查看全部订单
   public static final String WILL_PAY = "https://www.189.cn/client/wap/wapproject/wapclient/order/order_list.html?ticket=$ticket$&type=1";//待付款
   public static final String PROGRESSING = "https://www.189.cn/client/wap/wapproject/wapclient/order/order_list.html?ticket=$ticket$&type=2";//进行中
   public static final String DELIVERED = "https://www.189.cn/client/wap/wapproject/wapclient/order/order_list.html?ticket=$ticket$&type=3";//已发货
   public static final String NOLOGIN = "http://wapzt.189.cn/pages/orderQuery/selfService_qucklyOrderQuery.html?channel=0";//未登录订单

   public static final String START_CARD_ACTIVITY = "https://www.189.cn/images/client/commonupload/2019/1/23/205629/wuyouka/index.html?cmpid=scjh-scep-tcnr-wuyouka";//小图无忧卡
   public static final String UPGRADE_5G_ACTIVITY = "https://wapact.189.cn:9001/5gshengji/5GshengjiOtherLoginApp.html?ticket=$ticket$";//大图升5g
   public static final String ONE_CLICK_DIAGNOSIS = "https://pms.189.cn/cljy-web/huangoSso/login?moduleName=yjzd&ticket=$ticket$&fromSystem=huango&shopid=yjzd&cmpid=yjzd-wdpdwdfw-chachongjiao";//一键诊断

   public static final String URL_VIDEO_ONE = "http://im.189.cn:80/svideo/nA36Nv";
   public static final String URL_VIDEO_TWO = "http://im.189.cn:80/svideo/baueue";
   public static final String URL_VIDEO_THREE = "http://im.189.cn:80/svideo/fqAnQ3";
   public static final String URL_VIDEO_FOUR = "http://im.189.cn:80/svideo/aQfaYv";
   public static final String URL_VIDEO_FIVE = "http://im.189.cn:80/svideo/zqaARz";
   public static final String URL_VIDEO_SIX = "http://im.189.cn:80/svideo/7juqeq";
   public static final String URL_VIDEO_SEVEN = "http://im.189.cn:80/svideo/2M7j6f";
   public static final String URL_VIDEO_EIGHT = "http://im.189.cn:80/svideo/BjMRVj";
   public static final String URL_VIDEO_NINE = "http://im.189.cn:80/svideo/bMnq6r";
   public static final String URL_RECHARGE_RECORD_KF = "http%3A%2F%2Fxiaozhi.189.cn%3A8082%2FAPPOnlineCustomer%2Fservlet%2Frobot%3Fparams%3D%7B%22applyCode%22%3A%22huangososo%22%2C%22mobile%22%3A%22%22%2C%22ticket%22%3A%22%24ticket%24%22%2C%22quest%22%3A%22%22%2C%22channel%22%3A%22goAPP%22%7D";
   public static final String URL_BILL = "https://lm.189.cn/yearbill/yearbill_details.html?ct=1&ticket=$ticket$&cmpid=jt-xiaochengxu-app-fenxiang&version=$version$";
   public static final String URL_AUTOCHARGE = "https%3A%2F%2Fwww.189.cn%2Fclient%2Fwap%2Fwapproject%2Fnewwap%2Fautocharge%2Findex.html%23%2F%3Fticket%3D%24ticket%24%26version%3D%24version%24";
   // 金豆兑好礼
   public static final String URL_GLOD_BEAN = "https%3a%2f%2fwapact.189.cn%3a9001%2fJinDouMall%2fJinDouMall_luckDraw.html%3fticket%3d%24ticket%24%26cmpid%3djt-khd-jdsc-wdjd_2";
   // 我的订单
   public static final String URL_MY_ORDER = "https%3a%2f%2fwww.189.cn%2fclient%2fwap%2fwapproject%2fwapclient%2forder%2forder_list.html%3fticket%3d%24ticket%24";
   // 在线客服
   public static final String URL_ONLINE_CUSTOMER = "http%3A%2F%2Fxiaozhi.189.cn%3A8082%2FAPPOnlineCustomer%2Fservlet%2Frobot%3Fparams%3D%7B%22applyCode%22%3A%22huangososo%22%2C%22mobile%22%3A%22%22%2C%22ticket%22%3A%22%24ticket%24%22%2C%22quest%22%3A%22%22%7D";
   /*本地写死URL的静态页面---end---*/

   //创建快捷方式弹窗-点击了解详情按钮
   public static final String CREATE_SHORTCUT_LEARN_MORE = "https://www.189.cn/wapportalweb/hasdesktop/index.html#/";

   public final static String SRC = "110003";// "990001";
   public final static String PWD = "Sid98s";// "aOzny2";
   public final static String SHOP = "20002"; // "20002" 安卓手机店铺ID
   public final static String SHOPORIGINAL = "20005"; // "20005" 安卓手机原价充值店铺ID
   public final static String SHOPBROADBAND = "20027"; // "20027" 安卓宽带店铺ID
   public final static String SHOP4G = "20008";// "20008"

   public final static String GUEST_ID = "bank888";

   public final static String PAY_SCENE_UNKNOWN = "0";// 未知
   public final static String PAY_SCENE_CALL_RECHARGE = "1";// 话费直冲
   public final static String PAY_SCENE_CALL_BUYCARD = "3";// 购话费卡
   public final static String PAY_SCENE_FLOW_RECHARGE = "2";// 流量直冲
   public final static String PAY_SCENE_FLOW_BUYCARD = "4";// 购流量卡
   public final static String PAY_SCENE_NUM_BUYCARD = "5";// 购买号卡
   public final static String PAY_SCENE_BUYPHONE = "6";// 购买手机
   public final static String PAY_SCENE_BUYXIAOHAO = "7";// 购买小号
   public final static String PAY_SCENE_BUY_FLOW_PACKAGE = "8";// 购买流量包
   public final static String CHARGE_CARD_RESULT = "9";//充值卡充值。

   //后激活接口调用场景
   public final static String HUOTIJIHUO_TYPE_QUERY_DETAIL = "3";// 详单查询
   public final static String HUOTIJIHUO_TYPE_XIAOHAO_ORDER = "4";// 小号订购

   public static class ResponseErrorCode {
      public static final String TokenInvalid = "X104";//X104:token校验失败
      public static final String BroadbandTokenInvalid = "X105";//X105:宽带token校验失败
      public static final String BroadbandNoLogin = "X108";//X108:宽带未登录
      public static final String PhoneNoLogin = "X110";//X110:手机未登录
      public static final String TokenException = "X201";//X201:手机token异常
      public static final String NETWORK_NO_CONNECT = "001"; // 非业务逻辑接口失败
      public static final String ClusterException = "999";// 容灾
      public static final String ERROR_CODE_STATE_EXCEPTION = "901003";// 号码状态异常
   }

   public static class SHOP_TYPE {
      public static final int SHOPTYPE_PHONENUM = 0;//靓号
      public static final int SHOPTYPE_BD = 1;//百搭销售品， 裸机
      public static final int SHOPTYPE_PKG_LEXIANG = 2;//单套餐 4g乐享
      public static final int SHOPTYPE_PKG_CLOUD = 3;//单套餐 4g飞young云卡
      public static final int SHOPTYPE_PKG_GRMAKE4G = 4;//单套餐 4g个人定制
      public static final int SHOPTYPE_JK_GRMAKEANDPHONE = 5;//机卡组合：个人定制+裸机
      public static final int SHOPTYPE_JK_LX4G = 6;//机卡组合：乐享4G
      public static final int SHOPTYPE_JK_CLOUD = 7;//机卡组合：飞young云卡
      public static final int SHOPTYPE_HEYUEJI_SINGLE = 8;//单档位合约机
      public static final int SHOPTYPE_HEYUEJI_KINDS = 9;//多档位合约机
      public static final int SHOPTYPE_OLD_USER = 10;// 老用户续约
   }

   /**
    * 升级场景
    */
   public static class UPDATE_FLAG {
      public static final String FORMAL = "F"; // 正式版本，走正常升级流程
      public static final String GRAY = "G"; // 正式版本（F版本）对应补丁版本，当正式版本有问题时，该版本作为补丁版本升级（维持F版本相同版本号，G版本不弹升级，F版本弹升级）
   }

   /**
    * 验证码场景
    */
   public static class RANDOM_FLAG {
      public static final String FLAG_CALL = "1";//兑换话费
      public static final String FLAG_FLOW = "2";//兑换流量
   }

   /**
    * 订购场景,0：话费充值,1：流量订购
    */
   public static class LOGIN_ERROR_CODE {
      public static final String ERROR_CODE_FOR_LOGIN_FAIL = "3001";//登录失败5次错误码
      public static final String ERROR_CODE_FOR_NOT_REGISTER = "3002";//该号码可能没有注册过的错误码
      public static final String ERROR_CODE_FOR_AUTHORIZATION_FAIL = "3003";//登录授权失败的错误码
      public static final String ERROR_CODE_FOR_SAFE_CHECK_DIALOG = "3005";// 图片安全弹窗校验错误码
      public static final String ERROR_CODE_FOR_NUMBER_SAFE_CHECK_DIALOG = "3006";// 数字安全弹窗校验错误码
   }


   /**
    * @author tangwzh
    * @function 省份标识码规范
    * @remark 山西:SHANXI,陕西:SHANXI_
    * @createTime 2013-4-2 下午01:59:38
    */
   public enum ProvinceID {
      BEIJING("01"), SHANGHAI("02"), TIANJIN("03"), CHONGQING("04"), HEBEI(
              "05"), SHANXI("06"), NEIMENGGU("07"), LIAOLING("08"), JILIN(
              "09"), HEILONGJIANG("10"), JIANGSU("11"), ZHEJIANG("12"), ANHUI(
              "13"), FUJIAN("14"), JIANGXI("15"), SHANDONG("16"), HENAN("17"), HUBEI(
              "18"), HUNAN("19"), GUANGDONG("20"), GUANGXI("21"), HAINAN("22"), SICHUAN(
              "23"), GUIZHOU("24"), YUNNAN("25"), XIZANG("26"), SHANXI_("27"), GANSU(
              "28"), QINGHAI("29"), NINGXIA("30"), XINJIANG("31"), TAIWAN(
              "32"), XIANGGANG("33"), AOMEN("34"), QUANGUO("35"), HAIWAI("36");

      private final String code;

      ProvinceID(String code) {
         this.code = code;
      }

      public String getCode() {
         return code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * @author tangwzh
    * @function 帐户类型规范
    * @remark ELEC开头为电子渠道，UAM为UAM
    * @createTime 2013-4-2 下午02:25:02
    */
   public enum AccountType {
      ELEC_SITES_REGISTER_ACCOUNT("1"), // 网站注册用户
      ELEC_MOBILE_ACCOUNT("201"), // 手机用户
      ELEC_TELEPHONE_ACCOUNT("202"), // 固话用户
      ELEC_BROADBAND_ACCOUNT("203"), // 宽带用户
      ELEC_OTHERS_ACCOUNT("204"), // 异网用户
      ELEC_QQ_ACCOUNT("301"), // QQ 用户
      ELEC_SINA_MICROBLOG_ACCOUNT("302"), // 新浪微博
      ELEC_RENREN_ACCOUNT("303"), // 人人
      ELEC_TIANYI_ACCOUNT("304"), // 天翼
      ELEC_TEMP_ACCOUNT("4"), // 临时用户
      UAM_CLIENT_TYPE("0000000"), // 客户类型
      UAM_CLIENT_CARD("0000001"), // 客户卡
      UAM_ACCOUNT_TYPE("1000000"), // 帐户类型
      UAM_PRODUCT_TYPE_TELEPHONE_AUTH("c2000001"), // 固话（产品类型）认证接口
      UAM_PRODUCT_TYPE_TELEPHONE("2000001"), // 固话（产品类型）
      UAM_PRODUCT_TYPE_BROADBAND_AUTH("c2000002"), // 宽带（产品类型）认证接口
      UAM_PRODUCT_TYPE_BROADBAND("2000002"), // 宽带（产品类型）
      UAM_PRODUCT_TYPE_XIAOLINGTONG("2000003"), // 小灵通（产品类型）
      UAM_PRODUCT_TYPE_MOBILE_AUTH("c2000004"), // 手机（产品类型）认证接口传
      UAM_PRODUCT_TYPE_MOBILE("2000004"), // 手机（产品类型）
      UAM_REGISTER_ACCOUNT("2000005"), // 注册帐号
      UAM_OTHERS_MOBILE("3000001"); // 异网手机

      private final String code;

      AccountType(String code) {
         this.code = code;
      }

      public String getCode() {
         return code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * @author tangwzh
    * @function 销售品类型规范
    * @createTime 2013-4-2 下午02:50:53
    */
   public enum SalesProductType {
      BARE_TERMINAL("1"), // 裸终端
      ACCESSORY("2"), // 配件
      BACK_COVER("3"), // 后壳
      RECHARGE_CARD("4"), // 充值卡
      BASE_SALES_PRODUCT("5"), // 基础销售品
      SURFING_CARD("6"), // 上网卡
      GIFT("7"), // 赠品
      CONTRACT_MACHINE("8"), // 合约机
      CARD_NUM("9"), // 号卡
      CLOUD_CARD("10"), // 套餐号卡 / 云卡
      CONTRACT_MACHINE_BIND_CLOUD_CARD("11"), // 合约机绑云卡
      UIM_CARD("12"), // UIM 卡
      YIYOU_CARD("13"), // 翼游卡
      BLOCK_SETMEAL("21"), // 积木套餐
      BLOCK_SETMEAL_TERMINAL("22"), // 积木套餐 + 终端
      PURE_FLOW("23"), // 纯流量
      BANK_RECHARGE_CARD("41"), // 银行卡直充充值卡
      FLOW_CARD("42"), // 流量卡
      BANK_FLOW_CARD("43"), // 银行卡直充流量卡
      PROMOTION_FLOW_CARD("44"), // 促销类流量卡
      SINGLE_INCREMENT_SALES_PRODUCT("60"), // 单增值销售品
      INCREMENT_BIND_SETMEAL("61"), // 增值捆绑套餐
      COMPOUND_BIND_SETMEAL("62"), // ( 传统+ 增值 )捆绑套餐
      FLOW_KING_CONTRACT_MACHINE("81"), // 流量帝 + 合约机
      BEAUTIFUL_NUM("88"), // 靓号
      OTHERS("99"), // 其他
      SPECIAL_EVENTS("999"); // 特殊活动

      public static Map<String, String> mSalesProductTypes = new HashMap<String, String>();

      static {
         mSalesProductTypes.put("1", "裸终端");
         mSalesProductTypes.put("2", "配件");
         mSalesProductTypes.put("3", "后壳");
         mSalesProductTypes.put("21", "积木套餐");
         mSalesProductTypes.put("210", "游戏达人积木套餐");
         mSalesProductTypes.put("211", "积木套餐+B类裸终端");
         mSalesProductTypes.put("212", "积木套餐+C类裸终端");
         mSalesProductTypes.put("22", "积木套餐+裸终端");
         mSalesProductTypes.put("23", "飞young纯流量卡");
         mSalesProductTypes.put("231", "飞young纯流量卡+B类裸终端");
         mSalesProductTypes.put("232", "飞young纯流量卡+C类裸终端");
         mSalesProductTypes.put("24", "飞young纯流量+裸终端");
         mSalesProductTypes.put("990", "一元抢纯流量+A类裸终端");
         mSalesProductTypes.put("991", "一元抢纯流量+B类裸终端");
         mSalesProductTypes.put("992", "一元抢+C类裸终端");
         mSalesProductTypes.put("999", "一元抢飞young纯流量卡");
         mSalesProductTypes.put("25", "19元包年打");
         mSalesProductTypes.put("250", "19元包年打+A类裸终端");
         mSalesProductTypes.put("252", "19元包年打+C类裸终端");
         mSalesProductTypes.put("4", "充值卡");
         mSalesProductTypes.put("42", "流量卡");
         mSalesProductTypes.put("44", "促销类流量卡");
         mSalesProductTypes.put("41", "银行卡直充充值卡");
         mSalesProductTypes.put("46", "银行卡直充固话");
         mSalesProductTypes.put("47", "银行卡直充宽带");
         mSalesProductTypes.put("43", "银行卡直充流量卡");
         mSalesProductTypes.put("7", "赠品");
         mSalesProductTypes.put("71", "合约赠品");
         mSalesProductTypes.put("77", "虚拟赠品");
         mSalesProductTypes.put("8", "合约机");
         mSalesProductTypes.put("82", "C类合约机");
         mSalesProductTypes.put("51", "B类机卡组合");
         mSalesProductTypes.put("52", "B类单套餐");
         mSalesProductTypes.put("53", "B类单宽带");
         mSalesProductTypes.put("54", "B类融合套餐");
         mSalesProductTypes.put("1001", "卡密充话费");
         mSalesProductTypes.put("1003", "卡密充流量");
         mSalesProductTypes.put("10", "套餐号卡 / 云卡");
         mSalesProductTypes.put("11", "合约机+云卡");
         mSalesProductTypes.put("110", "云卡+A类裸终端");
         mSalesProductTypes.put("112", "云卡+C类裸终端");
         mSalesProductTypes.put("113", "云卡+D类裸终端");
         mSalesProductTypes.put("13", "翼游卡");
         mSalesProductTypes.put("45", "流量帝");
         mSalesProductTypes.put("6", "B类上网卡");
         mSalesProductTypes.put("62", "A类上网卡");
         mSalesProductTypes.put("61", "4G上网卡");
         mSalesProductTypes.put("601", "上网卡+终端");
         mSalesProductTypes.put("602", "上网卡+配件");
         mSalesProductTypes.put("611", "4G上网卡+终端");
         mSalesProductTypes.put("612", "4G上网卡+配件");
         mSalesProductTypes.put("81", "合约机+流量帝");
         mSalesProductTypes.put("88", "靓号");
         mSalesProductTypes.put("881", "靓号+云卡");
         mSalesProductTypes.put("882", "靓号+积木");
         mSalesProductTypes.put("883", "靓号+纯流量");
         mSalesProductTypes.put("884", "靓号+乐享上网");
         mSalesProductTypes.put("885", "靓号+乐享聊天");
         mSalesProductTypes.put("886", "靓号+19元包年打");
         mSalesProductTypes.put("9", "号卡");
         mSalesProductTypes.put("90", "单套餐号卡");
         mSalesProductTypes.put("902", "乐享套餐+C类裸终端");
         mSalesProductTypes.put("91", "个人定制单号卡");
         mSalesProductTypes.put("920", "个人定制机卡组合");
         mSalesProductTypes.put("922", "个人定制机卡组合A+C");
         mSalesProductTypes.put("12", "UIM卡/SIM卡");
         mSalesProductTypes.put("14", "无线宽带续费");
         mSalesProductTypes.put("95", "基础销售品");
         mSalesProductTypes.put("60", "单增值销售品");
         mSalesProductTypes.put("96", "业务办理销售品");
         mSalesProductTypes.put("961", "增值捆绑套餐");
         mSalesProductTypes.put("962", "(传统+增值)捆绑套餐");
         mSalesProductTypes.put("998", "工本费");
         mSalesProductTypes.put("99", "其他");

         // 以下是文档中被删掉了的
         mSalesProductTypes.put("5", "基础销售品");
         mSalesProductTypes.put("960", "单增值销售品");
         mSalesProductTypes.put("999", "一元抢飞young纯流量卡");
      }

      private final String code;

      SalesProductType(String code) {
         this.code = code;
      }

      public String getCode() {
         return code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * @author tangwzh
    * @function 订单类型规范
    * @createTime 2013-4-2 下午02:56:11
    */
   public enum OrderType {
      GENERAL_ORDER("1"), // 普通订单
      RESERVE_ORDER("2"), // 预定订单
      RECHARGE_CARD_ORDER("3"), // 充值卡订单
      BANK_CARD_ORDER("4"); // 银行卡直充订单

      private final String code;

      OrderType(String code) {
         this.code = code;
      }

      public String getCode() {
         return code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * @author tangwzh
    * @function 业务动作
    * @createTime 2013-4-7 下午02:39:36
    */
   public enum IncrementServiceOperate {
      ORDER("0"), // 订购
      UNSUBSCRIBE("1"); // 退订

      private final String code;

      IncrementServiceOperate(String code) {
         this.code = code;
      }

      public String getCode() {
         return code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * @author tangwzh
    * @function 增值销售品类型
    * @remark 目前客户端可以办理的增值业务均属于类型0
    * @createTime 2013-4-7 下午02:46:30
    */
   public enum IncrementProductsType {
      PURE_VALUE_ADDED_PRODUCTS("0"), // 纯增值销售品
      INCREMENT_BIND_SETMEAL("1"); // 增值捆绑套餐

      private final String code;

      IncrementProductsType(String code) {
         this.code = code;
      }

      public String getCode() {
         return code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * @author tangwzh
    * @function 积分消费记录_标识类型
    * @remark 客户统一标识编码(16位)
    * @createTime 2013-4-7 下午04:37:42
    */
   public enum PointCosumeHistoryMarkType {
      MOBILE_ACCOUNT("11"), // 手机号码
      TELEPHONENO_ACCOUNT("12"), // 固话号码
      BROADBAND_ACCOUNT("13"), // 宽带帐号
      CUSTOMER_UNIFY_MARK_CODE("15"); // 客户统一标识编码(16位)

      private final String code;

      PointCosumeHistoryMarkType(String code) {
         this.code = code;
      }

      public String getCode() {
         return code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 排序规则取值
    *
    * @author kehw
    */
   public enum Sortby {
      MOBILE_ASC("1"), // 号码升序
      MOBILE_DESC("2"), // 号码降序
      PRICE_ASC("3"), // 价格升序
      PRICE_DESC("4"); // 价格降序

      private final String code;

      Sortby(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 靓号类型
    *
    * @author kehw
    */
   public enum PrettyPattern {
      AAAA("1"), AAA("2"), ABCD("3"), AABB("4"), ABC("5"), AA("6");

      private final String code;

      PrettyPattern(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 号头
    *
    * @author kehw
    */
   public enum Headnumber {
      ALL(""), H_133("133"), H_153("153"), H_180("180"), H_181("181"), H_189(
              "189"), H_177("177"), H_1700("1700");

      private final String code;

      Headnumber(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 区域编码类型
    *
    * @author kehw
    */
   public enum AreaCodeType {
      ORGANIZATION("1"), // 组织机构
      LOGISTICS("2"), // 物流
      BROADBAND("3");// 小区宽带

      private final String code;

      AreaCodeType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 团购秒杀标识
    *
    * @author kehw
    */
   public enum GpFlag {
      GENERAL_ORDER("0"), // 普通订单
      GROUP_BUYING("1"), // 团购订单
      SECKILL_ORDER("2"); // 秒杀订单

      private final String code;

      GpFlag(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 配送时间
    *
    * @author kehw
    */
   public enum DeliveryTime {
      WORKING_DAYS("1"), // 只工作日送货（双休日、假日不用送）
      NONWORKING_DAYS("2"), // 只双休日、假日送货（工作日不用送）
      EVERYDAY("3"); // 工作日、双休日与假日均可送货

      private final String code;

      DeliveryTime(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 支付方式
    *
    * @author kehw
    */
   public enum PayMethod {
      ONLINE_PAYMENT("0"), // 网上支付
      CASH_ON_DELIVERY("1"), // 货到付款
      PAID_IN_INSTALLMENTS("2"), // 分期支付
      TAKE_THEIR_OWN("3"); // 上门自提

      private final String code;

      PayMethod(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 付款方式
    *
    * @param code
    * @return paymethodname
    * @author jiangwenxin
    * @createTime 2014年8月20日 上午11:18:24
    */
   public static String getPayMethodName(String code) {
      if (code == null) {
         return "无";
      } else if (code.equals("0")) {
         return "在线支付";
      } else if (code.equals("1")) {
         return "货到付款";
      } else if (code.equals("10")) {
         return "货到付款-农业银行信用卡分期POS刷卡";
      } else if (code.equals("11")) {
         return "货到付款-广发银行信用卡分期POS刷卡";
      } else if (code.equals("2")) {
         return "分期支付";
      } else if (code.equals("21")) {
         return "北京银行-冻结";
      } else if (code.equals("3")) {
         return "上门自提";
      } else if (code.equals("31")) {
         return "以旧换新-货到付款";
      } else if (code.equals("4")) {
         return "货到付款-现金";
      } else if (code.equals("40")) {
         return "山西工行分期付款";
      } else if (code.equals("5")) {
         return "货到付款-银联POS刷卡";
      } else if (code.equals("6")) {
         return "货到付款-招商银行信用卡分期POS刷卡";
      } else if (code.equals("7")) {
         return "货到付款-中国银行信用卡分期POS刷卡";
      } else if (code.equals("8")) {
         return "货到付款-工商银行信用卡分期POS刷卡";
      } else if (code.equals("9")) {
         return "货到付款-建设银行信用卡分期POS刷卡";
      } else {
         return "其他";
      }
   }

   /**
    * 收藏ActionType
    *
    * @author kehw
    */
   public enum SCActionType {
      USERNAME_AS_KEYWORD("1"), // 以用户名为存取主键
      USERID_AS_KEYWORD("2"); // 以userid为存取主键

      private final String code;

      SCActionType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 区号查询ActionType
    *
    * @author kehw
    */
   public enum ACActionType {
      AREACODE_TO_CITY("1"), // 区号查询城市
      CITY_TO_AREACODE("2"); // 城市查询区号

      private final String code;

      ACActionType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 营业厅及WIFI列表类型
    *
    * @author kehw
    */
   public enum WifiHallType {
      WIFI("3"), // WIFI
      HALL("4"); // 营业厅

      private final String code;

      WifiHallType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 充值卡类型
    *
    * @author kehw
    */
   public enum CardType {
      CHARGE("1"), // 充值卡
      FLOW("2"), // 流量卡
      CONTRACT("7"); // 合同号

      private final String code;

      CardType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 充值类型
    */
   public enum ChargeType {
      FIXEDNO("3"), // 固话
      BROADBAND("4");// 宽带
      private final String code;

      ChargeType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 轮换分类
    *
    * @author kehw
    */
   public enum SwitchType {
      MESSAGE_CENTER("1"), // 消息中心
      HOMEPAGE_SWITCH("2"), // 首页轮换位——首页焦点图
      PROMOTION_LIST("3"), // 促销优惠列表
      ANNOUNCEMENT("4"), // 公告
      TIANYI_APP_SWITCH("5"), // 天翼应用轮换位
      HOMEHUAN_SHANMENU("6"), // 欢首页-圆盘ICON——首页微笑圆弧
      HOMEGO_AREA("7"), // Go首页-专区
      PROMOTION_TOP("8"), // 优惠首页顶部广告——已废弃
      PROMOTION_PHONE("9"), // 优惠首页手机专区——已废弃
      CHARGE("10"), // 充值活动获取
      HOME_PAGE_SECOND("11"), // 首页4G专区3个广告位
      SLIDDINGMENU_RECOMMEND("12"), // 右边侧滑页面推荐广告位
      MORE_LAST_ICON("13"), // 更多最后一个图标
      ORDERDONE_CHARGETYPE("14"), // 订单完成-银行卡充值类型
      QUERYCATELOG_DRAWABLE("15"), // 新版查询-抽屉菜单——已废弃
      VIRTUALNO_SET("16"), // 副号-设置页面——已废弃
      ORDERDONE_CHARGECARDTYPE("17"), // 订单完成-充值卡充值类型
      PROMOTION_BIG_IMAGE("101"), // 优惠首页大图广告（5.3版本）
      PROMOTION_PHONE_TOP("102"), // 优惠-手机频道顶部广告位（5.3版本）
      PERSONAL_CENTER("201"), // 个人中心（5.3版本）
      MORE_DISK_ICON("301"), // 更多-圆盘及ICON（5.3版本）
      RECHARGE_SUCCESS_FLOW4G("401"), // 订购成功页配置-4G流量包（5.3版本）
      RECHARGE_FAIL_FLOW4G("402"), //  订购失败页配置-4G流量包（5.3版本）
      BROADBAND_AREA("501"),// 宽带专区配置（5.3版本）——已废弃
      LLB_ORDER_FLOW4G("503"), // 流量包订购（5.3版本）
      PROMOTION_TOP_MENU("100"), // 优惠顶部菜单（5.3版本）
      GET_COLLECTION_LIST_FAIL("601"), //  收藏列表获取失败 （5.3版本）
      GET_MYADDRESS_FAIL("602"),// 查看我的地址失败 （5.3版本）
      RESET_PASSWORD_FAIL("603"), // 重置密码失败（5.3版本）
      DIFFERENT_NETWORK_REGISTERUSER_SUCCESS("701"),// 异网用户注册成功广告位（5.5版本）
      SIGNSCORE("702"),// 签到广告位（5.5版本）
      LIFE_COUPONS("703"),// 生活手机券跳转（5.5版本）
      DIFFERENT_NETWORK_FIVE_CIRCLE("1001"),//异网五圆盘（5.5版本）
      DIFFERENT_NETWORK_SHAN_MENU("1002"),//异网微笑曲线 （5.5版本）
      DIFFERENT_NETWORK_HELPER("1003"),//异网小助手（5.5版本）
      DIFFERENT_NETWORK_GUESS_LIKE("1004"),//异网猜你喜欢（5.5版本）
      DIFFERENT_NETWORK_SWITCH("1005"),//异网首页轮播广告（5.5版本）
      DIFFERENT_NETWORK_MORE("1006"),//异网更多（5.5版本）
      DIFFERENT_NETWORK_BROADBAND_AREA("1007"),//异网宽带专区（5.5版本）——已废弃
      DIFFERENT_NETWORK_PERSONAL_CENTER("1008"),//异网我的频道（5.5版本）
      XIAOHAO_TRANSACT_RESULT("1009"),//小号办理结果页面-本网（5.5版本）
      DIFFERENT_NETWORK_XIAOHAO_TRANSACT_RESULT("1010"),//小号办理结果页面-异网（5.5版本）
      HOME_PAGE_DROP_ADITEM("2000"),//首页下拉广告位（本网）(5.6版本)——已废弃
      BROADBAND_AREA_NEW_USER("2011"),//宽带专区（新用户）（5.6版本）
      BROADBAND_AREA_OLD_USER("2012"),//宽带专区（老用户）（5.6版本）
      SERVICE_OPTIONAL_PACKAGE("2020"),//已订业务套餐可选包（5.6版本）——已废弃
      SERVICE_VALUE_ADD("2021"),//已订业务增值业务（5.6版本）
      QUERY_ACCOUNT("2080"),//查询账户（6.1版本）
      RECHARGE_TAB("3010"),//充值频道顶部TAB（6.2版本）
      RECHARGE_CALL_SERVICE("3020"),//充话费的特色服务（6.2版本）
      RECHARGE_FLOW_SERVICE("3030"),//充流量包的特色服务（6.2版本）
      RECHARGE_FLOW_RECOMMEND("3040"),//充流量包的为你推荐（6.2版本）
      XIAOHAO_IMAIN("1000"),//小号我的频道（6.2版本）
      MYCHANNEL_SUSPENDWINDOW("4010"),//我的频道浮窗（6.4版本）
      EXCHANGECHARGE_RESULT("900"),//积分兑换话费结果页（6.7版本）
      EXCHANGEFLOW_RESULT("901");//积分兑换流量包结果页（6.7版本）

      private final String code;

      SwitchType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }

    /*  public static SwitchType getMoreDisk() {
         if (MyApplication.mDataCache.isCtUser) {
            return MORE_DISK_ICON;
         } else {
            return DIFFERENT_NETWORK_MORE;
         }
      }*/

     /* public static SwitchType getXiaohaoTransactResult() {
         if (MyApplication.mDataCache.isCtUser) {
            return XIAOHAO_TRANSACT_RESULT;
         } else {
            return DIFFERENT_NETWORK_XIAOHAO_TRANSACT_RESULT;
         }
      }*/

   }

   /**
    * 分类类型
    *
    * @author kehw
    */
   public enum AppType {
      GAME("1"), // 游戏
      APPLICATION("2"); // 应用

      private final String code;

      AppType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 订单状态类型
    *
    * @author kehw
    */
   public enum OrderStatusType {
      ALL("0"), // 全部
      VALIDORDER("1"), // 有效订单
      CANCELEDORDER("2"), // 已取消订单
      WAITPAIDORDER("3"), // 待支付订单
      NOTRECEIPTORDER("4"), // 待收货订单
      COMPLETEORDER("5");// 已完成订单
      private final String code;

      OrderStatusType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 消息类型
    *
    * @author zhuofq
    */
   public enum MsgType {
      MSG_CENTER("0"), // 消息中心
      MSG_PUSH("1"); // 推送

      private final String code;

      MsgType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 靓号类型
    *
    * @author zhuofq
    */
   public enum PhoneNumType {
      ALL(""), // 全部靓号
      OTHER("0"), // 无类型
      TOP("1"), // 顶级靓号
      LOVE("2"), // 爱情靓号
      BUSINESS("3"), // 事业靓号
      WITHOUT4("4"),//尾号无4
      FREE("4"), // 0元靓号
      BD_NICE("998"), // 百搭靓号
      BD_FREE("999"); // 百搭0元靓号

      private final String code;

      PhoneNumType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 靓号类型(静态String类型不用枚举)
    */
   public static class PhoneNumTypeString {
      public static final String ALL = "";// 全部靓号
      public static final String OTHER = "0";// 无类型
      public static final String TOP = "1";// 顶级靓号
      public static final String LOVE = "2";// 爱情靓号
      public static final String BUSINESS = "3";// 事业靓号
      public static final String WITHOUT4 = "4";// 尾号无4
      public static final String FREE = "4";// 0元靓号
      public static final String BD_NICE = "998";// 百搭靓号
      public static final String BD_FREE = "999";// 百搭0元靓号
      public static final String NEWWITHOUT4 = "5";// 新的尾号无4判定标识
   }

   /**
    * 自电商收益状态
    *
    * @作者 zhangyi
    * @创建时间 2015-8-19 下午9:59:46
    * @版本
    * @------修改记录-------
    * @修改人
    * @版本
    * @修改内容
    */
   public enum HdInComeStatus {
      ALL(""), // 全部
      PREPARE("1"), // 预收益
      SUCCESS("2"), // 已结佣
      FAIL("3"); // 已失效

      private final String code;

      HdInComeStatus(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 优惠券状态
    *
    * @作者 zhangyi
    * @创建时间 2015-9-30 下午5:21:18
    * @版本
    * @------修改记录-------
    * @修改人
    * @版本
    * @修改内容
    */
   public enum CouponStatus {
      ALL("0"), // 全部
      USED("1"), // 已使用
      NOTUSED("2"), // 未使用
      EXPIRED("3"); // 已过期

      private final String code;

      CouponStatus(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 业务类型
    *
    * @author zhangyi
    */
   public enum ServiceType {
      GET_USER_PRIVILEGE("1"), // 获取用户特权
      TIANYIYUN("2"), // 天翼云
      MAIL_189("3"); // 189邮箱

      private final String code;

      ServiceType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 身份证图片类型
    *
    * @author zhangyi
    */
   public enum IdCardType {
      FRONT("30010003"), // 身份证正面
      BACK("30010004"), // 身份证反面
      HAND("30010005"); // 身份证手持照

      private final String code;

      IdCardType(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   public enum LevelType {
      MAIN, MINE
   }

   public enum CustLevel {
      LEVEL0("0"), LEVEL1("1"), LEVEL2("2"), LEVEL3("3"), LEVEL4("4"),
      LEVEL10("10"), LEVEL11("11"), LEVEL12("12"), LEVEL13("13"),
      LEVEL14("14"), LEVEL15("15"), LEVEL16("16"), LEVEL17("17");

      private final String code;

      CustLevel(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   public enum UserLevel {
      DIAMONDLEVEL("钻石卡"), GOLDENLEVEL("金卡"), SILVERLEVEL("银卡"), NOMALLEVAL(
              "普卡");

      private final String code;

      UserLevel(String code) {
         this.code = code;
      }

      @Override
      public String toString() {
         return code;
      }
   }

   /**
    * 流量查询流量类型
    *
    * @作者 zhangyi
    * @创建时间 2015-11-12 上午11:17:55
    * @版本
    * @------修改记录-------
    * @修改人
    * @版本
    * @修改内容
    */
   public static class FLOW_TYPE {
      public static final String INTERNATIONAL = "21";//国际流量
      public static final String NATIONAL = "22";//国内流量
      public static final String PROVINCE = "23";//省内流量
      public static final String DIRECTIONAL = "24";//定向流量
      public static final String FREETIME = "25";//闲时流量
      public static final String LOCAL = "26";//本地流量
      public static final String OTHER = "27";//其他
   }

   /**
    * 流量包订单状态
    */
   public static class FLOW_PACKAGE_ORDER_STATUS {
      public static final String WAIT_FOR_PAYMENT = "10100";// 等待支付/未支付
      public static final String PAYMENT_SUCCESSFUL = "10101";// 支付成功
      public static final String PAYMENTS = "10702";// 支付中
      public static final String PROCESSING = "20100";// 办理中
      public static final String SUCCESS = "20101";// 办理成功
      public static final String FAIL = "20102";// 办理失败
   }

   /**
    * 小号最近订单状态
    */
   public static class XH_LAST_ORDER_STATUS {
      public static final String WAIT_FOR_PAYMENT = "10100";// 等待支付/未支付
      public static final String PAYMENT_SUCCESSFUL = "10101";// 支付成功
      public static final String DOCUMENT_VERIFICATION_NOT_PASS = "10103";// 证件审核不通过
      public static final String ORDER_CLOSED = "10104";// 订单关闭
      public static final String DOCUMENT_RESUBMITTED = "10107";// 证件已重新提交
      public static final String WAITING_FOR_DOCUMENT_REVIEW = "10109";// 等待证件审核
      public static final String CANCELLED = "10701";// 已取消
      public static final String PAYMENTS = "10702";// 支付中
      public static final String PAYMENT_FAILED = "10703";// 支付失败
      public static final String REFUNDING = "11201";// 退款中
      public static final String REFUND_SUCCESSFULLY = "11202";// 退款成功
      public static final String REFUND_FAILED = "11203";// 退款失败
      public static final String PROCESSING = "20100";// 办理中
      public static final String SUCCESS = "20101";// 办理成功
      public static final String FAIL = "20102";// 办理失败
   }

   /**
    * 小号最近订单类型
    */
   public static class XH_LAST_ORDER_TYPE {
      public static final String ORDER = "196";// 订购
      public static final String RENEW = "9196";// 续订
      public static final String UNSUBSCRIBE = "8196";// 退订
   }

   /**
    * 积分兑换标识
    */
   public static class INTEGRAL_FLAG {
      public static final String CONVERTIBLE = "0";// 可兑换
      public static final String UNCONVERTIBLE = "1";// 不可兑换
   }

   /**
    * 流量包状态  1：订购；2：变更；3：已订购
    */
   public static class FLOW_STATE_FLAG {
      public static final String ORDER = "1";
      public static final String CHANGE = "2";
      public static final String ORDERED = "3";

   }

   /**
    * 取值标识
    */
   public static class SHOWFIELD_FLAG {
      public static final String USED = "1";// 已使用
      public static final String LEFT = "2";// 剩余
      public static final String OVER = "3";// 超出
   }

   /**
    * 话费取值标识
    */
   public static class BILLFIELD_FLAG {
      public static final String OWE = "0";// 欠费
      public static final String BALANCE = "1";// 余额
   }

   /**
    * 余额展示标识
    */
   public static class BALANCE_SHOW_FLAG {
      public static final String OWE = "0";// 您已欠费
      public static final String BALANCE = "1";// 话费余额
   }

   /**
    * 语音类型
    */
   public static class VOICE_TYPE {
      public static final String INTERNATIONAL = "21";// 国际
      public static final String NATIONAL = "22";// 国内
      public static final String PROVINCE = "23";// 省内
      public static final String RELATIVE = "24";// 主副卡
   }

   /**
    * 复合广告位接口CompositeAd场景类型——大数据接口
    */
   public static class MAIN_SCENE_TYPE {
      public static final String SCENE_ROUND = "1";// 圆盘
      public static final String SCENE_SMILE = "2";// 微笑圆弧
      public static final String SCENE_FOCUS = "3";// 焦点图
      public static final String SCENE_RECHARGE = "4";// 充值页面
      public static final String SCENE_SHOP_PROMOTION = "5";// 商城优惠
      public static final String SCENE_SHOP_PHONE = "6";// 商城手机
      public static final String SCENE_MYINFO = "7";// 我的频道
      public static final String SCENE_NEW_FUNCTION = "8";//新版700首页功能入口
      public static final String SCENE_NEW_FOCUS = "9";//新版700首页焦点图
      public static final String SCENE_FOUND_RECOMMEND = "10";//发现频道推荐页
   }

   /**
    * 700广告位信息接口QueryAdvertisingSpace场景类型
    */
   public static class AD {
      public static final String AD_FUNCTION = "1";//首页功能入口
      public static final String AD_FOCUS = "2";//首页焦点图
      public static final String AD_FOUND = "3";//发现频道标签
      public static final String AD_FOUND_RECOMMEND = "4";//发现频道推荐页
      public static final String AD_MALL_FOCUS = "5";//商城首页轮播图
      public static final String AD_MALL_MAIN_NAV = "6"; //商城首页主导航
      public static final String AD_MALL_DEPUTY_NAV = "7"; //商城首页副导航
      public static final String AD_MYINFO_ACTIVE_PIC = "8"; //我的频道-活动图片
      public static final String AD_MYINFO_FOCUS = "9"; //我的频道-轮播图广告
      public static final String AD_MESSAGE_TOP = "10"; //消息列表-顶部广告区域
      public static final String AD_PHONE_MAIN_FUNCTION = "11"; //7.4手机主功能区
      public static final String AD_YW_TOP_FOCUS = "12"; //7.4异网首页顶部广告位
      public static final String AD_5G_SPEED_TEST = "13"; //7.5.1 5G测速页
      public static final String AD_5G_SPEED_TEST_HISTORY = "14"; //7.5.1 5G测速历史页
      public static final String AD_FLOW_PACKAGE_FOCUS = "15"; //7.8.0 流量超市轮播图
      public static final String AD_FLOW_PACKAGE_TOP = "16"; //7.8.0 流量超市顶部tab栏
      public static final String AD_FLOW_PACKAGE_ORDER_SUCC = "17"; //7.8.0 流量包订购成功
      public static final String AD_FLOW_PACKAGE_ORDER_FAIL = "18"; //7.8.0 流量包订购失败
      public static final String AD_FLOW_PACKAGE_CHANGE_SUCC = "19"; //7.8.0 流量包变更成功
      public static final String AD_FLOW_PACKAGE_CHANGE_FAIL = "20"; //7.8.0 流量包变更失败
      public static final String AD_RECHARGE_BILL = "21"; //7.8充值页 充话费特色服务
      public static final String AD_RECHARGE_FLOW_PACKAGE = "22"; //7.8充值页 流量包特色服务
      public static final String AD_ADD_MORE = "23"; //7.8.2首页加号区
      public static final String AD_QUICK_FUNCTION = "24"; //7.8.2首页快捷
      public static final String AD_YW_TOP_FOCUS_NEW = "25"; //7.8.2异网首页顶部广告位
      public static final String AD_ROTATION_FOCUS = "26"; //7.8.2首页轮播图
      public static final String AD_RECHARGE_RESULT_MORE = "27"; //8.1.0充值结果页-更多服务
      public static final String AD_NEAR_BUSH = "28"; //8.3.0附近营业厅-轮播图
   }

   /**
    * 730广告位信息接口QueryMyChannelAdvSpace场景类型
    */
   public static class MyChannel_AD {
      public static final String AD_MY_INFORMATION = "1";//我的信息
      public static final String AD_SETTING = "2";//设置页面可配置项
      public static final String AD_ABOUT_US = "3";//关于我们可配置项（order=0的位置显示我的二维码）
      public static final String AD_NEW_SETTING = "4";//新版设置页可配置项（order=0的位置显示我的二维码）
   }

   /**
    * 注销接口Cancel注销类型
    */
   public static class Cancel_Type {
      public static final String LOGOUT = "1";//提交注销
      public static final String CANCEL_LOGOUT = "2";//撤销注销
   }

   /**
    * @desc:充值类型-状态
    * @author:zhangyz
    * @created at：2018/9/6 18:02
    **/
   public static class CHARGE_TYPE {
      public static final String E_BANK_SUCC = "1";//网银手机充值成功
      public static final String E_BANK_FAIL = "2";//网银手机充值失败
      public static final String E_BANK_ING = "9";//网银手机充值中
      public static final String CAMILO_SUCC = "3";//卡密充值成功
      public static final String CAMILO_FAIL = "4";//卡密充值失败
      public static final String INTEGRAL_SUCC = "5";//积分抵扣成功
      public static final String INTEGRAL_FAIL = "6";//积分抵扣失败
      public static final String FIXED_LINE_SUCC = "7";//网银固话充值成功
      public static final String FIXED_LINE_FAIL = "8";//网银固话充值失败
      public static final String FIXED_LINE_ING = "10";//网银固话充值中
      public static final String BROADBAND_SUCC = "11";//宽带充值成功
      public static final String BROADBAND_FAIL = "12";//宽带充值失败
      public static final String BROADBAND_ING = "13";//宽带充值中
   }

   /**
    * @desc:充值结果与方式(840版本供结果页使用)
    * @author:zhangyz
    * @created at: 2020/10/23 0023 10:46
    **/
   public static class TOP_UP_TYPE {
      public static final String TOP_UP_SUCC = "SUCC";
      public static final String TOP_UP_ING = "ING";
      public static final String TOP_UP_FAIL = "FAIL";
      public static final String TOP_UP_WAY_E_BANK = "E_BANK";
      public static final String TOP_UP_WAY_CAMILO = "CAMILO"; // 卡密
      public static final String TOP_UP_SCAN = "1";//扫描充值卡页进来
      public static final String TOP_UP_ALONG = "2";//独立充值卡页进来
      public static final String TOP_UP_CHARGECHANNEL = "3";//充值频道进来

   }

   /**
    * 单位类型
    */
   public static class UNIT_TYPE {
      public static final String MONEY = "0";// 金额
      public static final String TIME = "1";// 时长
      public static final String NUM = "2";// 次数
      public static final String FLOW = "3";// 流量
   }

   /**
    * 套餐类型
    */
   public static class MEAL_TYPE {
      public static final String VOICE = "1";// 语音
      public static final String WIFI = "2";// wifi
      public static final String FLOW = "3";// 数据
      public static final String MSG = "4";// 短信
      public static final String AMOUNT = "5";// 金额
   }

   /**
    * 发票抬头类型
    */
   public static class INVOICE_TITLE_TYPE {
      public static final String PERSONAL = "0";// 个人
      public static final String COMPANY = "1";// 非企业
      public static final String ENTERPRICE = "2";// 企业
   }


   /**
    * 红点
    */
   public static class REDDOT_FLAG {
      public static final String HOME_SECOND_FEED_TAB = "HOME_SECOND_FEED_TAB";// 首页二屏瀑布流
      public static final String HOME_SECOND_FEED_TAB_PREMIUM = "HOME_SECOND_FEED_TAB_PREMIUM";// 首页二屏瀑布流-尊享
      public static final String HOME_PAGE_MINE = "HOME_PAGE_MINE";// 首页我的频道
      public static final String CARE_FEED_TAB = "CARE_FEED_TAB";// 关爱版瀑布流
      public static final String FEED_TAB_FLOW = "FEED_TAB_FLOW";// 流量查询
      public static final String FEED_TAB_MSG = "FEED_TAB_MSG";// 短信查询
      public static final String FEED_TAB_VOICE = "FEED_TAB_VOICE";// 语音查询
      public static final String BILL_BALANCE_TAB = "BILL_BALANCE_TAB";// 话费余额
      public static final String RECHARGE_RESULT_TAB = "RECHARGE_RESULT_TAB";// 充值结果页
      public static final String DO_NOT_PUSH_DOWN_HOME_PAGE = "DO_NOT_PUSH_DOWN_HOME_PAGE";// 首页地图滑动，通知父控件不响应手势
   }

   /**
    * 本异网标识
    */
   public static class IS_CHINATELECOM {
      public static final String CT = "201";// 本网
      public static final String YW = "204";// 异网
   }

   /**
    * 字体路径
    */
   public static class TYPE_FACE_PATH {
      public static final String DEFAULT = "DEFAULT";//
      public static final String D_DIN_Bold = "fonts/D-DIN-Bold.ttf";//
   }

   /**
    * 发票状态类型
    */
   public static class INVOICE_STATE_TYPE {
      public static final String MAKE_EMAIL_INVOICE_ING = "6";// 邮箱开票中
      public static final String MAKE_EMAIL_INVOICE_FAIL = "8";// 邮箱开票失败
      public static final String DELIVER_TO_EMAIL_INVOICE_ING = "7";// 投递至邮箱中
      public static final String DELIVER_TO_EMAIL_INVOICE_FAIL = "9";// 投递至邮箱失败
      public static final String DELIVER_TO_EMAIL_INVOICE_SUCCESS = "10";// 投递至邮箱成功
      public static final String MAKE_WX_INVOICE_ING = "11";// 微信开票中
      public static final String MAKE_WX_INVOICE_FAIL = "13";// 微信开票失败
      public static final String DELIVER_TO_WX_INVOICE_ING = "12";// 投递至微信中
      public static final String DELIVER_TO_WX_INVOICE_FAIL = "14";// 投递至微信失败
      public static final String DELIVER_TO_WX_INVOICE_SUCCESS = "15";// 投递至微信成功
      public static final String NOT_MAKE_OUT_INVOICE = "0";// 未开票
   }

   /**
    * 页面id
    */
   public static class PAGE_ID {
      public static final String PkgUsingQuary = "00001";// 套餐用量
      public static final String FlowMeal = "00002";// 流量查询
      public static final String MsgMeal = "00003";// 短信查询
      public static final String VoiceMeal = "00004";// 语音查询
      public static final String FlowHistory = "00005";// 流量使用历史
      public static final String BalanceChange = "00006";// 余额变动
      public static final String MoneySpending = "00007";// 话费支出
   }

   /**
    * 随机码接口场景id
    */
   public static class RandomV2_SceneType {
      public static final String HgoChkRcode = "1";// 客服短信验证码登录随机码校验
      public static final String LoginInfo = "2";// 登录随机码校验
      public static final String UnsubscribeDataPackage = "3";// 退订流量包随机码校验
      public static final String HdExchange = "4";// 欢豆兑换商品随机码校验
      public static final String Ll4gllbOrder = "5";// 订购流量包随机码校验
      public static final String Ll4gllbChange = "6";// 变更流量包随机码校验
      public static final String JfyBillDetailAndRandomCodeAndAuthValidate = "7";// 详单查询随机码校验 和 实名制校验随机码校验
      public static final String SmsAuthorizationLogin = "8";// 短信授权登录随机码校验
   }

   /**
    * 新随机码接口场景id
    */
   public static class GetLoginRandom_Scene {
      public static final String Login = "55";// 登录
      public static final String Logout = "58";// 注销
      public static final String SmsAuthorizationLogin = "59";// 设备校验
      public static final String BroadbandBindPhone = "60"; // 宽带绑定手机
      public static final String WeChatBind = "93"; // 微信绑定
      public static final String BroadbandBind = "94"; // 宽带绑定
      public static final String ResetPsw = "98"; // 重置密码(9.1.0新增)
      public static final String CareLogin = "101"; // 适老版登录(9.1.0新增)
      public static final String CancelLogout = "105";// 撤销注销（930新增）
      public static final String Ll4gllbOrder = "1040";// 流量包订购(10.4新增)
      public static final String Ll4gllbChange = "1041";// 流量包变更(10.4新增)
      public static final String InternationalLogin = "293"; // 国际版登录(10.6新增)
   }

   /**
    * 获取短信验证码接口场景id
    */
   public static class GetRandomCodeWithLogin_Scene {
      public static final String RechargeByCardForOther = "112";// 陌生号码充值卡充值(11.2新增)
      public static final String RechargeForOther = "166";// 给他人充值(本网)
      public static final String RechargeWithYWUser = "238";// 异网号码登陆充值
   }

   /**
    * 统一客服场景id
    */
   public static class OnlineCustomer_SceneType {
      public static final String ThePhoneAndBalance = "010101";// 查询-查话费-话费及余额
      public static final String BalanceChange = "010102";// 查询-查话费-余额变动
      public static final String PrepaidPhoneRecords = "010103";// 查询-查话费-话费充值记录
      public static final String ReturnDeposit = "010104";// 查询-查话费-话费预存返还
      public static final String AccountBalance = "010105";// 查询-查话费-账户余额
      public static final String QueryDetail = "010201";// 查询-查详单-使用详单
      public static final String FlowUseHistory = "010202";// 查询-查详单-流量使用历史
      public static final String AccountInfo = "010203";// 查询-查个人信息-账户信息
      public static final String MoneySpending = "010301";// 查询-查账单-话费支出
      public static final String PhoneBill = "010302";// 查询-查账单-话费支出账单
      public static final String IntegralQuery = "010401";// 查询-查积分-当前积分
      public static final String IntegralGeneratorRecord = "010402";// 查询-查积分-积分产生记录
      public static final String IntegralExchangeRecord = "010403";// 查询-查积分-积分兑换记录
      public static final String QueryAccountInCredit = "010501";// 查询-查个人信息-信用额度
      public static final String BookedBusiness = "010502";// 查询-查个人信息-已定业务查询
      public static final String PkgUsingQuaryActivity = "010601";// 查询-查套餐-套餐用量
      public static final String FlowSearch = "010602";// 查询-查套餐-流量查询
      public static final String VoiceSearch = "010603";// 查询-查套餐-语音查询
      public static final String SmsSearch = "010604";// 查询-查套餐-短信查询
      public static final String ShareUsage = "010605";// 查询-查套餐-共享用量
      public static final String UserFor5g = "030105";// 5G专区
      public static final String ProductInfo = "040101";// 销售-买好物-销售品通用
      public static final String ShoppingMall = "040102";// 销售-买好物-商城我的客服
      public static final String CxblSuspensionWindow = "000001";// 通用-通用-查询办理悬浮按钮
      public static final String MyinfoSuspensionWindow = "000002";// 通用-通用-我的悬浮按钮
      public static final String YwMore = "000003";// 通用-通用-异网更多在线客服
      public static final String Setting = "000005";// 通用-设置-在线客服
      public static final String Default = "000000";// 通用默认场景
   }

   /*
    * 消息中心列表场景id
    */
   public static class QueryMessageList_Scene {
      public static final String Homepage = "1";//首页
      public static final String ShoppingMall = "2";//商城
   }

   /*
    * 消息中心列表场景id
    */
   public static class GetSecondScreenList_Scene {
      public static final String Phone = "0";//手机
      public static final String Broadband = "1";//宽带
   }

   /*
    * 新搜索接口搜索类型
    */
   public static class NewHgoSearch_Type {
      public static final String HOT_SEARCH = "0";//热门
      public static final String SEARCH = "1";//搜索
   }

   /*
    * 短信轮询时间
    */
   public static class MSG_TIME {
      public static final long TOTLE_TIME = 15000l;// 总时间
      public static final long DELAY = 7000l; //间隔
      public static final int COUNT = 2; // 轮询次数
   }

   /*
    * 服务评价场景id
    */
   public static class ServiceEvaluation_SceneId {
      public static final String RechargeSuccess = "1";//手机网银充值成功
      public static final String LtePackageSuccess = "2";//流量包订购成功页
      public static final String PrepaidPhoneRecords = "3";//话费充值记录
      public static final String ReturnDeposit = "4";//话费预存返回
      public static final String IntegralQuery = "5";//老积分查询页
      public static final String IntegralQueryNew = "6";//新积分查询页
      public static final String IntegralExchangeRecord = "7";//积分兑换记录
      public static final String FlowSearch = "8";//流量查询
      public static final String PkgUsingQuaryActivity = "9";//套餐用量（通用）
      public static final String PkgUsingQuaryActivityForBeijing = "10";//套餐用量（北京）
      public static final String DetailFlow = "11";//上网详单
      public static final String DetailVoice = "12";//语音详单
      public static final String DetailSms = "13";//短信详单
      public static final String BalanceChangeDetail = "14";//余额变动
      public static final String MyPackage = "15";//已订业务4合1中的我的套餐
      public static final String IntegralGeneratorRecord = "16";//积分收入
      public static final String VoiceSearch = "17";//语音查询
      public static final String SmsSearch = "18";//短信查询
      public static final String FlowUseHistory = "19";//流量使用历史
      public static final String QueryDetail = "20";//详单查询
      public static final String CallRechargeBalance = "21";//账户余额
   }

   /*
    * 悬浮窗场景
    */
   public static class SuspendWindow_Type {
      public static final String ThePhoneAndBalance = "1";//话费余额页
      public static final String FlowVoiceSmsSearch = "2";//流量语音短信查询页
      public static final String CallRechargeRecord = "3";//话费充值记录页
      public static final String QryFareBack = "4";//话费预存返还页
      public static final String BalanceChangeDetail = "5";//余额变动明细页
      public static final String AccountInfo = "6";//账户信息页
      public static final String IntegralQuery = "7";//积分查询页
      public static final String Search = "8";//搜索页
      public static final String QueryMain = "9";//查询办理首页
      public static final String SignalOverlay = "10";//5G热力图
      public static final String SearchResult = "11";//搜索页-搜索结果
      public static final String QueryDetails = "12";//9.2详单查询
      public static final String HomePageForPRAN = "13";//10.0.1P-RAN首页
   }

   /*
    * 猜你喜欢广告位信息场景
    */
   public static class QueryGuessYouLike_Type {
      public static final String BroadbandAccount = "1";//宽带账户信息
      public static final String ThePhoneAndBalance = "2";//话费余额
      public static final String FlowSearch = "3";//流量查询
      public static final String VoiceSearch = "4";//语音查询
      public static final String SmsSearch = "5";//短信查询
      public static final String IntegralQuery = "6";//积分查询
      public static final String PhoneAccount = "7";//手机账户信息
      public static final String FlowSearchNew = "8";//9.0流量查询
      public static final String VoiceSearchNew = "9";//9.0语音查询
      public static final String SmsSearchNew = "10";//9.0短信查询
      public static final String QueryDetails = "11";//9.2详单查询
      public static final String QueryPhoneBillBalance = "12";//9.2话费余额
      public static final String IntegralQueryNew = "13";//9.4积分查询
   }

   /*
    * 查询办理广告位信息场景
    */
   public static class QryTraAdvertisingSpace_Type {
      public static final String BroadbandAccount = "1";//宽带账户信息
      public static final String ThePhoneAndBalance = "2";//话费余额
      public static final String FlowSearch = "3";//流量查询
      public static final String VoiceSearch = "4";//语音查询
      public static final String SmsSearch = "5";//短信查询
      public static final String IntegralQuery = "6";//积分查询
      public static final String FlowSearchNew = "7";//9.0流量查询
      public static final String VoiceSearchNew = "8";//9.0语音查询
      public static final String SmsSearchNew = "9";//9.0短信查询
      public static final String QueryDetails = "10";//9.2详单查询
      public static final String QueryPhoneBillBalance = "11";//9.2话费余额
   }

   /**
    * 话费充值接口充值类型
    */
   public static class BankCardRecharge_Type {
      public static final String Charge = "1";//充值
      public static final String Flow = "2";//流量
      public static final String Contract = "7";//合同号
   }

   /**
    * 直播-更新访问人数类型
    */
   public static class UpdateVisitorNum_Type {
      public static final String InRoom = "1";//用户进入直播间
      public static final String OutRoom = "2";//用户退出直播间
      public static final String Share = "3";//点分享时调用，统计分享人数
   }

   /**
    * 一月一次预警类型
    *
    * @作者 huangssh
    * @创建时间 2015-11-12 上午11:17:55
    * @版本
    * @------修改记录-------
    * @修改人
    * @版本
    * @修改内容
    */
   public static class ThisMonthHasShowTipType {
      // 实名制
      public static final int RealNameDialog = 0;
   }

   /**
    * 遮罩页面来源
    */
   public static class FuncGuidePageType {
      // 首页
      public static final int MainActivity = 0x00001;
   }


   /**
    * 早中晚
    */
   public static class PeriodOfTimeType {
      // 早
      public static final int MORNING = 1;
      // 中
      public static final int NOON = 2;
      // 晚
      public static final int NIGHT = 3;
   }

   /**
    * 登录页面类型
    */
   public static class JsLoginType {
      // 手机登录
      public static final String PHONE = "1";
      // 关爱版登录
      public static final String CARE = "2";
      // 宽带登录
      public static final String BROADBAND = "3";
      // 国际版登录
      public static final String INTERNATIONAL = "4";
   }

   /**
    * 充值场景
    */
   public static class PayType {
      // 手机充值不可积分抵扣
      public static final String PHONE_CHARGE_INTEGRALNOT = "1";
      // 手机充值可积分抵扣
      public static final String PHONE_CHARGE_INTEGRAL = "2";
      // 固话充值
      public static final String FIXED_CHARGE = "3";
      // 流量包
      public static final String FLOW_CHARGE = "4";
      // 宽带
      public static final String BROADBAND = "5";
      // 卡密
      public static final String CARD = "6";
   }

   /**
    * 登录号码类型
    */
   public static class LoginType {
      // 绑定登录
      public static final String BIND = "0";
      // 历史记录
      public static final String HISTORY = "1";
   }

   /**
    * 是否标红(0:否、1:是)
    */
   public static class IS_REDDENING {
      public static final String NO = "0";
      public static final String YES = "1";
   }


   /**
    * 是否失效(0:否、1:是)
    */
   public static class IS_INVALID {
      public static final String NO = "0";
      public static final String YES = "1";
   }


   /**
    * 是否结转(0:通用、1:结转)
    */
   public static class TRANSFER_AMOUNT {
      public static final String NO = "0";
      public static final String YES = "1";
   }


   /**
    * 是否不限量(0:否、1:是)
    */
   public static class IS_INFINITE {
      public static final String NO = "0";
      public static final String YES = "1";
   }


   /**
    * 没有返回按默认勾选处理
    */
   public static class Check_STATE {
      // 默认不勾选
      public static final String No_Check = "0";
      // 默认勾选
      public static final String Check = "1";
   }

   /**
    * 假删除标识(下架)
    */
   public static class DELETE_STATE {
      // 未删除，上架
      public static final String EXIST = "0";
      // 已删除，下架
      public static final String DELETE = "1";
   }

   /**
    * 是否自动登录
    */
   public static class AUTO_LOGIN {
      // 自动登录
      public static final String AUTO = "0";
      // 不自动
      public static final String NOT_AUTO = "1";
   }

   /**
    * 登录账号类型
    */
   public static class UnifyAccountType {
      public static final String Login_Broadband = "1";// 单宽带
      public static final String Login_Phone = "2";// 单手机
      public static final String Login_PhoneAndBroadband = "3"; // 手机宽带（混合登录）
      public static final String Login_Broadband_ID = "4"; // 手机宽带（身份证登录）
   }

   /**
    * 宽带登录类型
    */
   public static class BroadBandLoginType {
      // 1:绑定手机-随机码登录
      public static final String SMS = "1";
      // 2:宽带账密登录
      public static final String PSW = "2";
      // 3:(宽带手机随机码登录)token登录
      public static final String PHONENUM_TOKEN = "3";
      // 4:(宽带账密登录)token登录
      public static final String BROADBAND_TOKEN = "4";
      // 5:手机号关联登录(手机号+自动登录token)
      public static final String AUTO = "5";
      // 6:宽带手机随机码token登录数据刷新
      public static final String PHONENUM_TOKEN_REFRESH = "6";
      // 7:宽带账密登录token登录数据刷新
      public static final String BROADBAND_TOKEN_REFRESH = "7";
      // 8:手机关联登录数据刷新
      public static final String AUTO_REFRESH = "8";
      // 9:(身份证宽带)token登录
      public static final String IDCARD_TOKEN = "9";
   }

   /**
    * 是否显示大图标
    */
   public static class ShowBigIcon {
      // 大图
      public static final String BIG = "1";
      // 小图
      public static final String SMALL = "0";
   }

   /**
    * 异形广告位类型
    */
   public static class HomeDialogType {
      // 手机异形
      public static final String DIALOG_NORMAL = "1";
      // 5g异形
      public static final String DIALOG_5G = "2";
   }


   /**
    * 是否走免密登录
    */
   public static class IsNoPasswordLoginFlag {
      // 0、默认不勾选；1、默认勾选
      public static final String NO = "0";
      public static final String YES = "1";
   }

   /**
    * 积量单位类型(0 – 元（金额）,1 – 分钟（时长）,2 – 条数,3 – 流量（KB）)
    */
   public static class UNITTYPE_ID {
      public static final String MONEY = "0";
      public static final String MINUTES = "1";
      public static final String SLIP = "2";
      public static final String FLOW = "3";

   }

   /**
    * 首页二楼展示规则，1、一天显示一次；2、每次启动客户端展示；3、仅展示一次；4、不展示
    */
   public static class HOME_SECEOND_FLAG {
      public static final String ONCE_A_DAY = "1";
      public static final String EVERYTIME = "2";
      public static final String ONCE = "3";
      public static final String GONE = "4";
   }

   /**
    * （1、每次都显示 2、仅展示1次 3、1天1次）默认1
    */
   public static class HOME_Ad_FLAG {
      public static final String EVERYTIME = "1";
      public static final String ONCE = "2";
      public static final String ONCE_A_DAY = "3";
   }

   /**
    * （1：按天、2：按周、3：按月、4：永久）默认为4
    */
   public static class HOME_AD_CLOSE_RULE {
      public static final String BY_DAY = "1";
      public static final String BY_WEEK = "2";
      public static final String BY_MONTH = "3";
      public static final String BY_FOREVER = "4";
   }

   /**
    * 闪屏页登录相关操作
    */
   public static class SplashLoginHandle {
      // 闪屏时间结束
      public static final int FINISH = -1;
      // 底部小圆点变化控制
      public static final int DOT_CHANGE = -2;
      // 开启首页或手势密码
      public static final int LOGIN = -4;
      // 显示跳过按钮
      public static final int SKIP = -8;
      // 自动登录
      public static final int AUTO_LOGIN = -9;
      // 超时进入首页
      public static final int TIME_OUT = -11;
      // 进行imsi登录
      public static final int AUTO_IMSI = -13;
      // 电话权限
      public static final int PHONE_STATE = -15;
      // 获取ACCESS_CODE
      public static final int GET_ACCESS_CODE = -17;
      // 进行onekey登录
      public static final int GET_ONE_KEY = -19;
   }


   /**
    * 测速模式
    */
   public static class SpeedTestMode {
      // 上传
      public static final int UPLOAD = 1;
      // 下载
      public static final int DOWNLOAD = 2;
      // 结束
      public static final int FINISH = 0;
      // 初始
      public static final int BEGIN = -1;
   }

   public static class SpeedResult {
      //没有连接翼相连网络
      public static final int SPEED_CONNECT_WRONG = 1;
      //测速出错
      public static final int SPEED_COMMON_WRONG = 2;
      //点击  中断测速
      public static final int SPEED_INTERRUPT_WRONG = 3;
      //Ping出错
      public static final int SPEED_PING_WRONG = 4;
   }
   /**
    * 网络制式
    */
   public static class NetworkMode {
      // wifi
      public static final String WIFI = "3";
      // 4g
      public static final String NET4G = "1";
      // 5g
      public static final String NET5G = "2";
   }


   /**
    * N 日一次显示类型
    *
    * @作者 huangssh
    * @创建时间 2015-11-12 上午11:17:55
    * @版本
    * @------修改记录-------
    * @修改人
    * @版本
    * @修改内容
    */
   public static class ThisDayHasShowTipType {
      // 催发票
      public static final String URGE_INVOICE = "URGE_INVOICE";
      // 小号宣传弹窗
      public static final String XH_POP_WINDOW = "XH_POP_WINDOW";
      // 节气动画，一天一次
      public static final String SOLAR_ANIM = "SOLAR_ANIM";
      // 首页二楼显示
      public static final String HOME_SECOND = "HOME_SECOND";
      // 首页异形
      public static final String HOME_AD = "HOME_AD";
      // 充值
      public static final String HOME_RECHARGE = "HOME_RECHARGE";
      // 首页签到
      public static final String HOME_SIGN = "HOME_SIGN";
      // 首页feed浮窗
      public static final String HOME_FEED_FLOAT = "HOME_FEED_FLOAT";
   }

   /**
    * 省市区选择器
    */
   public static class JDConst {
      public static final int INDEX_TAB_PROVINCE = 0;
      public static final int INDEX_TAB_CITY = 1;
      public static final int INDEX_TAB_AREA = 2;
      public static final int INDEX_INVALID = -1;
   }

   /**
    * 省市区选择器mode
    */
   public static class JDConstMode {
      public static final int Mode_PROVINCE = 0;
      public static final int Mode_PROVINCE_CITY = 1;
      public static final int Mode_AREA = 2;
   }

   /**
    * 登录id
    */
   public static class LoginTASKID {
      public static final int INDEX_TAB_PROVINCE = 0;
      public static final int INDEX_TAB_CITY = 1;
      public static final int INDEX_TAB_AREA = 2;
      public static final int INDEX_INVALID = -1;
   }

   /**
    * 仅一次显示类型
    *
    * @作者 huangssh
    * @创建时间 2015-11-12 上午11:17:55
    * @版本
    * @------修改记录-------
    * @修改人
    * @版本
    * @修改内容
    */
   public static class OnlyOnceType {
      // 小号宣传弹窗
      public static final String XH_POP_WINDOW = "00001";
      // 二楼
      public static final String HOME_SECOND_SHOW = "10001";
      // 首页异形
      public static final String HOME_AD_SHOW = "10002";
   }

   //===========微信支付暂时需要================

   //appid
   //请同时修改  androidmanifest.xml里面，.PayActivityd里的属性<data android:scheme="wxdf261c3b90ffbc25"/>为新设置的appid
   public static final String APP_ID = "wxdf261c3b90ffbc25";
   public static final int MY_PERMISSIONS_REQUEST_CAMERA = 54;//限制在255以内！
   public static final int MY_PERMISSIONS_REQUEST_CONTACTS = 55;
   public static final int MY_PERMISSIONS_REQUEST_LOCATION = 53;
   public static final int MY_PERMISSIONS_REQUEST_MICROPHONE = 59;
   public static final int MY_PERMISSIONS_REQUEST_STORAGE = 61;
   public static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 62;
   public static final int MY_PERMISSIONS_REQUEST_SYSTEM_ALERT_WINDOW = 64;
   //避免input type=file能力授权requestcode和getpic冲突，单独设置requestcode
   // 跳转H5页面携带申请系统权限
   public static final int MY_PERMISSIONS_REQUEST_FORH5 = 67;


   /***
    * 首页第二屏
    */
   public static class HomeSecondScreenType {
      public static final String FLOW_AREA = "1";//流量专区
      public static final String BROADBAND_AREA = "2";//宽带专区
      public static final String NATIVE_GOOD = "3";//地方好物
      public static final String HOTSEL_OF_4G = "4";//4G热卖
      public static final String NO_WORRY_SHOP = "5";//解忧杂货铺
      public static final String RECOMMED_FOR_YOU = "6";//为你推荐

      public static final String UPFLOOR = "7";
      public static final String DOWNFLOOR = "8";

      public static final String TOEXPAND = "TOEXPAND";//回到顶部
      public static final String DEFAULT_STATE = "DEFAULT_STATE";//回到默认状态
      public static final String CLOSE_FLOAT = "CLOSE_FLOAT";//关闭浮窗
      public static final String REFRESH_YUE = "REFRESH_YUE";//刷新首页余额

      public static final String CHANGE_BEHAVIOR = "CHANGE_BEHAVIOR";//改变底部状态栏状态
      public static final String REFRESHOTWORD = "REFRESHOTWORD";//刷新搜索框热搜词

   }

   /***
    * 充值频道
    */
   public static class Recharge {
      public static final String CHARGE_CALL = "手机";//充手机
      public static final String CHARGE_FIXED = "固话";//充固话
      public static final String CHARGE_FLOW = "流量";//充流量
      public static final String CHARGE_CARD = "充值卡";//充值卡
      public static final String CUXIAOTITLE = "CuXiaoTitle";//促销标题
      public static final String CUXIAOCONTENT = "CuXiaoContent";//促销内容
      public static final String WXTSTITLE = "WxtsTitle";//温馨提示标题
      public static final String WXTSCONTENT = "WxtsContent";//温馨提示内容
      public static final String DEDUCTION = "DeDuction";//积分抵扣标题
      public static final String PROVINCECODE = "ProvinceCode";//省编码
      public static final String CITYCODE = "CityCode";//市编码
      public static final String CITYNAME = "CityName";//城市名称

      public static final String SAVE_PHONE_HISTORY_NUM = "SavePhoneHistoryNum";//保存手机历史号码
      public static final String SAVE_BROADBAND_HISTORY_NUM = "SAVE_BROADBAND_HISTORY_NUM";//保存宽带历史号码
      public static final String SAVE_FIX_LINE_HISTORYNUM = "SaveFixedPhoneHistoryNum";//保存固话历史号码

      public static final String HISTORY_CHARGECARD_CHARGE = "充值卡充值";//历史充值卡充值
      public static final String HISTORY_CHARGECARD_CHARGE_CLEAR = "充值卡充值—清除";//历史充值卡充值清除
      public static final String QUICK_CHARGE = "quickcharge";//快速充值
      public static final String HOME_QUICK_CHARGE = "HOME_QUICK_CHARGE";//首页快速充值

   }

   /***
    * 亲密号跳转
    */
   public static class IntimateType {
      public static final String ADD_FIXERTEL = "2";//添加固话亲密号页面的index
      public static final int ADD_INTIMATENUM = 888;//添加固话亲密号页面的index

      public static final String VOICEDETAITYPE = "1";//详单列表
      public static final String INTIMATETYPE = "2";//亲密号推荐列表

      public static final String NOTIFY_QMF_1 = "INTIMATE_REFRESH_1";
      public static final String NOTIFY_QMF_2 = "INTIMATE_REFRESH_2";
   }

   public static class Kefu {
      public static final int TYPE_ROBOT_TEXT = 0101;//普通文本
      public static final int TYPE_ROBOT_MANUAL_SCENES_MENU = 0102;//场景菜单
      public static final int TYPE_ROBOT_WEATHER = 0103;//天气
      public static final int TYPE_ROBOT_EVALUATE = 0104;//评价弹框
      public static final int TYPE_ROBOT_MANUAL_ENTRANCE = 0105;//人工入口
      public static final int TYPE_ROBOT_SCENES_MENU_MANUAL_ENTRANCE = 0106;//人工入口

      public static final int TYPE_MANUAL_TEXT = 0201;
      public static final int TYPE_MANUAL_IMAGE = 0202;

      public static final int TYPE_SEND_TEXT = 0301;
      public static final int TYPE_SEND_IMAGE = 0302;
      public static final int TYPE_SEND_IMAGE_URI = 030202;

      public static final int TYPE_QUERY_TEXT = 0401;
      public static final int TYPE_QUERY_IMAGE = 0402;

      public static final int TYPE_NORMAL_TEXT = 0000;
      public static final int TYPE_HINT_MESSAGE = 1111;
      public static final int TYPE_TIME_DIVID = 2222;
      public static final int TYPE_EMOJI_MSG = 3333;
      public static final int TYPE_5G = 5555;
      public static final int TYPE_HIGH_LEVEL = 7777;
      public static final int TYPE_NORMAL_LEVEL = 7778;

   }

   public static class UserAgreement {
      public static final String BASICS_USER_PRIVACY_POLICY = "https://www.189.cn/yszc/h5/001/126041.html";// 基础版用户隐私政策
      public static final String USER_PRIVACY_POLICY = "https://www.189.cn/images/client/commonupload/2018/11/8/142432/yinsi/index.html";// 用户隐私政策
      public static final String USER_PRIVACY_POLICY_CARE = "https://www.189.cn/images/client/commonupload/2021/8/31/16037/yinsixieyi/index.html";// 关爱版用户隐私政策
      public static final String USER_PRIVACY_POLICY_INTERNATIONAL = "https://www.189.cn/yszc/h5/001/124506.html";// 国际版用户隐私政策
      public static final String ID_CARD_PRIVACY_POLICY = "https://wapact.189.cn:9001/privacyPolicy/privacyPolicy.html";//身份证隐私政策
      public static final String USER_SERVICE_AGREEMENT = "https://www.189.cn/images/client/commonupload/2018/11/8/14517/fuwuxieyi/index.html";//用户服务协议
      public static final String USER_SERVICE_AGREEMENT_CARE = "https://www.189.cn/images/client/commonupload/2021/8/31/16128/fuwuxieyi/index.html";//关爱版用户服务协议
      public static final String USER_SERVICE_AGREEMENT_INTERNATIONAL = "https://www.189.cn/yszc/h5/001/124505.html";//国际版版用户服务协议

      public static final String USER_LIFE_SERVICE_AGREEMENT = "https://e.189.cn/sdk/agreement/detail.do?hidetop=true";// 天翼数字生活账号服务条款
      public static final String USER_INFO_COLLECTION = "https://www.189.cn/images/client/commonupload/2021/8/19/151827/shoujiqingdan/index.html";//用户信息收集清单
      public static final String SHARE_INFO_LIST = "https://www.189.cn/images/client/commonupload/2021/8/19/152015/gongxiangqingdan/index.html";//第三方共享信息清单
      public static final String P_RAN_USER_SERVICE_AGREEMENT = "https://www.189.cn/wapportalweb/vue/pages/pran/index.html#/notice";//P_RAN网络注意事项
   }

   public static class DetailCheck {
      public static final int DETAIL_TYPE_FLOW = 3;
      public static final int DETAIL_TYPE_VOICE = 1;
      public static final int DETAIL_TYPE_SMS = 2;
      public static final int DETAIL_TYPE_ADDSER = 4;
   }

   public static class SalaAdType {
      public static final String REAL_TIME_CALL = "1";//实时话费
      public static final String VOICE_QUERY = "2";//语音查询
      public static final String TRAFFIC_QUERY = "3";//流量查询
      public static final String SMS_QUERY = "4";//短信查询
      public static final String SHOPPING_MALL = "5";//商场
      public static final String UNSUBSCRIBER_SUCCESS = "6";//退订成功
      public static final String DOSAGE_OF_PACKAGE = "7";//套餐用量
      public static final String CHANGE_BALANCE = "8";//余额变动
      public static final String MONEY_SPEND = "9";//话费支出
      public static final String PHONE_RECHARGE_RECORD = "10";//话费充值记录
      public static final String RETURN_THE_DEPOSIT = "11";//话费预存返还
      public static final String TRAFFIC_USAGE_HISTORY = "12";//流量使用历史
      public static final String LINE_OF_CREDIT = "13";//信用额度
   }

   public static class CancelAccounttype {
      public static final String USER_FOR_CHINATELECOM = "201";//本网用户
      public static final String USER_FOR_FIXED = "202";//固话用户
      public static final String USER_FOR_BROADBAND = "203";//宽带用户
      public static final String USER_FOR_DIFFERENT_NETWORK = "204";//异网用户
   }

   /*
    * 文案接口类型
    */
   public static class QueryNoticeId {
      public static final String BALANCE_AND_ARREARS = "1";//余额及欠费页面详解入口文案(缓存)2.1
      public static final String GENERAL_BALANCE_AND_SPECIAL_BALANCE = "2";//通用余额及专用余额文案(缓存)2.1.1
      public static final String ARREARS = "3";//账户欠费说明文案2.1.2
      public static final String PREPAID_PHONE_RECORDS = "4";//话费充值记录说明文案2.5
      public static final String RETURN_DEPOSIT = "5";//话费预存返还说明文案2.6
      public static final String FLOW_SEARCH_FORWARD = "6";//流量查询-结转流量说明文案3.2
      public static final String PKY_USING_QUERY_FORWARD = "7";//套餐用量-结转说明-说明文案
      public static final String FLOW_USE_FORWARD = "8";//流量使用说明的结转
      public static final String QUERY_ACCOUNT_IN_CREDIT = "9";//信用额度
      public static final String BALANCE_AND_ARREARS_NEW = "10";//余额及欠费页面详解入口文案(缓存)2.1（新页面支持账户用户切换）
      public static final String TIPS_FOR_RECOMMEND = "11";//扫码推荐上报流程温馨提示
      public static final String INTEGRAL_EXCHANGE_RULES = "12";//积分兑换获取验证码页面兑换规则
      public static final String INVOICE_LIST = "13";//7.2发票列表页面说明
      public static final String LOGIN_BY_PASSWORD = "14";//7.3账密登录文案说明
      public static final String PHONE_DETAIL = "15";//7.8详单查询提示文案
      public static final String CALL_RECHARGE_BALANCE_HELP = "16";//8.3账户余额页面帮助说明弹窗
   }

   public static class MYCHANNEL {
      public static final String TOOL_SCENE_HOME = "0";//我的频道必备工具首页
      public static final String TOOL_SCENE_MORE = "1";//我的频道必备工具更多

      public static final String SECONDPAGE_PHONE = "1";//第二屏我的手机
      public static final String SECONDPAGE_INTIMATE = "2";//第二屏我的亲密号
      public static final String SECONDPAGE_NEIRHOOD = "3";//第二屏我的附近营业厅
      public static final String SECONDPAGE_LITTLESHOP = "4";//第二屏我的解忧小铺

      public static final String SECONDPAGE_SHOP_GAME = "1";//游戏
      public static final String SECONDPAGE_SHOP_AUDIO = "2";//音频
      public static final String SECONDPAGE_SHOP_NOVEL = "3";//小说
      public static final String SECONDPAGE_SHOP_VIDEO = "4";//视频

      public static final String CODE = "1";//二维码
      public static final String SET = "2";//设置
      public static final String MESSAGE = "3";//消息
   }

   public static class AcessNetworkInfo {
      public static final int SERVICEREQUESTCODE = 1024;
      public static final String COMM_PARAMS_CAMERE_SUFFIX = ".jpg";
      public static final int POP_ADD_PICTURE_FROM_CAMERA = 6666;
      public static final int POP_ADD_PICTURE_FROM_PHOTO = 6667;
      public static final int REQUEST_FILE_PHOTO_CODE = 6669;
      public static final int REQUEST_FILE_CAMERA_CODE = 6670;
      public static final int REQUEST_CONTACT_CODE = 6671;
   }

   public static class HomeSearch {
      public static final String HOMESEAR = "1";//首页搜索
      public static final String HOMESEARCHSHOP = "2";//商城搜索
      public static final String HOMESEARCHQUERY = "3";//查询办理页搜索
      public static final String HOMESEARCHAD = "4";//广告位搜索
      public static final String HOMESEARCHALL = "";//全部场景
   }

   public static class Permission {
      public static final String PERMISSIONTYPE = "PermissionType";//系统权限类型
      public static final String PERMISSIONTITLE = "PmermissionTitle";//系统权限标题
      public static final String PERMISSION = "Permission";//系统权限
      public static final String PERMISSIONAUTHDESC = "PermissionAuthdesc";
      public static final String PERMISSIONSAFEDESC = "PermissionSafedesc";

      public static final String PERMISSIONGRANT = "1";//亲密号推荐号码是否有授权，是
      public static final String PERMISSIONDENIED = "0";//语音详单列表是否有授权，否

      public static final String AUTHTYPELOCATION = "1";//位置
      public static final String AUTHTYPECAMERA = "2";//相机
      public static final String AUTHTYPECONTACT = "3";//通讯录
      public static final String AUTHTYPESTORAGE = "4";//相册
      public static final String AUTHTYPEMICROPHONE = "5";//麦克风
      public static final String AUTHTYPEREADPHONE = "6";//读取手机
      public static final String AUTHTYPECALLPHONE = "7";//拨打电话
      public static final String AUTHTYPENOTIFICATION = "8";//通知权限（Android13新特性）
      public static final String READ_LIST_OF_APP = "android.permission.READ_LIST_OF_APP"; // 读取应用列表
   }

   public static class AccountInfo {
      public static final String PHONE = "手机";
      public static final String BROADBAND = "宽带";
      public static final String FIXEDPHONE = "固话";
      public static final String OTHER = "其他";
   }

   public static class RechargeBalance {
      public static final String ACCOUNT = "1"; //支持账户
      public static final String USER = "2"; //支持用户
      public static final String ACCOUNTPRI = "3"; //默认优先账户
      public static final String USERPRI = "4"; //默认优先用户
   }

   public static class BroadbandRepair {
      public static final String PUSH = "1";//催一催
      public static final String CONTACT = "2";//联系装修师傅
      public static final String TOEVALUATE = "3";//去评价
      public static final String EVALUATED = "4";//已评价
   }

   public static class TelephoneType {
      public static final String Home = "1";//首页
      public static final String Phone = "2";//手机
      public static final String FixedPhone = "3";//固话
   }

   public static class BestPayResultCode {
      public static final int SUCCESS = -1; // 支付成功
      public static final int CANCEL = 0; // 取消支付
      public static final int FAIL = 1; // 支付失败
   }

   /**
    * 查询业务类型（0:流量，1:语音，2:短信）
    */
   public static class Queryflag {
      public static final String FLOW = "0"; // 流量
      public static final String VOICE = "1"; // 语音
      public static final String MESSAGE = "2"; // 短信
   }

   /**
    * 宽带是否允许绑定：0否，1是
    */
   public static class BroadbandBindingFlag {
      public static final String NO = "0";//否
      public static final String YES = "1";//是
   }

   /**
    * 是否开启关爱版：0否，1是
    */
   public static class CareState {
      public static final String NO = "0";//否
      public static final String YES = "1";//是
   }

   /**
    * 是否开启：0否，1是
    */
   public static class OpenState {
      public static final String NO = "0";//否
      public static final String YES = "1";//是
   }

   /**
    * 曝光控件key
    */
   public static class ExpostureModelKeyWord {
      public static final String MODULE1 = "module1";
      public static final String QUICK_CHARGE = "快速充值";
      public static final String HOMEPAGE_FEED_TAB = "HOMEPAGE_FeedTab";
      public static final String HOMEPAGE_TITLE = "HOMEPAGE_TITLE";
      public static final String HOMEPAGE_MAIN_FUNC = "HOMEPAGE_MAIN_FUNC";
      public static final String HOMEPAGE_BROADBAND = "HOMEPAGE_BROADBAND";
      public static final String HOMEPAGE_WATCHSTREAMCARDVIEW = "HOMEPAGE_WATCHSTREAMCARDVIEW";
      public static final String HOMEPAGE_NETWORK_MEASUREMENT = "HOMEPAGE_NETWORK_MEASUREMENT";
      public static final String HOMEPAGE_ONECLICK_DIAGNOSIS = "HOMEPAGE_ONECLICK_DIAGNOSIS";
      public static final String HOMEPAGE_PRIVACY_SENTINEL = "HOMEPAGE_PRIVACY_SENTINEL";
      public static final String HOMEPAGE_FAMILY_CARD = "HOMEPAGE_FAMILY_CARD";
      public static final String HOMEPAGE_DAILY_USAGE_CARD = "HOMEPAGE_DAILY_USAGE_CARD";
      public static final String HOMEPAGE_BASE_STATION_CONNECT = "HOMEPAGE_BASE_STATION_CONNECT";
      public static final String HOMEPAGE_LOCATION_SERVICE = "HOMEPAGE_LOCATION_SERVICE";
      public static final String HOMEPAGE_HOMEPAGE_RECOMMEND = "HOMEPAGE_HOMEPAGE_RECOMMEND";
      public static final String QUICK_ENTRANCE = "QUICK_ENTRANCE";
      public static final String QUICK_ENTRANCE_YW = "QUICK_ENTRANCE_YW";
      public static final String QUICK_ENTRANCE_BROADBAND = "QUICK_ENTRANCE_BROADBAND";
      public static final String PHONE_CARD = "PHONE_CARD";
      public static final String PHONE_YW_CARD = "PHONE_YW_CARD";
      public static final String FLOW_TAB = "FLOW_TAB";
      public static final String YW_TOP_ENTRANCE = "FLOW_TAB";
      public static final String HOME_MARQUEEVIEW = "HOME_MARQUEEVIEW";
      public static final String HOME_TIPVP = "HOME_TIPVP";
      public static final String HOME_FOCUS_VIEW = "HOME_FOCUS_VIEW";
      public static final String HOME_FOCUS_VIEW_BD = "HOME_FOCUS_VIEW_BD";
      public static final String HOMEPAGE_SEARCH_SHRINK = "HOMEPAGE_SEARCH_SHRINK";
      public static final String RLHAVEBACKGROUND = "RLHAVEBACKGROUND";
      public static final String HOMEPAGE_SECOND_LOOPVIEW = "HOMEPAGE_SECOND_LOOPVIEW";
   }

   public static class MealAllowanceExposureModleKeyWord {
      public static final String IV_LINK_FLOW = "IV_LINK_FLOW";
      public static final String TV_OVER_TITLE_FLOW = "TV_OVER_TITLE_FLOW";
      public static final String RECOMMENDED_FOR_YOU_VIEW_FLOW = "RECOMMENDED_FOR_YOU_VIEW_FLOW";
      public static final String SERVICE_FLOW = "SERVICE_FLOW";
      public static final String EVALUATION_FLOW = "EVALUATION_FLOW";

      public static final String IV_LINK_MSG = "IV_LINK_MSG";
      public static final String TV_OVER_TITLE_MSG = "TV_OVER_TITLE_MSG";
      public static final String RECOMMENDED_FOR_YOU_VIEW_MSG = "RECOMMENDED_FOR_YOU_VIEW_MSG";
      public static final String SERVICE_MSG = "SERVICE_MSG";
      public static final String EVALUATION_MSG = "EVALUATION_MSG";

      public static final String IV_LINK_VOICE = "IV_LINK_VOICE";
      public static final String TV_OVER_TITLE_VOICE = "TV_OVER_TITLE_VOICE";
      public static final String RECOMMENDED_FOR_YOU_VIEW_VOICE = "RECOMMENDED_FOR_YOU_VIEW_VOICE";
      public static final String SERVICE_VOICE = "SERVICE_VOICE";
      public static final String EVALUATION_VOICE = "EVALUATION_VOICE";
   }

   public static class ChargeExposureModleKeyWord {
      public static final String PAYPHONE_IMAGE = "PAYPHONE_IMAGE";
      public static final String PAYPHONE_SERVICE = "PAYPHONE_SERVICE";
      public static final String FLOW_IMAGE = "FLOW_IMAGE";
      public static final String FLOW_SERVICE = "FLOW_SERVICE";
      public static final String CLOSE_IMAGE = "CLOSE_IMAGE";
      public static final String CLOSE_SERVICE = "CLOSE_SERVICE";
      public static final String CARD_IMAGE = "CARD_IMAGE";
      public static final String CARD_SERVICE = "CARD_SERVICE";
      public static final String FIXED_PAYPHONE_IMAGE = "FIXED_PAYPHONE_IMAGE";
      public static final String FIXED_PAYPHONE_SERVICE = "FIXED_PAYPHONE_SERVICE";
   }

   public static class ExpostureModelKeyWordKd {
      public static final String MODULE1_KD = "module1_kd";
      public static final String REPAIR = "装移修标题栏";
      public static final String NORMAL_SERVICE = "常用服务";
   }

   /**
    * 我的频道曝光key
    */
   public static class MyInfoExpostureModelKeyWord {
      public static final String TITLE_BAR = "TITLE_BAR"; // 标题栏
      public static final String USER_MSG = "USER_MSG"; // 用户信息区
      public static final String LEGAL = "LEGAL"; // 权益
   }

   /****
    * 手机第二屏模块1
    */
   public static class PhoneSecondScrrencardType {
      public static final String AREA = "1";//地区卡片
      public static final String COMMON = "2";//通用卡片
      public static final String IMAGE = "3";//单独图片

      public static final String AREACARDVIEW = "AreaCardView";
      public static final String IMAGECARDVIEW = "ImageCardView";

      public static final String MODULE2 = "module2_";
      public static final String MODULE3 = "module3_";
      public static final String MODULE4 = "module4_";
      public static final String MODULE2_KD = "module2_kd";
      public static final String MODULE3_KD = "module3_kd";
      public static final String MODULE4_KD = "module4_kd";
   }

   /****
    * 手机第二屏模块1
    */
   public static class PhoneSecondScreen {
      public static final String MODULE2 = "7";//模块2
      public static final String MODULE3 = "8";//模块3
      public static final String MODULE4 = "9";//模块4
      public static final String MODULE_WAP = "10";//wap楼层
   }

   /*
    * 扫码登录类型 1:登录 2:取消登录
    */
   public static class ScanLoginType {
      public static final String LOGIN = "1";
      public static final String CANCLE = "2";
   }

   /*
    *手机还是固话
    */
   public static class ChargeAcountType {
      public static final String FIXEDPHONE = "0";//固话
      public static final String MOBILEPHONE = "1";//手机
   }

   /**
    * @desc:京东认证
    * @author:zhangyz
    * @created at: 2019/12/16 15:41
    **/
   public static class JdAuthType {
      public static final String USERCANCELLOGIN = "997";//用户取消登录
      public static final String GETINFOFAIL = "998";//获取弹窗信息失败
      public static final String USERCANCELAUTH = "999";//用户取消授权
   }

   /**
    * 掌上营业厅（现有）：gh_77d07394e351
    * 掌上智慧营业厅（4升5）：gh_b44fbb532465
    */
   public static final String WxUserName = "gh_77d07394e351"; // 掌上营业厅微信UserName
   public static final String WxUserNameForWisdom = "gh_b44fbb532465"; // 掌上智慧营业厅微信UserName

   public static class RechargeMsgType {
      public static final String PHONE = "0";//充话费
      public static final String FLOW = "1";//流量
      public static final String FIXED = "2";//固话
   }

   public static class MultiItemLevel {
      public static final int TYPE_LEVEL_0 = 0;
      public static final int TYPE_LEVEL_1 = 1;
      public static final int TYPE_LEVEL_2 = 2;
   }

   public static class IntelligentAssistant {
      //0、发送 1、闲聊 2、天气 3、转人工 4、直达搜索 5、搜索列表 6、授权
      public static final int TYPE_0 = 0;
      public static final int TYPE_1 = 1;
      public static final int TYPE_2 = 2;
      public static final int TYPE_3 = 3;
      public static final int TYPE_4 = 4;
      public static final int TYPE_5 = 5;
      public static final int TYPE_6 = 6;
   }

   public static class CustomCallBack {
      public static final String STICKY = "0";  //置顶
   }
   public static class RedDotTabName {
      public static final String ClusterException = "999";// 容灾
      public static final String MESSAGE_CLOSE = "MESSAGE_CLOSE";// 消息关闭
      public static final String X104 = "X104";//x104 抢号
      public static final String X201 = "X201";//X201 抢号
      public static final String GET_CONTACT = "getContact";//web获取通讯录
      public static final String WEB_NESTER_SCROL = "nestedScrollWebView";//内嵌WAP页响应滑动手势
      public static final String PAYMENT_DETAIL = "PaymentDetailActivity";//服务评价接口完跳去结果页关闭付款详情页
      public static final String PAYMENT_SAVE_WALLET = "PAYMENT_SAVE_WALLET";// 保存钱包信息
      public static final String PAYMENT_SAVE_WALLET_FAIL = "PAYMENT_SAVE_WALLET_FAIL";// 保存钱包信息失败
      public static final String REFRESH_INVOICE = "refreshInvoice";//刷新电子发票
      public static final String MSG_READ_REFRESH = "MSG_READ_REFRESH";//消息直接刷新
      public static final String MSG_DELETE_ALL = "MSG_DELETE_ALL";// 消息删除所有
      public static final String MSG_DELETE = "MSG_DELETE";// 二级页删除
      public static final String MSG_NOTICE_ADD = "MSG_NOTICE_ADD";// 消息条添加事件触发(升级和公告)

      public final static String NOTIFY_DYNAMIC_MESSAGE = "NOTIFY_DYNAMIC_MESSAGE";

      public static final String MSG_UPDATE_RED_DOT_NUM = "MSG_UPDATE_RED_DOT_NUM"; //内嵌网页通知客户端更新消息中心Tab红点数接口
      public static final String MSG_UPDATE_CLASSIFY = "MSG_UPDATE_CLASSIFY"; // 内嵌网页通知客户端更新消息中心Tab继续通知首页服务分类
      public static final String XH_XIAOHAO = "XH_xiaohao";//小号-->小号
      public static final String LIMIT_TIME = "LIMIT_TIME";//
      public static final String MALL_SEND_NAV = "MALL_SEND_NAV";//商城二级导航栏
      public static final String GO_LIFE_ASSIGN_TAB = "goLifeAssignTab";
      public static final String MOBILE_NUMBER_VIEW = "mobileNumber";
      public static final String NETWORK_ERROR = "NETWORK_ERROR";// 网络异常
      public static final String CHANGE_ADD = "CHANGE_ADD";
      public static final String HOME_RESTART_LOGIN = "home_restart_login";
      public static final String REFRESH_PHONE_LIST = "REFRESH_PHONE_LIST"; // 刷新登录号码列表
      public static final String CARE_CHECK = "CARE_CHECK";
      public static final String INTERNATIONAL_CHECK = "INTERNATIONAL_CHECK";
      public static final String REFRESH_CARE_HOMEPAGE = "REFRESH_CARE_HOMEPAGE";
      public static final String REFRESH_INTERNATIONAL_HOMEPAGE = "REFRESH_PREMIUM_HOMEPAGE";
      public static final String REFRESH_HOME_PAGE_MSG = "REFRESH_HOME_PAGE_MSG";
      public static final String REFRESH_HOME_PAGE_PHONE_CARD = "REFRESH_HOME_PAGE_PHONE_CARD";
      public static final String REFRESH_HOME_PAGE_BROADBAND_CARD = "REFRESH_HOME_PAGE_BROADBAND_CARD";
      public static final String REFRESH_HOME_PAGE_FOCUS = "REFRESH_HOME_PAGE_FOCUS";
      public static final String REFRESH_UNI_LOGIN_RESULT = "REFRESH_UNI_LOGIN_RESULT";
      public static final String SWITCH_BROADBAND_CXBL_TO_HOMEPAGE = "SWITCH_BROADBAND_CXBL_TO_HOMEPAGE";
      public static final String NOTIFY_CXBL_TAB_RED_DOT = "NOTIFY_CXBL_TAB_RED_DOT";
      public static final String REFRESH_BUSINESS_HALL_CARD = "REFRESH_BUSINESS_HALL_CARD";// 查询频道-附近-附近营业厅
      public static final String REFRESH_BUSINESS_HALL_SEARCH = "REFRESH_BUSINESS_HALL_SEARCH";// 签到搜索页
      public static final String STOP_VOICE = "STOP_VOICE";// 播报停止
      public static final String REFRESH_MINE_WALLET = "REFRESH_MINE_WALLET";// 播报停止
   }

   /**
    * UserLogin登录类型
    */
   public static class UserLoginType {
      //1、token 2、smsCode 3、smsGrant 4、密码 5、imsi 6、免密 7、人脸识别登录
      public static final String TYPE_TOKEN = "1";
      public static final String TYPE_SMSCODE = "2";
      public static final String TYPE_SMSGRANT = "3";
      public static final String TYPE_PSW = "4";
      public static final String TYPE_IMSI = "5";
      public static final String TYPE_WX_TOKEN = "6";
      public static final String TYPE_FACE = "7";
   }

   /**
    * UserLogin登录来源
    */
   public static class LoginSource {
      public static final String TYPE_APPLICATION_OBSERVER = "1000";
      public static final String TYPE_ONEKEY_CARE = "1001";
      public static final String TYPE_SMS_CARE = "1002";
      public static final String TYPE_ONEKEY_INTERNATIONAL = "1003";
      public static final String TYPE_SMS_INTERNATIONAL = "1004";
   }

   /**
    * EReport类型(1:token登录,2:短信随机码,3:短信授权,4:账密登录,5:imsi登录,6:免密登录,7:微信绑定token登录,8:微信关联登录,9本机绑定宽带登录:,10:随机码绑定宽带登录,11:人脸识别登录)
    */
   public static class EReportType {
      public static final String TYPE_TOKEN = "1";
      public static final String TYPE_SMSCODE = "2";
      public static final String TYPE_SMSGRANT = "3";
      public static final String TYPE_PSW = "4";
      public static final String TYPE_IMSI = "5";
      public static final String TYPE_ONEKEY = "6";
      public static final String TYPE_WX_TOKEN = "7";
      public static final String TYPE_WX_ASSOCIATE = "8";
      public static final String TYPE_ONEKEY_BROADBAND = "9";
      public static final String TYPE_SMS_BIND_BROADBAND = "10";
      public static final String TYPE_FACE = "11";
   }

   /**
    * 网络监测的域名
    */
   public static class PingUrl {
      public static final String URL = URL_LOGIN_HTTPS_HOST_FORMAL;
      public static final int PORT = PORT_LOGIN_HTTPS_FORMAL;
   }

   public static class QueryUpgrade5G {
      public static final String TYPE5G = "1";//1、成为5G楼层
      public static final String TYPE5G_WAP = "2";//1、5G用户权益楼层WAP地址
   }


   /**
    * 1. 初始化卡片 2.排序后刷新 3. 开启已使用总开关
    */
   public static class REFRESH_CARD_TYPE {
      public static final String INIT = "1";
      public static final String ORDER = "2";
      public static final String SWITCH = "3";
   }

   public static class RechargeRefresh {
      public static final String RECHARGE_SHOW_GRAPHIC = "RECHARGE_SHOW_GRAPHIC";//显示图形验证码
      public static final String RECHARGE_OUT_TIME = "Rcharge_Out_Time";//待支付时间失效
      public static final String RECHARGE_LIMITE = "RECHARGE_LIMITE";//到期时间
      public static final String RECHARGE_INPUT = "Recharge_Input";//840输入框
      public static final String RECHARGE_THISNET = "Recharge_ThisNet";//840本异网
      public static final String RECHARGE_GET_NUMBER_MOBILE = "Recharge_Get_Number_Mobile";//840获取输入框号码手机
      public static final String RECHARGE_GET_NUMBER_FIXED = "Recharge_Get_Number_Fixed";//840获取输入框号码固话
      public static final String RECHARGE_GET_NUMBER_CARD = "Recharge_Get_Number_Card";//8card40获取输入框号码充值卡
      public static final String RECHARGE_SET_FIXED_NUM = "Recharge_Set_Fixed_Num";//设置固话号码
      public static final String RECHARGE_SET_BROADBAND_NUM = "RECHARGE_SET_BROADBAND_NUM";//切换宽带号码
      public static final String RECHARGE_SET_HISTORY_PHONENUM = "Recharge_Set_History_PhoneNum";//设置历史手机号码
      public static final String RECHARGE_SET_HISTORY_FIXEDPHONENUM = "Recharge_Set_History_FixedPhoneNum";//设置历史固话号码
      public static final String RECHARGE_SET_HISTORY_BROADBAND = "RECHARGE_SET_HISTORY_BROADBAND";//设置历史宽带号码
      public static final String RECHARGE_SET_HISTORY_BROADBAND_PART = "RECHARGE_SET_HISTORY_BROADBAND_PART";// 历史宽带号码（不匹配带回的号码）
      public static final String RECHARGE_CLEAR_FOCUS_MOBILE = "Rcharge_Clear_Focus_Mobile";//失去焦点
      public static final String RECHARGE_CARD_SCAN = "Rcharge_Card_Scan";//充值卡点击扫一扫
      public static final String RECHARGE_GET_NUMBER_MOBILE_REALCHARGE = "Recharge_Get_Number_Mobile_RealCharge";//获取号码后充值
      public static final String RECHARGE_GET_NUMBER_MOBILE_REALCHARGE_LOGIN = "Recharge_Get_Number_Mobile_RealCharge_Login";//获取号码后并且登录后真正充值
      public static final String RECHARGE_GET_NUMBER_FIXED_REALCHARGE = "Recharge_Get_Number_Fixed_RealCharge";//840获取输入框号码固话后充值
      public static final String RECHARGE_GET_NUMBER_FIXED_REALCHARGE_LOGIN = "Recharge_Get_Number_Fixed_RealCharge_Login";//840获取输入框号码固话后充值
   }

   public static class RechargeType {
      public static final String RECHARGE_PONE = "1";//手机
      public static final String RECHARGE_FIXED = "2";//固话
      public static final String RECHARGE_BROADBAND = "3";//宽带
   }

   public static class MyRefresh {
      public static final String MESSAGE_COUNT = "Refresh_MyPageTitleView";//刷新我的频道标题
      public static final String MYINFOFRAGMENT_SERVICE = "MYINFOFRAGMENT_SERVICE";//我的频道我的服务
      public static final String MYINFOFRAGMENT_SERVICE_HOME = "MYINFOFRAGMENT_SERVICE_HOME";//我的频道我的服务首页
      public static final String MYINFOFRAGMENT_REFRESH_NUM = "MYINFOFRAGMENT_REFRESH_NUM";//我的频道消息数字红点底部拖动时刷新
      public static final String MYINFOFRAGMENT_MESSAGE_COUNT = "MYINFOFRAGMENT_MESSAGE_COUNT";//我的频道消息数字红点统一
   }

   public static class DetailType {
      public static final int DETAIL_TYPE_FLOW = 0;
      public static final int DETAIL_TYPE_CALL = 1;
      public static final int DETAIL_TYPE_SMS = 2;
   }

   /**
    * 不同设置页区分
    */
   public static class PermissionUtilCode {
      public static final int GO_SYS_SETTING = 772; // 系统设置页
      public static final String BACK_FROM_SYS_SETTING = "BACK_FROM_SYS_SETTING";
      public static final int GO_LOCATION_SOURCE_SETTING = 773;// 定位设置页
      public static final String BACK_FROM_LOCATION_SOURCE_SETTING = "BACK_FROM_LOCATION_SOURCE_SETTINGS";
      public static final int GO_ALERT_WINDOW_SETTING = 774;// 悬浮窗设置页
      public static final String BACK_FROM_ALERT_WINDOW_SETTING = "BACK_FROM_SYS_SETTING_FOR_ALERT_WINDOW";
   }

   /**
    * 首页跳转id
    */
   public static class MAIN_TAB_ID {
      public static final int TAB_1 = 10001; // 首页
      public static final int TAB_2 = 10002; // 跳转首页第二个tab
      public static final int TAB_3 = 10003; // 跳转首页第三个tab
      public static final int TAB_4 = 10004; // 跳转首页第四个tab
      public static final int TAB_5 = 10005; // 跳转首页第五个tab
      public static final int TAB_MAIN_PHONE = 10006; // 首页手机
      public static final int TAB_MAIN_BROADBAND = 10007; // 首页宽带
   }

   /**
    * 页面跳转用到的请求码
    */
   public static class OldRechargeCode {
      public static final int RECHARGE_BUY_CARD_CODE = 1024;
      public static final int RECHARGE_CALL_CONTACT_CODE = 1025;
      public static final int RECHARGE_FLOW_CONTACT_CODE = 1026;
   }

   /**
    * 引导页面或遮罩图片比例，超过此值则使用适配的图片
    */
   public static class UserGuide {
      public static final double PROPORTION = 1.9;
   }

   /**
    * 微信登录
    */
   public static class WxLogin {
      public static final String STATE = "ctclient_wx_login";
      public static final String SCOPE = "snsapi_userinfo";
   }

   /**
    * JavascriptInterface
    */
   public static class TYPE_JS {
      public static final String WEB_MONITOR = "myObj";
   }

   /**
    * 足迹分类id
    */
   public static class FOOTPRINT_CLASSIFY_ID {
      public static final String SERVICE = "1"; // 服务
      public static final String BABY = "2"; // 宝贝
      public static final String LIVE = "3"; // 直播
      public static final String ACTIVITY = "4"; // 活动
   }


   /**
    * EasyFloatTag
    */
   public static class EASY_FLOAT_TAG {
      public static final String FEED = "FEED"; // feed流
      public static final String CUSTOMER = "CUSTOMER"; // 客服
      public static final String DIGITAL_WAP = "DIGITAL_WAP"; // 数字人h5
      public static final String VIRTUAL_PERSON = "VIRTUAL_PERSON"; // 数字人
      public static final String MEAL_ALLOWANCE = "MEAL_ALLOWANCE"; // 套餐查询
      public static final String BILL_BALANCE_CARE = "BILL_BALANCE_CARE"; // 话费余额关爱版
      public static final String BILL_BALANCE = "BILL_BALANCE"; // 话费余额
      public static final String CARE_FLOW_MEAL = "CARE_FLOW_MEAL"; // 流量查询关爱版
      public static final String CARE_VOICE_MEAL = "CARE_VOICE_MEAL"; // 语音查询关爱版
      public static final String WAP = "WAP"; // 网页
      public static final String TEST = "TEST";
      public static final String INTEGRAL_QUERY = "INTEGRAL_QUERY"; // 积分查询
      public static final String ESTABLISHED_BUSINESS = "ESTABLISHED_BUSINESS"; // 已定业务
      public static final String NEW_BALANCE_CHANGE_DETAIL = "NEW_BALANCE_CHANGE_DETAIL"; // 余额变动
      public static final String QRY_FARE_BACK = "QRY_FARE_BACK"; // 话费预存返还
      public static final String RECHARGE_RECORD = "RECHARGE_RECORD"; // 全网充值记录
      public static final String ACCOUNT_INFO_PHONE = "ACCOUNT_INFO_PHONE"; // 账户信息
      public static final String DETAIL_DATE_CHECK = "DETAIL_DATE_CHECK"; // 详单查询-日期选择
      public static final String DETAIL_AUTH = "DETAIL_AUTH"; // 详单查询-身份认证
      public static final String DETAIL_FLOW = "DETAIL_FLOW"; // 详单查询-流量
      public static final String DETAIL_VOICE = "DETAIL_VOICE"; // 详单查询-语音
      public static final String DETAIL_SMS = "DETAIL_SMS"; // 详单查询-短信
      public static final String DETAIL_ADDED = "DETAIL_ADDED"; // 详单查询-增值

      public static final String PREMIUM_DYNAMIC_MESSAGE_BOTTOM = "PREMIUM_DYNAMIC_MESSAGE_BOTTOM"; // 详单查询-增值

   }

   /**
    * 足迹分类id
    */
   public static class FOOTPRINT_TYPE_ID {
      public static final String LONG = "1"; // 长期
      public static final String SHORT = "2"; // 短期
   }

   /**
    * 人脸识别分类code
    */
   public static class FACE_TYPE_ID {
      public static final int LOGIN_SUCCESS = 0; // 人脸登录识别成功
      public static final int MODIFY_SUCCESS = 1; // 已实名修改成功
      public static final int REAL_NAME_SUCCESS = 2; // 未实名实名成功
      public static final int LOGIN_FAIL = 3; // 人脸登录识别失败
      public static final int MODIFY_FAIL = 4; // 已实名修改失败
      public static final int REAL_NAME_FAIL = 5; // 未实名实名失败
   }

   /**
    * 人脸识别业务场景
    */
   public static class FACE_BUILD_TYPE {
      public static final String LOGIN = "1"; // 登录
      public static final String RESET_PSW = "2"; // 重置密码
      public static final String BEST_HELPER = "3"; // 翼相助
   }

   /**
    * 当前环境
    */
   public static class ENVIRONMENT {
      public static final String FORMAL = "0"; // 正式
      public static final String TEST = "1"; // 测试
      public static final String NORMAL = "8"; // 非容灾
      public static final String DISASTER = "9"; // 容灾
   }

   /**
    * 刷新标识
    */
   public static class REFRESH_TYPE {
      public static final String mainFunctionalZone = "hg_sy_zgnq"; // 主要功能
      public static final String qryBroadbandADSpace = "hg_sy_kdxxq"; // 宽带随消
      public static final String qryTopBackGround = "hg_sy_epdt"; // 营销层顶图
      public static final String compoundADSpaceQuick = "100102"; // 营销层快捷
      public static final String bottomTieADSpace = "hg_sy_dczgnq"; // 营销层星播客
      public static final String compoundADSpaceFocus = "100104"; // 营销层焦点图
      public static final String queryAdveSpac = "100501"; // 固定广告位
   }

   /**
    * 身份证状态码
    */
   public static class IDCardCode {
      public static final int SUCCESS = 1; // 认证完成
      public static final int ERRROR = 0; // 规则错误
      public static final int FINISH = 2; // 用户主动结束
   }

   /**
    * feed流tab列表及广告位列表场景
    */
   public static class FeedScene {
      public static final String HomePageSecTab = "1"; // 首页底部营销层
      public static final String RechargeSuccResult = "2"; // 充值成功结果页
      public static final String FlowSearch = "3"; // 流量查询
      public static final String VoiceSearch = "4"; // 语音查询
      public static final String SmsSearch = "5"; // 短信查询
      public static final String BillBalance = "6"; // 话费余额
      public static final String CareFeedList = "7"; // 关爱版独立页
      public static final String MyInfo = "8"; // 我的频道
      public static final String QueryTransactPhone = "9"; // 查询办理-手机
      public static final String QueryTransactBroadband = "10"; // 查询办理-宽带
      public static final String QueryTransactNear = "11"; // 查询办理-附近
      public static final String MessageChannel = "12"; // 消息频道
   }

   /**
    * 带加载样式沉浸式网页类型
    */
   public static class WapUIType {
      public static final String Care = "0"; // 关爱版h5
      public static final String NewSales = "1"; // 新销售服务H5页面
   }

   /**
    * 容器化小程序页面跳转类型
    */
   public static class CtMiniAppType {
      public static final String NoLogin = "0"; // 不需要登录
      public static final String LoginWithTicket = "1"; // 要登录-带单点
      public static final String LoginWithoutTicket = "2"; // 要登录-带单点
   }

   /**
    * 刷新内容类型
    */
   public static class RefreshContentType {
      public static final String CareHomePage = "1"; // 关爱版首页
      public static final String HomePageSign = "2"; // 首页签到
      public static final String HomePageMsg = "3"; // 首页动态消息
      public static final String HomePagePhoneCard = "4"; // 首页手机用量区
      public static final String HomePageBroadbandCard = "5"; // 首页宽带用量区
      public static final String HomePageFocus = "6"; // 首页关注区
      public static final String InternationalHomePage = "7"; // 国际版首页主要数据区
      public static final String BusinessHallCard = "8"; // 查询办理附近&近域签到页营业厅卡片
      public static final String MineWallet = "9"; // 我的频道-我的钱包
   }

   /**
    * 充值号码类型 0:固话 1:手机 2:宽带
    */
   public static class PayAccountType {
      public static final String FIXLINE = "0"; //
      public static final String PHONE = "1"; //
      public static final String BROADBAND = "2"; //
   }

   /**
    * 页面接收类型
    */
   public static class ReceiverType {
      public static final String PUSH = "push";
      public static final String J_PUSH = "jPush"; // 极光推送
      public static final String WX_SHARE = "wxshare"; // 微信分享 (暂时已屏蔽)
      public static final String WX_JUMP = "wxjump"; // 微信跳转
      public static final String MPaaS = "mPaaS"; // MPaaS推送
   }

   /**
    * 特殊的linkType，用于本地没有线上linkType页面跳转, 负值避免重复
    */
   public static class OtherLinkType {
      public static final String MSG_NOTICE_ANNOUNCE = "-1";
   }

   /**
    * 页面接收参数key
    */
   public static class ReceiverKey {
      public static final String Type = "type";
      public static final String Title = "Title";
      public static final String Content = "Content";
      public static final String Link = "Link";
      public static final String LinkType = "LinkType";
      public static final String PushTitle = "PushTitle";
      public static final String PushContent = "PushContent";
      public static final String Utm = "Utm"; // 微信
      public static final String CallLink = "CallLink"; // 微信
   }

   /**
    * 页面是否沉浸式
    */
   public static class IsImmersion {
      public static final String YES = "1"; //
      public static final String NO = "0"; //
   }

   /**
    * 适老化h5页面类型
    */
   public static class DynamicLinkWithCareLoginType {
      public static final String NoImmersion = "1"; // 非沉浸式
      public static final String IsImmersion = "2"; // 沉浸式
   }

   /**
    *  客户端类型（1标准版；2关爱版；3国际版）
    */
   public static class ClientType {
      public static final String NORMAL = "1";
      public static final String CARE = "2";
      public static final String INTERNATIONAL = "3";
   }

   /**
    * 登录用户类型
    */
   public static class UserType {
      public static final String NEW = "0"; // 新用户
      public static final String NORMAL = "1"; // 普通用户
      public static final String NEWSALE = "2"; // 新销售用户
   }

   public static class BaiduMapParams {
      public static final int[] HEAT_MAP_GRADIENT_COLORS = {
              Color.parseColor("#127CFF"),
              Color.parseColor("#127CFF"),
              Color.parseColor("#127CFF"),
              Color.parseColor("#127CFF")
      };
      public static final float[] HEAT_MAP_GRADIENT_START_POINTS = {0.001f, 0.002f, 0.3f, 0.5f};

      //热力图缩放限制
      public static final float HEAT_MAP_LEVEL_LIMIT_MAX = 17f;
      public static final float HEAT_MAP_LEVEL_LIMIT_MIN = 15f;
      public static final float HEAT_MAP_LEVEL_START = 16f;
      public static final int HEAT_MAP_RADIUS = 50;
      public static final double HEAT_MAP_OPACITY = 0.4;
   }

   public static class TaskStatus {
      /**
       * 0：无定位权限
       * 1：接口调用中
       * 2：接口成功并有数据
       * 3：接口成功无数据
       * 4：接口失败
       */
      public static final int STATUS_NO_PERMISSION = 0;
      public static final int STATUS_LOADING = 1;
      public static final int STATUS_SUCCESS = 2;
      public static final int STATUS_SUCCESS_WITHOUT_DATA = 3;
      public static final int STATUS_FAIL = 4;
   }

   public static class Stream {
      public static final int PLAY_STATUS_PLACEHOLDER = -1;//未开始，占位图
      public static final int PLAY_STATUS_STREAMING = 0;//播放中
      public static final int PLAY_STATUS_END = 1;//播放结束
      public static final int PLAY_STATUS_PAUSE = 2;//播放暂停
      public static final int PLAY_STATUS_PROGRESS = 3;//播放过程
      public static final int PLAY_STATUS_LOADING = 4;//播放加载中
      public static final int PLAY_STATUS_ERROR = 5;//播放错误
      public static final int PLAY_STATUS_CHANGE_RESOLUTION = 6;//播放分辨率改变
   }

   public static class HotSelection {
      public static final int HOME_PAGE = 1;
      public static final int DIALOG_TOP = 2;
      public static final int DIALOG_BOTTOM = 3;
   }

   public static class VirtualPerson {
      public static final int GO_VIRTUAL_PERSON_SETTING = 60;
      public static final int REFRESH_CONFIG = 66601;

      private static String separator = "11.1.0";
      public static final String LOCAL_VIDEO_URL = "LocalVideoUrl" + separator;
      public static final String LOCAL_VOICE_VIDEO_URL = "LocalVoiceVideoUrl" + separator;
      public static final String LOCAL_WELCOME_VIDEO_URL = "LocalWelcomeVideoUrl" + separator;
      public static final String TOP_BACKGROUND_IMG_URL = "TopBackgroundImgUrl" + separator;
      public static final String TOP_BACKGROUND_TITLE = "TopBackgroundTitle" + separator;
      public static final String FLOAT_WINDOW_IMG_URL = "FloatWindowImgUrl" + separator;
      public static final String FLOAT_WINDOW_IMG_URL_RATIO = "FloatWindowImgUrlRatio" + separator;
   }

   /**
    * 播放类型（0.不直接播放、1.直接播放）
    */
   public static class PLAY_TYPE {
      public static final String NO_AUTO_PALY = "0";
      public static final String AUTO_PALY = "1";
   }

   /**
    * 配置类型（1.图片、2.短视频、3.直播）
    */
   public static class TYPE {
      public static final String IMAGE = "1";
      public static final String VIDEO = "2";
      public static final String LIVE = "3";
   }

   public static class AdapterShowType{
      public static final int TYPE_NORMAL = 0;//正常情况显示类型
      public static final int TYPE_INTERNATIONAL = 1;//国际化显示类型
   }

   public static class ErrorPageParams{
      public static final String ESIM_ERROR_PAGES[] = {
              "https://www.189.cn/client/wap/wapproject/personalcustom/esimenter",
              "https://www.189.cn/client/wap/wapproject/personalcustom/huaweiWatch",
              "https://www.189.cn/client/wap/wapproject/personalcustom/huaweiMobilePhoneCaseEsim",
              "https://www.189.cn/client/wap/wapproject/personalcustom/oneCardTwoTerminals"
      };
   }
}

