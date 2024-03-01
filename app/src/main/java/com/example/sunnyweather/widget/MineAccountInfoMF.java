package com.example.sunnyweather.widget;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.sunnyweather.data.CompoundAdItem;
import com.gongwen.marqueen.MarqueeFactory;


/**
 * 我的账户-轮播
 */
public class MineAccountInfoMF<I> extends MarqueeFactory<LinearLayout, CompoundAdItem> {

    public MineAccountInfoMF(Context mContext) {
        super(mContext);
    }

    @Override
    protected LinearLayout generateMarqueeItemView(CompoundAdItem data) {
        MineAccountInfoMarqueeView marquee = new MineAccountInfoMarqueeView(mContext);
        marquee.setData(data);
        return marquee;
    }
}