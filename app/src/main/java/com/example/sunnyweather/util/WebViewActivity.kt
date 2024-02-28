package com.example.sunnyweather.util

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.sunnyweather.R
import com.example.sunnyweather.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra("url") ?: "http://www.baidu.com"
        binding.webView.loadUrl(url)
        //binding.webView.settings.javaScriptEnabled = true
        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        binding.webView.setWebViewClient(object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                //使用WebView加载显示url
                view.loadUrl(url)
                //返回true
                return true
            }
        })
    }
}