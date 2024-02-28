package com.ct.client.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ct.client.database.bean.CustomerServiceMsg
import com.ct.client.database.bean.Hot
import com.ct.net.model.MessageInfo
import com.ct.client.database.dao.MessageInfoDao
import com.example.sunnyweather.SunnyWeatherApplication

/**
 * 中国电信app数据库
 * @------修改记录-------
 * @修改人 luohao
 * @版本 10.4.0
 * @修改内容 客服聊天记录
 */
@Database(
    exportSchema = true,
    version = 3,
    entities = [ MessageInfo::class],
    autoMigrations = [
        AutoMigration (from = 1, to = 2),
        AutoMigration (from = 2, to = 3)
    ]
)
abstract class AppRoomDataBase : RoomDatabase() {
    abstract fun messageInfoDao(): MessageInfoDao

    companion object {
        private var instance: AppRoomDataBase? = null

        @Synchronized
        fun getDatabase(): AppRoomDataBase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                SunnyWeatherApplication.context,
                AppRoomDataBase::class.java,
                "CtClientRoom.db"
            ).allowMainThreadQueries() //调用这个方法让主线程也可以操作数据库
                .build().apply {
                    instance = this
                }
        }
    }
}