package com.example.sunnyweather.data;


import com.example.sunnyweather.model.MessageInfo;
import com.example.sunnyweather.util.UtilOther;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryMessageChannelData implements Serializable {

    /**
     * bottomTip 底部提示语
     */
    public String bottomTip = "";
    /**
     * floatWindowSwitch 消息详情页悬浮窗开关(0:不显示 、1:显示)
     */
    public String floatWindowSwitch = "";
    /**
     * marketingMessageList 营销类消息内容列表
     */
    public List<MarketingMessageListBean> marketingMessageList = new ArrayList<MarketingMessageListBean>();
    /**
     * marketingMessageTip 营销类消息提示条
     */
    public MarketingMessageTipBean marketingMessageTip = new MarketingMessageTipBean();
    /**
     * messageIdInfos 消息ID列表
     */
    public List<MessageInfo> messageIdInfos = new ArrayList<MessageInfo>();
    /**
     * messageSettingList 消息设置列表
     */
    public List<MessageSettingListBean> messageSettingList = new ArrayList<MessageSettingListBean>();
    /**
     * msgList 动态消息列表信息
     */
    public List<MsgListBean> msgList = new ArrayList<MsgListBean>();
    /**
     * pRANConfigureBean P-RAN配置结构
     */
    public PRANConfigureBeanBean pRANConfigureBean = new PRANConfigureBeanBean();
    /**
     * redDotMsgSumNum 一级消息汇总总红点数
     */
    public String redDotMsgSumNum = "";
    /**
     * serviceMessageClassifyList 服务类消息分类列表
     */
    public List<ServiceMessageClassifyListBean> serviceMessageClassifyList = new ArrayList<ServiceMessageClassifyListBean>();
    /**
     * serviceMessageList 服务类消息内容列表
     */
    public List<MsgListBean> serviceMessageList = new ArrayList<MsgListBean>();
    /**
     * serviceMessageSwitchDescription 服务消息开关描述
     */
    public String serviceMessageSwitchDescription = "";
    /**
     * topList 置顶列表（json串）
     */
    public String topList = "";

    /*
     * 消息详情页悬浮窗开关(0:不显示 、1:显示)
     */
    public static class FLOAT_WINDOW_SWITCH {
        public static final String NOSHOW = "0";// 不显示
        public static final String SHOW = "1";// 显示
    }

    public static class MarketingMessageTipBean implements Serializable {
        /**
         * iconUrl 图标地址
         */
        public String iconUrl = "";
        /**
         * title 标题
         */
        public String title = "";
    }

    public static class PRANConfigureBeanBean implements Serializable {
        /**
         * background 背景地址
         */
        public String background = "";
        /**
         * isShow 是否展示P-RAN卡片：0、否；1、是
         */
        public String isShow = "";

        /**
         * 是否展示P-RAN卡片：0、否；1、是
         */
        public static class IS_SHOW {
            public static final String NO = "0";
            public static final String YES = "1";
        }
    }

    public static class MarketingMessageListBean implements Serializable, Comparable<MarketingMessageListBean> {
        /**
         * iconUrl 图标地址
         */
        public String iconUrl = "";
        /**
         * isOutOfTime 是否是旧消息，0：否，1：是
         */
        public String isOutOfTime = "";
        /**
         * link 跳转地址
         */
        public String link = "";
        /**
         * linkType 跳转类型
         */
        public String linkType = "";
        /**
         * msgType 消息类型(1：消息通知类、2：跳转类)
         */
        public String msgType = "";
        /**
         * oneLevelMsgId 一级消息id
         */
        public String oneLevelMsgId = "";
        /**
         * order 排序
         */
        public String order = "";
        /**
         * provinceCode 省编码
         */
        public String provinceCode = "";
        /**
         * recommender 插码推荐码
         */
        public String recommender = "";
        /**
         * redDotMsgNum 红点数
         */
        public String redDotMsgNum = "";
        public int realSum = 0; // 总数用这个相加
        /**
         * sceneId 场景id
         */
        public String sceneId = "";
        /**
         * showTime 发布时间
         */
        public String showTime = "";
        /**
         * subtitle 副标题
         */
        public String subtitle = "";
        /**
         * threeLevelMsgId 三级消息id
         */
        public String threeLevelMsgId = "";
        /**
         * title 标题
         */
        public String title = "";
        /**
         * twoLevelMsgId 二级消息id
         */
        public String twoLevelMsgId = "";
        public boolean isTop = false; // 置顶标志

        @Override
        public int compareTo(MarketingMessageListBean o) {
            return UtilOther.parseInteger(this.order).compareTo(UtilOther.parseInt(o.order));
        }

        /**
         * 是否是旧消息，0：否，1：是
         */
        public static class IS_OUT_OF_TIME {
            public static final String NO = "0";
            public static final String YES = "1";
        }

        /**
         * 消息类型(1：消息通知类、2：跳转类)
         */
        public static class MSG_TYPE {
            public static final String MESSAGE_NOTIFICATION = "1";
            public static final String JUMP = "2";
        }
    }

    public static class MessageSettingListBean implements Serializable {
        /**
         * iconUrl 图标地址
         */
        public String iconUrl = "";
        /**
         * isOpen 是否开启（0、否；1、是）
         */
        public String isOpen = "";
        /**
         * title 标题
         */
        public String title = "";
        /**
         * twoLevelMsgId 消息分类Id（相当于二级消息Id）
         */
        public String twoLevelMsgId = "";

        /**
         * 是否开启（0、否；1、是）
         */
        public static class IS_OPEN {
            public static final String NO = "0";
            public static final String YES = "1";
        }
    }

    public static class MsgListBean implements Serializable {
        public boolean isPran = false;
        public boolean isScreenCapture = false;
        public int headResId;
        /**
         * background 背景地址
         */
        public String background = "";
        /**
         * functionBean 右侧功能结构
         */
        public FunctionBeanBean functionBean = new FunctionBeanBean();
        /**
         * headImage 头像地址
         */
        public String headImage = "";
        /**
         * isCanClose 是否可以关闭（0：否，1：是）
         */
        public String isCanClose = "";
        /**
         * isProprietaryCustomer 是否专属客服，0：否，1：是
         */
        public String isProprietaryCustomer = "";
        /**
         * label 标签
         */
        public String label = "";
        /**
         * link 跳转地址
         */
        public String link = "";
        /**
         * linkType 跳转类型
         */
        public String linkType = "";
        /**
         * msgId 消息ID（三级消息ID）
         */
        public String msgId = "";
        /**
         * msgType 消息类型
         */
        public String msgType = "";
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
         * subTitle 副标题
         */
        public String subTitle = "";
        /**
         * timestamp 时间戳（时间格式：yyyyMMddHHmmss）
         */
        public String timestamp = "";
        /**
         * title 标题
         */
        public String title = "";

        /**
         * 显示样式：1、图标；2、按钮
         */
        public static class SHOW_TYPE {
            public static final String ICON = "1";
            public static final String BUTTON = "2";
        }

        /**
         * 类型：1、普通广告位；2、打电话
         */
        public static class TYPE {
            public static final String AD = "1";
            public static final String CALL = "2";
        }


        /**
         * 是否可以关闭（0：否，1：是）
         */
        public static class IS_CAN_CLOSE {
            public static final String NO = "0";
            public static final String YES = "1";
        }

        /**
         * 是否专属客服，0：否，1：是
         */
        public static class IS_PROPRIETARY_CUSTOMER {
            public static final String NO = "0";
            public static final String YES = "1";
        }

        public static class FunctionBeanBean implements Serializable {
            /**
             * endTime 客服下班时间（时间格式：HH:mm）
             */
            public String endTime = "";
            /**
             * iconUrl 图片地址
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
             * showType 显示样式：1、图标；2、按钮
             */
            public String showType = "";
            /**
             * startTime 客服上班时间（时间格式：HH:mm）
             */
            public String startTime = "";
            /**
             * title 标题
             */
            public String title = "";
            /**
             * type 类型：1、普通广告位；2、打电话
             */
            public String type = "";

            /**
             * 显示样式：1、图标；2、按钮
             */
            public static class SHOW_TYPE {
                public static final String ICON = "1";
                public static final String BUTTON = "2";
            }

            /**
             * 类型：1、普通广告位；2、打电话
             */
            public static class TYPE {
                public static final String AD = "1";
                public static final String CALL = "2";
            }
        }
    }

    public static class ServiceMessageClassifyListBean implements Serializable {
        /**
         * iconUrl 图标地址
         */
        public String iconUrl = "";
        /**
         * link 跳转链接
         */
        public String link = "";
        /**
         * linkType 跳转类型
         */
        public String linkType = "";
        /**
         * oneLevelMsgId 一级消息id
         */
        public String oneLevelMsgId = "";
        /**
         * redDotMsgNum 红点消息数（IM消息使用）
         */
        public String redDotMsgNum = "";
        public int realSum = 0; // 总数用这个相加
        /**
         * title 标题
         */
        public String title = "";
        public String h5RedDotNum = "";

        public int resId = 0; // 默认资源id
    }

}
