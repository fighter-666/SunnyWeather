package com.example.sunnyweather.base;


import com.example.sunnyweather.common.Variable;
import com.example.sunnyweather.util.UtilText;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HeaderInfos {

	private String code;						// 接口名
	private static String source;				// 渠道号
	private static String userLoginName;		// 用户名
	private static String sourcePassword;		// 用户密码
	private static String clientType;			// 客户端版本
	private static String token = null;			// 认证成功后颁发的Token
	private static String shopId = null;		// 商铺Id
	private static String broadAccount = null;		// 宽带账号
	private static String broadToken = null;		// 宽带token
	private String timestamp;					// 报文发送时的时间

	public HeaderInfos() {
		super();
	}

	public static void init(String phoneNum){
		HeaderInfos.source = Constants.SRC;
        HeaderInfos.sourcePassword = Constants.PWD;
		HeaderInfos.userLoginName = UtilEncryption.encrypt(phoneNum);
		HeaderInfos.shopId = Constants.SHOP;
		HeaderInfos.clientType = Variable.mAppVersion;
	}

	public static void initBroad(String broadAccount){
		HeaderInfos.broadAccount = broadAccount;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSource() {
		return source;
	}
	public String getShopId() {
		return shopId;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginNameDefault(){
	    userLoginName = "";
	}
	public String getSourcePassword() {
		return sourcePassword;
	}
	public String getClientType() {
		return clientType;
	}
	public String getToken() {
		return token;
	}
	public static void setToken(String token){
		HeaderInfos.token = token;
	}
	public static String getBroadAccount() {
		return broadAccount;
	}
	public static String getBroadToken() {
		return broadToken;
	}
	// 登录的request要清空
	public static void setBroadToken(String broadToken) {
		HeaderInfos.broadToken = broadToken;
	}

	//7.1为解决登录接口加密串时间戳与请求头不一致才使用的函数
	public String getTimestamp() {
		//如果时间戳不为空则用之前生成好的时间戳用于加密，如果为空则重新生成时间戳（7.1为解决登录接口加密串时间戳与请求头不一致引入的逻辑）
		if(UtilText.isEmptyOrNull(timestamp)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			this.timestamp = sdf.format(new Date());
		}
		return timestamp;
	}
	
	public String toXmlString(){
		StringBuffer xml = new StringBuffer();
		Class<?> clazz = this.getClass();

		//如果时间戳不为空则用之前生成好的时间戳放在请求头，如果为空则重新生成时间戳（7.1为解决登录接口加密串时间戳与请求头不一致引入的逻辑）
		if(UtilText.isEmptyOrNull(timestamp)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			this.timestamp = sdf.format(new Date());
		}

		String className="";
		try{
			className = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
		}catch(Exception e){
			className = "";
		}
		
		xml.append("<").append(className).append(">");
		Field fields[] = clazz.getDeclaredFields();
		try {
			for(Field f : fields){
				String filed = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "get" + filed;
				Method getMethod = clazz.getMethod(methodName);
				Object value = getMethod.invoke(this, new Object[] {});
				if(value != null){
//				    if (filed.equals("UserLoginName")&&value.toString().equals("")){
//				        Log.e("@@@@@@XXXXX#####", "KAOKAO");
//				    }
				//if(value != null && value.toString().length() > 0){
					xml.append("<").append(filed).append(">");
					xml.append(value.toString());
					xml.append("</").append(filed).append(">");
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		xml.append("</").append(className).append(">");
		return xml.toString();
	}
	/**
	 * staitc 修饰的变量，GSON无法解析
	 * 提供json请求的参数写入方法
	 * @return
	 */
	public JSONObject toJsonObject(){
		JSONObject jsonObject = new JSONObject();
		Class<?> clazz = this.getClass();

		//如果时间戳不为空则用之前生成好的时间戳放在请求头，如果为空则重新生成时间戳（7.1为解决登录接口加密串时间戳与请求头不一致引入的逻辑）
		if(UtilText.isEmptyOrNull(timestamp)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			this.timestamp = sdf.format(new Date());
		}

		Field fields[] = clazz.getDeclaredFields();
		try {
			for(Field f : fields){
				String filed = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "get" + filed;
				Method getMethod = clazz.getMethod(methodName);
				Object value = getMethod.invoke(this, new Object[] {});
				if(value != null){
					jsonObject.put(f.getName(), value.toString());
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}
}
