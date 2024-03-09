package com.example.sunnyweather.pran

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.databinding.FragmentPRanScanBinding
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.MyToastD
import com.example.sunnyweather.widget.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.google.zxing.client.result.ParsedResult
import com.gyf.immersionbar.ImmersionBar
import com.mylhyl.zxing.scanner.OnScannerCompletionListener
import com.mylhyl.zxing.scanner.ScannerOptions
import com.mylhyl.zxing.scanner.decode.QRDecode
import com.youth.banner.util.LogUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * 扫一扫Fragment，用于处理二维码扫描功能。
 */
class ScanFragment : Fragment(),
    OnScannerCompletionListener, SensorEventListener {
    // 使用lateinit延迟初始化Fragment的视图绑定。
    private lateinit var binding: FragmentPRanScanBinding

    // 传感器管理器对象，用于访问设备传感器。
    private var mSensorManager: SensorManager? = null

    // 光线传感器对象。
    private var mSensor: Sensor? = null

    // 用于定时提示扫描二维码的Disposable对象。
    private var scanTipDisposable: Disposable? = null

    // 标记扫描是否初始化了，用于控制是否显示“屏幕内未识别到二维码”的提示。
    private var scanInit = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        // 初始化视图绑定。
        binding = FragmentPRanScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //沉浸式
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .init()
        // 初始化扫描视图和传感器管理器。
        initScannerView()
        initSensorManager()

        // 创建并启动一个定时器，定期提示用户扫描二维码。
        scanTipDisposable?.dispose()
        scanTipDisposable = Observable.interval(10, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (scanInit) {
                    MyToastD.show("屏幕内未识别到二维码")
                }
            }
    }

    override fun onDestroyView() {
        // 在视图销毁时关闭手电筒。
        binding.scannerView.toggleLight(false)
        super.onDestroyView()
    }

    override fun onDestroy() {
        // 在Fragment销毁时注销传感器监听器并取消定时器。
        mSensorManager?.unregisterListener(this)
        scanTipDisposable?.dispose()
        super.onDestroy()
    }

    /**
     * 初始化传感器管理器，用于获取光线传感器。
     */
    private fun initSensorManager() {
        mSensorManager =
            SunnyWeatherApplication.context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    /**
     * 处理从图库选择的图片，尝试解码二维码。
     */
    fun decodeQR(originalUri: Uri) {
        try {
            val photo = MediaStore.Images.Media.getBitmap(
                SunnyWeatherApplication.context.contentResolver, originalUri
            )
            QRDecode.decodeQR(photo, this)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 初始化扫描视图的配置。
     */
    private fun initScannerView() {
        binding.scannerView.setOnScannerCompletionListener(this)
        val builder = ScannerOptions.Builder()
            .setFrameStrokeColor(Color.RED) // 设置扫描框边框颜色
            .setFrameStrokeColor(Color.RED) // 设置扫描框边框颜色
            .setFrameStrokeWidth(1.5f) // 设置扫描框边框宽度
            .setLaserStyle(ScannerOptions.LaserStyle.RES_LINE, R.drawable.scan_line) // 设置扫描线样式
            .setScanFullScreen(true) // 设置全屏扫描
            .setFrameHide(true) // 隐藏扫描框
            .setTipText("") // 设置扫描提示文本
            .setScanMode(BarcodeFormat.QR_CODE) // 设置扫描模式为二维码
            .setTipTextSize(14) // 设置提示文本大小
        binding.scannerView.setScannerOptions(builder.build())
        scanInit = true // 标记扫描已初始化
    }

    override fun onResume() {
        super.onResume()
        binding.scannerView.onResume() // 恢复扫描视图
        // 为光线传感器注册监听器
        mSensorManager?.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        binding.scannerView.onPause() // 暂停扫描视图
        super.onPause()
    }

    /**
     * 扫描结果回调方法。
     */
    override fun onScannerCompletion(
        rawResult: Result?, parsedResult: ParsedResult?, barcode: Bitmap?,
    ) {
        try {
            val result = rawResult?.text // 获取扫描结果文本
            if (result != null) {
                val item = CommonLinkItem()
                item.linkType = "2"
                item.link = result
                item.goTarget(requireContext()) // 处理扫描结果
            }
        } catch (e: Exception) {
            LogUtils.e("扫描结果")
            MyToastD.show("屏幕内未识别到二维码")
            binding.scannerView.restartPreviewAfterDelay(3000) // 3秒后重启预览
        }
    }

    /**
     * 当传感器的值发生变化时回调的方法。
     * onSensorChanged(event: SensorEvent) 方法是传感器事件监听器的回调方法，当传感器数值发生变化时会被调用。
     *
     * event.values 是一个浮点数数组，表示传感器当前的数值。不同传感器的数值含义不同，在这个例子中，我们关注的是光线传感器的数值。
     *
     * event.sensor.type 获取当前传感器的类型，通过和 Sensor.TYPE_LIGHT 进行比较判断是否为光线传感器。
     *
     * 如果传感器类型是光线传感器 (Sensor.TYPE_LIGHT)，则判断光线的强度是否小于 10。如果是，则显示一个提示消息，告知用户光线太暗，请开启手电筒。
     */
    override fun onSensorChanged(event: SensorEvent) {
        val values = event.values
        val sensorType = event.sensor.type
        if (sensorType == Sensor.TYPE_LIGHT) {
            Log.i("onSensorChanged", values[0].toString())
            if (values[0] < 10) {
                MyToastD.show("光线太暗，请开启手电筒")
            }
        }
    }

    // 当传感器精度发生变化时回调的方法，此处未使用。
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    /**
     * 控制手电筒的开关。
     */
    fun toggleLight(openLight: Boolean) {
        binding.scannerView.toggleLight(openLight)
    }
}