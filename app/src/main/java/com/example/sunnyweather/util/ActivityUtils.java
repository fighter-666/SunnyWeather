package com.example.sunnyweather.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sunnyweather.base.Utils;
import com.example.sunnyweather.base.UtilsBridge;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class ActivityUtils {

   @Nullable
   private static Activity getActivityFromDecorContext(@Nullable Context context) {
      if (context == null) return null;
      if (context.getClass().getName().equals("com.android.internal.policy.DecorContext")) {
         try {
            Field mActivityContextField = context.getClass().getDeclaredField("mActivityContext");
            mActivityContextField.setAccessible(true);
            //noinspection ConstantConditions,unchecked
            return ((WeakReference<Activity>) mActivityContextField.get(context)).get();
         } catch (Exception ignore) {
         }
      }
      return null;
   }
   @Nullable
   private static Activity getActivityByContextInner(@Nullable Context context) {
      if (context == null) return null;
      List<Context> list = new ArrayList<>();
      while (context instanceof ContextWrapper) {
         if (context instanceof Activity) {
            return (Activity) context;
         }
         Activity activity = getActivityFromDecorContext(context);
         if (activity != null) return activity;
         list.add(context);
         context = ((ContextWrapper) context).getBaseContext();
         if (context == null) {
            return null;
         }
         if (list.contains(context)) {
            // loop context
            return null;
         }
      }
      return null;
   }
   @Nullable
   public static Activity getActivityByContext(@Nullable Context context) {
      if (context == null) return null;
      Activity activity = getActivityByContextInner(context);
      if (!isActivityAlive(activity)) return null;
      return activity;
   }

   public static boolean isActivityAlive(final Context context) {
      return isActivityAlive(getActivityByContext(context));
   }

   /**
    * Return the name of launcher activity.
    *
    * @param pkg The name of the package.
    * @return the name of launcher activity
    */
   public static String getLauncherActivity(@NonNull final String pkg) {
      if (UtilsBridge.isSpace(pkg)) return "";
      Intent intent = new Intent(Intent.ACTION_MAIN, null);
      intent.addCategory(Intent.CATEGORY_LAUNCHER);
      intent.setPackage(pkg);
      PackageManager pm = Utils.getApp().getPackageManager();
      List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
      if (info == null || info.size() == 0) {
         return "";
      }
      return info.get(0).activityInfo.name;
   }
}
