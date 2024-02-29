package com.example.sunnyweather.data;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.sunnyweather.util.UtilOther;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryServiceMessageData implements Serializable {

    /**
     * bottomTip 底部提示语
     */
    public String bottomTip = "";
    /**
     * messageClassifyList 消息分类信息列表
     */
    public List<MessageClassifyListBean> messageClassifyList = new ArrayList<MessageClassifyListBean>();

    public static class MessageClassifyListBean implements Serializable {
        /**
         * link 跳转链接
         */
        public String link = "";
        /**
         * linkType 跳转类型
         */
        public String linkType = "";
        /**
         * messageCardList 消息卡片信息列表
         */
        public List<MessageCardListBean> messageCardList = new ArrayList<MessageCardListBean>();
        /**
         * oneLevelMsgId 一级消息Id
         */
        public String oneLevelMsgId = "";
        /**
         * redDotMsgNum 红点消息数
         */
        public String redDotMsgNum = "";
        /**
         * tabTitle tab栏标题
         */
        public String tabTitle = "";


        public static class MessageCardListBean implements Serializable {
            public boolean isPran = false;
            public boolean isScreenCapture = false;
            /**
             * cardTypeInfo 卡片类型信息
             */
            public CompoundAdItem cardTypeInfo = new CompoundAdItem();
            /**
             * iconUrl 消息图标
             */
            public String iconUrl = "";
            /**
             * iconId 消息图标
             */
            public Integer iconId = -1;
            public static String ICONID = "";
            /**
             * link 跳转地址
             */
            public String link = "";
            /**
             * linkType 跳转类型
             */
            public String linkType = "";
            /**
             * messageAttributeList 消息属性列表
             */
            public List<MessageAttributeListBean> messageAttributeList = new ArrayList<MessageAttributeListBean>();
            /**
             * messageOperateList 消息操作列表
             */
            public List<MessageAttributeListBean> messageOperateList = new ArrayList<MessageAttributeListBean>();
            /**
             * msgId 消息ID（三级消息ID）
             */
            public String msgId = "";
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
             * showTime 展示时间
             */
            public String showTime = "";
            /**
             * subtitle 副标题
             */
            public String subtitle = "";
            /**
             * title 标题
             */
            public String title = "";

            public static class MessageAttributeListBean implements Serializable, MultiItemEntity {
                /**
                 * content 文案
                 */
                public String content = "";
                /**
                 * endTime 客服下班时间（时间格式：HH:mm）
                 */
                public String endTime = "";
                /**
                 * link 跳转地址
                 */
                public String link = "";
                /**
                 * linkType 跳转类型
                 */
                public String linkType = "";
                /**
                 * phoneNum 电话号码（加密）
                 */
                public String phoneNum = "";
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
                 * startTime 客服上班时间（时间格式：HH:mm）
                 */
                public String startTime = "";
                /**
                 * styleType 样式（1：默认2：蓝色 3：橙色 4：详情）
                 */
                public String styleType = "";
                /**
                 * subtitle 副标题（按钮文案或跳转副标题）
                 */
                public String subtitle = "";
                /**
                 * title 标题
                 */
                public String title = "";
                /**
                 * type 类型（1、普通广告位；2、打电话）
                 */
                public String type = "";

                @Override
                public int getItemType() {
                    switch (styleType) {
                        case STYLE_TYPE.BLUE:
                        case STYLE_TYPE.ORANGE:
                        case STYLE_TYPE.DETAIL:
                        {
                            return UtilOther.parseInt(styleType);
                        }
                        default: {
                            return UtilOther.parseInt(STYLE_TYPE.DEFAULT);
                        }
                    }
                }


                /**
                 * 样式（1：默认 2：蓝色 3：橙色 4：详情）
                 */
                public static class STYLE_TYPE {
                    public static final String DEFAULT = "1";
                    public static final String BLUE = "2";
                    public static final String ORANGE = "3";
                    public static final String DETAIL = "4";
                }

                /**
                 * 类型（1、普通广告位；2、打电话）
                 */
                public static class TYPE {
                    public static final String AD = "1";
                    public static final String CALL = "2";
                }

            }

        }
    }
}
