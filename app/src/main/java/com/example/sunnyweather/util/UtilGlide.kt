package com.example.sunnyweather.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication
import java.io.File
import java.util.concurrent.ExecutionException

/**
 * @作者: zhangyz
 * @创建时间：2018/6/26 15:50
 * @版本：
 * @-----修改记录----
 * @修改人：
 * @修改内容：
 * @版本：
 */
object UtilGlide {
    private fun getGlideUrl(url: Any): Any {
        val glideUrl: Any = if (url is String && !UtilText.isEmptyOrNull(url)) {
            GlideUrl(
                url, LazyHeaders.Builder()
                    .addHeader("Accept", "image/*,image/webp,*/*,q=0.8")
                    .build()
            )
        } else {
            url
        }
        return glideUrl
    }

    /**
     * 加载中灰色的网络图片
     * @param url 图片地址
     * @param iv ImageView控件
     */
    @JvmStatic
    fun showImage(url: Any?, iv: ImageView) {
        if (url != null) {
            val colorDrawable = ColorDrawable(SunnyWeatherApplication.context.resources.getColor(R.color.gray_f0f0f0))
            val options = RequestOptions().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(colorDrawable)
                .error(colorDrawable)
                .fallback(colorDrawable)
                .skipMemoryCache(true)

            Glide.with(SunnyWeatherApplication.context)
                .load(getGlideUrl(url))
                .apply(options)
                .into(iv)
        }
    }

    /**
     * 加载中暗黑色的网络图片-尊享版的加载中
     * @param url 图片地址
     * @param iv ImageView控件
     */
    fun showPremiumImage(url: Any?, iv: ImageView) {
        if (url != null) {
            val colorDrawable = ColorDrawable(SunnyWeatherApplication.context.resources.getColor(R.color.gray_272625))
            val options = RequestOptions().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(colorDrawable)
                .error(colorDrawable)
                .fallback(colorDrawable)
                .skipMemoryCache(true)

            Glide.with(SunnyWeatherApplication.context)
                .load(getGlideUrl(url))
                .apply(options)
                .into(iv)
        }
    }


    /**
     * 加载图片无占位图
     * @param url 图片地址
     * @param iv ImageView
     */
    @JvmStatic
    fun showSimpleImage(url: Any?, iv: ImageView) {
        if (url != null) {
            Glide.with(SunnyWeatherApplication.context)
                .load(getGlideUrl(url))
                .into(iv)
        }
    }

    /**
     * 加载图片, 自定义占位图
     * @param url 图片地址
     * @param iv ImageView
     * @param resId 占位图
     */
    @JvmStatic
    fun showImageByRes(url: Any?, iv: ImageView, resId: Int) {
        val options = RequestOptions().placeholder(resId)
            .error(resId)
            .fallback(resId)
        Glide.with(SunnyWeatherApplication.context)
            .load(url)
            .apply(options)
            .into(iv)
    }

    /**
     * 加载图片, 自定义失败占位图
     * @param url 图片地址
     * @param iv ImageView
     * @param resId 占位图
     */
    @JvmStatic
    fun showImageWithError(url: Any?, iv: ImageView, resId: Int) {
        val options = RequestOptions()
            .error(resId)
            .fallback(resId)
        Glide.with(SunnyWeatherApplication.context)
            .load(url)
            .apply(options)
            .into(iv)
    }

    /**
     * 下载图片有占位图 自定义options
     * @param uri 图片地址
     * @param iv ImageView
     */
    @JvmStatic
    fun showImageByOption(uri: Any?, iv: ImageView, options: RequestOptions?) {
        options?.run {
            Glide.with(SunnyWeatherApplication.context)
                .load(uri)
                .apply(options)
                .into(iv)
        }
    }

    /**
     * 加载图片地址url, 自定义占位颜色
     * @param url 图片地址
     * @param iv ImageView
     * @param colorId 占位图
     */
    fun showImageByColor(url: Any?, iv: ImageView, colorId: Int) {
        val colorDrawable = ColorDrawable(SunnyWeatherApplication.context.resources.getColor(colorId))
        val options = RequestOptions().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .placeholder(colorDrawable)
            .error(colorDrawable)
            .fallback(colorDrawable)
        Glide.with(SunnyWeatherApplication.context)
            .load(url)
            .apply(options)
            .into(iv)
    }

    /**
     * glide获取Bitmap，需要放在线程中
     * @param url
     * @return
     */
    @JvmStatic
    fun getImageBitmap(url: String?): Bitmap? {
        try {
            return Glide.with(SunnyWeatherApplication.context).asBitmap().load(url).submit().get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * glide获取File，需要放在线程中
     * @param url
     * @return
     */
    @JvmStatic
    fun getImageFile(url: String?): File? {
        try {
            return Glide.with(SunnyWeatherApplication.context).asFile().load(url).submit().get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return null
    }

    @JvmStatic
/*
    fun liveBgGlide(context: Context, url: String?, imageView: ImageView) {
        val target = object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                val originalWidth = bitmap.width
                val originalHeight = bitmap.height
                Log.d("ZhiBoTest", originalWidth.toString() + "x" + originalHeight)
                //得到裁剪过后的宽和高
                if (originalHeight > UtilPhoneParam.getScreenRealHeight(context) && originalWidth > UtilPhoneParam.getScreenRealWidth(
                        context
                    )
                ) {
                    Log.d("ZhiBoTest", "裁剪")
                    val mBitmap = Bitmap.createBitmap(
                        bitmap,
                        0,
                        0,
                        originalWidth,
                        UtilPhoneParam.getScreenRealHeight(context)
                    )
                    Glide.with(context).load(mBitmap).into(imageView)
                } else {
                    Log.d("ZhiBoTest", "未裁剪")
                    imageView.scaleType = ImageView.ScaleType.FIT_XY
                    Glide.with(context).load(url).into(imageView)
                }
            }
        }
        Glide.with(context).asBitmap().load(url).into(target)
    }
*/

    /**
     * 加载gif文件 限制播放次数
     * @param model
     * @param imageView
     * @param count 播放次数
     */
    fun showGifLoopCount(model: Any?, imageView: ImageView, count: Int) {
        val options = RequestOptions() //配置
        Glide.with(SunnyWeatherApplication.context)
            .asGif()
            .apply(options) //应用配置
            .load(model)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .listener(object : RequestListener<GifDrawable?> {
                //添加监听，设置播放次数
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<GifDrawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any,
                    target: Target<GifDrawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.setLoopCount(count)
                    return false
                }
            })
            .into(imageView)
    }

   /* fun showImageApng(uri: Any?, iv: ImageView) {
        Glide.with(SunnyWeatherApplication.context)
            .load(uri)
            .set(AnimationDecoderOption.NO_ANIMATION_BOUNDS_MEASURE, true)
            .into(iv)
    }*/
}