package com.example.sunnyweather.base;

public class EReportData {
    public String mPingTime = "";
    public String mPingCode = "";
    public String mResponseCode = "900";
    public String mResultCode = "";
    public String mLoginType = ""; // 登录类型
    public String mDesperation = ""; // 异常信息，可能是output也可能是接口返回的resultDesc
    public String mExceptionName = ""; // 如果接口请求还没到服务端就发生异常则上报客户端捕获到的异常信息
    public String mElapsedTime = ""; // 登录耗时
}
