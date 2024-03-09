package com.example.sunnyweather.pran

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.content.ContextCompat
import com.example.sunnyweather.R
import com.example.sunnyweather.databinding.TitleBarPRanBinding


/**
 * 说明：
 *
 * @作者 liujj
 * @创建时间 2019/7/26 15:36
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */


class TitleBar(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private var mOnClickListener: OnClickListener? = null
    private var mOnTitleBarBackClickListener: OnTitleBarBackClickListener? = null
    private lateinit var mDrawerArrow: DrawerArrowDrawable
    private var mOnCheckedListener: OnCheckedListener? = null

    private val bind: TitleBarPRanBinding? by lazy {
        TitleBarPRanBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        val left = typedArray.getResourceId(R.styleable.TitleBar_ivTitleBarLeft, 0)
        val title = typedArray.getString(R.styleable.TitleBar_tvTitleBarTitle)
        val titleColor = typedArray.getColor(R.styleable.TitleBar_tvTitleColor, 0)
        val tvTitleRightColor = typedArray.getColor(R.styleable.TitleBar_tvTitleRightColor, 0)
        val right = typedArray.getResourceId(R.styleable.TitleBar_ivTitleBarRight, 0)
        val tvTitleBarRightBarBackground =
            typedArray.getResourceId(R.styleable.TitleBar_tvTitleBarRightBarBackground, 0)
        val background = typedArray.getResourceId(R.styleable.TitleBar_rlTitleBarBackground, 0)
        val backgroundColor =
            typedArray.getResourceId(R.styleable.TitleBar_rlTitleBarBackgroundColor, 0)
        val rightTitle = typedArray.getString(R.styleable.TitleBar_tvTitleBarRight)
        val rightTitleDrawables =
            typedArray.getResourceId(R.styleable.TitleBar_tvTitleBarRightDrawables, 0)
        val ivMsg = typedArray.getBoolean(R.styleable.TitleBar_ivMsg, false)
        val ivName = typedArray.getBoolean(R.styleable.TitleBar_ivName, false)
        val ivTitle = typedArray.getBoolean(R.styleable.TitleBar_ivTitle, false)
        val ivMenu = typedArray.getBoolean(R.styleable.TitleBar_ivMenu, false)
        val btnQuestion = typedArray.getBoolean(R.styleable.TitleBar_btnQuestion, false)
        val ivClose = typedArray.getBoolean(R.styleable.TitleBar_ivClose, false)
        val titleBarHeight = typedArray.getDimension(R.styleable.TitleBar_titleBarHeight, 0f)
        typedArray.recycle()

        if (background != 0) {
            bind?.rlTitleBar?.background = ContextCompat.getDrawable(context, background)
        }

        if (titleColor != 0) {
            bind?.tvTitleBarTitle?.setTextColor(titleColor)
        }

        if (backgroundColor != 0) {
            bind?.rlTitleBar?.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
        }

        if (btnQuestion) {
            bind?.btnQuestion?.visibility = View.VISIBLE
        }
        if (ivClose) {
            bind?.ivClose?.visibility = View.VISIBLE
        }

        if (ivMsg) {
            bind?.ivNewMsg?.visibility = View.VISIBLE
        }

        if (ivName) {
            bind?.ivHomeName?.visibility = View.VISIBLE
        }

        if (ivTitle) {
            bind?.ivHomeTitle?.visibility = View.VISIBLE
        }

        if (ivMenu) {
            bind?.ivMenu?.visibility = View.VISIBLE
        }

        if (titleBarHeight > 0) {
            //动态设置状态栏高度
            val ivHead: ViewGroup.LayoutParams? = bind?.rlTitleBar?.layoutParams
            ivHead?.height = titleBarHeight.toInt()
            bind?.rlTitleBar?.layoutParams = ivHead
        }

        if (left != 0) {
            bind?.ivTitleBarLeft?.visibility = View.VISIBLE
            bind?.ivTitleBarLeft?.setImageResource(left)
        }

        if (title != null) {
            bind?.tvTitleBarTitle?.visibility = View.VISIBLE
            bind?.tvTitleBarTitle?.text = title
        }

        if (right != 0) {
            bind?.ivTitleBarRight?.visibility = View.VISIBLE
            bind?.ivTitleBarRight?.setImageResource(right)
        }

        if (rightTitle != null) {
            bind?.tvTitleBarRight?.visibility = View.VISIBLE
            bind?.tvTitleBarRight?.text = rightTitle
        }
        if (0 != rightTitleDrawables) {
            val drawable = ContextCompat.getDrawable(context, rightTitleDrawables)
            drawable?.setBounds(
                context.resources.getDimensionPixelSize(R.dimen.dp_5),
                context.resources.getDimensionPixelSize(R.dimen.dp_1),
                drawable.intrinsicWidth + context.resources.getDimensionPixelSize(R.dimen.dp_5),
                drawable.intrinsicHeight
            )
            bind?.tvTitleBarRight?.setCompoundDrawables(
                null,
                null,
                drawable,
                null
            )

        }
        if (tvTitleBarRightBarBackground != 0) {
            bind?.tvTitleBarRight?.setBackgroundResource(tvTitleBarRightBarBackground)
        }
        if (tvTitleRightColor != 0) {
            bind?.tvTitleBarRight?.setTextColor(tvTitleRightColor)
        }
        bind?.ivTitleBarLeft?.setOnClickListener {
            if (mOnTitleBarBackClickListener != null) {
                mOnTitleBarBackClickListener?.onBackClickListener()
            } else {
                val activity = context as Activity
                activity.finish()
            }
        }

        bind?.ivTitleBarRight?.setOnClickListener {
            mOnClickListener?.onClick(it)
        }

        bind?.tvTitleBarRight?.setOnClickListener {
            mOnClickListener?.onClick(it)
        }

        bind?.ivMenu?.setOnClickListener {
            mOnClickListener?.onClick(it)
        }
        bind?.ivClose?.setOnClickListener {
            mOnClickListener?.onClick(it)
        }

        initMenu(context)

    }

    private fun initMenu(context: Context) {
        mDrawerArrow = object : DrawerArrowDrawable(context) {
            fun isLayoutRtl(): Boolean {
                return false
            }
        }
        bind?.ivMenu?.setImageDrawable(mDrawerArrow)
    }

    fun getDrawerArrow(): DrawerArrowDrawable {
        return mDrawerArrow
    }

    override fun setOnClickListener(onClickListener: OnClickListener?) {
        mOnClickListener = onClickListener
    }

    fun setOnCheckedListener(onCheckedChangeListener: OnCheckedListener) {
        mOnCheckedListener = onCheckedChangeListener
    }

    fun setOnTitleBarBackClickListener(onTitleBarBackClickListener: OnTitleBarBackClickListener) {
        mOnTitleBarBackClickListener = onTitleBarBackClickListener
    }

    interface OnTitleBarBackClickListener {
        fun onBackClickListener()
    }

    interface OnCheckedListener {
        fun onCheckedListener(isChecked: Boolean, id: Int)
    }

    fun setTitle(title: String) {
        bind?.tvTitleBarTitle?.visibility = View.VISIBLE
        bind?.tvTitleBarTitle?.text = title
    }

    fun setTitleBarVisibility(isVisibility: Boolean) {
        if (isVisibility) bind?.tvTitleBarTitle?.visibility =
            View.VISIBLE else bind?.tvTitleBarTitle?.visibility = View.GONE
    }

    fun setIvBarRightVisibility(isVisibility: Boolean) {
        if (isVisibility) bind?.ivTitleBarRight?.visibility =
            View.VISIBLE else bind?.ivTitleBarRight?.visibility = View.GONE

    }

    fun setIvBarRightResource(resId: Int) {
        bind?.ivTitleBarRight?.setImageResource(resId)
        bind?.ivTitleBarRight?.visibility = View.VISIBLE
    }

    fun setRightTitle(title: String) {
        bind?.tvTitleBarRight?.visibility = View.VISIBLE
        bind?.tvTitleBarRight?.text = title
    }

    fun getRightTitle(): String {
        return bind?.tvTitleBarRight?.text.toString()
    }

    fun setMsgVisibility(b: Boolean) {
        if (b) bind?.ivNewMsg?.visibility = View.VISIBLE else bind?.ivNewMsg?.visibility = View.GONE
    }

}