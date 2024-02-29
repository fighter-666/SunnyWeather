package com.example.sunnyweather.widget

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.sunnyweather.R
import com.example.sunnyweather.base.binding.inflate
import com.example.sunnyweather.databinding.CommonTitleBarBinding
import com.example.sunnyweather.util.UtilGlide
import com.example.sunnyweather.util.UtilOther
import com.gyf.immersionbar.ImmersionBar

/**
 * 920新版标题栏
 */
class CommonTitleBar(content: Context, attrs: AttributeSet) : RelativeLayout(content, attrs) {
    private val binding = inflate<CommonTitleBarBinding>()

    // 是否自定义返回时机
    var isBackCustom = false

    init {
            initView(attrs)
            initData()
            initListener()
    }

    private fun initView(attrs: AttributeSet) {
        // 从布局文件中读出控件设置
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomWidget)
        val mTitleText = a.getString(R.styleable.MyCustomWidget_custom_title)
        val lightMode = a.getBoolean(R.styleable.MyCustomWidget_light_mode, false)
        val mBackgroundColor = a.getColor(R.styleable.MyCustomWidget_custom_background_color, resources.getColor(R.color.white))
        val customID1 = a.getResourceId(R.styleable.MyCustomWidget_custom_icon1, -1)
        val customID2 = a.getResourceId(R.styleable.MyCustomWidget_custom_icon2, -1)
        val lineShow = a.getBoolean(R.styleable.MyCustomWidget_line_show, false)
        isBackCustom = a.getBoolean(R.styleable.MyCustomWidget_back_custom, false)

        a.recycle()

        binding.run {
            // 标题
            tvCommonTitle.text = mTitleText
            // 背景色
            rlTitleBar.setBackgroundColor(mBackgroundColor)
            ivLine.visibility = if (lineShow) VISIBLE else GONE

            // 白色模式
            if (lightMode){
                tvCommonTitle.setTextColor(resources.getColor(R.color.white))
                ivBackButton.setImageResource(R.drawable.ic_titlebar_back_white)
            }
            // 自定义按钮1
            if (customID1 != -1) {
                ivCusButton1.setImageResource(customID1)
            }

            // 自定义按钮2
            if (customID2 != -1) {
                ivCusButton2.setImageResource(customID2)
                ivCusButton2.visibility = View.VISIBLE
            }

            if (!isInEditMode){
                // 沉浸式处理
                ImmersionBar.with(context as Activity).statusBarDarkFont(true).init()
                //UtilView.setStatusPadding(rlTitleBar)
            }
        }
    }

    private fun initListener() {
        binding.run {
            ivBackButton.setOnClickListener {
                if(::onBackClickListener.isInitialized){
                    onBackClickListener()
                }

                if (!isBackCustom){
                    val activity = context as Activity
                    UtilOther.closeIMS(activity)
                    activity.finish()
                }
            }

            ivCusButton1.setOnClickListener {
                if(::onCusBtn1ClickListener.isInitialized){
                    onCusBtn1ClickListener()
                }
            }
            ivCusButton2.setOnClickListener {
                if(::onCusBtn2ClickListener.isInitialized){
                    onCusBtn2ClickListener()
                }
            }
            ivCusButton3.setOnClickListener {
                if(::onCusBtn3ClickListener.isInitialized){
                    onCusBtn3ClickListener()
                }
            }
        }
    }

    // 返回回调
    lateinit var onBackClickListener: () -> Unit
    fun setBackClickListener(listener: () -> Unit) {
        this.onBackClickListener = listener
    }
    // 自定义按钮1回调
    lateinit var onCusBtn1ClickListener: () -> Unit
    // 自定义按钮2回调
    lateinit var onCusBtn2ClickListener: () -> Unit
    // 自定义按钮3回调
    lateinit var onCusBtn3ClickListener: () -> Unit

    fun initData() {}

    /**
     * 设置标题文本
     * @param text
     */
    fun setTitle(text: String) {
        binding.tvCommonTitle.text = text
    }

    /**
     * 设置按钮1图片url, 分享锁死按钮1
     * @param url
     */
    fun setCusBtn1(url: String, contentDescription : String) {
        UtilGlide.showSimpleImage(url, binding.ivCusButton1)
        binding.ivCusButton1.contentDescription = contentDescription
    }

    /**
     * 设置按钮1图片res
     * @param res
     */
    fun setCusBtnRes1(res: Int, contentDescription : String) {
        binding.ivCusButton1.setImageResource(res)
        binding.ivCusButton1.contentDescription = contentDescription
    }

    /**
     * 设置自定义按钮1是否可见
     * @param visible
     */
    fun setCusBtn1Visibility(visible: Boolean) {
        binding.ivCusButton1.visibility = if (visible) VISIBLE else INVISIBLE
        binding.ivCusTag1.visibility = if (visible) VISIBLE else INVISIBLE
    }


    /**
     * 设置按钮2图片url
     * @param url
     */
    fun setCusBtn2(url: String, contentDescription : String) {
        UtilGlide.showSimpleImage(url, binding.ivCusButton2)
        binding.ivCusButton2.visibility = View.VISIBLE
        binding.ivCusButton2.contentDescription = contentDescription
    }

    /**
     * 设置自定义按钮2是否可见
     * @param visible
     */
    fun setCusBtn2Visibility(visible: Boolean) {
        binding.ivCusButton2.visibility = if (visible) VISIBLE else INVISIBLE
        binding.ivCusTag2.visibility = if (visible) VISIBLE else INVISIBLE
    }

    /**
     * 设置自定义按钮3是否可见
     * @param visible
     */
    fun setCusBtn3Visibility(visible: Boolean) {
        binding.ivCusButton3.visibility = if (visible) VISIBLE else INVISIBLE
        binding.ivCusTag3.visibility = if (visible) VISIBLE else INVISIBLE
    }

    /**
     * 设置按钮3图片res
     * @param res
     */
    fun setCusBtnRes3(res: Int, contentDescription : String) {
        binding.ivCusButton3.setImageResource(res)
        binding.ivCusButton3.contentDescription = contentDescription
        binding.ivCusButton3.visibility = View.VISIBLE
    }


 /*   *//**
     * 设置原生和h5分享图标tag
     * @param view
     *//*
    fun setShareTag() {
        NetParamsUtil.qryTitleConfigurationData?.run {
            if (MyApplication.mDataCache.isLoginYet && commonShaConfig != null) {
                if (!UtilText.isEmptyOrNull(commonShaConfig.iconUrl)) {
                    UtilGlide.showSimpleImage(commonShaConfig.iconUrl, ivCusTag1)
                }
            }
        }
    }*/


   /* *//**
     * 设置h5标题栏默认值
     *//*
    fun initDefaultTitleBar(mIsShare: Boolean){
        NetParamsUtil.qryTitleConfigurationData?.run {
            if (mIsShare) {
                if (moreListPopupBean != null || clientSharingDataBean != null){
                    // 填充按钮1位置moreListPopupBean 优先级高于 clientSharingData
                    moreListPopupBean?.run {
                        if (UtilText.isEmptyOrNull(icon)) {
                            setCusBtnRes1(R.drawable.ic_titlebar_more_black, "更多")
                        } else {
                            setCusBtn1(icon, "更多")
                        }
                    }?: run {
                        clientSharingDataBean.run {
                            if (UtilText.isEmptyOrNull(clientSharingDataBean.titleIcon)) {
                                setCusBtnRes1(R.drawable.ic_titlebar_share_black, "分享")
                            } else {
                                setCusBtn1(clientSharingDataBean.titleIcon, "分享")
                            }
                            setShareTag()
                        }
                    }

                    // 右侧按钮2位置取iconDataList第一位填充
                    if (!iconDataList.isNullOrEmpty()){
                        val iconDataBean = iconDataList[0]
                        setCusBtn2(iconDataBean.iconUrl, iconDataBean.title)
                        onCusBtn2ClickListener = {
                            val item = CommonLinkItem()
                            item.linkType = iconDataBean.linkType
                            item.link = iconDataBean.link
                            item.goTarget(context)
                        }
                    }
                }else{
                    setCusBtnRes1(R.drawable.ic_titlebar_share_black, "分享")
                }
            }else{
                if (!iconDataList.isNullOrEmpty()){
                    val iconDataBean = iconDataList[0]
                    setCusBtn1(iconDataBean.iconUrl, iconDataBean.title)
                    onCusBtn1ClickListener = {
                        val item = CommonLinkItem()
                        item.linkType = iconDataBean.linkType
                        item.link = iconDataBean.link
                        item.goTarget(context)
                    }
                    if (iconDataList.size > 1) {
                        val iconDataBean2 = iconDataList[1]
                        setCusBtn2(iconDataBean2.iconUrl, iconDataBean2.title)
                        onCusBtn2ClickListener = {
                            val item = CommonLinkItem()
                            item.linkType = iconDataBean2.linkType
                            item.link = iconDataBean2.link
                            item.goTarget(context)
                        }
                    }
                }
            }
        }
    }*/
}