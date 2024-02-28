package com.example.sunnyweather.task;

import android.content.Context;

import com.example.sunnyweather.base.BaseTask;
import com.example.sunnyweather.base.CompoundADSpaceResponse;
import com.example.sunnyweather.base.Constants;
import com.example.sunnyweather.base.MyApplication;
import com.example.sunnyweather.base.MyTaskCache;
import com.example.sunnyweather.base.Response;
import com.example.sunnyweather.base.UtilData;
import com.example.sunnyweather.base.UtilEncryption;
import com.example.sunnyweather.base.UtilLog;
import com.example.sunnyweather.request.CompoundADSpaceRequest;
import com.example.sunnyweather.util.UtilText;


/**
 * 8.5.0 复合广告位信息接口
 */
public class CompoundADSpaceTask extends BaseTask {
    private CompoundADSpaceResponse response;
    private CompoundADSpaceRequest request;

    private String type = "";
    private String provinceCode = "";//省编码，有就传没有就默认“”
    private String cityCode = "";//市编码，有就传没有就默认“”

    /**
     *  广告位类型:1、商城首页轮播位 2、商城首页主导航 3、商城首页副导航 4、生活频道顶部tab 5、9.0首页顶部icon区 6、9.0首页加号区 7、9.0手机信息区卡片底部配置 8、9.0异网推荐套餐 9、9.0首页底部营销层快捷入口
     *  10、9.0首页底部营销层轮播图 11、9.0无宽带广告位配置 12、960关爱版首页金刚栏、 13、960关爱版首页广告位、14、960关爱版首页底部轮播图 15、960适老版底部tab栏 16、10.0首页金刚栏icon区 17、10.0我的频道我的服务
     *  18、10.0我的频道营销活动 19、10.0我的频道底部客服入口 20、10.2版本：桌面小插件 21、10.5版本：近域签到设置页 22、10.6版本：国际版首页金刚栏 23、10.6版本：国际版首页功能区 24、11.1版本，消息频道首页-顶部配置功能列表
     *  25、11.1版本，消息频道首页-提示条列表 100、9.7 P-RAN首页营销广告位 101、9.7 P-RAN首页底部轮播图 102、10.1.0 P-RAN设置页配置项
     */
    public static class TYPE {
        public static final String AD_MALL_FOCUS = "1";//商城首页轮播图
        public static final String AD_MALL_MAIN_NAV = "2"; //商城首页主导航
        public static final String AD_MALL_DEPUTY_NAV = "3"; //商城首页副导航
        public static final String AD_LIFE_TOP = "4";//生活频道顶部tab
        public static final String AD_MAIN_TOP_ICON = "5";//9.0首页顶部icon区
        public static final String AD_MAIN_TOP_ADD = "6";//9.0首页加号区
        public static final String AD_PHONE_INFORMATION_BOTTOM = "7";//9.0手机信息区卡片底部配置
        public static final String AD_YW_RECOMMEND = "8";//9.0异网推荐套餐
        public static final String AD_BOTTOM_QUICK_FUNCTION = "9";//9.0首页底部营销层快捷入口
        public static final String AD_BOTTOM_ROTATION_FOCUS = "10";//9.0首页底部营销层轮播图
        public static final String AD_NO_BROADBAND = "11";//9.0无宽带广告位配置
        public static final String AD_CARE_MAIN_FUNCTION = "12";//960关爱版首页金刚栏
        public static final String AD_CARE_QUICK_FUNCTION = "13";//960关爱版首页广告位
        public static final String AD_CARE_BOTTOM_FOCUS = "14";//960关爱版首页底部轮播图
        public static final String AD_CARE_TABLE_BAR = "15";//960适老版底部tab栏
        public static final String AD_QUICK_FUNCTION = "16";//10.0首页金刚栏icon区
        public static final String AD_MY_SERVICE = "17";//10.0我的频道我的服务
        public static final String AD_MY_FUNCTION = "18";//10.0我的频道营销活动
        public static final String AD_MY_BOTTOM_CUSTOMER_SERVICE = "19";//10.0我的频道底部客服入口
        public static final String AD_DESKTOP_PLUGIN = "20";//10.2版本：桌面小插件
        public static final String AD_SIGN_IN_SETTING = "21";//10.5版本：近域签到设置页
        public static final String AD_INTERNATIONAL_MAIN_FUNCTION = "22";//10.6版本：国际版首页金刚栏
        public static final String AD_INTERNATIONAL_QUICK_FUNCTION = "23";//10.6版本：国际版首页功能区
        public static final String AD_MESSAGE_CHANNEL_TOP_FUNCTION = "24";//11.1版本，消息频道首页-顶部配置功能列表
        public static final String AD_MESSAGE_CHANNEL_TIP = "25";//11.1版本，消息频道首页-提示条列表
        public static final String AD_CAMERA_PAGE = "26";//11.2版本，扫一扫广告位配置
        public static final String AD_PRAN_FOCUS = "100";//970 P-RAN首页营销广告位
        public static final String AD_PRAN_BOTTOM_FOCUS = "101";//970 P-RAN首页底部轮播图
        public static final String AD_PRAN_SETTING = "102";//10.1.0 P-RAN设置页配置项
    }

    public CompoundADSpaceTask(Context context){
        super(context);
    }

    /**
     *  广告位类型:1、商城首页轮播位 2、商城首页主导航 3、商城首页副导航 4、生活频道顶部tab 5、9.0首页顶部icon区 6、9.0首页加号区 7、9.0手机信息区卡片底部配置 8、9.0异网推荐套餐 9、9.0首页底部营销层快捷入口
     *  10、9.0首页底部营销层轮播图 11、9.0无宽带广告位配置 12、960关爱版首页金刚栏、 13、960关爱版首页广告位、14、960关爱版首页底部轮播图 15、960适老版底部tab栏 16、10.0首页金刚栏icon区 17、10.0我的频道我的服务
     *  18、10.0我的频道营销活动 19、10.0我的频道底部客服入口 20、10.2版本：桌面小插件 21、10.5版本：近域签到设置页 22、10.6版本：国际版首页金刚栏 23、10.6版本：国际版首页功能区 24、11.1版本，消息频道首页-顶部配置功能列表
     *  25、11.1版本，消息频道首页-提示条列表 26、11.2版本，扫一扫广告位配置 100、9.7 P-RAN首页营销广告位 101、9.7 P-RAN首页底部轮播图 102、10.1.0 P-RAN设置页配置项
     * 使用CompoundADSpaceTask.TYPE赋值
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        request = new CompoundADSpaceRequest();
        request.setShopId(Constants.SHOP);
        request.setAccount(UtilEncryption.encrypt(MyApplication.mDataCache.UserPhoneNbr));
        request.setType(type);
        request.setPhoneType(MyApplication.mDataCache.PhoneType);
        request.setNetType(MyApplication.mDataCache.NetType);
        request.setAccessAuth(MyApplication.mDataCache.getAccessAuth());
        request.setIsChinatelecom(MyApplication.mDataCache.getIsChinatelecom());
        request.setIsPremiumEdition(MyApplication.mDataCache.getIsPremiumEdition());
        request.setIsNewUser(MyApplication.mDataCache.IsNewUser);
        request.setDevelopCode(MyApplication.mDataCache.DevelopCode);

        //如果外部有设置则用设置的值去请求(商城首页轮播图、商城首页主导航、商城首页副导航)，如果没有则用定位或缓存的信息去请求
        if (!UtilText.isEmptyOrNull(provinceCode)) {
            request.setProvinceCode(provinceCode);
        } else {
            //未登录用定位返回的省市编码，已登录用登录接口返回的省市编码
            if (!MyApplication.mDataCache.isLoginYet() && MyApplication.mDataCache.CityInfo != null) {
                request.setProvinceCode(MyApplication.mDataCache.CityInfo.getProvCode());
            } else {
                request.setProvinceCode(MyApplication.mDataCache.ProvinceCode);
            }
        }
        if(!UtilText.isEmptyOrNull(cityCode)){
            request.setCityCode(cityCode);
        }else {
            //未登录用定位返回的省市编码，已登录用登录接口返回的省市编码
            if (!MyApplication.mDataCache.isLoginYet() && MyApplication.mDataCache.CityInfo != null) {
                request.setCityCode(MyApplication.mDataCache.CityInfo.getCityCode());
            } else {
                request.setCityCode(MyApplication.mDataCache.CityCode);
            }
        }

       /* if (mOnTaskStart != null) {
            Response response = (Response) MyTaskCache.getInstance(mContext).getCacheTaskJsonRetrofit(request);
            if(response!=null && response instanceof CompoundADSpaceResponse){
                mOnTaskStart.onStart((CompoundADSpaceResponse)response);
            }else{
                mOnTaskStart.onStart(null);
            }
        }*/
    }

    @Override
    protected Boolean doInBackground(String... params) {
        response = request.getResponse();
        if (type.contains(TYPE.AD_BOTTOM_QUICK_FUNCTION) || type.contains(TYPE.AD_BOTTOM_ROTATION_FOCUS)){
            UtilData.setRefreshState(response.compoundADSpaceData.refreshType);
            UtilLog.saveRefreshLogcat("compoundADSpaceData refreshType：" + response.compoundADSpaceData.refreshType);

        }
        return response.isSuccess();
    }

    @Override
    protected void onPostExecute(Boolean rslt) {
        super.onPostExecute(rslt);
        if (rslt) {
            if (mOnTaskFinished != null){
                mOnTaskFinished.onSucc(response);
                if (mOnTaskStart != null) {
                    MyTaskCache.getInstance(mContext).saveTaskJsonRetrofit(request, response);
                }
            }
        } else {
            if (mOnTaskFinished != null)
                mOnTaskFinished.onFail(response);
        }
    }
}
