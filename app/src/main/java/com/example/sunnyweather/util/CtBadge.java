package com.example.sunnyweather.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.bingoogolapple.badgeview.annotation.BGABadge;

/**
 * 说明：小红点的model
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/8/7 15:13
 * @版本
 * @------修改记录-------
 * @修改人 linwen@ffcs.cn
 * @版本  6.4.0
 * @修改内容   添加BGABadge
 */
@BGABadge({
        View.class,
        TextView.class,
        ImageView.class,
        RelativeLayout.class
})
public abstract class CtBadge {

    public interface OnBadgeDismissListener {
        void onDismiss();
    }

    public int key;//确保唯一性并能和view关联
    public int ori;//显示序列，以此进行升序排列，靠前先显示
    public int num;//显示的数量

    OnBadgeDismissListener onBadgeDismissListener;//徽章拖拽消失回调接口

    CtBadge(int key, int ori) {
        this(key, ori, NavBadgeManager.Ori.NON);
    }

    public int getRealNbr(){
        int realNbr;
        switch (num) {
            case NavBadgeManager.Ori.NON: {
                realNbr = 0;
                break;
            }
            case NavBadgeManager.Ori.NAN: {
                realNbr = 1;
                break;
            }
            default:{
                realNbr = num;
                break;
            }
        }
        return realNbr;
    }

    CtBadge(int key, int ori, int num) {
        this.key = key;
        this.ori = ori;
        this.num = num;
    }

    public void setOnBadgeDismissListener(OnBadgeDismissListener onBadgeDismissListener) {
        this.onBadgeDismissListener = UtilOther.ref(onBadgeDismissListener);
    }

}
