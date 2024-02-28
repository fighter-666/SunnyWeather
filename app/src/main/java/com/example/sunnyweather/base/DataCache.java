package com.example.sunnyweather.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.Contacts;


import com.example.sunnyweather.util.UtilText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataCache {
    public boolean isCtUser;//true-电信用户 false-异网用户

    public boolean hasHBsSharedRecords;//是否有欢豆分享记录
    public String UserPhoneNbr;//手机号
    public String UserPhoneNbrStatus;//手机号码状态
    public String touristId;// 游客id
    public String DevelopCode;// 推荐人id
    public String UserName;    //客户名
    public String ImageUrl; // 首页标题栏用户类型标识图
    public String UsedAmount;  //已使用流量
    public String AccAmount;   //流量总量
    public String UsedNationalAmount;  //已使用全国流量
    public String AccNationalAmount;   //全国流量总量
    public String Token;       //登录成功后的回执
    public String UserId;      //网购时用的ID
    public ShareData mShareData;//推荐分享数据
    public boolean IsDirectCon;//是否计费直连省份 true：是  false：否
    public String ProtraitUrl; // 用户头像Url-平台获取的
    public String RefereeCode; // 推荐码
    public String AccountType; // 当前卡号类型（主卡/副卡）
    public String CardsNum; // 可切换号码数量
    public String NetType; // 用户类型是否是5G用户 0:非5G用户 1:5G用户
    public String IsNewUser; // 是否新用户 1:新用户 0:老用户
    public String MemberType; // 会员类型 0黄金会员 1白金会员
    public String Operator; // 异网运营商信息
    public boolean Authenticate;//是否实名制（true， 实名制用户； false，非实名制用户）
    public boolean isPrepaid;//是否预付费（true， 预付费用户； false，后付费用户）,默认预付费
    public Bitmap tempUserIcon;// 临时用户头像的bitmap。存在的目的主要是因为在上传头像成功后，服务端会有10s的延迟时间，所以我们不会第一时间更新回图片。
    public String ProtraitLocalPath;//用户头像本地存储的位置   --平台能获取到头像就是用户的头像 没有的话是随机生成的头像
    public String frontIdCardFileName;//正面身份证照FileName
    public String backIdCardFileName;//反面身份证照FileName
    public String handIdCardFileName;//手持身份证照FileName
    public String accessFrontIdCardFileName;//入网界面正面身份证照FileName
    public String accessbackIdCardFileName;//入网界面反面身份证照FileName
    public String accesshandIdCardFileName;//入网界面手持身份证照FileName
    public DefaultSearchTermsV2Data.ScenesBean syntheticScenes,serviceScenes,lifeScenes,storeScenes,specialScenes;
    public List<CustomizeIntNumQueryData.CustomizeIntNumInfosBean> mInitNumList;//840亲密号列表
    public boolean mIsUser;//是否为登录号码，如不是就为我的账户

    public String PhoneType;//用户网络及服务类型
    public String ProvinceCode;//用户所属省编码
    public String CityCode;//用户所属市编码
    public String AreaCode;//用户所属区编码
    public String ProvinceName;//用户所属省名称
    public String CityName;//用户所属市名称
    public CityInfo CityInfo;//根据定位结果城市信息
    public String Longitude;//根据定位结果经度信息
    public String Latitude;//根据定位结果纬度信息
    public String LocationCityName;//根据百度定位SDK返回城市名称

    public CityInfo ShoppingMallCityInfo;//根据定位结果城市信息

    public String UserLevel;//用户等级，对应4G尊享查询接口
    public String UserLevelLinkType;//用户等级，对应4G尊享查询接口（跳转参数）
    public String UserLevelLink;//用户等级，对应4G尊享查询接口（跳转参数）
    public String UserIsPremiumEditionUser;//是否尊享版用户，0：否，1：是，10.6新增
    public String UserTips;//尊享版弹窗提示语，10.6新增
    public String FlowBalance;//剩余流量
    public String FlowTotal;//总流量 kb
    public String FlowUsage;//已使用流量
    public String FlowShowField;//流量展示标识
    public String YuE;//余额 元
    public String ShouldCharge;//欠费 元
    public String BillTitle;//首页话话费标题
    public String BillShowFlag;//首页话费展示标识 0、您已欠费；1、话费余额
    public String BillIsShowRed;//首页话费是否标红显示：0、不显示；1、显示
    public String BillTotal;//本月实时话费
    public String IndexBalanceInfoLinkType;//实时话费接口跳转类型
    public String IndexBalanceInfoLink;//实时话费接口跳转链接
    public String HgoLlLinkType;//首页流量接口跳转类型
    public String HgoLlLink;//首页流量接口跳转链接
    public QryDiscountLlbConfig qryDiscountLlbConfig;//流量充值页面流量包配置信息
    public List<JfyAccountInfoItem> userAccountList;//用户账户列表，如果列表项大于1则表示该用户支持账户级和用户级，小于等于1则表示该用户只支持用户级
    public QueryVisitRuleBean queryVisitRuleBean;

    //看代码单纯用于传参，不建议这种写法，考虑老页面已经不用了暂不处理
    public String provinceCurrentValue;  //省份当前值 （用于“小区宽带资源查询”界面下拉列表）
    public String cityCurrentValue;  //城市当前值
    public String countyCurrentValue;  //区域当前值

    public String integralAmount;//积分余额

    public Map<String,String> InfoMap;//缓存一些信息，如PUK码
    public static final String[] LOGIN_TYPES = {"GUEST","CTWAP","IMSI","LOGIN"};
    public int LoginType;//登录类型  0：其他（默认值）  1：CTWAP登录  2：IMSI进行登录  3：手机帐密方式登录  4：统一账户免密登录 5：微信关联登录
    public static final int LOGIN_NOINIT = 0;
    public static final int LOGIN_CTWAP = 1;
    public static final int LOGIN_IMSI = 2;
    public static final int LOGIN_PHONENUM = 3;
    public static final int LOGIN_ACCOUNTPLATFORM = 4;
    public static final int LOGIN_WECHAT = 5;
    public String lunchtype = "aut_lunch";//用户主动打开:aut_lunch，h5页面打开：h5_lunch, push打开：push_lunch,默认：aut_lunch

    public static List<Contacts.People> peopleList = new ArrayList<Contacts.People>();


    public static boolean IdIdentityAuth; // 是否通过活体认证
    public boolean isAnnounceOneShot = false;// 公告-lottie-异形接口是否调用结束
    public boolean isAnnounceDialogOpen = false;// 公告是否弹过
    public boolean isHgo4GLevelDone = false;// 星级接口是否调用结束
    public boolean isShowHomePagePopup = false;// 是否首页弹窗流程正在展示
    public boolean isJpushStart = false;// 极光推送是否开启

    public static String eSimVendorCode = "-1"; //esim厂商代码
    public static String eSimServiceName = ""; //esim调起的服务名
    public static String eSimPackageName = "";//esim穿戴APP包名
    public static String eSimSHA256; //服务端返回的包名
    public static String eSimVersionCode; //esim服务端返回的版本号

    public static String HUOTITYPE; // 活体状态, 默认未活体
    public static String HUOTI_NAME;
    public static String HUOTI_IDCODE;
    public static String HUOTI_ORDERID;

    public static String goLifeMainLink; //发现频道跳指定TAB
    public static String goQueryMainLink;//跳转查询办理指定TAB
    public static String goMsgMainLink;//跳转消息指定TAB
    

    public DataCache(){
        isCtUser = true;
        isAnnounceOneShot = false;
        isAnnounceDialogOpen = false;
        isShowHomePagePopup = false;
        isHgo4GLevelDone = false;
        isJpushStart = false;
        hasHBsSharedRecords = false;
        UserPhoneNbr = null;
        UserPhoneNbrStatus = null;
        DevelopCode = null;
        UserName = null;
        ImageUrl = null;
        UsedAmount = null;
        AccAmount = null;
        UsedNationalAmount = null;
        AccNationalAmount = null;
        Token = null;
        UserId = "";
        ProtraitUrl = "";
        RefereeCode = "";
        AccountType = "";
        CardsNum = "";
        NetType = "";
        IsNewUser = "";
        MemberType = "";
        Operator = "";
        ProtraitLocalPath = "";
        InfoMap = new HashMap<String,String>();
        LoginType=LOGIN_NOINIT;
        //mInquiryIconManager = new InquiryIconManager();//去掉旧版查询
        mShareData = new ShareData();
        IsDirectCon = false;
        PhoneType = "0";//默认未知类型为0
        ProvinceCode = null;
        CityCode = null;
        ProvinceName = null;
        CityName = null;
        CityInfo = null;
        Longitude = null;
        Latitude = null;
        LocationCityName = null;
        UserLevel = null;
        UserLevelLinkType = null;
        UserLevelLink = null;
        UserIsPremiumEditionUser = null;
        UserTips = null;
        frontIdCardFileName = null;
        backIdCardFileName = null;
        handIdCardFileName = null;
        accessFrontIdCardFileName = null;
        accessbackIdCardFileName = null;
        accesshandIdCardFileName = null;
        mInitNumList = new ArrayList<>();
        mIsUser = true;

        Authenticate=true;
        isPrepaid = true;
        tempUserIcon = null;

        FlowBalance = null;
        FlowTotal = null;
        FlowUsage = null;
        FlowShowField = null;
        YuE = null;
        ShouldCharge = null;
        BillTitle = null;
        BillShowFlag = null;
        BillIsShowRed = null;
        BillTotal = null;
        IndexBalanceInfoLinkType = null;
        IndexBalanceInfoLink = null;
        HgoLlLinkType = null;
        HgoLlLink = null;
        qryDiscountLlbConfig = new QryDiscountLlbConfig();
        userAccountList= new ArrayList<>();
        queryVisitRuleBean = new QueryVisitRuleBean();
        integralAmount = "";
        IdIdentityAuth = false;
        HUOTI_NAME = "";
        HUOTI_IDCODE = "";
        HUOTI_ORDERID = "";
        HUOTITYPE = CommonUtil.HUOTI_TYPE.HUOTI_NOT;
        lunchtype = "aut_lunch";
//        UtilLog.saveImportantDataLogcat("DataCache() null");
    }

    /**
     * 登录成功重置状态
     * 不清除号码和用户名,其他个人信息全部清除(除了登入接口返回的数据，其他接口返回的数据都清除)
     */
    public void reset(){
        isAnnounceOneShot = false;
        isAnnounceDialogOpen = false;
        isShowHomePagePopup = false;
        isHgo4GLevelDone = false;
        isJpushStart = false;
        hasHBsSharedRecords = false;
        UserName = null;
        ImageUrl = null;
        UserPhoneNbrStatus = null;
        UsedAmount = null;
        AccAmount = null;
        UsedNationalAmount = null;
        AccNationalAmount = null;
        ProtraitUrl = "";
        RefereeCode = "";
        AccountType = "";
        CardsNum = "";
        ProtraitLocalPath = "";
        InfoMap.clear();
//    	mInquiryIconManager = new InquiryIconManager();//去掉旧版查询
        mShareData.RcmmdFlg = false;
        mShareData.AwardFlg = false;
        mShareData.RcmmdRule = "";

        UserLevel = null;
        UserLevelLinkType = null;
        UserLevelLink = null;
        UserIsPremiumEditionUser = null;
        UserTips = null;
        frontIdCardFileName = null;
        backIdCardFileName = null;
        handIdCardFileName = null;
        accessFrontIdCardFileName = null;
        accessbackIdCardFileName = null;
        accesshandIdCardFileName = null;
        mInitNumList.clear();
        mIsUser = true;


        Authenticate =true;
        isPrepaid = true;
        tempUserIcon = null;

        FlowBalance = null;
        FlowTotal = null;
        FlowUsage = null;
        FlowShowField = null;
        YuE = null;
        ShouldCharge = null;
        BillTitle = null;
        BillShowFlag = null;
        BillIsShowRed = null;
        BillTotal = null;
        IndexBalanceInfoLinkType = null;
        IndexBalanceInfoLink = null;
        HgoLlLinkType = null;
        HgoLlLink = null;
        qryDiscountLlbConfig.linkType = "";
        qryDiscountLlbConfig.link= "";
        userAccountList.clear();
        integralAmount = "";
        IdIdentityAuth = false;
        HUOTI_NAME = "";
        HUOTI_IDCODE = "";
        HUOTI_ORDERID = "";
        HUOTITYPE = CommonUtil.HUOTI_TYPE.HUOTI_NOT;
        Variable.isX104 = false;
        Variable.isX201 = false;
//        UtilLog.saveImportantDataLogcat("reset() null");
    }

    /**
     * 退出登录后重置用户信息，置为和未登录状态一样
     */
    public void resetForLogout(){
        isCtUser = true;
        isAnnounceOneShot = false;
        isAnnounceDialogOpen = false;
        isShowHomePagePopup = false;
        isHgo4GLevelDone = false;
        isJpushStart = false;
        hasHBsSharedRecords = false;
        UserPhoneNbr = null;
        UserPhoneNbrStatus = null;
        UserName = null;
        ImageUrl = null;
        UsedAmount = null;
        AccAmount = null;
        UsedNationalAmount = null;
        AccNationalAmount = null;
        Token = null;
        UserId = "";
        ProtraitUrl = "";
        RefereeCode = "";
        AccountType = "";
        CardsNum = "";
        NetType = "";
        IsNewUser = "";
        MemberType = "";
        Operator = "";
        ProtraitLocalPath = "";
        InfoMap.clear();
        LoginType=LOGIN_NOINIT;
        //mInquiryIconManager = new InquiryIconManager();//去掉旧版查询
        mShareData.RcmmdFlg = false;
        mShareData.AwardFlg = false;
        mShareData.RcmmdRule = "";
        mShareData.UserType = null;
        IsDirectCon = false;
        PhoneType = "0";//默认未知类型为0
        ProvinceCode = null;
        CityCode = null;
        ProvinceName = null;
        CityName = null;
        UserLevel = null;
        UserLevelLinkType = null;
        UserLevelLink = null;
        UserIsPremiumEditionUser = null;
        UserTips = null;
        frontIdCardFileName = null;
        backIdCardFileName = null;
        handIdCardFileName = null;
        accessFrontIdCardFileName = null;
        accessbackIdCardFileName = null;
        accesshandIdCardFileName = null;
        mInitNumList.clear();
        mIsUser = true;

        Authenticate=true;
        isPrepaid = true;
        tempUserIcon = null;

        FlowBalance = null;
        FlowTotal = null;
        FlowUsage = null;
        FlowShowField = null;
        YuE = null;
        ShouldCharge = null;
        BillTitle = null;
        BillShowFlag = null;
        BillIsShowRed = null;
        BillTotal = null;
        IndexBalanceInfoLinkType = null;
        IndexBalanceInfoLink = null;
        HgoLlLinkType = null;
        HgoLlLink = null;
        qryDiscountLlbConfig.linkType = "";
        qryDiscountLlbConfig.link= "";
        userAccountList.clear();
        integralAmount = "";
        IdIdentityAuth = false;
        HUOTI_NAME = "";
        HUOTI_IDCODE = "";
        HUOTI_ORDERID = "";
        HUOTITYPE = CommonUtil.HUOTI_TYPE.HUOTI_NOT;

        //极光推送重置
//        MyApplication.jPushDao.resetJpush(); 
//        UtilLog.saveImportantDataLogcat("resetForLogout() null");
    }

    public boolean isLoginYet(){
        return (UserPhoneNbr != null);
    }

    /*未读短信总数*/
    public int totalNumber=0;

    /**
     * 一个用户创建一个sharepreference文件，名称是lp.+号码
     * @param context
     * @return
     */
    public SharedPreferences getCurrentUserLockPatternData(Context context){
        return context.getSharedPreferences("lp."+UserPhoneNbr, Context.MODE_PRIVATE);
    }

    /**
     * 根据用户获取手势密码信息是否存在，名称是lp.+号码
     * @param context
     * @return
     */
    public boolean getIsExistUserLockPatternData(Context context, String number){
        return context.getSharedPreferences("lp." + number, Context.MODE_PRIVATE).getBoolean("inTO",false);
    }

    /***
     * 根据类型获得相应搜索默认值
     * @param type
     * @return
     */
    public DefaultSearchTermsV2Data.ScenesBean getScenesBean(String type){
        if(CommonUtil.Search.SEARCH_QUERY.equals(type)){
            return serviceScenes;
        }else if(CommonUtil.Search.SEARCH_LIFE.equals(type)){
            return lifeScenes;
        }else if(CommonUtil.Search.SEARCH_SHOP.equals(type)){
            return storeScenes;
        }else if(CommonUtil.Search.SEARCH_DISCOUNT.equals(type)){
            return specialScenes;
        }else {
            return syntheticScenes;
        }
    }

    /**
     * 返回大数据开关状态
     * @return 开启返回1，关闭返回0
     */
    public String getAccessAuth(){
        //开启大数据推荐传1，关闭大数据推荐传0
        if(Setting.getBoolean(Setting.KEY_BUSINESS_RECOMMONED_STATE,true)){//开启大数据推荐
            return "1";
        }else {//关闭大数据推荐
            return "0";
        }
    }

    /**
     * 是否加载尊享版配置，0：否，1：是，传空按默认0：否处理
     * @return 加载返回1，不加载返回0
     */
    public String getIsPremiumEdition(){
        //加载尊享版配置传1，不加载尊享版配置传0
        if(Setting.getBooleanByNbr(Setting.KEY_PREMIUM_SWITCH,false)){//不加载尊享版配置
            return "1";
        }else {//不加载尊享版配置
            return "0";
        }
    }

    /**
     * 是否开启尊享版
     */
    public Boolean isOpenPremium(){
        return Setting.getBooleanByNbr(Setting.KEY_PREMIUM_SWITCH,false) && MyApplication.mDataCache.isLoginYet();
    }

    /**
     * 是否尊享版用户
     */
    public Boolean isPremium(){
        return "1".equals(MyApplication.mDataCache.UserIsPremiumEditionUser) || Log.dbgFlg;
//        return "1".equals(MyApplication.mDataCache.UserIsPremiumEditionUser);
    }

    /**
     * 是否切换回尊享版
     */
    public Boolean isNeedOpenPremium(){
//        return MyApplication.mDataCache.isOpenPremium() && Variable.isOnHome;
        return MyApplication.mDataCache.isOpenPremium();
    }

    /**
     * 是否切换回尊享版-底部字体颜色默认值和背景默认值使用
     */
    public Boolean isNeedOpenPremium2(){
        return MyApplication.mDataCache.isOpenPremium() && Variable.isOnHome;
    }

    /**
     * 是否关联登录宽带
     */
    public Boolean isRelateLoginBroadband(){
        return MyApplication.mBroadbandDataCache.isLoginYet()
                && UtilText.isEmptyOrNull(Setting.getStringDecode("BroadBandLoginMsg", ""));
    }

    /**
     * 返回用户登录状态
     * @return 本网返回0，异网返回1，未登录返回空
     */
    public String getIsChinatelecom(){
        //本网传0，异网传1，未登录传空
        if(isLoginYet()){
            if(isCtUser){//本网
                return "0";
            }else {//异网
                return "1";
            }
        }else {//未登录
            return "";
        }
    }

}
