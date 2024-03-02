package com.example.sunnyweather.sql

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sunnyweather.SunnyWeatherApplication


@Database(entities = [Hot::class], version = 1, exportSchema = false)
abstract class HotDatabase : RoomDatabase() {
    abstract fun hotDao(): HotDao

    companion object {
        private var instance: HotDatabase? = null

        @Synchronized
        fun getDatabase(): HotDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                SunnyWeatherApplication.context.applicationContext,
                HotDatabase::class.java,
                "hot.db"
            ).allowMainThreadQueries() //调用这个方法让主线程也可以操作数据库
                .build().apply {
                    instance = this
                }
        }
    }
}

