package com.example.sunnyweather.data;

import androidx.annotation.NonNull;


import java.io.Serializable;

/**
 * 说明：通用广告位bean
 *
 * @作者 luohao
 * @创建时间 2023/7/7 16:58
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class CompoundAdItem implements Serializable, BannerBean {

    /**
     * order : 显示位置 string
     * title : 标题 string
     * subtitle : 副标题 string
     * iconUrl : 图标地址 string
     * labelUrl : 标签图片地址 string
     * labelText : 标签文案 string
     * redDotFlag : 红点标识(0不开启1开启) string
     * timestamp : 时间戳 string
     * link : 跳转地址 string
     * linkType : 跳转类型 string
     * sceneId : 大数据场景ID string
     * provinceCode : 省份、集团编码 string
     * recommender : 插码推荐码 string
     */

    public String order = "";
    public String title = "";
    public String subtitle = "";
    public String iconUrl = "";
    public Integer iconId = -1;
    public static String ICONID = "";
    public String labelUrl = "";
    public String labelText = "";
    public String redDotFlag = "";
    public String timestamp = "";
    public String link = "";
    public String linkType = "";
    public String sceneId = "";
    public String provinceCode = "";
    public String recommender = "";

    @NonNull
    @Override
    public String getIconUrl() {
        return iconUrl;
    }

    @NonNull
    @Override
    public String getTitle() {
        return title;
    }

    @NonNull
    @Override
    public String getLink() {
        return link;
    }

    @NonNull
    @Override
    public String getLinkType() {
        return linkType;
    }

    @NonNull
    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @NonNull
    @Override
    public String getRedFlag() {
        return redDotFlag;
    }

    /**
     * 是否显示红点：0否 1是
     */
    public static class RED_DOT_FLAG {
        public static final String NO = "0";
        public static final String YES = "1";
    }
}
