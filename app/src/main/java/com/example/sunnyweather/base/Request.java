package com.example.sunnyweather.base;

import androidx.annotation.WorkerThread;


import com.example.sunnyweather.common.Variable;
import com.example.sunnyweather.util.UtilText;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/12/27 16:10
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public abstract class Request<T extends Response> {

    private static volatile OkHttpClient okHttpClient = null;
    private static final String TAG = "Request";
    private HeaderInfos headerInfos; // 头部信息
    private Content content; // 内容信息

    private boolean isHttps;
    public boolean isReqEnc = false;
    public boolean isRespEnc = false;

    @WorkerThread
    public abstract T getResponse();

    public Request() {
        this.headerInfos = new HeaderInfos();
        this.content = new Content();
    }

    public HeaderInfos getHeaderInfos() {
        return headerInfos;
    }

    public Content getContent() {
        return content;
    }

    public void put(String key, Object value) {
        content.put(key, value);
    }

    public static void init(String phoneNum, String token) {
        HeaderInfos.init(phoneNum);
        HeaderInfos.setToken(token);
    }

    public static void initBroad(String broadAccount, String broadToken) {
        HeaderInfos.initBroad(broadAccount);
        HeaderInfos.setBroadToken(broadToken);
    }

//    public static void revert() {
//        Request.init(MyApplication.mDataCache.UserPhoneNbr,MyApplication.mDataCache.token);
//    }

    protected Retrofit https() {
        isHttps = true;
        return link(ClientKeyStore.getKeyStore());
    }

    protected Retrofit http() {
        isHttps = false;
        return link(ClientKeyStore.getKeyStore());
    }

    public boolean isHttps() {
        return isHttps;
    }

    private  Retrofit link(KeyStore keyStore) {
        //签名校验，防止二次签名
     /*   if(UtilText.isEmptyOrNull(Variable.mSK)){
            echo("Request: the sign key is null or invalid");
            return null;
        }*/

        try {
            HttpUrl.Builder httpUrl = HttpUrl.parse(isHttps ? Constants.URL_HTTPS_HOST : Constants.URL_HTTP_HOST)
                    .newBuilder()
                    .port(isHttps ? Constants.PORT_HTTPS : Constants.PORT_HTTP);

            // 卡密OCR识别接口比较特殊, 连到独立的域名和端口
//            if(headerInfos.getCode().equals("kmOcrInfo")){
//                httpUrl = HttpUrl.parse(Constants.URL_KMOCR_HTTPS_HOST)
//                        .newBuilder()
//                        .port(Constants.PORT_KMOCR_HTTPS);
//            }

            // 统计上报接口比较特殊, 连到独立的域名和端口
            if(headerInfos.getCode().equals("EReport")||headerInfos.getCode().equals("hFiveEreportInfo")||headerInfos.getCode().equals("directSeedingInfoMonitor")){
                httpUrl = HttpUrl.parse(Constants.URL_REPORT_HTTPS_HOST)
                        .newBuilder()
                        .port(Constants.PORT_REPORT_HTTPS);
            }

            // 广告位接口比较特殊, 连到独立的域名和端口
            if(headerInfos.getCode().equals("queryAdvertisingSpace")||headerInfos.getCode().equals("queryIndexTop")||headerInfos.getCode().equals("queryOffersAndBroadband")
                    ||headerInfos.getCode().equals("suspendWindow")||headerInfos.getCode().equals("queryRechargeSudoku")||headerInfos.getCode().equals("queryEssentialTools")
                    ||headerInfos.getCode().equals("queryActivityInfo")||headerInfos.getCode().equals("defaultSearchTerms")||headerInfos.getCode().equals("hotWordSearch")
                    ||headerInfos.getCode().equals("keyWordSearch")||headerInfos.getCode().equals("markReadMsg") ||headerInfos.getCode().equals("queryThreeLevelMsg")
                    ||headerInfos.getCode().equals("queryRollImage") ||headerInfos.getCode().equals("queryProvinceList")||headerInfos.getCode().equals("queryMyService")
                    ||headerInfos.getCode().equals("queryBusHallAdvSpace") ||headerInfos.getCode().equals("getRechargePageTab")||headerInfos.getCode().equals("getRechargePageV2")
                    ||headerInfos.getCode().equals("getRechargePageFlowPacket") ||headerInfos.getCode().equals("queryRechargeResultPage")||headerInfos.getCode().equals("compoundADSpace")
                    ||headerInfos.getCode().equals("loginSuspendWindow") ||headerInfos.getCode().equals("defaultSearchTermsV2")||headerInfos.getCode().equals("hotWordSearchList")
                    ||headerInfos.getCode().equals("hotWordSearchV2") ||headerInfos.getCode().equals("keyWordClaifySearch")||headerInfos.getCode().equals("keyWordSearchV2")
                    ||headerInfos.getCode().equals("suspendWindowV2") ||headerInfos.getCode().equals("queryOrderResult")||headerInfos.getCode().equals("baseStationConnect")
                    ||headerInfos.getCode().equals("bottomTieADSpace") ||headerInfos.getCode().equals("mainFunctionalZone") ||headerInfos.getCode().equals("qryTopBackGround")
                    ||headerInfos.getCode().equals("qryTitleConfiguration") ||headerInfos.getCode().equals("getSpecificationsMonth")
                    ||headerInfos.getCode().equals("getFeedList") ||headerInfos.getCode().equals("getFeedTab")||headerInfos.getCode().equals("predictiveSearch")
                    ||headerInfos.getCode().equals("getBroadBandConf") ||headerInfos.getCode().equals("qryIndexTopBackGround") ||headerInfos.getCode().equals("directSeedingAdGuide")
                    ||headerInfos.getCode().equals("suspendWindowForQuery")||headerInfos.getCode().equals("hotSelectionCard") ||headerInfos.getCode().equals("queryAssistTopConf")
                    ||headerInfos.getCode().equals("assistAdvSpace")||headerInfos.getCode().equals("recommendedProductCard") ||headerInfos.getCode().equals("defaultMemberConf")
                    ||headerInfos.getCode().equals("getQueryProcessingTab")||headerInfos.getCode().equals("getFunctionPage") ||headerInfos.getCode().equals("getCompProgress")
                    ||headerInfos.getCode().equals("welfareFloors") ||headerInfos.getCode().equals("refreshTaskList") ||headerInfos.getCode().equals("getSignInBottomTab")
                    ||headerInfos.getCode().equals("usageCard") ||headerInfos.getCode().equals("getPremiumPrivilege") ||headerInfos.getCode().equals("boutiqueRecommend")
                    ||headerInfos.getCode().equals("tradePreferred")||headerInfos.getCode().equals("queryServiceMessage") ||headerInfos.getCode().equals("queryMessageChannel")
                    ||headerInfos.getCode().equals("getPRANNetworkList")||headerInfos.getCode().equals("qryNearConfigAdvertising") ||headerInfos.getCode().equals("updateMessageOpen")
                    ||headerInfos.getCode().equals("updateMessageTop")||headerInfos.getCode().equals("deleteMessage") ||headerInfos.getCode().equals("myLiveCard")
                    ||headerInfos.getCode().equals("queryMyChannelTop")||headerInfos.getCode().equals("queryMyPrivilegeCard") ||headerInfos.getCode().equals("myChannelFloor")
                    ||headerInfos.getCode().equals("bestPayQrCodeCheck")){
                httpUrl = HttpUrl.parse(Constants.URL_AD_HTTPS_HOST)
                        .newBuilder()
                        .port(Constants.PORT_AD_HTTPS);
            }

            // 统一客服接口比较特殊, 连到独立的域名和端口
            if(headerInfos.getCode().equals("queryAdRotation")||headerInfos.getCode().equals("queryCustomerAdvertising") ||headerInfos.getCode().equals("customserConnect")
                    ||headerInfos.getCode().equals("customserEndConnect") ||headerInfos.getCode().equals("customserSendMsg")||headerInfos.getCode().equals("ueryCustomserMsg")
                    ||headerInfos.getCode().equals("queryStatus")||headerInfos.getCode().equals("robotMsg") ||headerInfos.getCode().equals("queryCustomerVoiceVideo")
                    ||headerInfos.getCode().equals("robotVoiceInter") ||headerInfos.getCode().equals("queryVideoCallInfo")||headerInfos.getCode().equals("newRobotVoiceInter")
                    ||headerInfos.getCode().equals("intelligentAssistant")||headerInfos.getCode().equals("bestPay")||headerInfos.getCode().equals("bankCardRecharge")
                    ||headerInfos.getCode().equals("llb4gChange")||headerInfos.getCode().equals("llb4GOrder")||headerInfos.getCode().equals("aliPayOrder")
                    ||headerInfos.getCode().equals("wechatPayOrder")||headerInfos.getCode().equals("telePaymentOrder")||headerInfos.getCode().equals("cardRecharge")
                    ||headerInfos.getCode().equals("directSeedingInfo")||headerInfos.getCode().equals("focusAnchor")||headerInfos.getCode().equals("getAuxiliaryList")
                    ||headerInfos.getCode().equals("getPopupInfo")||headerInfos.getCode().equals("getStreamState") ||headerInfos.getCode().equals("giveThumbsup")
                    ||headerInfos.getCode().equals("importAccount")||headerInfos.getCode().equals("updateVisitorNum") ||headerInfos.getCode().equals("qryTaskList")
                    ||headerInfos.getCode().equals("completeTaskReport")||headerInfos.getCode().equals("isReadCustomserMsg") ||headerInfos.getCode().equals("addIntimateNumAuth")
                    ||headerInfos.getCode().equals("queryIntimateNumAuthStatus")||headerInfos.getCode().equals("recommendDirectSeedingList") ||headerInfos.getCode().equals("interruptLink")
                    ||headerInfos.getCode().equals("semanticRecognition")||headerInfos.getCode().equals("liveRoomGiveGifts") ||headerInfos.getCode().equals("getLiveRoomGiftList")
                    ||headerInfos.getCode().equals("broadbandRechargeCheck")||headerInfos.getCode().equals("broadbandRecharge") ||headerInfos.getCode().equals("getDHStyleConfig")
                    ||headerInfos.getCode().equals("setDHStyleConfig")||headerInfos.getCode().equals("unionPay") ||headerInfos.getCode().equals("getDirectSeedingGoodsList")
                    ||headerInfos.getCode().equals("getDirectSeedingGoodsTopConfigList")||headerInfos.getCode().equals("remoteCall") ||headerInfos.getCode().equals("onCallAuthentication")
                    ||headerInfos.getCode().equals("changeGuessYouAsk")||headerInfos.getCode().equals("questionMsgCheck") ||headerInfos.getCode().equals("verificationName")
                    ||headerInfos.getCode().equals("paymentListV2")||headerInfos.getCode().equals("bestPayCheck") ||headerInfos.getCode().equals("bestPayAuth")
                    ||headerInfos.getCode().equals("suspendWindowForWap")||headerInfos.getCode().equals("myWallet")){
                httpUrl = HttpUrl.parse(Constants.URL_ONLINECUSTOMER_HTTPS_HOST)
                        .newBuilder()
                        .port(Constants.PORT_ONLINECUSTOMER_HTTPS);
            }

            // 登录接口比较特殊, 连到独立的域名和端口
            if(headerInfos.getCode().equals("loginNormal")||headerInfos.getCode().equals("getLoginRandomCode") ||headerInfos.getCode().equals("getTouristId")
                    ||headerInfos.getCode().equals("userLoginNormal")||headerInfos.getCode().equals("oneKeyLogin")||headerInfos.getCode().equals("broadBandSSOLogin")
                    ||headerInfos.getCode().equals("broadbandLogin")||headerInfos.getCode().equals("getAccessCodeDaily")||headerInfos.getCode().equals("queryNumberForAccessCode")
                    ||headerInfos.getCode().equals("broadbindLoginForMsg")||headerInfos.getCode().equals("broadbindLoginForOneK")||headerInfos.getCode().equals("qryWeixinBind")
                    ||headerInfos.getCode().equals("weixinBind")||headerInfos.getCode().equals("weixinBindLogin")||headerInfos.getCode().equals("weixinUnBind")
                    ||headerInfos.getCode().equals("getSmsIdStatus")||headerInfos.getCode().equals("authCodeCheck")||headerInfos.getCode().equals("getAuthCode")
                    ||headerInfos.getCode().equals("delTrustEquipment")||headerInfos.getCode().equals("queryTrustEquipment")||headerInfos.getCode().equals("getSingleForPran")
                    ||headerInfos.getCode().equals("familyBroadBandSSOLogin")||headerInfos.getCode().equals("getAuthRandomCode")||headerInfos.getCode().equals("authRandomCodeCheck")){
                httpUrl = HttpUrl.parse(Constants.URL_LOGIN_HTTPS_HOST)
                        .newBuilder()
                        .port(Constants.PORT_LOGIN_HTTPS);
            }

            // 服务一致性查询接口比较特殊, 连到独立的域名和端口
            if(headerInfos.getCode().equals("queryDetails")||headerInfos.getCode().equals("signatureStrCheck")||headerInfos.getCode().equals("integral")
                    ||headerInfos.getCode().equals("getIntegral")||headerInfos.getCode().equals("integralExchange")||headerInfos.getCode().equals("vouchersExchange")
                    ||headerInfos.getCode().equals("vouchersRecord")||headerInfos.getCode().equals("exchangeCharge")||headerInfos.getCode().equals("exchangeFlow")
                    ||headerInfos.getCode().equals("getRandomCode")||headerInfos.getCode().equals("invoiceList")||headerInfos.getCode().equals("issueInvoice")
                    ||headerInfos.getCode().equals("queryInvoiceInfo")||headerInfos.getCode().equals("queryInvoiceResult")||headerInfos.getCode().equals("sendMail")
                    ||headerInfos.getCode().equals("queryInvoiceType") ||headerInfos.getCode().equals("bookedBusiness")||headerInfos.getCode().equals("establishedhis")
                    ||headerInfos.getCode().equals("queryMyAgreement") ||headerInfos.getCode().equals("queryMyBroadBand")||headerInfos.getCode().equals("queryMyPackage")
                    ||headerInfos.getCode().equals("queryProductDetails")||headerInfos.getCode().equals("queryAccountInfo")||headerInfos.getCode().equals("queryBroadbandAccountInfo")
                    ||headerInfos.getCode().equals("qryRechargeRecord")||headerInfos.getCode().equals("qryFareBack")||headerInfos.getCode().equals("qryShareUsage")
                    ||headerInfos.getCode().equals("queryBillBalance")||headerInfos.getCode().equals("userPackage")||headerInfos.getCode().equals("qryFiveGenerHeatMap")
                    ||headerInfos.getCode().equals("yyLlQuery")||headerInfos.getCode().equals("queryMyData")||headerInfos.getCode().equals("queryPhoneNumInfor")
                    ||headerInfos.getCode().equals("queryIntimateNumMoneyFlow")||headerInfos.getCode().equals("getTelNumsByPhoneNum")||headerInfos.getCode().equals("sharingGetGold")
                    ||headerInfos.getCode().equals("broadbandAdrTabs")||headerInfos.getCode().equals("updateBroadbandBindAuth") ||headerInfos.getCode().equals("queryBroadbandBindingInfo")
                    ||headerInfos.getCode().equals("accountPwdAuth")||headerInfos.getCode().equals("accountPwdBind") ||headerInfos.getCode().equals("doBind")
                    ||headerInfos.getCode().equals("idCardAuth")||headerInfos.getCode().equals("qryInfoByIdCard") ||headerInfos.getCode().equals("unBind")
                    ||headerInfos.getCode().equals("accountInfoPopup")||headerInfos.getCode().equals("familyNetCard")||headerInfos.getCode().equals("qryCountOfDay")
                    ||headerInfos.getCode().equals("qryImportantData")||headerInfos.getCode().equals("qryIntegralLineData")||headerInfos.getCode().equals("userFluxPackage")
                    ||headerInfos.getCode().equals("rechargeRecord")||headerInfos.getCode().equals("queryPhoneBillBalance")||headerInfos.getCode().equals("getIntegralData")
                    ||headerInfos.getCode().equals("getIntegralRecord")||headerInfos.getCode().equals("qryImportantDataCare")||headerInfos.getCode().equals("importantDataKilo")
                    ||headerInfos.getCode().equals("myAccountNumCard")||headerInfos.getCode().equals("getSearchBill")||headerInfos.getCode().equals("getMemberList")
                    ||headerInfos.getCode().equals("combinedUsageQuery")||headerInfos.getCode().equals("myBillCardLine")||headerInfos.getCode().equals("myBalance")
                    ||headerInfos.getCode().equals("progressBarCard")||headerInfos.getCode().equals("familyCircleCard")||headerInfos.getCode().equals("querySignInPointDetail")
                    ||headerInfos.getCode().equals("createSignInPoint")||headerInfos.getCode().equals("deleteSignInPoint")||headerInfos.getCode().equals("equityCardAndBackGround")){
                httpUrl = HttpUrl.parse(Constants.URL_QUERY_HTTPS_HOST)
                        .newBuilder()
                        .port(Constants.PORT_QUERY_HTTPS);
            }

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(httpUrl.build())
                    .addConverterFactory(new MyConverters())
                    .client(createOkHttpClient(keyStore));
            return builder.build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private OkHttpClient createOkHttpClient(KeyStore keyStore) throws Exception{
        if (okHttpClient == null){
            synchronized (Request.class){
                if (okHttpClient == null){
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    /*if (Variable.isNeedForceIpv4){
                        builder.dns(new MyDns());
                    }*/
                    builder.followRedirects(true);
                    // 设置连接超时
                    builder.connectTimeout(10, TimeUnit.SECONDS);
                    // 设置socket超时
                    builder.readTimeout(30, TimeUnit.SECONDS);
                    builder.writeTimeout(30, TimeUnit.SECONDS);
                    builder.connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS));
                    builder.interceptors().add(new MyInterceptor());
                    if (isHttps) {
                        builder.sslSocketFactory(HttpsFactroy.getSSLSocketFactory(keyStore), HttpsFactroy.createX509TrustManager(keyStore));
                    }
                    List<Protocol> protocols = new ArrayList<>();
                    protocols.add(Protocol.HTTP_1_1);
                    protocols.add(Protocol.HTTP_2);
                    builder.protocols(protocols);

                    //UtilCtAPM.setOkHttp(builder);
                    okHttpClient = builder.build();
                }
            }
        }
        return okHttpClient;
    }

    private class MyInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Request originalRequest = chain.request();
            MediaType mediaType = originalRequest.body().contentType();
            okhttp3.Request.Builder newReq = originalRequest.newBuilder();
            newReq.addHeader("Accept", mediaType.type()+"/"+mediaType.subtype())
                    .addHeader("Content-Type", mediaType.toString())
                    .addHeader("User-Agent", Variable.mUserAgent);
            String url = originalRequest.url().toString();
            String headCode = "";
            try {
                headCode = url.substring(url.lastIndexOf("/") + 1);
            }catch (Exception e){
                e.printStackTrace();
            }
            //由于homePageUrl接口不适用从url截取接口code值，单独适配（请求https://appgo.189.cn:9200/map/clientXML的接口仅homePageUrl用此基类）
            if(headCode.equals("clientXML")){
                headCode = "homePageUrl";
            }

            echo("URL[" + headCode + "]:" + originalRequest.url().toString());
            echo("UA :" + Variable.mUserAgent);
            Buffer buffer = new Buffer();
            originalRequest.body().writeTo(buffer);
            String output = buffer.readString(Charset.defaultCharset());
            echo("Request[" + headCode + "]: "+output);
//            if(headCode.equals("mPaasPushRegister")){
//                UtilLog.saveMPaaSPushLogcat("URL[" + headCode + "]:" + originalRequest.url().toString());
//                UtilLog.saveMPaaSPushLogcat("Request[" + headCode + "]: "+output);
//            }
            if (isReqEnc) {
                try {
                    output = UtilEncryption.encode(output);
                    RequestBody bodyN = RequestBody.create(mediaType, output);
                    newReq.method(originalRequest.method(), bodyN);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            okhttp3.Request myRequest = newReq.build();

            long startTime = System.currentTimeMillis();
            okhttp3.Response response = chain.proceed(myRequest);
            long consumingTime = System.currentTimeMillis() - startTime;

            // 有新增登录接口的时候这边要添加相应的code用于登录统计上报接口使用
            if (headCode.equals("userLoginNormal") || headCode.equals("oneKeyLogin") || headCode.equals("weixinBindLogin")|| headCode.equals("broadbindLoginForMsg")|| headCode.equals("broadbindLoginForOneK")){
                SingleEReportData.getInstance().mResponseCode = response.code() + "";
                SingleEReportData.getInstance().mDesperation = output;
                SingleEReportData.getInstance().mElapsedTime = String.valueOf(consumingTime);
            }

            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                BufferedSource source = responseBody.source();
                if (source != null) {
                    source.request(Long.MAX_VALUE);
                    output = source.buffer().clone().readString(Charset.defaultCharset());
                    if (isRespEnc) {
                        try {
                            output = UtilEncryption.decode(output);
                            //替换返回报文里多余的字符，为了AS能够显示
                            output = output.replace("\r","");
                            response = response.newBuilder().body(ResponseBody.create(responseBody.contentType()
                                    , responseBody.contentLength()
                                    , Okio.buffer(Okio.source(new ByteArrayInputStream(output.getBytes(Charset.defaultCharset()))))))
                                    .build();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    echo("Response[" + headCode + "]: http = " + response.protocol());
                    echo("Response[" + headCode + "]: "+output);
//                    if(headCode.equals("mPaasPushRegister")){
//                        UtilLog.saveMPaaSPushLogcat("Response[" + headCode + "]: "+output);
//                    }
                }
            }
            return response;
        }
    }

    public static String getRequestXml(Request request) throws Exception {
        StringBuffer xml = new StringBuffer();
        xml.append("<Request>");
        xml.append(request.getHeaderInfos().toXmlString()).append(request.getContent().toXmlString());
        xml.append("</Request>");

        // if(headerInfos.getCode().equals("getPush")){//如果是推送报文写入文件中
        // Toolkits.saveLogcat(xml.toString());
        // }
        return xml.toString();
    }

    public static String getRequestJson(Request request) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("headerInfos", request.getHeaderInfos().toJsonObject());
        jsonObject.put("content", request.getContent().toJsonObject());
        return jsonObject.toString();
    }

    private static void echo(String msg) {
        Log.v(TAG, msg);
    }

    /**
     * 接口缓存标识，将request关键字段拼接，从而判断两个request是否可以共用response数据
     * 标识使用"Request;id=xxx;name=xxx;icon=xxx"这样的格式存储 默认使用全字段，子类可以自定义
     *
     * @return
     * @author jiangwenxin
     * @createTime 2014年12月17日 下午2:08:57
     */
    public String getCacheKey() {
        String cachekey = this.getClass().getName();
        for (String key : content.getFieldData().keySet()) {
            if (!UtilText.isEmptyOrNull(key)
                    && content.getFieldData().get(key) != null
                    && !UtilText.isEmptyOrNull(content.getFieldData().get(key)
                    .toString()))
                cachekey += ";" + key + "=" + content.getFieldData().get(key);
        }
        return cachekey;
    }

}
