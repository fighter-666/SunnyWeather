package com.example.sunnyweather.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyInformationPageData implements Serializable {


    /**
     * accountInformationFloor 账户信息楼层
     */
    public AccountInformationFloorBean accountInformationFloor = new AccountInformationFloorBean();
    /**
     * ctArchivesFloor 电信档案楼层
     */
    public CtArchivesFloorBean ctArchivesFloor = new CtArchivesFloorBean();
    /**
     * medalFloor 勋章楼层
     */
    public MedalFloorBean medalFloor = new MedalFloorBean();
    /**
     * userInformation 用户信息
     */
    public UserInformationBean userInformation = new UserInformationBean();
    /**
     * userInformationAdList 用户信息弹窗列表
     */
    public List<CompoundAdItem> userInformationAdList = new ArrayList<CompoundAdItem>();

    public static class AccountInformationFloorBean implements Serializable {
        /**
         * accountInformationList 账户信息列表
         */
        public List<AccountInformationListBean> accountInformationList = new ArrayList<AccountInformationListBean>();
        /**
         * title 标题
         */
        public String title = "";

        public static class AccountInformationListBean implements Serializable {
            /**
             * accountDetailList 账户详细信息列表
             */
            public List<CompoundAdItem> accountDetailList = new ArrayList<CompoundAdItem>();

        }
    }

    public static class CtArchivesFloorBean implements Serializable {
        /**
         * archivesList 档案列表
         */
        public List<ArchivesListBean> archivesList = new ArrayList<ArchivesListBean>();
        /**
         * subtitle 副标题
         */
        public String subtitle = "";
        /**
         * title 标题
         */
        public String title = "";

        public static class ArchivesListBean implements Serializable {
            /**
             * arrowIcon 箭头图标
             */
            public String arrowIcon = "";
            /**
             * backgroundImage 背景图片
             */
            public String backgroundImage = "";
            /**
             * link 跳转地址
             */
            public String link = "";
            /**
             * linkType 跳转类型
             */
            public String linkType = "";
            /**
             * provinceCode 省份、集团编码
             */
            public String provinceCode = "";
            /**
             * recommender 插码推荐码
             */
            public String recommender = "";
            /**
             * sceneId 大数据场景ID
             */
            public String sceneId = "";
            /**
             * subtitle 副标题
             */
            public String subtitle = "";
            /**
             * subtitleColor 副标题色值
             */
            public String subtitleColor = "";
            /**
             * title 标题
             */
            public String title = "";
            /**
             * titleColor 标题色值
             */
            public String titleColor = "";
        }

    }

    public static class MedalFloorBean implements Serializable {
        /**
         * backgroundImage 背景图片
         */
        public String backgroundImage = "";
        /**
         * floorStructure 楼层标题栏结构
         */
        public FloorStructureBean floorStructure = new FloorStructureBean();
        /**
         * medalList 勋章列表
         */
        public List<MedalListBean> medalList = new ArrayList<MedalListBean>();

        public static class FloorStructureBean implements Serializable {
            /**
             * arrowIcon 箭头图标
             */
            public String arrowIcon = "";
            /**
             * link 跳转地址
             */
            public String link = "";
            /**
             * linkType 跳转类型
             */
            public String linkType = "";
            /**
             * provinceCode 省份、集团编码
             */
            public String provinceCode = "";
            /**
             * recommender 插码推荐码
             */
            public String recommender = "";
            /**
             * sceneId 大数据场景ID
             */
            public String sceneId = "";
            /**
             * subtitle 副标题
             */
            public String subtitle = "";
            /**
             * subtitleColor 副标题色值
             */
            public String subtitleColor = "";
            /**
             * subtitleHighLight 副标题高亮
             */
            public String subtitleHighLight = "";
            /**
             * subtitleHighLightColor 副标题高亮色值
             */
            public String subtitleHighLightColor = "";
            /**
             * title 标题
             */
            public String title = "";
            /**
             * titleColor 标题色值
             */
            public String titleColor = "";
        }

        public static class MedalListBean implements Serializable {
            /**
             * iconUrl 图标地址
             */
            public String iconUrl = "";
            /**
             * link 跳转地址
             */
            public String link = "";
            /**
             * linkType 跳转类型
             */
            public String linkType = "";
            /**
             * provinceCode 省份、集团编码
             */
            public String provinceCode = "";
            /**
             * recommender 插码推荐码
             */
            public String recommender = "";
            /**
             * sceneId 大数据场景ID
             */
            public String sceneId = "";
            /**
             * title 标题
             */
            public String title = "";
            /**
             * titleColor 标题色值
             */
            public String titleColor = "";
        }
    }

    public static class UserInformationBean implements Serializable {
        /**
         * electronicSignature 电子签名
         */
        public String electronicSignature = "";
        /**
         * headImagePendantIcon 用户头像挂件图标
         */
        public String headImagePendantIcon = "";
        /**
         * signatureTemplateList 签名模板列表
         */
        public List<String> signatureTemplateList = new ArrayList<String>();
    }

}
