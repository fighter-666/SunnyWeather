package com.example.sunnyweather.widget;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;

import com.example.sunnyweather.util.UtilOther;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class UtilBaseApp {

    /**
     * 说明：跳转到通知权限设置界面，部分小米手机的通知权限设置界面无法打开通知权限，所以小米手机适配跳转应用详情页
     */
    public static void goToNotificationSetting(Context context) {
//        String manufacturer = Build.MANUFACTURER;
        //这个字符串可以自己定义,例如判断华为就填写huawei,魅族就填写meizu
//        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
//            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
//            intent.setData(uri);
//            context.startActivity(intent);
//            return;
//        }
        try {
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, pkg);
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, uid);
                //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
                intent.putExtra("app_package", pkg);
                intent.putExtra("app_uid", uid);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }
    }
    
    /**
     * 恢复极光推送服务
     */
    /**
     * 获取当前系统SDK版本号
     */
    public static int getSystemVersion() {
        /* 获取当前系统的android版本号 */
        return Build.VERSION.SDK_INT;
    }
    
    public static Boolean isCurrentProcess(Context context) {
        String processName = UtilBaseApp.getProcessName(context);
        String packageName = context.getPackageName();
        if (packageName.equals(processName)) {
            return true;
        }else{
            return false;
        }
    }
    
    public static String getProcessName(Context context) {
        if (context == null) return null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }
    
    public static void saveLogcat(String fileName, String write) {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {
                String pathName = "/ctclient_log";
                File filename = new File(pathName);
                if (!filename.exists()) {
                    filename.mkdirs();
                }
                File path = new File(filename + "/" + fileName + ".txt");

                SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyy年MM月dd日 HH:mm:ss:SSS ");
                Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
                String nowTime = formatter.format(curDate);

                String writeToFile = nowTime + " : " + write + "\n";
                FileOutputStream fos = new FileOutputStream(path, true);
                byte[] bytes = writeToFile.getBytes();
                fos.write(bytes);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * The version name of this package, as specified by the <manifest> tag's
     * versionName attribute.
     *
     * @return String
     * @author zhuofq
     */

    public static String getCurAppVerNameFromCode(Context context) {
        //该函数现存问题：10000和97008无法区分是10.0.0还是1.0.000，因此无法针对97008做单独处理成9.7.008，考虑到现有版本都是10.0.0及其以上版本，故暂不做优化处理。
        String verCode = getCurAppVer(context);
        String verName = verCode;
        try {
            if (UtilOther.parseInt(verCode) > 9999) {
                verName = "" + verCode.charAt(0) + verCode.charAt(1) + "." + verCode.charAt(2) + "." + verCode.charAt(3);//注意char直接相加不是拼接字符串
            } else {
                verName = verCode.charAt(0) + "." + verCode.charAt(1) + "." + verCode.charAt(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verName;
    }

    public static String getCurAppVer(Context context) {
        int verCode = 0;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "" + verCode;
    }


    /**
     * 获取渠道号，打包时需要
     *
     */
    public static String getChannelIdOld(Context context) {
        String result = "channel0";
        try {
            ApplicationInfo appinfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            Bundle bundle = appinfo.metaData;
            result = bundle.getString("APP_CHANNEL_ID");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    private static final String CHANNEL_KEY = "ctchannel";
    private static String mChannel;
    
    public static String getChannel(Context context) {
        return getChannel(context, "");
    }
    public static String getChannel(Context context, String defaultChannel) {
        //内存中获取
        if (!TextUtils.isEmpty(mChannel)) {
            return mChannel;
        }
//zhuofq 不做保存,以新的为准
//		//sp中获取
//		mChannel = getChannelBySharedPreferences(context);
//		if(!TextUtils.isEmpty(mChannel)){
//			return mChannel;
//		}
        //从apk中获取
        mChannel = getChannelFromApk(context);
        if (!TextUtils.isEmpty(mChannel)) {
            //保存sp中备用
//			saveChannelBySharedPreferences(context, mChannel);
            return mChannel;
        }
        //全部获取失败
        return defaultChannel;
    }

    /**
     * 从apk中获取版本信息
     */
    private static String getChannelFromApk(Context context) {
        //从apk包中获取
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        //默认放在meta-inf/里， 所以需要再拼接一下
        String key = "META-INF/" + UtilBaseApp.CHANNEL_KEY;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();

                if (entryName.contains("../")) {
                    break;
                } else {
                    if (entryName.startsWith(key)) {
                        ret = entryName;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] split = ret.split("_");
        String channel = "";
        if (split.length >= 2) {
            channel = ret.substring(split[0].length() + 1);
        }
        return channel;
    }
    
}
