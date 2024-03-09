package com.example.sunnyweather.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.base.ext.dp
import com.example.sunnyweather.R
import com.example.sunnyweather.adapter.AccountInformationAdapter
import com.example.sunnyweather.adapter.CtArchivesAdapter
import com.example.sunnyweather.adapter.MedalAdapter
import com.example.sunnyweather.base.Constants
import com.example.sunnyweather.base.binding.BaseBindingFragment
import com.example.sunnyweather.data.CompoundAdItem
import com.example.sunnyweather.data.MyInformationPageData
import com.example.sunnyweather.databinding.ActivityMyInformationBinding
import com.example.sunnyweather.pran.PranScanActivity
import com.example.sunnyweather.util.GetScreenUtils
import com.example.sunnyweather.util.UtilBitmap
import com.example.sunnyweather.util.UtilGlide
import com.example.sunnyweather.util.UtilText
import com.example.sunnyweather.widget.MyInformationSignatureDialog
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar

/**
 * 说明：我的信息
 *
 * @作者 luohao
 * @创建时间 2024/2/21 09:54
 * @版本 11.2.0
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
class MyInformationFragment : BaseBindingFragment<ActivityMyInformationBinding>() {
    private var mReceiver: MyReceiver? = null
    private var mSignatureDialog: MyInformationSignatureDialog? = null
    private var mUserInformationAdList = ArrayList<CompoundAdItem>()
    override fun lazyInit() {
        ImmersionBar.with(this)
            .titleBar(binding.llTitle)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true).init()
        initView()
        initBroadcastReceiver()
        loadUserInfo()
        queryMyInformationPage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
       /* mReceiver?.run {
            unregisterReceiver(this)
        }*/
    }

    private fun initView() {
        binding.run {
            ivBack.layoutParams.apply {
                this as MarginLayoutParams
                //topMargin = UtilView.getStatusBarHeight()
            }
            ivBack.setOnClickListener {
                val intent = Intent(requireContext(), com.example.sunnyweather.activity.MyActivity::class.java)
                requireContext().startActivity(intent)
            }
            ivScan.setOnClickListener {
                val intent = Intent(requireContext(), PranScanActivity::class.java)
                requireContext().startActivity(intent)
            }
            rlAvatar.setOnClickListener {
                val params = ivAvatar.layoutParams
                //SelectAvatarActivity.goActivity(requireContext(), params.height, params.width, mUserInformationAdList)
            }
            llSignature.setOnClickListener {
                mSignatureDialog?.onShow(binding.tvSignature.text.toString())
            }
            ivMedalBackground.layoutParams.apply {
                width = GetScreenUtils.getScreenWidth(requireContext())-26.dp
                height = (width * 134f / 334).toInt()
            }
            rvMedal.layoutParams.apply {
                width = GetScreenUtils.getScreenWidth(requireContext())-26.dp
            }
        }
        mSignatureDialog = MyInformationSignatureDialog(requireContext()).apply {
            setCallback(object : MyInformationSignatureDialog.Callback {
                override fun onSuccess(signature: String) {
                    binding.tvSignature.text = signature
                }
            })
        }
    }

    private fun initBroadcastReceiver() {
        mReceiver = MyReceiver()
        val filter = IntentFilter()
        filter.addAction(Constants.ACTION_UPDATE_NAME)
        filter.addAction(Constants.ACTION_GET_USER_IMAGE_URL_SUCCESS)
//        registerReceiver(mReceiver, filter)
    }

    private fun queryMyInformationPage(){
        val json: String = // 从文件中读取 JSON 数据，这里使用 assets 文件夹中的示例
            requireContext().assets.open("myInformationPage.json").bufferedReader()
                .use { it.readText() }
        //使用了Gson库来将JSON数据转换为GetFeedTabData对象
        val gson = Gson()
        val response = gson.fromJson(json, MyInformationPageData::class.java)

        response.run {
            mUserInformationAdList.clear()
            mUserInformationAdList.addAll(userInformationAdList)
            initUserInformation(userInformation)
            initMedal(medalFloor)
            initCtArchives(ctArchivesFloor)
            initAccountInformation(accountInformationFloor)
        }
    }

    fun initUserInformation(data: MyInformationPageData.UserInformationBean?) {
        data?.run {
            UtilGlide.showSimpleImage(headImagePendantIcon, binding.ivPendant)
            UtilText.setText(binding.tvSignature, electronicSignature, "")
            initSignatureDialog(signatureTemplateList)
        }
    }

    fun initMedal(data: MyInformationPageData.MedalFloorBean?) {
        data?.run {
            UtilGlide.showSimpleImage(backgroundImage, binding.ivMedalBackground)
            floorStructure.run {
                UtilText.setText(binding.tvMedalTitle, title)
                UtilText.setTextColor(binding.tvMedalTitle, titleColor)
                UtilText.setText(binding.tvMedalSubtitleHighlight, subtitleHighLight)
                UtilText.setTextColor(binding.tvMedalSubtitleHighlight, subtitleHighLightColor)
                UtilText.setText(binding.tvMedalSubtitle, subtitle)
                UtilText.setTextColor(binding.tvMedalSubtitle, subtitleColor)
                UtilGlide.showSimpleImage(arrowIcon, binding.ivMedalArrow)
            }
            if (medalList.isNullOrEmpty()) {
                binding.rlMedal.visibility = View.GONE
            } else {
                binding.rlMedal.visibility = View.VISIBLE
                binding.rvMedal.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvMedal.adapter = MedalAdapter().apply { setNewData(medalList) }
            }
        } ?: run {
            binding.rlMedal.visibility = View.GONE
        }
    }

    fun initCtArchives(data: MyInformationPageData.CtArchivesFloorBean?) {
        data?.run {
            binding.llArchives.visibility = View.VISIBLE
            UtilText.setText(binding.tvArchivesTitle, title)
            UtilText.setText(binding.tvArchivesSubtitle, subtitle)

            if (archivesList.isNullOrEmpty()) {
                binding.llArchives.visibility = View.GONE
            } else {
                binding.llArchives.visibility = View.VISIBLE
                binding.rvArchives.layoutManager = GridLayoutManager(requireContext(), 3)
                binding.rvArchives.adapter = CtArchivesAdapter().apply { setNewData(archivesList) }
            }
        } ?: run {
            binding.llArchives.visibility = View.GONE
        }
    }

    fun initAccountInformation(data: MyInformationPageData.AccountInformationFloorBean?) {
        data?.run {
            binding.llInfo.visibility = View.VISIBLE
            UtilText.setText(binding.tvInfoTitle, title)

            if (this.accountInformationList.isNullOrEmpty()) {
                binding.llInfo.visibility = View.GONE
            } else {
                binding.llInfo.visibility = View.VISIBLE
                binding.rvInfo.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvInfo.adapter = AccountInformationAdapter().apply { setNewData(accountInformationList) }
            }
        } ?: run {
            binding.llInfo.visibility = View.GONE
        }
    }

    private fun initSignatureDialog(signatureTemplateList: MutableList<String>) {
        mSignatureDialog?.setData(signatureTemplateList)
    }

    fun loadUserInfo() {
        UtilText.setText(binding.tvName, "吴道满", "您好!")
        UtilBitmap.setImageBitmap(requireContext(), binding.ivAvatar, UtilBitmap.getloadlBitmap("https://image.baidu.com/search/albumsdetail?tn=albumsdetail&word=蛋糕&fr=albumslist&album_tab=人物&album_id=43&rn=30", 100, 100), R.drawable.ic_mine_avatar_default, true)
    }

    private inner class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //通过广播来刷新数据的，要把刷新的数据放到onCreate里来解决有时候收不到广播，不能刷新的问题
            if (intent.action == null) {
                return
            }
            if (intent.action == Constants.ACTION_GET_USER_IMAGE_URL_SUCCESS) {
                loadUserInfo()
                loadUserInfo()
            } else if (intent.action == Constants.ACTION_UPDATE_NAME) {
                loadUserInfo()
                loadUserInfo()
            }
        }
    }
}