package com.example.sunnyweather.widget;


import java.io.PrintWriter;
import java.io.StringWriter;


public class Log {
	public static boolean dbgFlg = Boolean.parseBoolean("true");
	static String TAG = "CT_CLIENT";
	
	public static void v(String tag, String msg){
		if (dbgFlg){
		    android.util.Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg){
		if (dbgFlg){
		    android.util.Log.d(tag, msg);
		}
	}
	
	public static void i(String tag, String msg){
		if (dbgFlg){
		    android.util.Log.i(tag, msg);
		}
	}
	
	public static void w(String tag, String msg){
		if (dbgFlg){
		    android.util.Log.w(tag, msg);
		}
	}
	
	public static void e(String tag, String msg){
		if (dbgFlg){
		    android.util.Log.e(tag, msg);
		}
	}
	
	public static void v(String msg){
		if (dbgFlg)android.util.Log.v(TAG, msg);
	}

	public static void d(String msg){
		if (dbgFlg)android.util.Log.d(TAG, msg);
	}
	
	public static void i(String msg){
		if (dbgFlg)android.util.Log.i(TAG, msg);
	}
	
	public static void w(String msg){
		if (dbgFlg)android.util.Log.w(TAG, msg);
	}
	
	public static void e(String msg){
		if (dbgFlg)android.util.Log.e(TAG, msg);
	}
	
	public static void alwaysDumpError(String msg){
		android.util.Log.e(TAG, msg);
	}
	
    public static void w(String tag, Throwable tr) {
        if (dbgFlg)android.util.Log.w(tag, getStackTraceString(tr));
    }
    
    public static void e(String tag, Throwable tr) {
        if (dbgFlg)android.util.Log.e(tag, getStackTraceString(tr));
    }
    
    public static void w(Throwable tr) {
        if (dbgFlg)android.util.Log.w(TAG, getStackTraceString(tr));
    }
    
    public static void e(Throwable tr) {
        if (dbgFlg)android.util.Log.e(TAG, getStackTraceString(tr));
    }
	
    public static void w(String tag, String msg, Throwable tr) {
    	if (dbgFlg)android.util.Log.w(TAG, msg + "\n" + getStackTraceString(tr));
    }
    public static void e(String tag, String msg, Throwable tr) {
    	if (dbgFlg)android.util.Log.e(TAG, msg + "\n" + getStackTraceString(tr));
    }
    
    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }
}
