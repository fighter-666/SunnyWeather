package com.example.sunnyweather.base;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2018/1/4 14:58
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class XmlResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Class<T> cls;

    XmlResponseBodyConverter(Type type) {
        cls = (Class) type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String rawData = value.string();
        try {
            return new Persister().read(cls, rawData, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
