package com.example.sunnyweather.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.sunnyweather.SunnyWeatherApplication

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
     * 加载图片无占位图
     * @param url 图片地址
     * @param iv ImageView
     */
    @JvmStatic
    fun showSimpleImage(url: Any?, iv: ImageView) {
        if (url != null) {
            Glide.with(SunnyWeatherApplication.context.applicationContext)
                .load(getGlideUrl(url))
                .into(iv)
        }
    }

    /**
     * 加载gif文件 限制播放次数
     * @param model
     * @param imageView
     * @param count 播放次数
     */
    @JvmStatic
    fun showGifLoopCount(model: Any?, imageView: ImageView, count: Int) {
        val options = RequestOptions() //配置
        Glide.with(SunnyWeatherApplication.context.applicationContext)
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

}