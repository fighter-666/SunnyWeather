package com.example.sunnyweather.util;

import android.content.Context;

import com.ct.client.widget.TabWidgetItem;
import com.ct.net.homepage.utils.CtBadgeHelper;

import cn.bingoogolapple.badgeview.BGABadgeable;
import cn.bingoogolapple.badgeview.BGADragDismissDelegate;

/**
 * 说明：首页导航栏badge
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/8/7 16:49
 * @版本
 * @------修改记录-------
 * @修改人 linwen@ffcs.cn
 * @版本 6.4.0
 * @修改内容 引入BGABadge而做的相关修改
 */
public class NavBadge extends CtBadge implements CtBadgeHelper.IBadge<TabWidgetItem> {

    public NavBadge(int key, int ori) {
        super(key, ori);
    }

    public NavBadge(int key, int ori, int num) {
        super(key, ori, num);
    }

    private Context mContext;

    public NavBadge(int key, int ori, int num, Context context) {
        super(key, ori, num);
        this.mContext = context;
    }

    private BGADragDismissDelegate mListener = new BGADragDismissDelegate() {
        @Override
        public void onDismiss(BGABadgeable badgeable) {
            num = NavBadgeManager.Ori.NON;
            NavBadgeManager.getInstance().updateView();
            if (onBadgeDismissListener != null) {
                onBadgeDismissListener.onDismiss();
            }
        }
    };

    @Override
    public void show() {
        TabWidgetItem tabWidgetItem = getView();
        if (tabWidgetItem != null) {
            tabWidgetItem.setRedPoint(num, mContext, onBadgeDismissListener);
        }
    }

    @Override
    public void hide() {
//        TabWidgetItem tabWidgetItem = getView();
//        if (tabWidgetItem != null) {
//            tabWidgetItem.setBadge(NavBadgeManager.Ori.NON, null);//由于某些限（如：显示个数）制而主动隐藏就不回调了
//        }
    }

    @Override
    public int getOri() {
        return ori;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public TabWidgetItem getView() {
        return (TabWidgetItem) NavBadgeManager.getInstance().getView(key);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ori:");
        builder.append(ori).append(",key:").append(key);
        builder.append(",num:").append(num);
        return builder.toString();
    }
}
