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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ActivityUtils
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.base.binding.BaseBindingFragment
import com.example.sunnyweather.databinding.FragmentComponentsBinding
import com.example.sunnyweather.databinding.FragmentPRanScanBinding
import com.example.sunnyweather.util.CommonLinkItem
import com.example.sunnyweather.util.MyToastD
import com.example.sunnyweather.util.UtilText
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
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

/**
 * 描述　: 扫一扫
 */

class ScanFragment : Fragment(),
    OnScannerCompletionListener, SensorEventListener {
    private lateinit var binding: FragmentPRanScanBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPRanScanBinding.inflate(inflater, container, false)
        return binding.root
    }



    //传感器管理器对象
    private var mSensorManager: SensorManager? = null

    private var mSensor: Sensor? = null

    private var scanTipDisposable: Disposable? = null

    /**
     * 扫描是否初始化了，初始化了才会显示屏幕内没有二维码
     */
    private var scanInit = false


    override fun onDestroyView() {
        binding.scannerView.toggleLight(false)
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //UtilOperationRecord.scan()
        initScannerView()
        initSensorManager()
        scanTipDisposable?.dispose()
        scanTipDisposable = Observable.interval(10, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                if (scanInit) {
                    MyToastD.show("屏幕内未识别到二维码")
                }
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        mSensorManager?.unregisterListener(this)
        scanTipDisposable?.dispose()
    }

    private fun initSensorManager() {
        mSensorManager = SunnyWeatherApplication.context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    public fun decodeQR(originalUri: Uri) {
        if (originalUri != null) {
            try {
                val photo = MediaStore.Images.Media.getBitmap(
                    SunnyWeatherApplication.context.contentResolver, originalUri
                )
                QRDecode.decodeQR(photo, this)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun initScannerView() {
        binding.scannerView.setOnScannerCompletionListener(this)
        val builder = ScannerOptions.Builder()
        builder.setFrameStrokeColor(Color.RED).setFrameStrokeWidth(1.5f)
            .setMediaResId(R.raw.scan_succ_music)
            //                .setFrameSize(256, 256)
            //                .setFrameCornerLength(22)
            //                .setFrameCornerWidth(2)
            //                .setFrameCornerColor(0xff06c1ae)
            //                .setFrameCornerInside(true)
            //                .setLaserLineColor(0xff06c1ae)
            //                .setLaserLineHeight(18)
            .setLaserStyle(ScannerOptions.LaserStyle.RES_LINE, R.drawable.scan_line)
            //                .setLaserStyle(ScannerOptions.LaserStyle.RES_GRID, R.mipmap.zfb_grid_scan_line)//网格图
            //                .setFrameCornerColor(0xFF26CEFF)//支付宝颜色
            .setScanFullScreen(true).setFrameHide(true).setTipText("")
            //                .setFrameCornerHide(false)
            //                .setLaserMoveFullScreen(false)
            //                .setViewfinderCallback((view, canvas, frame) -> {
            //                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            //                    canvas.drawBitmap(bmp, frame.right / 2, frame.top - bmp.getHeight(), null);
            //                })
            .setScanMode(BarcodeFormat.QR_CODE).setTipTextSize(14)
        binding.scannerView.setScannerOptions(builder.build())
        scanInit = true
    }

    override fun onResume() {
        binding.scannerView.onResume()
        super.onResume()
        //为加速度传感器注册监听器
        mSensorManager?.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME)
//        MainScope().launch {
//            delay(10000)
//            // 注册光线感应监听，第二个参数：传感器对象 光线传感器类型的常量：TYPE_LIGHT 第三个参数：传感器数据的频率
//            sensorManager.registerListener(
//                this@ScanFragment,
//                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
//                SensorManager.SENSOR_DELAY_GAME
//            )
//        }
    }

    override fun onPause() {
        binding.scannerView.onPause()
        super.onPause()
    }

    /**
     * 扫描结果
     */
    override fun onScannerCompletion(
        rawResult: Result?, parsedResult: ParsedResult?, barcode: Bitmap?
    ) {
        try {
            val result = RecodeUtils.recode(rawResult?.text)
            if (result != null){
                    val item = CommonLinkItem()
                    item.linkType = "2"
                    item.link = result
                    item.goTarget(requireContext())
            }

        } catch (e: Exception) {
            LogUtils.e("扫描结果")
            MyToastD.show("屏幕内未识别到二维码")
            binding.scannerView.restartPreviewAfterDelay(3000)
        }
    }

    /**
     *  当传感器的值，发生变化时，回调的方法
     */
    override fun onSensorChanged(event: SensorEvent) {
        //获取传感器的值
        val values = event.values
        //获取传感器类型
        val sensorType = event.sensor.type
        if (sensorType == Sensor.TYPE_LIGHT) {
            Log.i("onSensorChanged", values[0].toString())
            if (values[0] < 10) {
                MyToastD.show("光线太暗，请开启手电筒")
            }
        }
    }

    //当传感器的精度，发生变化时，回调的方法
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    fun toggleLight(openLight: Boolean) {
        binding.scannerView.toggleLight(openLight)
    }


}