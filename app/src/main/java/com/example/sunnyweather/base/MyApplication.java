package com.example.sunnyweather.base;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

public class MyApplication {
   public static DataCache mDataCache;
   public static Context mContext;

   public void onCreate(@NonNull Application application) {
      Log.i("BaseAppCreate", "--------------------MyApplication---------------------");
      mContext = application.getApplicationContext();


   }
}
