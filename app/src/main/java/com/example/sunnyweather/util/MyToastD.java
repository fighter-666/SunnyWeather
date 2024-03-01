package com.example.sunnyweather.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunnyweather.R;
import com.example.sunnyweather.SunnyWeatherApplication;

/**
 * 通用Toast框 防止多次点击
 *
 * @作者
 * @创建时间 2015-8-7 下午4:50:38
 * @修改人
 * @修改时间
 * @修改内容
 */
public class MyToastD extends Toast {

    private static CharSequence oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;

    private static Toast toast = null;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public MyToastD(Context context) {
        super(context);
    }

    public static void show(String text) {
        if (!UtilText.isEmptyOrNull(text)){
            makeText(text, 0, 0, Toast.LENGTH_SHORT,false);
        }
    }

    public static void show(String text, int duration) {
        if (!UtilText.isEmptyOrNull(text)){
            makeText(text, 0, 0, duration,false);
        }
    }

    public static void makeText(CharSequence text, int drawableId, int gravity, int duration, boolean isCenter) {
        if (toast == null) {
            toast = getToast(text, drawableId, gravity, duration, isCenter);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (text.equals(oldMsg)) {
                if (twoTime - oneTime > 2000) {
                    // 这里是判断toast上一次显示的时间和这次的显示时间如果大于2000，
                    //  则显示新的toast
                    toast.cancel();
                    toast = getToast(text, drawableId, gravity, duration, isCenter);
                    toast.show();
                    oneTime = twoTime;
                }
            } else {
                toast.cancel();
                toast = getToast(text, drawableId, gravity, duration, isCenter);
                oldMsg = text;
                toast.show();
                oneTime = twoTime;
            }
        }
    }

    public static Toast getToast(CharSequence text, int drawableId, int gravity, int duration, boolean isCenter){
        Toast toast = new Toast(getContext());
        // 获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 由layout文件创建一个View对象
        View layout = inflater.inflate(R.layout.my_toast_c, null);

        // 实例化ImageView和TextView对象
        TextView tvContent = (TextView) layout.findViewById(R.id.tv_content);
        ImageView ivContent = (ImageView) layout.findViewById(R.id.iv_content);
        if (isCenter){
            tvContent.setGravity(Gravity.CENTER);
        }
        tvContent.setText(text);

        //通过drawableId是否是0来判断是否需要展示图片
        if (drawableId != 0) {
            ivContent.setVisibility(View.VISIBLE);
            ivContent.setImageDrawable(getContext().getResources().getDrawable(drawableId));
        } else {
            ivContent.setVisibility(View.GONE);
        }

        toast.setView(layout);
        if (gravity != 0) {
            toast.setGravity(gravity, 0, 0);
        } else {
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.setDuration(duration);
        return toast;
    }

    public static Context getContext() {
        return SunnyWeatherApplication.context;
    }
}
