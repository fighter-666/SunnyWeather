package com.example.sunnyweather.adapter

import android.graphics.Color
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ct.base.ext.dp
import com.example.sunnyweather.R
import com.example.sunnyweather.data.GetFeedListData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

class ContentAreaListAdapter(data: MutableList<GetFeedListData.FeedListBean.ContentAreaListBean>) :
    BaseMultiItemQuickAdapter<GetFeedListData.FeedListBean.ContentAreaListBean, BaseViewHolder>(data) {
    private var tvPriceIntegerWidth: Int = 0
    private var tvPriceDecimalWidth: Int = 0
    private var tvOriginalPriceWidth: Int = 0
    private var tvIsShowPriceUnitWidth: Int = 0

    init {
        addItemType(
            GetFeedListData.CONTENTAREA_TYPE.MAIN_TITLE.toInt(),
            R.layout.item_feed_content_main_title
        )
        addItemType(
            GetFeedListData.CONTENTAREA_TYPE.SALE_TIP.toInt(),
            R.layout.item_feed_content_sale_tip
        )
        addItemType(
            GetFeedListData.CONTENTAREA_TYPE.PRICE.toInt(),
            R.layout.item_feed_content_price
        )
        addItemType(
            GetFeedListData.CONTENTAREA_TYPE.LOCATION.toInt(),
            R.layout.item_feed_content_location
        )
        addItemType(
            GetFeedListData.CONTENTAREA_TYPE.COUNTDOWN.toInt(),
            R.layout.item_feed_content_countdown
        )
        addItemType(GetFeedListData.CONTENTAREA_TYPE.NUM.toInt(), R.layout.item_feed_content_num)
        addItemType(
            GetFeedListData.CONTENTAREA_TYPE.PIC_ONE.toInt(),
            R.layout.item_feed_content_pic_one
        )
        addItemType(
            GetFeedListData.CONTENTAREA_TYPE.PIC_TWO.toInt(),
            R.layout.item_feed_content_pic_two
        )
        addItemType(
            GetFeedListData.CONTENTAREA_TYPE.COMPLETION.toInt(),
            R.layout.item_feed_content_completion
        )
    }

    override fun convert(
        holder: BaseViewHolder,
        item: GetFeedListData.FeedListBean.ContentAreaListBean,
    ) {
        holder.run {
            when (holder.itemViewType) {
                //2：随销条、
                GetFeedListData.CONTENTAREA_TYPE.SALE_TIP.toInt() -> {
                    val rvSaleTipList = getView<RecyclerView>(R.id.rvSaleTipList)
                    if (item.saleTipList != null) {
                        //创建适配器
                        val myAdapter = SaleTipListAdapter(
                            R.layout.adapter_recharge_contentarealist_saletiplist,
                            item.saleTipList
                        )

                        rvSaleTipList.visibility=View.VISIBLE
                        //设置布局管理器和给 recyclerView设置适配器
                        rvSaleTipList.apply {
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            adapter = myAdapter
                        }
                        //saleTipList : 随销条
                    } else {
                        rvSaleTipList.visibility=View.GONE
                    }


                }

                //3：价格
                3 -> {
                    val clIsShowPrice = getView<ConstraintLayout>(R.id.clIsShowPrice)
                    val tvPriceInteger = getView<TextView>(R.id.tvPriceInteger)
                    val tvPriceDecimal = getView<TextView>(R.id.tvPriceDecimal)
                    val tvIsShowPriceUnit = getView<TextView>(R.id.tvIsShowPriceUnit)
                    val tvOriginalPrice = getView<TextView>(R.id.tvOriginalPrice)
                    //price : 价格
                    if (item.price != null) {
                        clIsShowPrice.visibility = View.VISIBLE
                        //售价字体颜色通过priceColor控制，默认颜色为#red_ea5858
                        tvPriceInteger.text = item.price.priceInteger
                        if (item.price.priceColor != "") {
                            // 获取颜色值
                            val color = item.price.priceColor
                            try {
                                tvPriceInteger.setTextColor(Color.parseColor(color))
                            } catch (e: Exception) {
                                e.printStackTrace()
                                tvPriceInteger.setTextColor(mContext.resources.getColor(R.color.red_ea5858))
                            }
                            try {
                                tvPriceDecimal.setTextColor(Color.parseColor(color))
                            } catch (e: Exception) {
                                e.printStackTrace()
                                tvPriceDecimal.setTextColor(mContext.resources.getColor(R.color.red_ea5858))
                            }
                            try {
                                tvIsShowPriceUnit.setTextColor(Color.parseColor(color))
                            } catch (e: Exception) {
                                e.printStackTrace()
                                tvIsShowPriceUnit.setTextColor(mContext.resources.getColor(R.color.red_ea5858))
                            }

                        }

                        //售价字体颜色通过priceColor控制，默认颜色为#red_ea5858
                        tvPriceDecimal.text = item.price.priceDecimal

                        //原价originalPrice字段控制（没有不显示），字体颜色通过originalPriceColor控制，默认为#999999
                        if (item.price.originalPriceColor != "") {
                            val color2 = item.price.originalPriceColor
                            try {
                                tvOriginalPrice.setTextColor(Color.parseColor(color2))
                            } catch (e: Exception) {
                                e.printStackTrace()
                                tvOriginalPrice.setTextColor(mContext.resources.getColor(R.color.gray_999999))
                            }
                        }

                        tvPriceInteger.visibility = View.VISIBLE
                        tvPriceDecimal.visibility = View.VISIBLE
                        tvOriginalPrice.visibility = View.VISIBLE
                        // "售价是否显示人民币符号：0：否1：是",
                        if (item.price.isShowPriceUnit == "1") {
                            tvIsShowPriceUnit.visibility = View.VISIBLE
                        }

                        //"isOriginalPriceLine": "原价是否划横线：0：否1：是"
                        if (item.price.isOriginalPriceLine == "1") {
                            //为文字设置删除线
                            val spannableString4 =
                                SpannableString(item.price.originalPrice)
                            val strikethroughSpan = StrikethroughSpan()
                            spannableString4.setSpan(
                                strikethroughSpan,
                                0,
                                spannableString4.length,
                                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                            )
                            tvOriginalPrice.text = spannableString4
                        } else {
                            tvOriginalPrice.text = item.price.originalPrice
                        }

                        tvPriceInteger.post {
                            run {
                                tvPriceDecimal.post {
                                    run {
                                        tvOriginalPrice.post {
                                            run {
                                                tvIsShowPriceUnit.post {
                                                    run {
                                                        tvIsShowPriceUnitWidth =
                                                            tvIsShowPriceUnit.width
                                                        tvOriginalPriceWidth =
                                                            tvOriginalPrice.width
                                                        tvPriceDecimalWidth =
                                                            tvPriceDecimal.width
                                                        tvPriceIntegerWidth =
                                                            tvPriceInteger.width
                                                        //当售价和原价过长出现交叉时仅展示原价
                                                        if (item.price.priceInteger != "" && item.price.originalPrice != "") {
                                                            val totalWidth =
                                                                tvIsShowPriceUnitWidth + tvOriginalPriceWidth + tvPriceDecimalWidth + tvPriceIntegerWidth + 20.dp
                                                            if (totalWidth > holder.itemView.measuredWidth) {
                                                                tvOriginalPrice.visibility =
                                                                    View.GONE

                                                            }
                                                        } else {
                                                            tvOriginalPrice.maxLines = 1
                                                            tvOriginalPrice.maxWidth =
                                                                holder.itemView.measuredWidth - 20.dp
                                                            tvOriginalPrice.isSingleLine = true
                                                            tvOriginalPrice.ellipsize =
                                                                TextUtils.TruncateAt.END
                                                        }


                                                        //当仅有原价或售价且超过一行宽度时右侧…展示
                                                        val totalWidth2 =
                                                            tvIsShowPriceUnitWidth + tvPriceDecimalWidth + tvPriceIntegerWidth + 20.dp
                                                        if (totalWidth2 >= holder.itemView.measuredWidth) {
                                                            tvPriceInteger.maxLines = 1
                                                            tvPriceInteger.maxWidth =
                                                                holder.itemView.measuredWidth - tvIsShowPriceUnitWidth - 20.dp
                                                            tvPriceInteger.isSingleLine = true
                                                            tvPriceInteger.ellipsize =
                                                                TextUtils.TruncateAt.END
                                                            tvPriceDecimal.visibility =
                                                                View.GONE
                                                            tvOriginalPrice.visibility =
                                                                View.GONE
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        clIsShowPrice.visibility = View.GONE
                        tvPriceInteger.visibility = View.GONE
                        tvPriceDecimal.visibility = View.GONE
                        tvOriginalPrice.visibility = View.GONE
                    }
                }
                //4：位置
                4 -> {
                    val tvLocationTitle = getView<TextView>(R.id.tvLocationTitle)
                    val ivLocationIcon = getView<ImageView>(R.id.ivLocationIcon)
                    if (item.location != null) {
                        tvLocationTitle.text = item.location.title
                        ivLocationIcon.visibility = View.VISIBLE
                        tvLocationTitle.visibility = View.VISIBLE
                    }else{
                        ivLocationIcon.visibility = View.GONE
                        tvLocationTitle.visibility = View.GONE
                    }

                }

                //5：倒计时
                5 -> {
                    val tvCountDown = getView<TextView>(R.id.tvCountDown)
                    val tvCountDownBackground = getView<TextView>(R.id.tvCountDown)
                    if (item.countDown != null) {
                        // 获取倒计时数据结构
                        val countDownBean = item.countDown // 假设从接口获取到倒计时数据结构

                        if (shouldDisplayCount(countDownBean)) {
                            // 判断是显示距开始还是距结束
                            val isCountingDownToStart =
                                shouldDisplayCountdownToStart(countDownBean)

                            // 计算距离开始或结束的剩余时间
                            val remainingTimeInMillis =
                                getRemainingTimeInMillis(countDownBean, isCountingDownToStart)

                            // 显示倒计时信息
                            tvCountDownBackground.visibility = View.VISIBLE
                            tvCountDown.visibility = View.VISIBLE
                            //创建了一个CountDownTimer对象，并设置了倒计时的逻辑
                            val countDownTimer =
                                object : CountDownTimer(remainingTimeInMillis, 1000) {
                                    //实现onTick方法：覆盖CountDownTimer类的onTick方法。在每个时间间隔（这里是1000毫秒）内，该方法会被调用一次
                                    override fun onTick(millisUntilFinished: Long) {
                                        //更新倒计时文本
                                        var countdownText = formatCountdownText(
                                            millisUntilFinished,
                                            isCountingDownToStart
                                        )
                                        if (isCountingDownToStart) {
                                            countdownText = "距开始  $countdownText"
                                            tvCountDown.setBackgroundResource(R.drawable.shape_recharge_count_down_start)
                                            tvCountDownBackground.setBackgroundResource(
                                                R.drawable.shape_recharge_count_down_background_start
                                            )
                                            val colorSpan =
                                                ForegroundColorSpan(Color.parseColor("#f5a937"))
                                            //设置文字的时间颜色为橘黄色
                                            val spannableString = SpannableString(countdownText)
                                            spannableString.setSpan(
                                                colorSpan,
                                                3,
                                                spannableString.length,
                                                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                                            )
                                            //设置文字的前景色为白色色
                                            val colorSpan2 =
                                                ForegroundColorSpan(Color.parseColor("#ffffff"))
                                            spannableString.setSpan(
                                                colorSpan2,
                                                0,
                                                3,
                                                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                                            )
                                            tvCountDown.text = spannableString
                                        } else {
                                            countdownText = "距结束  $countdownText"
                                            tvCountDown.setBackgroundResource(R.drawable.shape_recharge_count_down)
                                            //设置文字的前景色为白色
                                            val spannableString = SpannableString(countdownText)
                                            val colorSpan =
                                                ForegroundColorSpan(Color.parseColor("#ffffff"))
                                            spannableString.setSpan(
                                                colorSpan,
                                                0,
                                                3,
                                                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                                            )
                                            tvCountDown.text = spannableString
                                            tvCountDown.text = spannableString
                                        }
                                    }

                                    override fun onFinish() {
                                        // 倒计时结束
                                        tvCountDown.visibility = View.GONE
                                        tvCountDownBackground.visibility = View.GONE
                                    }
                                }
                            // 启动倒计时
                            countDownTimer.start()
                        } else {
                            tvCountDownBackground.visibility = View.GONE
                            tvCountDown.visibility = View.GONE
                        }
                    } else {

                    }
                }


                //6：人数
                6 -> {
                    val tvNumText = getView<TextView>(R.id.tvNumText)
                    if (item.numText != null) {
                        tvNumText.text = item.numText
                        tvNumText.visibility = View.VISIBLE
                        tvNumText.ellipsize =
                            TextUtils.TruncateAt.END
                    } else {
                        tvNumText.visibility = View.GONE
                    }
                }

                //7：配图：一行一个
                7 -> {
                    val rvPicList = getView<RecyclerView>(R.id.rvPicList)
                    if (item.picList != null) {
                        val rechargeAdapter = ContentAreaListPicListAdapter(
                            R.layout.adapter_recharge_contentarealist_piclist,
                            item.picList
                        )
                        rvPicList.visibility=View.VISIBLE

                        //设置布局管理器和给recyclerView 设置设配器
                        rvPicList.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = rechargeAdapter
                        }
                    }else{
                        rvPicList.visibility=View.GONE
                    }


                }

                //8：配图2：一行两个
                8 -> {
                    val rvPicListDouble = getView<RecyclerView>(R.id.rvPicListDouble)
                    if (item.picList != null) {
                        val myAdapter = ContentAreaListGridAdapter(
                            R.layout.adapter_recharge_contentarealist_piclist_double,
                            item.picList
                        )

                        rvPicListDouble.visibility= View.VISIBLE
                        //设置布局管理器和给recyclerView设置适配器
                        rvPicListDouble.apply {
                            layoutManager = GridLayoutManager(context, 2)
                            adapter = myAdapter
                        }
                    }else{
                        rvPicListDouble.visibility= View.GONE
                    }

                }
                //9：末尾卡片按钮列表
                9 -> {
                    val tvNullTitleFirst = getView<TextView>(R.id.tvNullTitleFirst)
                    if (item.completionInfo != null) {
                        tvNullTitleFirst.text = item.completionInfo.title
                        tvNullTitleFirst.visibility = View.VISIBLE
                    }else{
                        tvNullTitleFirst.visibility = View.GONE
                    }


                }

                //1.标题
                else -> {
                    val tvMainTitleTitle = getView<TextView>(R.id.tvMainTitle)
                    if (item.mainTitle != null) {
                        //mainTitle : 主标题
                        if (item.mainTitle.type == "1") {
                            tvMainTitleTitle.maxLines = 1
                        } else {
                            tvMainTitleTitle.maxLines = 2
                        }
                        if (item.mainTitle.color != "") {
                            tvMainTitleTitle.setTextColor(
                                Color.parseColor(item.mainTitle.color)
                            )
                        }
                        tvMainTitleTitle.ellipsize =
                            TextUtils.TruncateAt.END
                        tvMainTitleTitle.text = item.mainTitle.title
                        tvMainTitleTitle.visibility = View.VISIBLE
                    } else {
                        tvMainTitleTitle.visibility = View.GONE
                    }
                }
            }
        }
    }

    //// 判断是显示距开始还是距结束
    private fun shouldDisplayCountdownToStart(countDownBean: GetFeedListData.FeedListBean.ContentAreaListBean.CountDownBean): Boolean {
        //获取当前时间
        val currentTime = getCurrentTime()
        //验证开始时间和结束时间的有效性
        val isValidStartTime = isValidDateTime(countDownBean.startTime)
        val isValidEndTime = isValidDateTime(countDownBean.endTime)

        if (!isValidStartTime || !isValidEndTime) {
            return false // 默认按不显示倒计时区域处理
        }

        return currentTime < countDownBean.startTime
    }

    //// 判断是显示距开始还是距结束
    private fun shouldDisplayCount(countDownBean: GetFeedListData.FeedListBean.ContentAreaListBean.CountDownBean): Boolean {
        //获取当前时间
        val currentTime = getCurrentTime()




        if (countDownBean.startTime == null || countDownBean.endTime == null) {
            return false // 默认按不显示倒计时区域处理
        } else {
            //验证开始时间和结束时间的有效性
            val isValidStartTime = isValidDateTime(countDownBean.startTime)
            val isValidEndTime = isValidDateTime(countDownBean.endTime)

            if (!isValidStartTime || !isValidEndTime) {
                return false // 默认按不显示倒计时区域处理
            } else {
                if (countDownBean.startTime > countDownBean.endTime) {
                    return false // 默认按不显示倒计时区域处理
                }
            }
        }

        return currentTime < countDownBean.endTime
    }

    //获取当前时间并以指定的格式返回时间字符串。
    private fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    //检查给定的日期时间字符串是否是有效的
    private fun isValidDateTime(dateTime: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        //将isLenient属性设置为false，表示日期时间解析过程中严格按照指定的格式进行匹配
        dateFormat.isLenient = false
        return try {
            dateFormat.parse(dateTime)
            true
        } catch (e: Exception) {
            false
        }
    }

    //计算剩余时间（以毫秒为单位）
    private fun getRemainingTimeInMillis(
        countDownBean: GetFeedListData.FeedListBean.ContentAreaListBean.CountDownBean,
        isCountingDownToStart: Boolean,
    ): Long {
        //创建日期格式对象，使用默认的语言环境
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        //获取当前时间
        val currentTime = getCurrentTime()
        //确定目标时间
        val targetTime =
            if (isCountingDownToStart) countDownBean.startTime else countDownBean.endTime
        //计算剩余时间：使用日期格式化对象将目标时间和当前时间解析为Date对象，并通过调用time方法获取它们的时间戳（以毫秒为单位）
        return abs(
            (dateFormat.parse(targetTime)?.time ?: 0) - (dateFormat.parse(currentTime)?.time
                ?: 0)
        )
    }

    //格式化倒计时文本，将给定的剩余时间（以毫秒为单位）转换为可读的倒计时字符串
    private fun formatCountdownText(
        remainingTimeInMillis: Long,
        isCountingDownToStart: Boolean,
    ): String {
        //取整
        val days = remainingTimeInMillis / (24 * 60 * 60 * 1000)
        //取余完，再取整
        val hours = (remainingTimeInMillis % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000)
        val minutes = (remainingTimeInMillis % (60 * 60 * 1000)) / (60 * 1000)
        val seconds = (remainingTimeInMillis % (60 * 1000)) / 1000

        //计算得到的天数，判断是否大于0
        val daysText = if (days > 0) "$days 天" else ""
        //使用String.format方法将小时、分钟和秒格式化为两位数的字符串，例如"01:05:30"
        val timeText = String.format("%02d:%02d:%02d", hours, minutes, seconds)

        //根据isCountingDownToStart参数的值，决定最终返回的倒计时文本
        return if (isCountingDownToStart) {
            "$daysText$timeText"
        } else {
            "$daysText$timeText"
        }
    }

}

