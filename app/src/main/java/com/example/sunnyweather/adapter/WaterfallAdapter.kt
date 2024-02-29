package com.example.sunnyweather.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterViewFlipper
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ct.base.ext.dp
import com.ct.base.ext.isEmptyOrNull
import com.example.sunnyweather.R
import com.example.sunnyweather.data.GetFeedListData
import com.example.sunnyweather.databinding.ActivityBannerBinding
import com.example.sunnyweather.util.UtilGlide
import com.example.sunnyweather.widget.GetTelephoneNumberManager
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WaterfallAdapter(data: MutableList<GetFeedListData.FeedListBean>) :
    BaseMultiItemQuickAdapter<GetFeedListData.FeedListBean, BaseViewHolder>(data) {
    init {
        addItemType(
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE,
            R.layout.widget_multiple_item_many_image
        )
        addItemType(
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.ONE_IMAGE, R.layout.widget_multiple_item_common
        )
        addItemType(
            GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL, R.layout.widget_multiple_item_null
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.ADVERTISE.toInt(), R.layout.activity_banner
        )
        addItemType(
            GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt(),
            R.layout.widget_multiple_item_recharge
        )
    }

    override fun convert(holder: BaseViewHolder, item: GetFeedListData.FeedListBean) {
        holder.run {
            when (holder.itemViewType) {
                GetFeedListData.FEED_ADAPTER_ITEM_TYPE.MANY_IMAGE -> {
                    val rvContentAreaList = getView<RecyclerView>(R.id.rvContentAreaList)
                    val rvPicAreaImageUrl = getView<RecyclerView>(R.id.rvPicAreaImageUrl)

                    //contentAreaList : 内容区域
                    if (!item.contentAreaList.isNullOrEmpty()) {
                        val rechargeAdapter = ContentAreaListAdapter(item.contentAreaList)

                        //设置布局管理器和给recyclerView 设置设配器
                        rvContentAreaList.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = rechargeAdapter
                            isNestedScrollingEnabled = false
                        }
                        rvContentAreaList.visibility = View.VISIBLE
                    } else {
                        rvContentAreaList.visibility = View.GONE
                    }

                    val myAdapter = ManyImageGridAdapter(
                        R.layout.adapter_recharge_many_image_grid, item.picArea.picList
                    )

                    //设置布局管理器和给recyclerView设置适配器
                    rvPicAreaImageUrl.apply {
                        layoutManager = GridLayoutManager(context, 2)
                        adapter = myAdapter
                        isNestedScrollingEnabled = false
                    }
                }

                GetFeedListData.FEED_ADAPTER_ITEM_TYPE.NULL -> {
                    // 处理空布局
                    val rvContentAreaList = getView<RecyclerView>(R.id.rvContentAreaList)
                    //contentAreaList : 内容区域
                    if (!item.contentAreaList.isNullOrEmpty()) {
                        rvContentAreaList.visibility = View.VISIBLE
                        val rechargeAdapter = ContentAreaListAdapter(item.contentAreaList)

                        //设置布局管理器和给recyclerView 设置设配器
                        rvContentAreaList.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = rechargeAdapter
                            isNestedScrollingEnabled = false
                        }
                    } else {
                        rvContentAreaList.visibility = View.GONE
                    }
                }

                GetFeedListData.FEED_LIST_ITEM_TYPE.ADVERTISE.toInt() -> {

                    // 处理广告布局
                    val binding = ActivityBannerBinding.bind(holder.itemView)
                    binding.banner.setAdapter(object :
                        BannerImageAdapter<GetFeedListData.FeedListBean.AdListBean>(item.adLists) {
                        override fun onBindView(
                            holder: BannerImageHolder,
                            data: GetFeedListData.FeedListBean.AdListBean,
                            position: Int,
                            size: Int,
                        ) {
                            CoroutineScope(Dispatchers.Main).launch {
                                Glide.with(holder.imageView).load(data.imageUrl)
                                    .error(R.drawable.ic_launcher_foreground).into(holder.imageView)
                            }
                        }
                    })
                    //设置圆角
                    binding.cvBanner.isNestedScrollingEnabled = false
                    binding.banner.isNestedScrollingEnabled = false
                    binding.banner.setBannerRound2(20f)
                    //设置轮播时间间隔
                    binding.banner.setLoopTime(5000)
                    binding.banner.indicator = CircleIndicator(mContext) // 设置指示器为圆圈样式
                    binding.banner.setIndicatorWidth(15, 15)

                }

                GetFeedListData.FEED_LIST_ITEM_TYPE.RECHARGE.toInt() -> {
                    holder.addOnClickListener(R.id.btnSelect)
                    val cvRecharge = getView<CardView>(R.id.cvRecharge)
                    val tvGetTelephoneNumber = getView<TextView>(R.id.tvGetTelephoneNumber)
                    val rlRecharge = getView<RecyclerView>(R.id.rlRecharge)
                    // 处理充值布局
                    cvRecharge.isNestedScrollingEnabled = false
                    GetTelephoneNumberManager.setGetTelephoneNumberListener(object :
                        GetTelephoneNumberManager.OnGetTelephoneNumberManager {
                        override fun onGetTelephoneNumber(number: String) {
                            if (number.replace(" ", "").length == 11) {
                                //消除空格，并且第四位到第七位用*代替
                                tvGetTelephoneNumber.text =
                                    hideCharactersFromIndex(number.replace(" ", ""))
                            } else {
                                tvGetTelephoneNumber.text = number
                                Toast.makeText(mContext, "请输入有效号码！", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    })

                    val rechargeAdapter =
                        RechargeAdapter(R.layout.adapter_recharge, item.quickRecharge.denominations)

                    //设置布局管理器和给recyclerView 设置设配器
                    rlRecharge.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = rechargeAdapter
                        isNestedScrollingEnabled = false
                    }
                }

                else -> {
                    val ivTopImage = holder.getView<ImageView>(R.id.ivTopImage)
                    val ivPicAreaImageUrl = holder.getView<ImageView>(R.id.ivPicAreaImageUrl)
                    val tvCommentList = holder.getView<AdapterViewFlipper>(R.id.tvCommentList)
                    val clCommentList = holder.getView<ConstraintLayout>(R.id.clCommentList)
                    val clContentAreaList = holder.getView<ConstraintLayout>(R.id.clContentAreaList)
                    val tvStockout = holder.getView<TextView>(R.id.tvStockout)
                    val rvContentAreaList = holder.getView<RecyclerView>(R.id.rvContentAreaList)
                    //顶部标签
                    if (!item.picArea.topImage.isEmptyOrNull()) {
                        ivTopImage.visibility = View.VISIBLE
                        UtilGlide.showImage(item.picArea.topImage, ivTopImage)
                    } else {
                        ivTopImage.visibility = View.GONE
                    }

                    //commentList : 评论列表
                    if (!item.picArea.commentList.isNullOrEmpty()) {
                        val stars: MutableList<String> = mutableListOf() // 创建空的可变列表
                        for (tab in item.picArea.commentList) {
                            stars.add(tab.title) // 将每个标题添加到列表中
                        }
                        val viewFlipperAdapter = ViewFlipperAdapter(mContext, stars)
                        tvCommentList.adapter = viewFlipperAdapter
                        tvCommentList.visibility = View.VISIBLE
                        clCommentList.visibility = View.VISIBLE
                    } else {
                        tvCommentList.visibility = View.GONE
                        clCommentList.visibility = View.GONE
                    }

                    //库存显示
                    if (!item.picArea.stock.isEmptyOrNull()) {
                        tvStockout.text = item.picArea.stock
                        tvStockout.visibility = View.VISIBLE
                    } else {
                        tvStockout.visibility = View.GONE
                    }
                    //比例
                    if (item.picArea.imageRatio == null) {
                        item.picArea.imageRatio = 1.0f.toString()
                    }

                    //卡片锁宽等比缩放（imageRatio用来计算高度）
                    val layoutParams =
                        ivPicAreaImageUrl.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams.dimensionRatio = item.picArea.imageRatio // 例如，设置宽高比为16:9
                    ivPicAreaImageUrl.layoutParams = layoutParams

                    //contentAreaList : 内容区域
                    if (!item.contentAreaList.isNullOrEmpty()) {
                        val rechargeAdapter = ContentAreaListAdapter(item.contentAreaList)

                        if (item.contentAreaList[0].type != "7" && item.contentAreaList[0].type != "8"
                            && item.contentAreaList[item.contentAreaList.size - 1].type != "7"
                            && item.contentAreaList[item.contentAreaList.size - 1].type != "8"
                        ) {
                            val lp =
                                clContentAreaList.layoutParams as ViewGroup.MarginLayoutParams
                            lp.topMargin = 5.dp
                            lp.bottomMargin = 5.dp
                            clContentAreaList.layoutParams = lp
                        }
                        rvContentAreaList.visibility = View.VISIBLE
                        clContentAreaList.visibility = View.VISIBLE

                        //设置布局管理器和给recyclerView 设置设配器
                        rvContentAreaList.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = rechargeAdapter
                            isNestedScrollingEnabled = false
                        }
                        UtilGlide.showImage(item.picArea.imageUrl, ivPicAreaImageUrl)
                    } else {
                        UtilGlide.showImage(item.picArea.imageUrl, ivPicAreaImageUrl)
                        rvContentAreaList.visibility = View.GONE
                        clContentAreaList.visibility = View.GONE
                    }
                }
            }
        }
    }

    //第四位到第七位用*代替
    private fun hideCharactersFromIndex(text: String): String {
        val length = text.length
        if (3 >= length) {
            return text
        }

        val hiddenText = StringBuilder(text)
        for (i in 3 until 3 + 4) {
            hiddenText.setCharAt(i, '*')
        }

        return hiddenText.toString()
    }
}


