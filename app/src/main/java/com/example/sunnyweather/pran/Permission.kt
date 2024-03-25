package com.example.sunnyweather.pran

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object Permission {
    const val REQUEST_CODE = 5

    //定义三个权限
    private val permission = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    //每个权限是否已授
    fun isPermissionGranted(activity: Activity?): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            for (i in permission.indices) {
                val checkPermission = ContextCompat.checkSelfPermission(
                    activity!!,
                    permission[i]
                )
                /***
                 * checkPermission返回两个值
                 * 有权限: PackageManager.PERMISSION_GRANTED
                 * 无权限: PackageManager.PERMISSION_DENIED
                 */
                if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
            true
        } else {
            true
        }
    }

    fun checkPermission(activity: Activity?): Boolean {
        return if (isPermissionGranted(activity)) {
            true
        } else {
            //如果没有设置过权限许可，则弹出系统的授权窗口
            ActivityCompat.requestPermissions(
                activity!!,
                permission,
                REQUEST_CODE
            )
            false
        }
    }
}