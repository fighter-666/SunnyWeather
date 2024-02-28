package com.example.sunnyweather.base

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ProcessLifecycleOwner
import com.blankj.utilcode.util.Utils
import com.ct.base.R
import com.ct.base.common.Constants
import com.ct.base.common.Log
import com.ct.base.helper.ChinaTelecomDataHelper
import com.ct.base.helper.RedDotHelper
import com.ct.base.widget.MyBaseDialogBlue
import com.ct.base.widget.PermissionOverlay
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import org.json.JSONObject


/**
 * 说明：掌厅权限请求封装
 */
object UtilXPermission {
    private lateinit var mPermission: Array<out String>
    private var mTips = "" // 授权内容
    private var onAgreeListener: (() -> Unit) ?= null
    private var onCancelListener: (() -> Unit) ?= null

    /**
     * 用于只需要处理同意的回调
     */
    fun setOnAgreeListener(listener: () -> Unit): UtilXPermission {
        onAgreeListener = listener
        onCancelListener = null
        return this
    }

    /**
     * 用于需要处理同意和拒绝或取消的回调
     */
    fun setOnPermissionListener(
        agreeListener: () -> Unit,
        cancelListener: () -> Unit
    ): UtilXPermission {
        onAgreeListener = agreeListener
        onCancelListener = cancelListener
        return this
    }

    /**
     * 校验权限是否同意
     */
    @JvmStatic
    fun checkPermission(vararg permission: String): Boolean {
        var isAllGranted = true // 有一项权限不同意则返回false
        permission.forEach {
            if (ContextCompat.checkSelfPermission(
                    Utils.getApp(),
                    it
                ) != PackageManager.PERMISSION_GRANTED
            ){
                isAllGranted = false
            }
        }
        return isAllGranted
    }

    @Deprecated("新合规要求使用权限需要分场景授权，请确认调用此函数时不需要进行分场景授权,11.2版本后推荐使用 requestPermissionByScene ",
        ReplaceWith("requestPermissionByScene()"))
    fun requestPermission(context: Context, title: Int, tips: Int, vararg permission: String) {
        requestPermission(context, context.getString(title),context.getString(tips), *permission)
    }

    /**
     * 也可以不弹原生窗直接请求权限
     */
    @SuppressLint("CheckResult")
    @Deprecated("新合规要求使用权限需要分场景授权，请确认调用此函数时不需要进行分场景授权,11.2版本后推荐使用 requestPermissionByScene ",
        ReplaceWith("requestPermissionByScene()"))
    fun requestPermission(context: Context, title: String, tips: String, vararg permission: String) {
        mPermission = permission
        mTips = tips // 权限提示

        if (checkPermission(*mPermission)){
            // 用户已经同意该权限
            onAgreeListener?.invoke()
            return
        }

        val permissionOverlay = PermissionOverlay(context)
        val lifecycle = try {
            (context as AppCompatActivity).lifecycle
        } catch (e:Exception){
            ProcessLifecycleOwner.get().lifecycle
        }
        lifecycle.addObserver(permissionOverlay)
        permissionOverlay.initView(title, mTips)

        val mRxPermissions = RxPermissions(context as Activity)
        mRxPermissions.requestEachCombined(*mPermission)
            .subscribe { rxPermission: Permission ->
                permissionOverlay.destroy()
                if (rxPermission.granted) {
                    // 用户已经同意该权限
                    onAgreeListener?.invoke()
                    Log.i("hss_UtilXPermission", "用户已经同意${rxPermission.name}")
                } else if (rxPermission.shouldShowRequestPermissionRationale) {
                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）
                    Log.i("hss_UtilXPermission", "用户拒绝了${rxPermission.name}，没有选中『不再询问』")
                    onCancelListener?.invoke()
                } else {
                    // 用户拒绝了该权限，而且选中『不再询问』, 系统设置弹窗
                    // 不处理模糊定位的拒绝
                    Log.i("hss_UtilXPermission", "用户拒绝了${rxPermission.name}，而且选中『不再询问』")
                    initRedDotListener(context)
                    goSetting(context)
                }
                lifecycle.removeObserver(permissionOverlay)
            }
    }

    fun requestPermissionByScene(context: Context, title: Int, tips: Int, scene: Scenes, vararg permission: String) {
        requestPermissionByScene(context, context.getString(title),context.getString(tips),scene, *permission)
    }

    @SuppressLint("CheckResult")
    fun requestPermissionByScene(context: Context, title: String, tips: String, scene: Scenes, vararg permission: String) {
        mPermission = permission
        mTips = tips // 权限提示
        if (checkPermission(*mPermission)){
            // 已经授权过该场景
            if (Setting.getBoolean(scene.scene, false)){
                onAgreeListener?.invoke()
            }else{
                showSceneDialog(context, scene)
            }
        }else{
//            removeAllScene(scene)
            val permissionOverlay = PermissionOverlay(context)
            val lifecycle = try {
                (context as AppCompatActivity).lifecycle
            } catch (e:Exception){
                ProcessLifecycleOwner.get().lifecycle
            }
            lifecycle.addObserver(permissionOverlay)
            permissionOverlay.initView(title, mTips)

            val mRxPermissions = RxPermissions(context as Activity)
            mRxPermissions.requestEachCombined(*mPermission)
                .subscribe { rxPermission: Permission ->
                    permissionOverlay.destroy()
                    if (rxPermission.granted) {
                        // 用户已经同意该权限
                        grantScene(scene)
                        onAgreeListener?.invoke()
                        Log.i("hss_UtilXPermission", "用户已经同意${rxPermission.name}")
                    } else if (rxPermission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）
                        Log.i("hss_UtilXPermission", "用户拒绝了${rxPermission.name}，没有选中『不再询问』")
                        onCancelListener?.invoke()
                    } else {
                        // 用户拒绝了该权限，而且选中『不再询问』, 系统设置弹窗
                        // 不处理模糊定位的拒绝
                        Log.i("hss_UtilXPermission", "用户拒绝了${rxPermission.name}，而且选中『不再询问』")
                        initRedDotListener(context)
                        goSetting(context)
                    }
                    lifecycle.removeObserver(permissionOverlay)
                }
        }

    }

    /**
     * 移除未授权权限的所有场景授权，业务要求撤回权限时不清空场景
     */
    private fun removeAllScene(scene: Scenes){
        val index = scene.scene.indexOf("_Scene")
        val sceneType = scene.scene.substring(0,index)
        for (item in Scenes.values()){
            if (item.scene.startsWith(sceneType)){
                Setting.setBoolean(item.scene, false)
            }
        }
    }

    /**
     * 授权场景
     */
    private fun grantScene(scene: Scenes){
        Setting.setBoolean(scene.scene, true)
    }

    private fun goSetting(context: Activity) {
        val mSettingDialog = MyBaseDialogBlue(context)
        mSettingDialog.setTitle("授权提示")
        mSettingDialog.setMsg(mTips)
        mSettingDialog.setYesBtn("去设置")
        mSettingDialog.setNoBtn("取消")
        mSettingDialog.setMsgGravity(Gravity.CENTER)
        mSettingDialog.setOnYesButtonCallback {
            val intent = Intent()
            intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            intent.data =
                Uri.fromParts("package", context.packageName, null)
            context.startActivityForResult(
                intent,
                Constants.PermissionUtilCode.GO_SYS_SETTING
            )
        }
        mSettingDialog.setOnCloseButtonCallback {
            onCancelListener?.invoke()
        }
        mSettingDialog.setOnNoButtonCallback {
            onCancelListener?.invoke()
        }
        mSettingDialog.show()
    }

    /**
     * 设置页返回app结果监听
     */
    private fun initRedDotListener(context: Context) {
        // 系统设置
        RedDotHelper.getInstance()
            .addRedDotListener(Constants.PermissionUtilCode.BACK_FROM_SYS_SETTING) {
                backFromSystemSetting(context)
            }

        // 悬浮窗授权
        RedDotHelper.getInstance()
            .addRedDotListener(Constants.PermissionUtilCode.BACK_FROM_ALERT_WINDOW_SETTING) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.canDrawOverlays(context as Activity)) {
                        //若未授权
                        onCancelListener?.invoke()
                    } else {
                        // 用户已经同意该权限
                        onAgreeListener?.invoke()
                        val bundle = context.intent.extras
                        val scene = bundle!!.getString("scene")
                        if (scene != null) {
                            Setting.setBoolean(scene, true)
                        }
                    }
                } else {
                    // M以下直接视为无权限
                    onCancelListener?.invoke()
                }
            }
    }

    private fun backFromSystemSetting(context: Context) {
        if (checkPermission(*mPermission)) {
            // 用户已经同意该权限
            onAgreeListener?.invoke()
        } else {
            showTipDialog(context)
            onCancelListener?.invoke()
        }
    }

    /**
     * 展示知道了
     */
    private fun showTipDialog(context: Context) {
        val mSettingCallBackDialog =
            MyBaseDialogBlue(context)
        mSettingCallBackDialog.setTitle("授权提示")
        mSettingCallBackDialog.setMsg(mTips)
        mSettingCallBackDialog.setYesBtn("知道了")
        mSettingCallBackDialog.setMsgGravity(Gravity.CENTER)
        mSettingCallBackDialog.show()
    }

    /**
     * 通知权限特殊处理跳转通知设置页
     */
    @SuppressLint("CheckResult")
    fun showNotificationDialog(mContext: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val dialog = MyBaseDialogBlue(mContext)
            dialog.setTitle("授权提示")
            dialog.setMsg(mContext.getString(R.string.permission_notification))
            dialog.setYesBtn("知道了")
            dialog.setNoBtn("拒绝")
            dialog.setMsgGravity(Gravity.CENTER)
            dialog.setOnNoButtonCallback {
                onCancelListener?.invoke()
            }
            dialog.setOnCloseButtonCallback {
                onCancelListener?.invoke()

            }
            dialog.setOnMyDialogBackPressed {
                onCancelListener?.invoke()
            }
            dialog.setOnYesButtonCallback {
                val rxPermissions = RxPermissions(mContext)
                rxPermissions.requestEach(Manifest.permission.POST_NOTIFICATIONS)
                    .subscribe {
                        if (it.granted) {
                            // 用户已经同意该权限
                            onAgreeListener?.invoke()
                        } else if (it.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）
                            onCancelListener?.invoke()
                        } else {
                            // 用户拒绝了该权限，而且选中『不再询问』
                            UtilBaseApp.goToNotificationSetting(mContext)
                        }
                    }
            }
            dialog.show()
        }
    }

    fun showCanDrawOverlaysDialog(mActivity: Activity, mTips: Int,scene: Scenes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(mActivity)) {
                // 悬浮窗权限授权提示弹窗
                val mPermissionDialog =
                    MyBaseDialogBlue(mActivity)
                mPermissionDialog.setTitle("授权提示")
                mPermissionDialog.setMsg(mActivity.getString(mTips))
                mPermissionDialog.setYesBtn("知道了")
                mPermissionDialog.setNoBtn("拒绝")
                mPermissionDialog.setMsgGravity(Gravity.CENTER)
                mPermissionDialog.setOnYesButtonCallback { requestCanDrawOverlays(mActivity, scene) }
                mPermissionDialog.setOnNoButtonCallback {
                    onCancelListener?.invoke()
                }
                mPermissionDialog.setOnCloseButtonCallback {
                    onCancelListener?.invoke()
                }
                mPermissionDialog.show()
            } else {
                // 用户已经同意该权限
                if (Setting.getBoolean(scene.scene, false)){
                    // 已经授权过该场景
                    onAgreeListener?.invoke()
                }else{
                    showSceneDialog(mActivity, scene)
                }
            }
        } else {
            // M以下直接视为无权限
            onCancelListener?.invoke()
        }
    }


    /**
     * 申请悬浮窗权限
     */
    private fun requestCanDrawOverlays(mActivity: Activity, scene:Scenes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(mActivity)){
                // 已经授权过该场景
                if (Setting.getBoolean(scene.scene, false)){
                    onAgreeListener?.invoke()
                }else{
                    showSceneDialog(mActivity, scene)
                }
            } else {
                initRedDotListener(mActivity)
                //若未授权则请求权限
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                intent.data = Uri.parse("package:" + mActivity.packageName)
                intent.putExtra("scene",scene.scene)
                mActivity.startActivityForResult(
                    intent,
                    Constants.PermissionUtilCode.GO_ALERT_WINDOW_SETTING
                )
            }
        } else {
            // M以下直接视为无权限
            onCancelListener?.invoke()
        }
    }

    private fun showSceneDialog(context: Context, scene:Scenes) {
        val dialog = MyBaseDialogBlue(context)
        dialog.setTitle("允许获取本机${scene.permissionDescription}")
        dialog.setMsg("为了使用${scene.tips}功能,我们会在您授权同意后获取允许获取本机${scene.permissionDescription};如不授予该权限,将不能使用该功能服务")
        dialog.setYesBtn("同意")
        dialog.setNoBtn("暂不")
        dialog.setMsgGravity(Gravity.CENTER)
        dialog.setOnYesButtonCallback {
            onAgreeListener?.invoke()
            grantScene(scene)
        }
        dialog.setOnNoButtonCallback {
            onCancelListener?.invoke()
        }
        dialog.setOnCloseButtonCallback {
            onCancelListener?.invoke()
        }
        dialog.setOnMyDialogBackPressed {
            onCancelListener?.invoke()
        }
        dialog.show()
    }

    /**
     * 获取权限字符串
     */
    fun getGrantPermissionCode(): String {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Constants.Permission.READ_LIST_OF_APP
        )
        val code = StringBuilder()
        for (i in permissions.indices) {
            if (permissions[i] == Manifest.permission.SYSTEM_ALERT_WINDOW) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Settings.canDrawOverlays(Utils.getApp())) {
                        code.append(i + 1).append(",")
                    }
                }
                continue
            }
            if (permissions[i] == Constants.Permission.READ_LIST_OF_APP) {
                if (Setting.getBooleanByNbr("YSSBTC2", false)) {
                    code.append(i + 1).append(",")
                }
                continue
            }
            if (checkPermission(permissions[i])) {
                code.append(i + 1).append(",")
            }
        }
        return if (code.toString() == "") {
            ""
        } else {
            code.substring(0, code.toString().length - 1)
        }
    }

    private var lastClickTime: Long = 0
    fun isFastClick(time: Int): Boolean {
        var flag = true
        val currentClickTime = System.currentTimeMillis()
        if (currentClickTime - lastClickTime >= time) {
            flag = false
        }
        lastClickTime = currentClickTime
        return flag
    }

    enum class Scenes(val scene: String,val tips: String,val permissionDescription: String){
        /**摄像头*/
        CAMERA_SCAN("Camera_Scene_SCAN", "扫一扫","摄像头权限"), // 扫一扫
        CAMERA_CUSTOMER("Camera_Scene_Customer", "在线客服","摄像头权限"), // 客服
        CAMERA_SELECT_HEADER("Camera_Scene_SelectHeader", "上传头像","摄像头权限"), // 上传头像
        CAMERA_WAP("Camera_Scene_Wap", "Wap","摄像头权限"), // Wap
        CAMERA_REAL_NAME("Camera_Scene_Real_Name", "实名制认证","摄像头权限"), // 实名认证
        /**联系人*/
        CONTACT_DETAIL("Contact_Scene_DETAIL", "详单查询","联系人权限"), // 详单查询
        CONTACT_ADDRESS_BOOK("Contact_Scene_Contact", "联系人","联系人权限"), // 通讯录
        CONTACT_WAP("Contact_Scene_Wap", "WAP","联系人权限"), // WAP
        CONTACT_FAMILY_CIRCLE("Contact_Scene_Family_Circle", "家庭圈","联系人权限"), // 家庭圈
        CONTACT_TRUMPET("Contact_Scene_Trumpet", "小号","联系人权限"), // 小号
        /**定位*/
        LOCATION_P_RAN("Location_Scene_PR","翼相连网络","定位权限"),// P-RAN
        LOCATION_BUSINESS_HALL("Location_Scene_BusinessHall","附近营业厅","定位权限"),// 附近营业厅
        LOCATION_5G_COVERAGE("Location_Scene_5G","5G覆盖","定位权限"),// 5G覆盖
        LOCATION_WAP("Location_Scene_Wap","WAP框架","定位权限"),// WAP框架
        LOCATION_INTELLIGENT_ASSISTANT("Location_Scene_Intelligent_Assistant","智能助手","定位权限"),// 智能助手
        /**写入SD卡*/
        STORAGE_WAP("Storage_Scene_Wap","WAP框架","存储权限"), // WAP框架
        STORAGE_INVOICE("Storage_Scene_Invoice","电子发票","存储权限"), // 电子发票
        STORAGE_SHARE("Storage_Scene_Share","分享","存储权限"), // 分享保存图片
        /**悬浮窗权限*/
        DRAW_OVERLAYS_DIGITAL_PERSON("Draw_Overlays_Scene_Digital_Person","数字人","悬浮窗权限"), //数字人
        DRAW_OVERLAYS_VIRTUAL_PERSON("Draw_Overlays_Scene_Virtual_Person","虚拟人","悬浮窗权限"), //虚拟人
        DRAW_OVERLAYS_CUSTOMER("Draw_Overlays_Scene_Customer","视频客服","悬浮窗权限"), //视频客服
        DRAW_OVERLAYS_P_RAN("Draw_Overlays_Scene_PR","翼相连","悬浮窗权限"), // P_RAN
        /**麦克风权限*/
        AUDIO_CUSTOMER("Audio_Scene_Customer","视频客服","麦克风权限"), // 客服
        AUDIO_INTELLIGENT_ASSISTANT("Audio_Scene_Intelligent_Assistant","智能助手","麦克风权限"), // 智能助手
        AUDIO_FAMILY_CIRCLE("Audio_Scene_Family_Circle","家庭圈","麦克风权限"), // 家庭圈
        AUDIO_VOICE_RECOGNITION("Audio_Scene_Voice_Recognition","语音识别","麦克风权限"), // 语音识别
        AUDIO_WAP("Audio_Scene_Wap","WAP框架","麦克风权限"), // WAP框架
    }

    private fun pagePermission(permission: String){
        val properties = JSONObject()
        properties.put("pit_page_id","hg_fw_sqy");
        properties.put("pit_page_name","授权页");
        properties.put("track_id","87020518a");
        properties.put("pit_channel_id","hg_fw");
        properties.put("pit_channel_name","服务频道");
        setPermissionPageFlag(permission, properties)
        ChinaTelecomDataHelper.track("pageViewEvent", properties)
    }

    private fun hitPermissionClose(permission: String){
        val properties = JSONObject()
        properties.put("bd_scence_id","");
        properties.put("pit_channel_id","hg_fw");
        properties.put("pit_channel_name","服务频道");
        properties.put("pit_location","位置1_1");
        properties.put("pit_name","关闭");
        properties.put("pit_page_id","hg_fw_sqy");
        properties.put("pit_page_name","授权页");
        properties.put("province_code","");
        properties.put("track_id","69020346a");
        setPermissionPageFlag(permission, properties)
        ChinaTelecomDataHelper.track("hitEvent", properties)
    }

    private fun hitPermissionRefuse(permission: String){
        val properties = JSONObject()
        properties.put("bd_scence_id","");
        properties.put("pit_channel_id","hg_fw");
        properties.put("pit_channel_name","服务频道");
        properties.put("pit_location","位置1_2");
        properties.put("pit_name","拒绝");
        properties.put("pit_page_id","hg_fw_fsqy");
        properties.put("pit_page_name","授权页");
        properties.put("province_code","");
        properties.put("track_id","32477124a");
        setPermissionPageFlag(permission, properties)
        ChinaTelecomDataHelper.track("hitEvent", properties)
    }

    private fun hitPermissionAgree(permission: String){
        val properties = JSONObject()
        properties.put("bd_scence_id","");
        properties.put("pit_channel_id","hg_fw");
        properties.put("pit_channel_name","服务频道");
        properties.put("pit_location","位置1_3");
        properties.put("pit_name","知道了");
        properties.put("pit_page_id","hg_fw_fsqy");
        properties.put("pit_page_name","授权页");
        properties.put("province_code","");
        properties.put("track_id","37888948a");
        setPermissionPageFlag(permission, properties)
        ChinaTelecomDataHelper.track("hitEvent", properties)
    }

    private fun setPermissionPageFlag(permission: String, properties: JSONObject) {
        when (permission) {
            Manifest.permission.RECORD_AUDIO -> {
                properties.put("page_flag", "麦克风授权弹窗");
            }

            Manifest.permission.ACCESS_FINE_LOCATION -> {
                properties.put("page_flag", "地理位置授权弹窗");
            }

            Manifest.permission.CAMERA -> {
                properties.put("page_flag", "摄像头授权弹窗");
            }

            Manifest.permission.READ_CONTACTS -> {
                properties.put("page_flag", "读取联系人授权弹窗");
            }

            else -> {
                properties.put("page_flag", "授权弹窗");
            }
        }
    }
}