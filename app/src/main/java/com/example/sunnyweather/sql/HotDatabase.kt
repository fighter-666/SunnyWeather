package com.example.sunnyweather.sql

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sunnyweather.SunnyWeatherApplication

// 定义数据库类，继承自RoomDatabase
@Database(entities = [Hot::class], version = 1, exportSchema = false)
abstract class HotDatabase : RoomDatabase() {
    // 定义获取Dao的抽象方法
    abstract fun hotDao(): HotDao

    companion object {
        // 单例模式，防止多次实例化数据库造成资源浪费
        private var instance: HotDatabase? = null

        // 使用@Synchronized注解，保证线程安全
        @Synchronized
        fun getDatabase(): HotDatabase {
            // 如果已经有实例了，就直接返回
            instance?.let {
                return it
            }
            // 如果没有实例，就创建一个新的数据库实例
            return Room.databaseBuilder(
                SunnyWeatherApplication.context.applicationContext,
                HotDatabase::class.java,
                "hot.db" // 数据库名
            ).allowMainThreadQueries() // 允许在主线程中查询，不推荐这样做，可能会造成UI卡顿
                .build().apply {
                    instance = this // 将新创建的数据库实例赋值给instance
                }
        }
    }
}