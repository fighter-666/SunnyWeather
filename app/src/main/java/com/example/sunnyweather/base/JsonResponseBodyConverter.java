package com.example.sunnyweather.base;
 
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/12/29 17:09
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final TypeAdapter<?> adapter;
    private final Type type;

    JsonResponseBodyConverter(Type type) {
        this.adapter = new Gson().getAdapter(TypeToken.get(type));
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        //解密字符串
        String string = value.string();
        try {
//            return (T) adapter.fromJson(string)
            return (T) UtilGson.parseJsonWithGson(string, (Class<Object>) type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
