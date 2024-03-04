package com.example.sunnyweather.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.base.ext.dp
import com.example.sunnyweather.R
import com.example.sunnyweather.adapter.MessageMarketingDecoration
import com.example.sunnyweather.adapter.MySettingAdapter
import com.example.sunnyweather.adapter.RecommendAdapter
import com.example.sunnyweather.base.binding.BaseBindingFragment
import com.example.sunnyweather.data.MySettingData
import com.example.sunnyweather.data.RecommendData
import com.example.sunnyweather.databinding.FragmentMyBinding
import com.example.sunnyweather.databinding.FragmentRecommendBinding
import com.example.sunnyweather.widget.Log
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar


class RecommendFragment : BaseBindingFragment<FragmentRecommendBinding>() {
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
            /*etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    //recommendAdapter.filter(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
*/
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
    }
}

