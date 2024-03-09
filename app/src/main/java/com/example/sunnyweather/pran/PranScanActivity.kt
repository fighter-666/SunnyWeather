package com.example.sunnyweather.pran

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.FragmentUtils
import com.example.sunnyweather.R
import com.example.sunnyweather.databinding.ActivityPRanScanBinding
import com.example.sunnyweather.widget.Log
import com.gyf.immersionbar.ImmersionBar

/**
 * 扫一扫
 */

class PranScanActivity : AppCompatActivity() {
    // 使用lateinit关键字延迟初始化binding变量。
    private lateinit var binding: ActivityPRanScanBinding

    // 通过lazy委托，延迟初始化ScanFragment，只有在首次使用时才创建实例。
    private val scanFragment by lazy {
        ScanFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化binding对象，用于访问布局中的视图。
        binding = ActivityPRanScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //沉浸式
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init()

        // 设置标题栏的返回按钮点击事件，点击时结束当前Activity。
        binding.run {
            tbScan.setOnTitleBarBackClickListener(object :
                TitleBar.OnTitleBarBackClickListener {
                override fun onBackClickListener() {
                    finish()
                }
            })
            // 设置扫描灯开关的监听器。
            cbScan.setOnCheckedChangeListener { _, b ->
                // 根据开关状态，控制扫描灯的开关。
                scanFragment.toggleLight(b)
            }
            // 设置标题栏右侧按钮的点击事件，点击时打开图库选择图片。
            tbScan.setOnClickListener {
                if (R.id.iv_title_bar_right == it.id) {
                    val intent = Intent()
                    intent.type = "image/*"
                    intent.action = Intent.ACTION_GET_CONTENT
                    startActivityForResult(intent, 5002)
                }
            }
        }
        // 检查应用所需的权限。
        checkPermission()
    }

    /**
     * 检查应用所需的权限。
     */
    private fun checkPermission() {
        Permission.checkPermission(this)
    }

    override fun onResume() {
        super.onResume()
        // 在Activity恢复时，检查权限是否已授予。
        if (Permission.isPermissionGranted(this)) {
            Log.i("PERMISSION", "请求权限成功")
            // 初始化并显示扫描Fragment。
            initScanFragment()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 处理权限请求的结果。
        if (requestCode == Permission.REQUEST_CODE) {
            for (grantResult in grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", "授权失败！")
                    // 如果权限被拒绝，结束Activity。
                    finish()
                    return
                }
            }
        }
    }

    /**
     * 初始化并添加扫描Fragment到Activity。
     */
    private fun initScanFragment() {
        FragmentUtils.add(supportFragmentManager, scanFragment, R.id.container)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 处理从图库选择图片的结果。
        if (resultCode == Activity.RESULT_OK) {
            // 获取选择的图片Uri。
            if (data != null) {
                val originalUri = data.data
                if (originalUri != null) {
                    // 使用扫描Fragment处理选择的图片。
                    scanFragment.decodeQR(originalUri)
                }
            }
        }
    }
}