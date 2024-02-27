package com.example.sunnyweather.base;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/12/29 17:29
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class JsonRequestBodyConverter extends MyRequest2Body {

    public JsonRequestBodyConverter(Encrypt encrypt, Type type) {
        super(encrypt, type);
    }

    @Override
    RequestBody from(Request request) {
        String reqStr = "";
        try {
            reqStr = Request.getRequestJson(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), reqStr);
    }

}
