package com.example.sunnyweather.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

abstract class LazyFragment : Fragment() {

    var isLoaded = false
    var isLazy = true
    @JvmField
    var mExtraParams: ExtraParams? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isLazy) {
            Log.d("HomePageLog","onViewCreated lazy:${this.javaClass.simpleName}")
            lazyInit()
            isLoaded = true
        }else{
            Log.d("HomePageLog","onViewCreated:${this.javaClass.simpleName}")
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded && isLazy) {
            lazyInit()
            Log.d("HomePageLog","onResume lazyInit:${this.javaClass.simpleName}")
            isLoaded = true
        }else{
            Log.d("HomePageLog","onResume isLazy:${isLazy}, isLoaded:${isLoaded}, ${this.javaClass.simpleName}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
        Log.d("HomePageLog","onDestroyView:${this.javaClass.simpleName}")
    }

    abstract fun lazyInit()


    /**
     * 通过Class跳转界面
     *
     * @作者 huangssh
     * @创建时间 2015-8-6 上午9:19:29
     * @param cls 跳转到的class
     */
    protected open fun startActivity(cls: Class<*>) {
        startActivity(cls, null)
    }

    /**
     * 含有Bundle通过Class跳转界面
     *
     * @作者 huangssh
     * @创建时间 2015-8-6 上午9:21:52
     * @param cls 跳转到的class
     * @param bundle
     */
    protected open fun startActivity(cls: Class<*>, bundle: Bundle?) {
        val intent = Intent()
        intent.setClass(requireContext(), cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}


