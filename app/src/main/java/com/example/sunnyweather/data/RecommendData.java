package com.example.sunnyweather.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecommendData implements Serializable {

    public List<MyRecommendListBean> MyRecommendList = new ArrayList<MyRecommendListBean>();

    public static class MyRecommendListBean implements Serializable {
        /**
         * link 跳转链接
         */
        public String link = "";
        /**
         * linkType 跳转类型
         */
        public String linkType = "";
        /**
         * tabTitle 标题
         */
        public String title = "";
        /**
         * subtitle 副标题
         */
        public String subTitle = "";


    }
}
