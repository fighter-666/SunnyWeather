package com.example.sunnyweather.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class UtilOther {

    /**
     * 说明：获取安全的实例引用
     *
     * @作者 linwen@ffcs.cn
     * @创建时间 2018/2/24 15:03
     * @版本
     * @------修改记录-------
     * @修改人
     * @版本
     * @修改内容
     */
    public static <R>R ref(R ref) {
        return new WeakReference<>(ref).get();
    }

    public static <C>C cast(Object ref) {
        return (C) ref;
    }

    /**
     * 概率计算
     * @param probability 期望概率（0-1之间）
     * @return true:发生  false:不发生
     */
    public static boolean isFeasible(double probability){
        if (probability > 1) return true;
        if (probability < 0) return false;
        String pro = String.valueOf(probability);
        int ratio = pro.length() - pro.indexOf(".") -1;
        Random random = new Random();
        return random.nextInt(100 * ratio + 1) < (probability * 100 * ratio)? true : false;
    }



    /**
     *
     * 处理服务器返回resultDesc为null时的提示信息
     *
     * @author zhangyi
     * @createTime 2014-10-21 上午11:10:56
     * @param resultDesc
     * @return String
     */
    public static String getResultDescFilterNull(String resultDesc) {
        if (resultDesc.equals("null")) {
            resultDesc = "服务器异常，请稍后再试";
        }
        return resultDesc;
    }

    /**
     * 收起输入法键盘
     *
     * @author zhuofq
     * @param activity
     */
    public static void closeIMS(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity
                                .getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 收起输入法键盘 注意 ：当焦点在对话框中，仅仅传Activity无法获得焦点时
     *
     * @author zhuofq
     * @param activity
     * @param view
     */
    public static void closeIMS(Activity activity, View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (view != null) {
                inputMethodManager.hideSoftInputFromWindow(
                        view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹出输入法
     * @param activity
     * @param view
     */
    public static void showIMS(Activity activity, View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (view != null) {
                inputMethodManager.showSoftInputFromInputMethod(
                        view.getWindowToken(),
                        InputMethodManager.SHOW_FORCED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 蜜汁滴泪弹出输入法
     * @param activity
     * @param view
     */
    public static void showSoftInput(final Activity activity, final View view) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask()   {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(view, 0);
            }
        }, 300);
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @作者 huangssh
     * @创建时间 2015-8-26 上午9:16:18
     * @版本
     * @param s
     *            原字符串
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 最多保留n位有效数字（不包含小数点）
     *
     * @作者 huangssh
     * @创建时间 2015-8-26 上午9:24:23
     * @版本
     * @param s
     * @param n
     * @return
     */
    public static String keepEffectiveDigital(String s, int n) {
        int i = s.indexOf(".");
        s = s.replace(".", "");
        if (s.length() > n) {
            s = s.substring(0, n);
        }

        s = Stringinsert(s, ".", i);
        return s;
    }

    /**
     * 插入字符串
     *
     * @作者 huangssh
     * @创建时间 2015-8-26 上午9:25:06
     * @版本
     * @param a
     *            原字符
     * @param b
     *            插入的字符
     * @param t
     *            插入位置
     * @return
     */
    public static String Stringinsert(String a, String b, int t) {
        return a.substring(0, t) + b + a.substring(t, a.length());
    }

    /**
     * 去掉重复项
     *
     * @作者 HRX
     * @创建时间 2015-8-26 下午5:24:44
     * @版本
     * @param list
     * @return
     */
    public static List<String> removeDuplicate(List<String> list) {
        List<String> newList = new ArrayList<String>();
        Set set = new LinkedHashSet<String>();
        set.addAll(list);
        list.clear();
        list.addAll(set);

        for (String string : list) {
            if (string != null)
                newList.add(string);

        }
        return newList;
    }

    /**
     * kb转换到mb，保留2未小数并去除末尾0
     * @param string
     * @return
     */

    /**
     * 流量使用百分比计算
     *
     * @param sum
     * @param used
     * @return
     */
    public static double getpercentShow(String sum, String used) {
        //当月使用的当月流量= 当月使用的总流量-上个月未使用的
        if (UtilText.isEmptyOrNull(sum)) {
            sum = "0";
        } else if (UtilText.isEmptyOrNull(used)) {
            used = "0";
        }
        double mySum = Double.parseDouble(sum);
        double myused = Double.parseDouble(used);
        double result = myused * 100 / mySum;
        if (result < 0) {
            result = 0;
        }
        return result;
    }

    /**
     * 根据计算后的总额重新计算百分比 不足百分一的直接认作百分一
     * @param newSum
     * @param onePercentNum
     * @return
     */
    public static double getNewPercent(double item, double newSum, double onePercentNum){
        double percent;
        if (item < onePercentNum && item > 0){
            percent = 1;
        }else {
            percent = getpercentShow(newSum + "", item + "");
        }

        if (percent < 0){
            percent = 0;
        }

        return percent;
    }

    /**
     * 总流量计算
     *
     * @param current
     * @param last
     * @return
     */
    public static String getSumFlow(String current, String last) {
        //防止空串或者null转化异常
        if (UtilText.isEmptyOrNull(current)) {
            current = "0";
        } else if (UtilText.isEmptyOrNull(last)) {
            last = "0";
        }
        double myCurrent = Double.parseDouble(current);
        double myLast = Double.parseDouble(last);
        double result = myCurrent + myLast;
        if (result < 0) {
            result = 0;
        }
        return "" + result;
    }

    /**
     * 剩余流量计算
     *
     * @param sum
     * @param used
     * @return
     */
    public static String getCanUsedFlow(String sum, String used) {
        //防止空串或者null转化异常
        if (UtilText.isEmptyOrNull(sum)) {
            sum = "0";
        } else if (UtilText.isEmptyOrNull(used)) {
            used = "0";
        }
        double mySum = Double.parseDouble(sum);
        double myused = Double.parseDouble(used);
        double result = mySum - myused;
        if (result < 0) {
            result = 0;
        }
        return "" + result;
    }

    /*
     *流量充值成功，发送广播消息到欢页面，更新流量
     */

    /**
     * 余额充值成功，发消息到欢页面，更新余额
     */

    /**
     * 宽带充值成功广播
     */

    public static Activity unWrap(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }

        return (Activity) context;
    }

    /**
     * Context转成Activity，非直接强转
     * @param cont
     * @return
     */
    public static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity)cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper)cont).getBaseContext());

        return null;
    }

    /**
     * 判断一个数字是否等于0
     * @param number
     * @return
     */
    public static boolean isZero(String number){
        try {
            if (Double.parseDouble(number) == 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 强制转换为double
     * @param number
     * @return
     */
    public static double parseDouble(String number){
        double num;
        try {
            num = Double.parseDouble(number);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    /**
     * 强制转换为float
     * @param number
     * @return
     */
    public static float parseFloat(String number){
        float num;
        try {
            num = Float.parseFloat(number);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }


    /**
     * 强制转换为int
     * @param number
     * @return
     */
    public static int parseInt(String number){
        int num;
        try {
            num = Integer.parseInt(number);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    public static Integer parseInteger(String number){
        int num;
        try {
            num = Integer.parseInt(number);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    /**
     * 强制转换为int, 只支持整形字符串，小数会变成0
     * @param number
     * @return
     */
    public static int parseInt(String number, int mDefault){
        int num;
        try {
            num = Integer.parseInt(number);
        } catch (Exception e) {
            e.printStackTrace();
            return mDefault;
        }
        return num;
    }

    /**
     * 强制转换为int，向上取整
     * @param number
     * @return
     */
    public static int parseIntCeil(String number){
        int num;
        try {
            num = (int) Math.floor(Double.parseDouble(number));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    /**
     * 强制转换为int，向下取整
     * @param number
     * @return
     */
    public static int parseIntFloor(String number){
        int num;
        try {
            num = (int) Math.floor(Double.parseDouble(number));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    /**
     * 强制转换为int，四舍五入
     * @param number
     * @return
     */
    public static int parseIntRound(String number){
        int num;
        try {
            num = (int) Math.round(Double.parseDouble(number));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }


    public static long parseLong(String number){
        long num;
        try {
            num = Long.parseLong(number);
        }catch (Exception e) {
//			e.printStackTrace();
            return 0;
        }
        return num;
    }

    //设置listview高度
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            try {
                listItem.measure(0, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    //获取listvie高度
    public static int getListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
//        listView.setLayoutParams(params);
        return params.height;
    }

    /**
     *@desc: 是否显示红点
     *@author:zhangyz
     * @param flag: 是否显示红点(0:不显示;1:显示)
     * @param timeStamp:修改时间
     * @param key
     *@created at: 2019/8/15 15:54
     **/

    /**
     * 根据包名打开手机内指定APP
     * @param mContext
     * @param packageName
     */
    public static void openInstallApp(Context mContext,String packageName) {
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent= new Intent();
        intent = packageManager.getLaunchIntentForPackage(packageName);
        if(intent==null){
//			MyToastC.makeText(mContext, "未安装该应用", Toast.LENGTH_LONG).show();
        }else{
            mContext.startActivity(intent);
        }
    }
    
}
