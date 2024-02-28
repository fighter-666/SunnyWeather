package com.example.sunnyweather.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class UtilPhoneParam {
    public static int screenWidth;// 屏幕宽度，单位为px
    public static int screenHeight;// 屏幕高度，单位为px

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
}
