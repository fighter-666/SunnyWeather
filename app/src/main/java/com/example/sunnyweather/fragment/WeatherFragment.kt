package com.example.sunnyweather.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sunnyweather.MainActivity
import com.example.sunnyweather.R
import com.example.sunnyweather.adapter.ComponentsAdapter
import com.example.sunnyweather.databinding.FragmentWeatherBinding
import com.example.sunnyweather.data.Piggy
import com.gyf.immersionbar.ImmersionBar

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.rvComponents)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()

        //创建了一个包含多个 Piggy 对象的可变列表 piggies，
        // 每个 Piggy 对象都包含了一个图片资源 ID 和一个帮助文本
        val piggies = listOf(
            Pair(R.mipmap.ic_launcher, "RechargePageActivity"),
            Pair(R.mipmap.ic_launcher, "VariousTextviewActivity"),
            Pair(R.mipmap.ic_launcher, "QWUIDrawableHelper"),
            Pair(R.mipmap.ic_launcher, "FeedStreamHomePageActivity"),
            Pair(R.mipmap.ic_launcher, "WebViewActivity"),
            Pair(R.mipmap.ic_launcher, "CustomActivity"),
            Pair(R.mipmap.ic_launcher, "CommonControlActivity"),
            Pair(R.mipmap.ic_launcher, "ViewModelTestActivity"),
            Pair(R.mipmap.ic_launcher, "LiveDataActivity"),
            Pair(R.mipmap.ic_launcher, "DataBindingActivity"),
            Pair(R.mipmap.ic_launcher, "ScoreActivity"),
            Pair(R.mipmap.ic_launcher, "SharedPreferencesActivity"),
            Pair(R.mipmap.ic_launcher, "PhoneActivity"),
            Pair(R.mipmap.ic_launcher, "BannerActivity"),
            Pair(R.mipmap.ic_launcher, "RoomActivity"),
            Pair(R.mipmap.ic_launcher, "Room2Activity"),
            Pair(R.mipmap.ic_launcher, "OkhttpActivity"),
            Pair(R.mipmap.ic_launcher, "ServiceActivity"),
            Pair(R.mipmap.ic_launcher, "BroadcastReceiverActivity"),
            Pair(R.mipmap.ic_launcher, "AdvertisingActivity"),
            Pair(R.mipmap.ic_launcher, "AsyncServiceActivity"),
            Pair(R.mipmap.ic_launcher, "StudentActivity"),
            Pair(R.mipmap.ic_launcher, "UserActivity"),
            Pair(R.mipmap.ic_launcher, "BaseApplicationActivity"),
            Pair(R.mipmap.ic_launcher, "FirstRoomActivity"),
            Pair(R.mipmap.ic_launcher, "HotListActivity"),
            Pair(R.mipmap.ic_launcher, "ViewpageActivity"),
            Pair(R.mipmap.ic_launcher, "TreeListActivity"),
            Pair(R.mipmap.ic_launcher, "DataUsageActivity"),
            Pair(R.mipmap.ic_launcher, "SQLiteActivity"),
            Pair(R.mipmap.ic_launcher, "RecyclerViewActivity"),
        ).map { (imageResId, helperText) ->
            Piggy(imageResId, helperText)
        }.toMutableList()

        //创建适配器
        val myAdapter = ComponentsAdapter(R.layout.adapter_components, piggies)

        //设置布局管理器和给recyclerView设置适配器
        binding.rvComponents.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = myAdapter
        }

        myAdapter.setOnItemClickListener { _, view, position ->
            Toast.makeText(context, "onItemClick $position", Toast.LENGTH_SHORT).show()
            when (position) {
                0 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

                1 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

                3 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

                4 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

                5 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

                6 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

                7 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

                8 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

                9 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

            /*    10 -> {*//*
            *//*        val intent = Intent(context, ScoreActivity::class.java)*//*
            *//*        startActivity(intent)*//*
            *//*    }*//*

                11 -> {
                    val intent = Intent(context, SharedPreferencesActivity::class.java)
                    startActivity(intent)
                }

                12 -> {
                    val intent = Intent(context, PhoneActivity::class.java)
                    startActivity(intent)
                }

                13 -> {
                    val intent = Intent(context, BannerActivity::class.java)
                    startActivity(intent)
                }

                14 -> {
                    val intent = Intent(context, RoomActivity::class.java)
                    startActivity(intent)
                }

                15 -> {
                    val intent = Intent(context, Room2Activity::class.java)
                    startActivity(intent)
                }

                16 -> {
                    val intent = Intent(context, OkhttpActivity::class.java)
                    startActivity(intent)
                }

                17 -> {
                    val intent = Intent(context, ServiceActivity::class.java)
                    startActivity(intent)
                }

                18 -> {
                    val intent = Intent(context, BroadcastReceiverActivity::class.java)
                    startActivity(intent)
                }

                19 -> {
                    val intent = Intent(context, AdvertisingActivity::class.java)
                    startActivity(intent)
                }
                20 -> {
                    val intent = Intent(context, AsyncServiceActivity::class.java)
                    startActivity(intent)
                }
                21 -> {
                    val intent = Intent(context, StudentActivity::class.java)
                    startActivity(intent)
                }
                22 -> {
                    val intent = Intent(context, UserActivity::class.java)
                    startActivity(intent)
                }
                23 -> {
                    val intent = Intent(context, BaseApplication::class.java)
                    startActivity(intent)
                }
                24 -> {
                    val intent = Intent(context, FirstRoomActivity::class.java)
                    startActivity(intent)
                }
                25 -> {
                    val intent = Intent(context, HotListActivity::class.java)
                    startActivity(intent)
                }
                26 -> {
                    val intent = Intent(context, ViewpageActivity::class.java)
                    startActivity(intent)
                }
                27 -> {
                    val intent = Intent(context, TreeListActivity::class.java)
                    startActivity(intent)
                }
                28 -> {
                    val intent = Intent(context, DataUsageActivity::class.java)
                    startActivity(intent)
                }
                29 -> {
                    val intent = Intent(context, SQLiteActivity::class.java)
                    startActivity(intent)
                }
                30 -> {
                    val intent = Intent(context, RecyclerViewActivity::class.java)
                    startActivity(intent)
                }
                // 其他Piggy对象的处理逻辑...*/

                else -> {
                    // 默认的页面跳转逻辑
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

}


