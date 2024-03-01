package com.example.sunnyweather.data;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.sunnyweather.util.UtilOther;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MySettingData implements Serializable {

    public List<MySettingListBean> MySettingList = new ArrayList<MySettingListBean>();

    public static class MySettingListBean implements Serializable {
        /**
         * link 跳转链接
         */
        public String link = "";
        /**
         * linkType 跳转类型
         */
        public String linkType = "";
        /**
         * tabTitle tab栏标题
         */
        public String title = "";


    }
}
