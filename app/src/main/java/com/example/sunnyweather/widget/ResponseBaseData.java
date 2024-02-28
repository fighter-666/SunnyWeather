package com.example.sunnyweather.widget;

import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2018/1/2 15:05
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class ResponseBaseData<T> implements Serializable {

    @Element(name = "ResultCode")
    public String resultCode;

    @Element(name = "ResultDesc", required = false)
    public String resultDesc;

    @Element(name = "Attach", required = false)
    public String attach;

}
