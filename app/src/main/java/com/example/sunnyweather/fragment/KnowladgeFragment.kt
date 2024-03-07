package com.example.sunnyweather.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.base.ext.dp
import com.example.sunnyweather.R
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.activity.AnimeActivity
import com.example.sunnyweather.activity.BasicsActivity
import com.example.sunnyweather.activity.CommonControlActivity
import com.example.sunnyweather.activity.DataStoringActivity
import com.example.sunnyweather.activity.DataUsageActivity
import com.example.sunnyweather.activity.FourComponentsActivity
import com.example.sunnyweather.activity.HighTechActivity
import com.example.sunnyweather.activity.ImageLoadingActivity
import com.example.sunnyweather.activity.IntentActivity
import com.example.sunnyweather.activity.JavaActivity
import com.example.sunnyweather.activity.KotlinActivity
import com.example.sunnyweather.activity.MultimediaActivity
import com.example.sunnyweather.activity.UserInteractionActivity
import com.example.sunnyweather.activity.ViewActivity
import com.example.sunnyweather.activity.WebAccessActivity
import com.example.sunnyweather.adapter.MessageMarketingDecoration
import com.example.sunnyweather.adapter.RecommendAdapter
import com.example.sunnyweather.base.binding.BaseBindingFragment
import com.example.sunnyweather.data.RecommendData
import com.example.sunnyweather.databinding.FragmentRecommendBinding
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar


class KnowladgeFragment : BaseBindingFragment<FragmentRecommendBinding>() {
    private val settingList = ArrayList<Fragment>()
    private var  recommendAdapter = RecommendAdapter()
    override fun lazyInit() {
        //沉浸式处理
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            //.titleBar(binding.etSearch)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initData()

    }

    private fun initView() {

    }

    private fun initListener() {
        binding.run {
        }

    }

    private fun initData() {
        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            requireContext().assets.open("Recommend.json").bufferedReader()
                .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val data = gson.fromJson(json, RecommendData::class.java)

        binding.run {
            binding.rvSetting.layoutManager = LinearLayoutManager(requireContext())
            recommendAdapter = RecommendAdapter().apply { setNewData(data.MyRecommendList) }
            binding.rvSetting.adapter =  recommendAdapter

            binding.rvSetting.addItemDecoration(
                MessageMarketingDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    0.5.dp.toDouble(),
                    68.dp,
                    18.dp,
                    requireContext().resources.getColor(R.color.gray_eeeeee)
                )
            )
        }

        recommendAdapter.setOnItemClickListener { _, view, position ->
            when (position) {
                0 -> {
                    val intent = Intent(SunnyWeatherApplication.context, IntentActivity::class.java)
                    startActivity(intent)
                }

                1 -> {
                    val intent = Intent(SunnyWeatherApplication.context, BasicsActivity::class.java)
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(SunnyWeatherApplication.context, FourComponentsActivity::class.java)
                    startActivity(intent)
                }

                3 -> {
                    val intent = Intent(SunnyWeatherApplication.context, CommonControlActivity::class.java)
                    startActivity(intent)
                }

                4 -> {
                    val intent = Intent(SunnyWeatherApplication.context, UserInteractionActivity::class.java)
                    startActivity(intent)
                }

                5 -> {
                    val intent = Intent(SunnyWeatherApplication.context, WebAccessActivity::class.java)
                    startActivity(intent)
                }

                6 -> {
                    val intent = Intent(SunnyWeatherApplication.context, ImageLoadingActivity::class.java)
                    startActivity(intent)
                }
                7 -> {
                    val intent = Intent(SunnyWeatherApplication.context, DataStoringActivity::class.java)
                    startActivity(intent)
                }
                8 -> {
                    val intent = Intent(SunnyWeatherApplication.context, AnimeActivity::class.java)
                    startActivity(intent)
                }
                9 -> {
                    val intent = Intent(SunnyWeatherApplication.context, ViewActivity::class.java)
                    startActivity(intent)
                }
                10 -> {
                    val intent = Intent(SunnyWeatherApplication.context, MultimediaActivity::class.java)
                    startActivity(intent)
                }
                11 -> {
                    val intent = Intent(SunnyWeatherApplication.context, HighTechActivity::class.java)
                    startActivity(intent)
                }
                12 -> {
                    val intent = Intent(SunnyWeatherApplication.context, KotlinActivity::class.java)
                    startActivity(intent)
                }
                13 -> {
                    val intent = Intent(SunnyWeatherApplication.context, JavaActivity::class.java)
                    startActivity(intent)
                }

                else -> {
                    val intent = Intent(SunnyWeatherApplication.context, IntentActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}

