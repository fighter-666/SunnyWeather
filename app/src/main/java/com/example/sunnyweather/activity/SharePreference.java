package com.example.sunnyweather.activity;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharePreference {
   private static SharePreference instance;
   private SharedPreferences sharedPreferences;

   private SharePreference(Context context) {
      sharedPreferences = context.getSharedPreferences("YourPreferenceName", Context.MODE_PRIVATE);
   }

   public static synchronized SharePreference getInstance(Context context) {
      if (instance == null) {
         instance = new SharePreference(context);
      }
      return instance;
   }

   public void setCache(String key, String value) {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(key, value);
      editor.apply();
   }

   public void setCache(String key, boolean value) {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putBoolean(key, value);
      editor.apply();
   }

   public void setCache(String key, int value) {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putInt(key, value);
      editor.apply();
   }

   public void setCache(String key, long value) {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putLong(key, value);
      editor.apply();
   }

   public void setCache(String key, float value) {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putFloat(key, value);
      editor.apply();
   }

   public void setCache(String key, Set<String> value) {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putStringSet(key, value);
      editor.apply();
   }

   public String getString(String key) {
      return sharedPreferences.getString(key, "");
   }

   public boolean getBoolean(String key, boolean defaultValue) {
      return sharedPreferences.getBoolean(key, defaultValue);
   }

   public int getInt(String key, int defaultValue) {
      return sharedPreferences.getInt(key, defaultValue);
   }

   public long getLong(String key, long defaultValue) {
      return sharedPreferences.getLong(key, defaultValue);
   }

   public float getFloat(String key, float defaultValue) {
      return sharedPreferences.getFloat(key, defaultValue);
   }

   public Set<String> getStringSet(String key, Set<String> defaultValue) {
      return sharedPreferences.getStringSet(key, defaultValue);
   }

   public boolean ifHaveShare(String key) {
      return sharedPreferences.contains(key);
   }

   public void removeOneCache(String key) {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.remove(key);
      editor.apply();
   }

   public void clearAll() {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.clear();
      editor.apply();
   }
}
