package com.example.sunnyweather.base;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity {
    protected String TAG = "MyActivity";
    protected MyActivity mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (savedInstanceState != null) {
            Intent intent = new Intent(mContext, SplashActivity.class);
            startActivity(intent);
            return;
        }*/

        requestWindowFeature(Window.FEATURE_NO_TITLE);
       /* MyApplication.addActivity(this);
        ZActivityManager.getManager().addActivity(this);*/
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = res.getConfiguration();
        //config.fontScale = Float.parseFloat(Setting.getString(Setting.KEY_TEXT_FONT_SCALE, "1"));//设置正常字体大小的倍数
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ContextWrapper(newBase) {
            @Override
            public Object getSystemService(String name) {
                if (Context.AUDIO_SERVICE.equals(name)) {
                    //不返回当前Activity的强引用，避免内存泄露
                    return getApplicationContext().getSystemService(name);
                }
                return super.getSystemService(name);
            }
        });
    }


    @Override
    protected void onDestroy() {
        // 关闭软键盘
        /*UtilOther.closeIMS(this);
        MyApplication.removeActivity(this);
        ZActivityManager.getManager().finishActivity(this);
*/
        super.onDestroy();
    }

    /**
     * 短时提示
     *
     * @param text 显示的文本
     */
   /* public void showToast(String text) {
        MyToastD.show(text);
    }

    *//**
     * 短时提示关爱版
     *
     * @param text 显示的文本
     *//*
    public void showCareToast(String text) {
        CareToastD.show(text);
    }


    *//**
     * 通过Class跳转界面
     *
     * @param cls 跳转到的class
     *//*
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    *//**
     * 含有Bundle通过Class跳转界面
     *
     * @param cls    跳转到的class
     *//*
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    *//**
     * 手机如果未登录，先跳到登录页面
     * 登录成功后跳到刚才的页面
     *
     * @author jiangwenxin
     *//*
    public boolean checkLoginState() {
        if (!MyApplication.mDataCache.isLoginYet()) {
            UtilGoLoginHelper.INSTANCE.startLoginActivityAndGoBack(mContext);
            return false;
        }
        return true;
    }

    *//**
     * 手机如果未登录，先跳到登录页面（关爱版）
     * 登录成功后跳到刚才的页面
     *//*
    public boolean checkCareLoginState() {
        if (!MyApplication.mDataCache.isLoginYet()) {
            UtilGoLoginHelper.INSTANCE.startCareLoginActivityAndGoBack(mContext);
            return false;
        }
        return true;
    }


    *//**
     * 手机如果未登录，先跳到登录页面（国际版）
     * 登录成功后跳到刚才的页面
     *//*
    public boolean checkInternationalLoginState() {
        if (!MyApplication.mDataCache.isLoginYet()) {
            UtilGoLoginHelper.INSTANCE.startInternationalLoginActivityAndGoBack(mContext);
            return false;
        }
        return true;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation != Configuration.ORIENTATION_LANDSCAPE) {
            UtilPhoneParam.init(mContext);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.PermissionUtilCode.GO_SYS_SETTING) {
            RedDotHelper.getInstance().onRedDotListener(Constants.PermissionUtilCode.BACK_FROM_SYS_SETTING);
        }

        if (requestCode == Constants.PermissionUtilCode.GO_LOCATION_SOURCE_SETTING) {
            RedDotHelper.getInstance().onRedDotListener(Constants.PermissionUtilCode.BACK_FROM_LOCATION_SOURCE_SETTING);
        }

        if (requestCode == Constants.PermissionUtilCode.GO_ALERT_WINDOW_SETTING) {
            RedDotHelper.getInstance().onRedDotListener(Constants.PermissionUtilCode.BACK_FROM_ALERT_WINDOW_SETTING);
        }
    }*/
}
