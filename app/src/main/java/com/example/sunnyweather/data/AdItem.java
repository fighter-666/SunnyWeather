package com.example.sunnyweather.data;

import java.io.Serializable;

public class AdItem implements Serializable {
    private String Id = "";

    private String detail = "";
    private String otherIntro = "";
    private String news = "";
    private String order = "";
    private String introduction = "";
    private String title = "";
    private String link = "";
    private String linkType = "";
    private String imageUrl = ""; // 角标
    private String iconUrl = ""; // 图片
    private String IsRead = "false";
    private boolean isBigData = false;
    private boolean isInsert = false;

    public boolean isInsert() {
        return isInsert;
    }

    public void setInsert(boolean insert) {
        isInsert = insert;
    }

    /**
     * 红点标识0不开启 1开启(返回非1的都不开启)
     *
     * @return
     */
    private String flag = "";
    private String timeStamp = "";
    private boolean showRedDot;//是否显示红点
    //	private CommonLinkItem commonLinkItem = new CommonLinkItem();
    public int resId;
    private String provinceCode = "";//省份，集团编码
    private String recommender = "";//9.0插码推荐码
    private String sceneId = "";//大数据场景ID
    private String adType = "";//9.1新增，广告类型
    private String clickLink = "";//9.1新增，点击上报地址
    private String impressionLink = "";//9.1新增，曝光上报地址
    private String autoSwitch = "";//自动关闭开关（0、否 1、是）没有返回或返回非1默认按0不自动关闭处理
    private String autoTime = "";//自动关闭时间，没有返回默认按5s处理

    /**
     * 显示规则（1、每次都显示 2、仅展示1次 3、1天1次）默认为1
     */
    private String type = "";
    /**
     * 930新增关闭规则（1：按天、2：按周、3：按月、4：永久）默认为4 用Constants.HOME_AD_CLOSE_RULE赋值
     */
    private String closeRule = "";

    /**
     * 自动关闭开关（0、否 1、是）
     */
    public static class SWITCH_FLAG {
        public static final String NO = "0";
        public static final String YES = "1";
    }


    public AdItem(String link, String linkType, int resId) {
        this.link = link;
        this.linkType = linkType;
        this.resId = resId;
    }

    public boolean isShowRedDot() {
        return showRedDot;
    }

    public void setShowRedDot(boolean showRedDot) {
        this.showRedDot = showRedDot;
    }

    public AdItem(String title, String link, String linkType, int resId) {
        this.title = title;
        this.link = link;
        this.linkType = linkType;
        this.resId = resId;
    }

    public AdItem copy() {
        AdItem adItem = new AdItem();
        adItem.Id = Id;
        adItem.detail = detail;
        adItem.otherIntro = otherIntro;
        adItem.news = news;
        adItem.order = order;
        adItem.introduction = introduction;
        adItem.title = title;
        adItem.link = link;
        adItem.linkType = linkType;
        adItem.imageUrl = imageUrl;
        adItem.iconUrl = iconUrl;
        adItem.IsRead = IsRead;
        adItem.flag = flag;
        adItem.timeStamp = timeStamp;
        adItem.provinceCode = provinceCode;
        adItem.recommender = recommender;
        adItem.sceneId = sceneId;
        adItem.adType = adType;
        adItem.clickLink = clickLink;
        adItem.impressionLink = impressionLink;
        adItem.autoSwitch = autoSwitch;
        adItem.autoTime = autoTime;
        adItem.type = type;
        adItem.closeRule = closeRule;
//		adItem.commonLinkItem = commonLinkItem;
        adItem.resId = resId;
        return adItem;
    }

    public AdItem() {
        IsRead = "false";
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOtherIntro() {
        return otherIntro;
    }

    public void setOtherIntro(String otherIntro) {
        this.otherIntro = otherIntro;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getOrder() {
        return order;
    }

    public int getOrderInt() {
        try {
            return Integer.valueOf(order);
        } catch (Exception e) {
            return 0;
        }
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
//		commonLinkItem.title=title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
//		commonLinkItem.link=link;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
//		commonLinkItem.linkType=linkType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "AdItem [detail=" + detail + ", otherIntro=" + otherIntro
                + ", news=" + news + ", order=" + order + ", introduction="
                + introduction + ", title=" + title + ", link=" + link
                + ", linkType=" + linkType + ", imageUrl=" + imageUrl
                + ", iconUrl=" + iconUrl + "]";
    }


//    public boolean isLinkLegal(Context context) {
////		return commonLinkItem.isLinkLegal(context);
//    }

    public boolean isRead() {
        return IsRead.equalsIgnoreCase("true");
    }

    public void setReadFlg() {
        this.IsRead = "true";
    }

    public void setRead(String value) {
        if (value != null && value.equals("true")) {
            this.IsRead = "true";
        } else {
            this.IsRead = "false";
        }
    }

    public String getRead() {
        return this.IsRead;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIsRead() {
        return IsRead;
    }

    public void setIsRead(String isRead) {
        IsRead = isRead;
    }

    /**
     * 红点标识0不开启 1开启(返回非1的都不开启)
     *
     * @return
     */
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

//    public void goTarget(Context context) {
////		commonLinkItem.title = title;
////		commonLinkItem.link = link;
////		commonLinkItem.linkType = linkType;
////		commonLinkItem.goTarget(context);
//    }


    public void setBigData(boolean bigData) {
        isBigData = bigData;
    }

    public boolean isBigData() {
        return isBigData;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAdType() {
        return adType;
    }

    public void setClickLink(String clickLink) {
        this.clickLink = clickLink;
    }

    public String getClickLink() {
        return clickLink;
    }

    public void setImpressionLink(String impressionLink) {
        this.impressionLink = impressionLink;
    }

    public String getImpressionLink() {
        return impressionLink;
    }

    public String getAutoSwitch() {
        return autoSwitch;
    }

    public void setAutoSwitch(String autoSwitch) {
        this.autoSwitch = autoSwitch;
    }

    public String getAutoTime() {
        return autoTime;
    }

    public void setAutoTime(String autoTime) {
        this.autoTime = autoTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCloseRule() {
        return closeRule;
    }

    public void setCloseRule(String closeRule) {
        this.closeRule = closeRule;
    }
}
