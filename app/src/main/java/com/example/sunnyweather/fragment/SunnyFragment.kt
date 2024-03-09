package com.example.sunnyweather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sunnyweather.databinding.FragmentComponentsBinding
import com.gyf.immersionbar.ImmersionBar


class SunnyFragment : Fragment() {
    private lateinit var binding: FragmentComponentsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentComponentsBinding.inflate(inflater, container, false)
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .statusBarDarkFont(true) //状态栏字体是深色，不写默认为亮色
            .init()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  //创建了一个包含多个 Piggy 对象的可变列表 piggies，
        // 每个 Piggy 对象都包含了一个图片资源 ID 和一个帮助文本
        val piggies = listOf(
            Pair(R.drawable.ic_sunny, "天气"),
            Pair(R.drawable.ic_draw, "抽奖"),
            Pair(R.drawable.ic_personal_information, "个人信息"),
            Pair(R.drawable.ic_traffic_query, "油量查询"),
            Pair(R.drawable.ic_recommend, "热门推荐"),
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
            when (position) {
                0 -> {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

               1 -> {
                    val intent = Intent(context, TestActivity::class.java)
                    startActivity(intent)
                }

                        2 -> {
                            val intent = Intent(context, MyInformationActivity::class.java)
                            startActivity(intent)
                        }

                        3 -> {
                           val intent = Intent(context, DataUsageActivity::class.java)
                           startActivity(intent)
                       }

                        4 -> {
                             val intent = Intent(context, HotListActivity::class.java)
                             startActivity(intent)
                         }

                *//*           5 -> {
                              val intent = Intent(context, CustomActivity::class.java)
                              startActivity(intent)
                          }

                          6 -> {
                              val intent = Intent(context, CommonControlActivity::class.java)
                              startActivity(intent)
                          }

                          7 -> {
                              val intent = Intent(context, ViewModelTestActivity::class.java)
                              startActivity(intent)
                          }

                          8 -> {
                              val intent = Intent(context, LiveDataActivity::class.java)
                              startActivity(intent)
                          }

                          9 -> {
                              val intent = Intent(context, DataBindingActivity::class.java)
                              startActivity(intent)
                          }

                          10 -> {
                              val intent = Intent(context, ScoreActivity::class.java)
                              startActivity(intent)
                          }

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
                          }*//*
                // 其他Piggy对象的处理逻辑...

                else -> {
                    // 默认的页面跳转逻辑
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }*/

    }

}


