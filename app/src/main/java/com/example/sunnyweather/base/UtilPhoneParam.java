/**   
 * @Title: UtilPhoneParam.java 
 * @Package com.ct.client.common.utils 
 * @Description: 工具包
 * @author linwen@ffcs.cn   
 * @date 2015年7月30日 上午11:12:09 
 * @version V1.0   
*/
package com.example.sunnyweather.base;

import static android.content.Context.WIFI_SERVICE;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import com.example.sunnyweather.util.UtilText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/** 
 * 手机参数 
 * 
 * @ClassName: UtilPhoneParam 
 * @author linwen@ffcs.cn 
 * @date 2015年7月30日 上午11:12:09
 * @-------修改记录-------
 * @修改人： zhangyz
 * @修改内容：增加650客服需求内容
 * @版本：650
 */
public class UtilPhoneParam {

	public final static int SCREEN_ORIENTATION_VERTICAL = 1; // 屏幕状态：横屏
	public final static int SCREEN_ORIENTATION_HORIZONTAL = 2; // 屏幕状态：竖屏

	public static int screenWidth;// 屏幕宽度，单位为px
    public static int screenHeight;// 屏幕高度，单位为px
    public static int densityDpi;// 屏幕密度，单位为dpi
    public static float scale;// 缩放系数，值为 densityDpi/160
    public static float fontScale;// 文字缩放系数，同scale
    public static int screenOrientation = SCREEN_ORIENTATION_VERTICAL;// 当前屏幕状态，默认为竖屏
	public static final int NETWORK_TYPE_UNKNOWN = 0;
	public static final int NETWORK_TYPE_GPRS = 1;
	public static final int NETWORK_TYPE_EDGE = 2;
	public static final int NETWORK_TYPE_UMTS = 3;
	public static final int NETWORK_TYPE_CDMA = 4;
	public static final int NETWORK_TYPE_EVDO_0 = 5;
	public static final int NETWORK_TYPE_EVDO_A = 6;
	public static final int NETWORK_TYPE_1xRTT = 7;
	public static final int NETWORK_TYPE_HSDPA = 8;
	public static final int NETWORK_TYPE_HSUPA = 9;
	public static final int NETWORK_TYPE_HSPA = 10;
	public static final int NETWORK_TYPE_IDEN = 11;
	public static final int NETWORK_TYPE_EVDO_B = 12;
	public static final int NETWORK_TYPE_LTE = 13;
	public static final int NETWORK_TYPE_EHRPD = 14;
	public static final int NETWORK_TYPE_HSPAP = 15;


	private static UtilPhoneParam instance;

    private UtilPhoneParam() {

    }
    
    private static synchronized void initSync() {
		if (instance == null) {
			instance = new UtilPhoneParam();
		}
    }

    /**
     * 获取实例
     * 
     * @return UtilPhoneParam
     */
    public static UtilPhoneParam getInstance() {
        if (instance == null) {
			initSync();
        }
        return instance;
    }

    /**
     * 初始化
     * 1、获取手机屏幕参数
     * getMetrics()获取到的是去除导航栏或状态栏后的尺寸，不同品牌手机获取到的值有区别
	 * @author zhuofq
     * @param context 
     */
    public static void init(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        densityDpi = dm.densityDpi;
        scale = dm.density;
        fontScale = dm.scaledDensity;
        screenOrientation = screenHeight > screenWidth ? SCREEN_ORIENTATION_VERTICAL
                : SCREEN_ORIENTATION_HORIZONTAL;       
    }
    
    /**
     * 获取屏幕高度
     *
	 * @author zhuofq
     * @param context
     * @return int
     */
	public static int getScreenHeight(Context context) {
//		WindowManager wm = (WindowManager) context
//				.getSystemService(Context.WINDOW_SERVICE);
//		int height = wm.getDefaultDisplay().getHeight();
//		return height;
		return screenHeight;
	}

	/**
	 *  getMetrics()获取到的是去除导航栏或状态栏后的尺寸，不同品牌手机获取到的值有区别
	 *  getReaMetrics()获取的则是真正原始的屏幕尺寸
	 * @param context
	 * @return
	 */
	public static int getScreenRealWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getRealMetrics(dm);
		return dm.widthPixels;
	}

	public static int getScreenRealHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getRealMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 *  获取屏幕高/屏幕宽比
	 * @param context
	 * @return
	 */
	public static double getScreenProportion(Context context) {
		return (double) getScreenRealHeight(context)/getScreenRealWidth(context);
	}

	/**
	 * 屏幕是否亮起
	 *
	 * @author zhuofq
	 * @param context
	 * @return boolean
	 */
	public static boolean isScreenOn(Context context) {
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		return pm.isScreenOn();
	}

	/**
	 * sim卡是否准备好
	 *
	 * @author zhuofq
	 * @param context
	 * @return boolean
	 */
	public static boolean isSimReady(Context context) {
		TelephonyManager telMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (telMgr.getSimState() == TelephonyManager.SIM_STATE_READY)
			return true;
		else
			return false;
	}

	/**
	 * 获取手机IMSI,获取不了则以0替代
	 *
	 * @author zhuofq
	 * @param context
	 * @return String
	 */
	public static String getIMSI(Context context) {
		// 合规要求，951修改固定返回0
		return "0";
	}

	/**
	 * 获取手机ICCID,获取不了则以0替代
	 *
	 * @author zhangyi
	 * @param context
	 * @return String
	 */
	public static String getICCID(Context context) {
		try {
			TelephonyManager telMgr;
			telMgr = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String iccid = telMgr.getSimSerialNumber();
			if (iccid == null) {
				iccid = "0";
			}
			return iccid;
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	/**
	 * 获取手机IMEI
	 *
	 * @author zhuofq
	 * @param context
	 * @return String
	 */
	public static String getIMEI(Context context) {
        //8.8.0版本调整为不获取IMEI，直接返回空串
		return "";
//		try {
//			//无电话权限时，不去获取IMEI
//			if (!UtilXPermission.INSTANCE.checkPermission(Manifest.permission.READ_PHONE_STATE)){
//				return "";
//			}
//			TelephonyManager telMgr;
//			telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//			String imei = telMgr.getDeviceId();
//
//			if (imei == null) {
//				imei = "";
//			}
//			return imei;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}
	}

	/**
	 * 获取手机MEID
	 *
	 * @author zhuofq
	 * @param context
	 * @return String
	 */
	public static String getMEID(Context context) {
		//8.8.0版本调整为不获取MEID，直接返回空串
		return "";
	}

	/**
	 * 获取AndroidID
	 * @return 返回AndroidID
	 */
	public static String getAndroidID() {
		String mAndroidID = Setting.getStringDecode(Setting.ANDROID_ID, "");
		if (UtilText.isEmptyOrNull(mAndroidID)) {
			mAndroidID = Settings.System.getString(MyApplication.mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
			if (UtilText.isEmptyOrNull(mAndroidID)) {
				mAndroidID = "";
			}
			Setting.setStringEncode(Setting.ANDROID_ID, mAndroidID);
		}
		return mAndroidID;
	}

	/**
	 * 获取SIM所属运营商
	 *
	 * @author zhuofq
	 * @param context
	 * @return 0 异常 1 移动 2 联通 3 电信
	 */
	public static int getTelCnpny(Context context) {
		try {
			int telCnpny = 0;
			//无电话权限时，不去获取imsi
			if (!UtilXPermission.INSTANCE.checkPermission(Manifest.permission.READ_PHONE_STATE)){
				return 0;
			}
			TelephonyManager telMgr;
			telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String imsi = telMgr.getSubscriberId();
			if (imsi != null) {
				if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
					telCnpny = 1;
				} else if (imsi.startsWith("46001")) {
					telCnpny = 2;
				} else if (imsi.startsWith("46003") || imsi.startsWith("46011")) {
					telCnpny = 3;
				}
			}
			return telCnpny;
		} catch (Exception e) {
			Log.e(e.toString());
			return 0;
		}
	}

	/**
	 * 是否为电信用户
	 *
	 * @author zhuofq
	 * @param context
	 * @return boolean
	 */
	public static boolean isCtImsi(Context context) {
		return (getTelCnpny(context) == 3);
	}

//	public static final String DEFAULT_SID = "13824";// 默认返回北京的SID

	/**
	 * 获取手机SID
	 *
	 * @author zhuofq
	 * @param context
	 * @return
	 */
	public static String getSID(Context context) {
		try {
			if (UtilText.isEmptyOrNull(Variable.mSID)) {//先判断本地缓存SID是否为空
				// 未授权定位直接return
				if (!UtilXPermission.INSTANCE.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)){
					return null;
				}

				TelephonyManager telMgr;
				telMgr = (TelephonyManager) MyApplication.mContext.getSystemService(Context.TELEPHONY_SERVICE);
				CdmaCellLocation celllocation = (CdmaCellLocation) telMgr.getCellLocation();
				int sid = celllocation.getSystemId();
				if (sid == -1) {
					Variable.mSID = "";
				} else {
					Variable.mSID = "" + sid;
				}
				if (UtilText.isEmptyOrNull(Variable.mSID)) {
					return "";
				} else {
					return Variable.mSID;
				}
			} else {
				return Variable.mSID;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 是否为 CDMA 手机
	 *
	 * @author zhuofq
	 * @param context
	 * @return boolean
	 */
	public static boolean isCTUser(Context context) {
		boolean ret = false;
		try {
			String sid = getSID(context);
			if (sid == null)
				return false;

			int intSid = Integer.valueOf(sid);
			if (intSid > 0)
				ret = true;
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 是否为 CDMA 手机
	 *
	 * @author zhuofq
	 * @param context
	 * @return boolean
	 */
	public static boolean isCDMAPhone(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm != null && tm.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
			return true;
		}
		return false;
	}

	/**
	 * 获取内置存储剩余总大小
	 *
	 * @author zhuofq
	 * @return long
	 */
	public static long getInternalStorgeLeft() {
		String internalPath = Environment.getDataDirectory().getPath();
		StatFs statInternal = new StatFs(internalPath);
		long blockSize = statInternal.getBlockSize();
		long totalBlocks = statInternal.getBlockCount();
		long availableBlocks = statInternal.getAvailableBlocks();

		long leftSize = availableBlocks * blockSize;// 内置存储总大小剩余 单位B
		return leftSize;
	}

	/**
	 * 获取android 手机版本
	 *
	 * The user-visible SDK version of the framework in its raw String
	 * representation; use SDK_INT instead.
	 *
	 * @author zhuofq
	 * @return String
	 */
	public static String getPhoneSDKVersion() {
		return Build.VERSION.SDK;
	}

	/**
	 * The user-visible version string. E.g., "1.0" or "3.4b5".
	 *
	 * @author zhuofq
	 * @return String
	 */
	public static String getPhoneSystemVersion() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * The end-user-visible name for the end product.
	 *
	 * @author zhuofq
	 * @return String
	 */
	public static String getPhoneModel() {
		String phonemodel = Build.MODEL;
		phonemodel = phonemodel.replace("&", "_");// 兼容<"&" 字符在xml中是特殊字符,必须转化>
		return phonemodel;
	}

	/**
	 * The manufacturer of the product/hardware
	 *
	 * @author zhuofq
	 * @return boolean
	 */
	public static String getPhoneModelLong() {
		if (UtilText.isEmptyOrNull(Variable.mPhoneModelLong)) {//先判断本地缓存品牌机型串是否为空
			String phonemodel = Build.MANUFACTURER + " " + Build.MODEL;
			Variable.mPhoneModelLong = phonemodel.replace("&", "_");// 兼容<"&" 字符在xml中是特殊字符,必须转化>

			if (UtilText.isEmptyOrNull(Variable.mPhoneModelLong)) {
				return "";
			} else {
				return Variable.mPhoneModelLong;
			}
		} else {
			return Variable.mPhoneModelLong;
		}
	}

	/**
	 * @author zhangyi
	 * @return String
	 */
	public static String getDeviceInformation() {
		String phonemodel = Build.MANUFACTURER + "-"+ Build.MODEL;
		phonemodel = phonemodel.replace("&", "-");// 特殊字符转换为-
		phonemodel = phonemodel.replace(" ", "-");// 特殊字符转换为-
		phonemodel = phonemodel.replace("_", "-");// 特殊字符转换为-
		return phonemodel;
	}

	/**
	 * 判断手机是否有插入SD卡
	 *
	 * @author zhuofq
	 * @return boolean
	 */
	public static boolean isSDCardMounted() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
     * 获取手机内存信息
     * @return
     */
    public static String getMemoryInfo(){
		return "总大小:"+formatSize(getTotalInternalMemorySize());
    }
    

    /** 
     * 获取手机内存的总空间大小
     * */
    private static long getTotalInternalMemorySize()
    {
		String memInfoPath = "/proc/meminfo";
		String readTemp = "";
		String memTotal = "";
		long memory = 0;
		try {
			FileReader fr = new FileReader(memInfoPath);
			BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
			while ((readTemp = localBufferedReader.readLine()) != null) {
				if (readTemp.contains("MemTotal")) {
					String[] total = readTemp.split(":");
					memTotal = total[1].trim();
				}
			}
			localBufferedReader.close();
			String[] memKb = memTotal.split(" ");
			memTotal = memKb[0].trim();
			memory = Long.parseLong(memTotal);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return memory;
    }
    
    private static String formatSize(long size) // 文件大小单位转换
	{
		long mb = 1024;
		long gb = (mb * 1024);
		if (size < mb) {
			return String.format("%.2f KB", (int)size); // 保留两位小数
		} else if (size < gb) {
			return String.format("%.2f MB", (float)size / mb);
		} else {
			return String.format("%.2f GB", (float)size / gb);
		}
	}

	/**
	 * 判断手机是否root
	 * @return
	 */
	public static boolean runRootCommand() {

		Process process = null;
		DataOutputStream os = null;
		try {
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes("test"+"\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			Log.d("*** DEBUG ***", "Unexpected error - Here is what I know: "+e.getMessage());
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		return true;
	}

	/**
	 * 获取手机品牌
	 * @return
	 */
	public static String getModel() {
		String model = Build.MODEL;
		if (model == null)
			return "";
		else
			return model;
	}

	/**
	 * 手机型号
	 * @return
	 */
	public static String getBrand() {
		if (Build.BRAND == null)
			return "";
		else
			return Build.BRAND;
	}

	/**
	 * 获取手机连接类型
	 * @param context
	 * @return
	 */
	public static String getNetConectionType(Context context) {
		return _getNetConectionType(context);
	}


	private static String _getNetConectionType(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
		String connectType = "unknown";
		if (networkinfo != null && networkinfo.isConnected()) {
			if (networkinfo.getType() == 1) {
				connectType = "wifi";
			} else if (networkinfo.getType() == 0 && networkinfo != null && networkinfo.isConnectedOrConnecting())
				switch (networkinfo.getSubtype()) {
					case NETWORK_TYPE_GPRS: // '\001'
					case NETWORK_TYPE_EDGE: // '\002'
					case NETWORK_TYPE_CDMA: // '\004'
					case NETWORK_TYPE_1xRTT: // '\007'
					case NETWORK_TYPE_IDEN: // '\013'
						connectType = "2G";
						break;

					case NETWORK_TYPE_UMTS: // '\003'
					case NETWORK_TYPE_EVDO_0: // '\005'
					case NETWORK_TYPE_EVDO_A: // '\006'
					case NETWORK_TYPE_HSDPA: // '\b'
					case NETWORK_TYPE_HSUPA: // '\t'
					case NETWORK_TYPE_HSPA: // '\n'
					case NETWORK_TYPE_EVDO_B: // '\f'
					case NETWORK_TYPE_EHRPD: // '\016'
					case NETWORK_TYPE_HSPAP: // '\017'
						connectType = "3G";
						break;

					case NETWORK_TYPE_LTE: // '\r'
						connectType = "4G";
						break;

					default:
						connectType = "unknown";
						break;
				}
		}
		return connectType;
	}

	/**
	 * 这里得到信号强度就靠wifiinfo.getRssi()这个方法。得到的值是一个0到-100的区间值，是一个int型数据，其中0到-50表示信号最好，
	 * -50到-70表示信号偏差，小于-70表示最差，有可能连接不上或者掉线，一般Wifi已断则值为-200
	 * @param context
	 * @return
	 */
	private static String obtainWifiInfo(Context context) throws Exception{
		// Wifi的连接速度及信号强度：
		int strength = 0;
		WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
		JSONObject jsonObject = new JSONObject();
		// WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		WifiInfo info = wifiManager.getConnectionInfo();

		if (info.getBSSID() != null) {
			// 链接信号强度，5为获取的信号强度值在5以内
//            strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);
			int rssi = info.getRssi();
			String s = String.valueOf(rssi) + "dBm";
			// 链接速度
			int speed = info.getLinkSpeed();
			// 链接速度单位
			String units = WifiInfo.LINK_SPEED_UNITS;
			// Wifi源名称
			String ssid = info.getSSID();
			jsonObject.put("ssid", ssid);
			jsonObject.put("speed", speed);
			jsonObject.put("strength",s);
			jsonObject.put("security", _getSecurity(info, wifiManager));

		}


//        return info.toString();
		return jsonObject.toString();
	}

	/**
	 * 获取缩放正确的dpi
	 * @param mContext
	 * @return
	 */
	public static int getScaleDensityDpi(Context mContext) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		if (dm.densityDpi <= 240) {
			return dm.densityDpi;
		} else if (240 < dm.densityDpi && dm.densityDpi <= 320) {
			return 320;
		} else if (320 < dm.densityDpi && dm.densityDpi <= 480) {
			return 480;
		} else if (480 < dm.densityDpi && dm.densityDpi <= 640) {
			return 640;
		} else {
			return dm.densityDpi;
		}
	}

	public static String obtainWifiInfo(Context context ,String keyValues){
		WifiManager manager= (WifiManager) context.getSystemService(WIFI_SERVICE);
		WifiInfo info = manager.getConnectionInfo();
		String str="";
		if (keyValues.equals("ssid")){
			str=info.getSSID();
		}else if (keyValues.equals("wifiStrength")){
			int rssi = info.getRssi();
			str = String.valueOf(rssi) + "dBm";
		}
		return str;
	}

	/**
	 *
	 * @param info
	 * @param wifiManager
	 * @return 0 - NONE 1 - WEP 2 - PSK 3 - EAP
	 */
	private static int _getSecurity(WifiInfo info, WifiManager wifiManager) {
		int security = 0;
		// 得到配置好的网络连接
		List<WifiConfiguration> wifiConfigList = wifiManager.getConfiguredNetworks();

		for (WifiConfiguration wifiConfiguration : wifiConfigList) {
			//配置过的SSID
			String configSSid = wifiConfiguration.SSID;
			configSSid = configSSid.replace("\"", "");

			//当前连接SSID
			String currentSSid = info.getSSID();
			currentSSid = currentSSid.replace("\"", "");

			//比较networkId，防止配置网络保存相同的SSID
			if (currentSSid.equals(configSSid) && info.getNetworkId() == wifiConfiguration.networkId) {
//                Log.e("river", "当前网络安全性：" + getSecurity(wifiConfiguration));
				security = getSecurity(wifiConfiguration);
			}
		}
		return  security;
	}


	/**
	 * These values are matched in string arrays -- changes must be kept in sync
	 */
	static final int SECURITY_NONE = 0;
	static final int SECURITY_WEP = 1;
	static final int SECURITY_PSK = 2;
	static final int SECURITY_EAP = 3;

	static int getSecurity(WifiConfiguration config) {
		if (config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_PSK)) {
			return SECURITY_PSK;
		}
		if (config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_EAP) || config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.IEEE8021X)) {
			return SECURITY_EAP;
		}
		return (config.wepKeys[0] != null) ? SECURITY_WEP : SECURITY_NONE;
	}


	/**
	 * 获取手机信息（原先提供给天翼客服使用，现提供给21cn使用）
	 * @param context
	 * @param keyValues
	 * @return
	 */
	public static String getPhoneInfo(Context context,String keyValues){
		JSONObject info= new JSONObject();
		try {
			JSONObject json= new JSONObject(keyValues);
			Iterator iterator=json.keys();
			while (iterator.hasNext()){
				String key= (String) iterator.next();
				if (key.equals("phoneInfoParams")){
					JSONArray jsonArray= null;
					try {
						jsonArray = json.getJSONArray("phoneInfoParams");
						for (int i = 0; i < jsonArray.length(); i++) {
							String values = jsonArray.getString(i);
							if (values.equals("phoneBrand")) {
								//手机品牌
								String phoneBrand = Build.BRAND;
								if (UtilText.isEmptyOrNull(phoneBrand)) {
									phoneBrand = "";
								}
								info.put("phoneBrand", phoneBrand);
							} else if (values.equals("phoneModel")) {
								//手机型号
								String phoneModel = Build.MODEL;
								if (UtilText.isEmptyOrNull(phoneModel)) {
									phoneModel = "";
								}
								info.put("phoneModel", phoneModel);
							} else if (values.equals("netType")) {
								//wifi,4G,3G 等
								String netType = UtilPhoneParam.getNetConectionType(context);
								info.put("netType", netType);
							} else if (values.equals("accessToken")) {
								String accessToken = "";
								try {
									accessToken = UtilEncryption.decode(Setting.getStringByNbr(Setting.KEY_CN_TOKEN));
								} catch (Exception e) {
									e.printStackTrace();
								}
								info.put("accessToken", accessToken);
							}
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}else if (key.equals("channel")){
					//渠道号
					String channel=json.getString("channel");
					info.put("channel",channel);

				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return info.toString();
	}

	//是否是Mi系列设备
	public static boolean isMiSeriesDervice() {
		return Build.MANUFACTURER.equals("Xiaomi") ? true : (Build.MANUFACTURER.equals("xiaomi") ? true : false);
	}

	// 根据手机品牌返回品牌代号
	public static int getChannelOfDevice() {
		for (BrandArr brand: BrandArr.values()){
			if (Build.BRAND.equalsIgnoreCase(brand.typeName)){
				return brand.code;
			}
		}
		return -1;
	}

	private enum BrandArr {
		Huawei("Huawei",1),
		HONOR("HONOR",2),
		xiaomi("xiaomi",3),
		Redmi("Redmi",3),
		OPPO("OPPO",4),
		realme("realme",4),
		vivo("vivo",5);
		private String typeName;
		private int code;

		BrandArr(String typeName,int code) {
			this.typeName = typeName;
			this.code = code;
		}

		public String getTypeName() {
			return typeName;
		}

		public int getCode(){
			return code;
		}
	}

}
