package com.example.sunnyweather.base;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.RequestBody;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2018/1/19 10:26
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public abstract class MyRequest2Body extends MyRequestBodyConverter<Request> {

    MyRequest2Body(Encrypt encrypt, Type type){
        super(encrypt, type);
    }

    @Override
    public final RequestBody convert(Request request) throws IOException {
        if (request != null) {
            request.isReqEnc = isReqEnc;
            request.isRespEnc = isRespEnc;
        }
        return from(request);
    }

    abstract RequestBody from(Request request);
}
