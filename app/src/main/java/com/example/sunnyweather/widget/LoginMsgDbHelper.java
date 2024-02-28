package com.example.sunnyweather.widget;


import com.example.sunnyweather.base.Variable;

import org.litepal.LitePal;

/**
 * 说明：绑定登录数据库操作类
 *
 * @作者 Huangssh
 * @创建时间 2018/10/23 16:08
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */

public class LoginMsgDbHelper {

/*
    */
/**
     * 绑定登录保存某条记录(存在则更新)
     * @return
     *//*

    public static void saveMsg(LoginMsg loginMsg){
        if (!UtilText.isEmptyOrNull(loginMsg.phoneNum)){
            // 自动登录
            loginMsg.isAutoLogin = Constants.AUTO_LOGIN.AUTO;
            loginMsg.isCtUser = MyApplication.mDataCache.isCtUser ? UserLoginNormalData.CT_TYPE.CT_USER : UserLoginNormalData.CT_TYPE.ALIEN;
            loginMsg.saveOrUpdate("phoneNum = ? and unifyAccountType = ?", loginMsg.phoneNum, loginMsg.unifyAccountType);
        }
    }

    */
/**
     * 历史记录登录保存某条记录(存在则更新)
     * @return
     *//*

    public static void saveHistoryMsg(LoginMsg loginMsg){
        if (!UtilText.isEmptyOrNull(loginMsg.phoneNum)){
            loginMsg.date = new Date();
            loginMsg.historyState = Constants.DELETE_STATE.EXIST;
            loginMsg.type = Constants.LoginType.HISTORY;
            loginMsg.isAutoLogin = Constants.AUTO_LOGIN.AUTO;
            // 绑定记录假删除
            loginMsg.bindState = Constants.DELETE_STATE.DELETE;
            loginMsg.saveOrUpdate("phoneNum = ? and unifyAccountType = ?", loginMsg.phoneNum, loginMsg.unifyAccountType);
        }
    }

    */
/**
     * 退出登录状态更新
     * @return
     *//*

    public static void logout(LoginMsg loginMsg){
        ContentValues values = new ContentValues();
        values.put("isAutoLogin", Constants.AUTO_LOGIN.NOT_AUTO);
        LitePal.updateAll(LoginMsg.class, values, "phoneNum = ? and unifyAccountType = ?", loginMsg.phoneNum, loginMsg.unifyAccountType);
    }

    */
/**
     * 更新730及之前的老用户的手机登录账户类型为手机
     * @return
     *//*

    public static void updatePhoneAccountType(){
        ContentValues values = new ContentValues();
        values.put("unifyAccountType", Constants.UnifyAccountType.Login_Phone);
        LitePal.updateAll(LoginMsg.class, values);
    }

    */
/**
     * 获取在绑定登录内的手机登录的token
     * @return
     *//*

    public static String getToken(String phoneNum){
        List<LoginMsg> loginMsgs = LitePal.where("phoneNum = ? and unifyAccountType = ? and bindState = ?", phoneNum,
                Constants.UnifyAccountType.Login_Phone, Constants.DELETE_STATE.EXIST).find(LoginMsg.class);
        String token = "";
        if (!loginMsgs.isEmpty()){
            token = loginMsgs.get(0).token;
        }
        return token;
    }

    */
/**
     * 获取宽带的最近一条登录信息
     * @return
     *//*

    public static LoginMsg getFirstList(){
        List<LoginMsg> firstNews = LitePal.where("unifyAccountType = ?", Constants.UnifyAccountType.Login_Broadband).order("date desc").find(LoginMsg.class);
        LoginMsg loginMsg = null;
        if (firstNews != null && firstNews.size() > 0){
            loginMsg = firstNews.get(0);
        }
        return loginMsg;
    }

    */
/**
     * 获取全部绑定登录号码(手机+宽带混合)
     * @return
     *//*

    public static List<LoginMsg> getList(){
        List<LoginMsg> list = LitePal.where("type = ? and bindState = ?", Constants.LoginType.BIND, Constants.DELETE_STATE.EXIST).order("date desc").find(LoginMsg.class);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).position = i;
        }
        // 去重
        list = removeDuplicteUsers(list);
        Collections.sort(list);
        return list;
    }


    */
/**
     * 获取手机全部绑定登录号码
     * @return
     *//*

    public static List<LoginMsg> getPhoneList(){
        List<LoginMsg> list = LitePal.where("type = ? and unifyAccountType = ? and bindState = ?", Constants.LoginType.BIND,
                Constants.UnifyAccountType.Login_Phone, Constants.DELETE_STATE.EXIST).order("date desc").find(LoginMsg.class);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).position = i;
        }
        // 去重
        list = removeDuplicteUsersByNum(list);
        Collections.sort(list);
        return list;
    }

    */
/**
     * 获取宽带全部绑定登录号码
     * @return
     *//*

    public static List<LoginMsg> getBroadbandList(){
        List<LoginMsg> list = LitePal.where("type = ? and unifyAccountType = ? and bindState = ?", Constants.LoginType.BIND,
                Constants.UnifyAccountType.Login_Broadband, Constants.DELETE_STATE.EXIST).order("date desc").find(LoginMsg.class);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).position = i;
        }

        // 去重
        list = removeDuplicteUsersByNum(list);
        Collections.sort(list);
        return list;
    }

    */
/**
     * 获取全部历史记录
     * 740之前按历史标识，之后获取全部的前四条
     * @param type
     * @return
     *//*

    public static List<LoginMsg> getHistoryList(String type, String broadBandLoginType){
        List<LoginMsg> list = new ArrayList<>();
        // 手机登录和宽带随机码登录的历史记录联合查询
        if (Constants.UnifyAccountType.Login_Phone.equals(type) || broadBandLoginType.equals(Constants.BroadBandLoginType.SMS)){
            list = LitePal.where("(unifyAccountType = ? or broadBandLoginType = ?) and historyState = ?",
                    Constants.UnifyAccountType.Login_Phone, Constants.BroadBandLoginType.SMS, Constants.DELETE_STATE.EXIST).limit(4).order("date desc").find(LoginMsg.class);

            for (int i = 0; i < list.size(); i++) {
                list.get(i).position = i;
            }
            // 去重
            list = removeDuplicteUsersByNum(list);
            Collections.sort(list);
        }else{
            // 宽带账密登录历史记录
            if (broadBandLoginType.equals(Constants.BroadBandLoginType.PSW)){
                list = LitePal.where("broadBandLoginType = ? and historyState = ?",  Constants.BroadBandLoginType.PSW, Constants.DELETE_STATE.EXIST).limit(4).order("date desc").find(LoginMsg.class);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).position = i;
        }
        return list;
    }

    */
/**
     * 获取本网手机号列表
     * @return
     *//*

    public static List<String> getHistoryList(){
        List<LoginMsg> list = LitePal.where("(unifyAccountType = ? or broadBandLoginType = ?) and historyState = ? and isCtUser = ?",
                Constants.UnifyAccountType.Login_Phone,
                Constants.BroadBandLoginType.SMS,
                UserLoginNormalData.CT_TYPE.CT_USER,
                Constants.DELETE_STATE.EXIST).limit(4).order("date desc").find(LoginMsg.class);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).position = i;
        }
        // 去重
        list = removeDuplicteUsersByNum(list);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).position = i;
        }

        List<String> phoneList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            phoneList.add(list.get(i).phoneNum);
        }
        return phoneList;
    }

    */
/**
     * 删除某条历史记录
     * @return
     *//*

    public static void deleteHistoryNum(String num){
        ContentValues values = new ContentValues();
        values.put("historyState", Constants.DELETE_STATE.DELETE);
        LitePal.updateAll(LoginMsg.class, values, "phoneNum = ?", num);
    }

    */
/**
     * 删除某条绑定记录
     * @return
     *//*

    public static void deleteBindNum(LoginMsg loginMsg){
        ContentValues values = new ContentValues();
        values.put("bindState", Constants.DELETE_STATE.DELETE);
        LitePal.updateAll(LoginMsg.class, values, "phoneNum = ? and unifyAccountType = ?", loginMsg.phoneNum, loginMsg.unifyAccountType);
    }

    */
/**
     * 更新某条数据的头像
     * @param loginMsg
     *//*

    public static void updateState(LoginMsg loginMsg){
        ContentValues values = new ContentValues();
        values.put("headUrl", loginMsg.headUrl);
        try {
            LitePal.updateAll(LoginMsg.class, values, "phoneNum = ? and unifyAccountType = ?", loginMsg.phoneNum, loginMsg.unifyAccountType);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    */
/**
     * 更新某条数据的token
     * @param loginMsg
     *//*

    public static void updateStateToken(LoginMsg loginMsg){
        ContentValues values = new ContentValues();
        values.put("token", loginMsg.token);
        try {
            LitePal.updateAll(LoginMsg.class, values, "phoneNum = ? and unifyAccountType = ?", loginMsg.phoneNum, loginMsg.unifyAccountType);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    */
/**
     * 绑定数据根据号码和类型去重
     * @param loginMsgs
     * @return
     *//*

    public static ArrayList<LoginMsg> removeDuplicteUsers(List<LoginMsg> loginMsgs) {
        Set<LoginMsg> s = new TreeSet<>(new Comparator<LoginMsg>() {

            @Override
            public int compare(LoginMsg o1, LoginMsg o2) {
                int compareToResult = 1;//==0表示重复
                if (o2.phoneNum.equals(o1.phoneNum) && o2.type.equals(o1.type) && o2.unifyAccountType.equals(o1.unifyAccountType)){
                    compareToResult = 0;
                }
                return compareToResult;
            }
        });
        s.addAll(loginMsgs);

        return new ArrayList<>(s);
    }

    */
/**
     * 数据根据号码去重
     * @param loginMsgs
     * @return
     *//*

    public static ArrayList<LoginMsg> removeDuplicteUsersByNum(List<LoginMsg> loginMsgs) {
        Set<LoginMsg> s = new TreeSet<>(new Comparator<LoginMsg>() {

            @Override
            public int compare(LoginMsg o1, LoginMsg o2) {
                return o2.phoneNum.compareTo(o1.phoneNum);
            }
        });
        s.addAll(loginMsgs);

        return new ArrayList<>(s);
    }

    */
/**
     * 是否已登录情况下 db处于默认数据库
     * @return
     *//*

    public static boolean isSwitchDefault() {
        if (MyApplication.mDataCache.isLoginYet() && Variable.mIsUseDefaultDb){
            return true;
        }else{
            return false;
        }
    }

    public static void switchDb(String source) {
        if (isSwitchDefault()){
            LitePalDB litePalDB = LitePalDB.fromDefault(MyApplication.mDataCache.UserPhoneNbr);
            LitePal.use(litePalDB);
            UtilLog.saveLitePalDataLogcat(source + "：当前数据库：" + MyApplication.mDataCache.UserPhoneNbr);
            Variable.mIsUseDefaultDb = false;
        }
    }
*/

    public static void useDefault(String source) {
        if (!Variable.mIsUseDefaultDb){
            LitePal.useDefault();
            UtilLog.saveLitePalDataLogcat(source + "：默认数据库");
            Variable.mIsUseDefaultDb = true;
        }
    }

}
