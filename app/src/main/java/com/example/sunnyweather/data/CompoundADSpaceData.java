package com.example.sunnyweather.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompoundADSpaceData implements Serializable {

    /**
     * refreshType : string 9.4广告位刷新串
     */
    public List<CompoundADSpaceInfoBean> compoundADSpaceInfos = new ArrayList<CompoundADSpaceInfoBean>();
    public String refreshType = "";

    public static class CompoundADSpaceInfoBean implements Serializable {
        /**
         * advertisingSpaceInfos : [{"detail":"string","flag":"string","iconUrl":"string","imageUrl":"string","introduction":"string","link":"string","linkType":"string","news":"string","order":0,"otherIntro":"string","provinceCode":"string","sceneId":"string","timeStamp":"string","title":"string"}]
         * type : string 同入参 使用CompoundADSpaceTask.TYPE解析 广告位类型:1、商城首页轮播位 2、商城首页主导航 3、商城首页副导航 4、生活频道顶部tab（传多个值时用,号分割）
         */
        public String type = "";
        public List<AdItem> advertisingSpaceInfos = new ArrayList<AdItem>();
    }
}
