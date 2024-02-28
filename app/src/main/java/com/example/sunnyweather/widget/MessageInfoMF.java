package com.example.sunnyweather.widget;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.sunnyweather.data.AdItem;
import com.gongwen.marqueen.MarqueeFactory;


/**
 * 消息中心-消息轮播
 */
public class MessageInfoMF<I> extends MarqueeFactory<LinearLayout, AdItem> {

    public MessageInfoMF(Context mContext) {
        super(mContext);
    }

    @Override
    protected LinearLayout generateMarqueeItemView(AdItem data) {
        MessageInfoMarquee marquee = new MessageInfoMarquee(mContext);
        marquee.setData(data);
        return marquee;
    }
}