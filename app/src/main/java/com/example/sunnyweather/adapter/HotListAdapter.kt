package com.example.sunnyweather.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.sunnyweather.R
import com.example.sunnyweather.data.GetHotListData


class HotListAdapter(data: MutableList<GetHotListData.HotListBean>) :
    BaseItemDraggableAdapter<GetHotListData.HotListBean, BaseViewHolder>(
        R.layout.item_drggble_view,
        data
    ) {

    override fun convert(holder: BaseViewHolder, item: GetHotListData.HotListBean) {
        when (item.type) {
            "1" -> {
                if (item.videoBean != null){
                    val tvTitle = holder.getView<TextView>(R.id.tvTitle)
                    val ivImage = holder.getView<ImageView>(R.id.ivImage)
                    val ivArrow = holder.getView<ImageView>(R.id.ivArrow)
                    tvTitle.visibility = View.VISIBLE
                    ivImage.visibility = View.VISIBLE
                    ivArrow.visibility = View.VISIBLE
                    tvTitle.text = item.topTitle.title
                    Glide.with(mContext)
                        .load(item.videoBean.imageUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                        //.apply(requestOptions
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivImage)
                }

            }

            "2" -> {
                if (item.advertisingList != null){
                    val cvAdvertisingList = holder.getView<RecyclerView>(R.id.cvAdvertisingList)
                    val tvTitle = holder.getView<TextView>(R.id.tvTitle)
                    val ivArrow = holder.getView<ImageView>(R.id.ivArrow)
                    tvTitle.visibility = View.VISIBLE
                    ivArrow.visibility = View.VISIBLE

                    tvTitle.text = item.topTitle.title
                    cvAdvertisingList.visibility = View.VISIBLE
                    val myAdapter = ManyImageHotAdapter(
                        R.layout.hot_many_image, item.advertisingList
                    )

                    //设置布局管理器和给recyclerView设置适配器
                    cvAdvertisingList.apply {
                        layoutManager = GridLayoutManager(context, 2)
                        adapter = myAdapter
                    }
                }

            }

            else -> {
                if (item.imageBean != null){
                    val ivIconUrl = holder.getView<ImageView>(R.id.ivIconUrl)
                    ivIconUrl.visibility = View.VISIBLE
                    Glide.with(mContext)
                        .load(item.imageBean.iconUrl)//使用 load() 方法传入 URL 字符串 imageUrl 来指定要加载的图片资源
                        //使用 transition() 方法可以设置过渡效果，例如交叉淡入淡出效果
                        .transition(DrawableTransitionOptions.withCrossFade())
                        //.transform(GranularRoundedCorners(20f, 20f, 0f, 0f))//四个角单独指定角度
                        //.apply(requestOptions
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivIconUrl)                }

            }
        }

    }

    fun updateDataSet(newData: MutableList<GetHotListData.HotListBean>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun getDataList(): MutableList<GetHotListData.HotListBean> {
        return data
    }

}


