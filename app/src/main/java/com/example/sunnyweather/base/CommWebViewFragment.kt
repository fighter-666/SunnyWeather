package com.example.sunnyweather.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil.setContentView
import com.example.sunnyweather.R
import com.example.sunnyweather.databinding.ActivityWebviewBinding
import com.example.sunnyweather.databinding.FragmentMyBinding
import com.example.sunnyweather.databinding.FragmentWebviewBinding
import com.example.sunnyweather.util.UtilText
import com.gyf.immersionbar.ImmersionBar

class CommWebViewFragment: LazyFragment() {
    private lateinit var binding: FragmentWebviewBinding
    private lateinit var mUrl: String

    private var url: String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWebviewBinding.inflate(layoutInflater)

        return binding.root
    }

    companion object {
        fun newInstance(url: String): CommWebViewFragment {
            val fragment = CommWebViewFragment()
            fragment.url = url
            return fragment
        }
    }

    override fun lazyInit() {
        mExtraParams?.run {
            if (!UtilText.isEmptyOrNull(data)) {
                mUrl = UtilText.urlDecoder(data)
            }else{
                mUrl = "https://www.bilibili.com"
            }
        }
        binding.webView.loadUrl(mUrl)
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