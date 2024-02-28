package com.example.sunnyweather.widget;

import android.content.Context;


/**
 * 8.3.0 设置消息已读接口
 */
public class MarkReadMsgTask extends BaseTask {
    private MarkReadMsgResponse response;
    private String mMarkReadFlag = "";
    private String mOneLevelMsgIds = "";
    private String mTwoLevelMsgId = "";
    private String mThreeLevelMsgId = "";

    /**
     *  设置已读标识( 0：全部消息已读(不需要传消息id)、1：一级消息已读(需传一级消息id)、2：二级消息已读(需传二级消息id)、3：三级消息已读(需传三级消息id))
     */
    public static class MARK_READ_FLAG {
        public static final String ALLMSG = "0";
        public static final String ONELEVELMSG = "1";
        public static final String TWOLEVELMSG = "2";
        public static final String THREELEVELMSG = "3";
    }

    public MarkReadMsgTask(Context context){
        super(context);
    }

    /**
     * 设置已读标识( 0：全部消息已读(不需要传消息id)、1：一级消息已读(需传一级消息id)、2：二级消息已读(需传二级消息id)、3：三级消息已读(需传三级消息id))
     * 使用MarkReadMsgTask.MARK_READ_FLAG赋值
     * @param mMarkReadFlag
     */
    public void setMarkReadFlag(String mMarkReadFlag) {
        this.mMarkReadFlag = mMarkReadFlag;
    }

    /**
     * 一级消息id(可传多个用,隔开)
     * @param mOneLevelMsgIds
     */
    public void setOneLevelMsgIds(String mOneLevelMsgIds) {
        this.mOneLevelMsgIds = mOneLevelMsgIds;
    }

    /**
     * 二级消息id
     * @param mTwoLevelMsgId
     */
    public void setTwoLevelMsgId(String mTwoLevelMsgId) {
        this.mTwoLevelMsgId = mTwoLevelMsgId;
    }

    /**
     * 三级消息id(仅用于消息跳转类)
     * @param mThreeLevelMsgId
     */
    public void setThreeLevelMsgId(String mThreeLevelMsgId) {
        this.mThreeLevelMsgId = mThreeLevelMsgId;
    }

 /*   @Override
    protected Boolean doInBackground(String... params) {
        MarkReadMsgRequest req = new MarkReadMsgRequest();
        req.setAccount(UtilEncryption.encrypt(MyApplication.mDataCache.UserPhoneNbr));
        req.setMarkReadFlag(mMarkReadFlag);
        req.setOneLevelMsgIds(mOneLevelMsgIds);
        req.setTwoLevelMsgId(mTwoLevelMsgId);
        req.setThreeLevelMsgId(mThreeLevelMsgId);
        req.setUserId(MyApplication.mDataCache.UserId);
        req.setNetType(MyApplication.mDataCache.NetType);
        req.setIsChinatelecom(MyApplication.mDataCache.getIsChinatelecom());
        req.setProvinceCode(MyApplication.mDataCache.ProvinceCode);

        response = req.getResponse();
        return response.isSuccess();
    }*/

    @Override
    protected void onPostExecute(Boolean rslt) {
        super.onPostExecute(rslt);
        if (rslt) {
            if (mOnTaskFinished != null){
                mOnTaskFinished.onSucc(response);
            }
        } else {
            if (mOnTaskFinished != null)
                mOnTaskFinished.onFail(response);
        }
    }
}
