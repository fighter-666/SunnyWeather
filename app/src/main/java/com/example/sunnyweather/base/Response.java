package com.example.sunnyweather.base;

import com.example.sunnyweather.R;
import com.example.sunnyweather.SunnyWeatherApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/12/28 9:13
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public abstract class Response<T> implements Serializable {

    public HeaderInfos headerInfos;

    public T responseData;

    //default value
    private String resultCode = SunnyWeatherApplication.context.getResources().getString(R.string.network_no_connect_code);//001 网络请求失败；002 解析失败
    private String resultDesc = SunnyWeatherApplication.context.getResources().getString(R.string.network_no_connect);
    private boolean isSuccess = false;
    private String attach;


    public String getResultCode() {
        return resultCode;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    public String getResultDesc() {
        return resultDesc;
    }
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
    public String getAttach() {
        return attach;
    }
    public void setAttach(String attach) {
        this.attach = attach;
    }
    public boolean isSuccess() {
        return isSuccess;
    }
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean fixed(ResponseBaseData responseData) {
        if (headerInfos == null) {
            this.isSuccess = false;
            this.resultCode = SunnyWeatherApplication.context.getResources().getString(R.string.network_no_connect_code);
            this.resultDesc = SunnyWeatherApplication.context.getResources().getString(R.string.network_no_connect);
            return false;
        }

       /* // 切回默认数据库
        if (Constants.ResponseErrorCode.TokenInvalid.equals(headerInfos.code) || Constants.ResponseErrorCode.BroadbandTokenInvalid.equals(headerInfos.code)
                || Constants.ResponseErrorCode.BroadbandNoLogin.equals(headerInfos.code) || Constants.ResponseErrorCode.PhoneNoLogin.equals(headerInfos.code) || Constants.ResponseErrorCode.TokenException.equals(headerInfos.code)){
            LoginMsgDbHelper.useDefault("Response" + headerInfos.code);
        }

        //X104:token校验失败
        if(Constants.ResponseErrorCode.TokenInvalid.equals(headerInfos.code)){
            this.isSuccess = false;
            //写死提示文案
            //亲，您长时间未登录或已在别处登录。
            this.resultDesc =  "";

            UtilLog.saveLoginLogcat("X104");

            //将UserPhoneNbr置为null，保证判断登录isLoginYet函数返回false
            MyApplication.mDataCache.UserPhoneNbr = null;
            Log.i("hssmDataCache.UserPhoneNbr = null", "BaseResponseJson-TokenInvalid");
            //下线通知
            RedDotHelper.getInstance().onRedDotListener(Constants.RedDotTabName.X104, headerInfos.reason);
            return false;
        //X105:宽带token校验失败
        }else if (Constants.ResponseErrorCode.BroadbandTokenInvalid.equals(headerInfos.code)) {
            this.isSuccess = false;
            //写死提示文案
            this.resultDesc =  "亲，您长时间未登录或已在别处登录该宽带。";
            UtilLog.saveLoginLogcat("X105");

            //将broadbandAccount置为null，保证判断登录isLoginYet函数返回false
            MyApplication.mBroadbandDataCache.getCurrentBroadband().broadbandAccount = null;
            return false;
        //X108:宽带未登录
        }else if (Constants.ResponseErrorCode.BroadbandNoLogin.equals(headerInfos.code)) {
            this.isSuccess = false;
            //写死提示文案
            this.resultDesc =  "亲，请登录宽带后使用此功能。";
            UtilLog.saveLoginLogcat("X108");

            //将broadbandAccount置为null，保证判断登录isLoginYet函数返回false
            MyApplication.mBroadbandDataCache.getCurrentBroadband().broadbandAccount = null;
            return false;
        //X110:手机未登录
        }else if (Constants.ResponseErrorCode.PhoneNoLogin.equals(headerInfos.code)) {
            this.isSuccess = false;
            //写死提示文案
            this.resultDesc =  "亲，请登录后使用此功能。";
            UtilLog.saveLoginLogcat("X110");

            //将UserPhoneNbr置为null，保证判断登录isLoginYet函数返回false
            MyApplication.mDataCache.UserPhoneNbr = null;
            Log.i("hssmDataCache.UserPhoneNbr = null", "BaseResponseJson-PhoneNoLogin");
            return false;
        //X201:手机token异常
        } else if(Constants.ResponseErrorCode.TokenException.equals(headerInfos.code)) {
            this.isSuccess = false;
            //写死提示文案
            this.resultDesc = "亲，您长时间未登录或已在别处登录。";
            UtilLog.saveLoginLogcat("X201");

            //将UserPhoneNbr置为null，保证判断登录isLoginYet函数返回false
            MyApplication.mDataCache.UserPhoneNbr = null;
            Log.i("hssmDataCache.UserPhoneNbr = null", "BaseResponseJson-TokenException");
            RedDotHelper.getInstance().onRedDotListener(Constants.RedDotTabName.X201);
            return false;
            // 容灾
        }else if(Constants.ResponseErrorCode.ClusterException.equals(headerInfos.code)){
            this.isSuccess = false;
            this.resultDesc = "";
            RedDotHelper.getInstance().onRedDotListener(Constants.RedDotTabName.ClusterException);
            return false;
        }
*/

        if (responseData == null) {
            this.isSuccess = false;
            this.resultCode = "001";
            this.resultDesc = "网络不给力啊，请稍后再试~";
            return false;
        }
        this.attach = (responseData.attach);
        this.resultCode = responseData.resultCode;
        this.resultDesc = responseData.resultDesc;

        if("".equals(this.resultDesc)){
            this.resultDesc = ErrorCode.get(resultCode);
        }
        if ("0000".equals(this.resultCode)) {
            isSuccess = true;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Response [resultCode="
                + resultCode + ", resultDesc=" + resultDesc + ", attach="
                + attach + "]";
    }

    /***
     * 深拷贝
     *
     * 针对缓存数据对象中包含敏感信息加密处理
     * 防止值引用改变初始值
     *
     * @return
     * @throws Exception
     */
    public Object deepCopy() throws Exception
    {
        // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(this);

        // 将流序列化成对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }
}
