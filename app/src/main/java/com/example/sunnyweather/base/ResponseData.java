package com.example.sunnyweather.base;

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
public class ResponseData<T> extends ResponseBaseData implements Serializable {

    public T data;
}
