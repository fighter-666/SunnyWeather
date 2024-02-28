package com.example.sunnyweather.widget;

import com.google.gson.annotations.Expose;

import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/12/28 9:20
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class HeaderInfos implements Serializable {
    @Expose
    @Element(name = "Code", required = false)
    public String code;

    @Expose
    @Element(name = "Reason", required = false)
    public String reason;
}
