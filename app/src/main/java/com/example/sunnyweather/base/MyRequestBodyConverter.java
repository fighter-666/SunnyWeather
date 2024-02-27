package com.example.sunnyweather.base;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2018/1/18 10:45
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public abstract class MyRequestBodyConverter<T> implements Converter<T, RequestBody> {
    protected final Type mType;
    final boolean isReqEnc;
    final boolean isRespEnc;

    MyRequestBodyConverter(Encrypt encrypt, Type type){
        if (encrypt == null) {
            this.isReqEnc = false;
            this.isRespEnc = false;
        } else {
            this.isReqEnc = encrypt.isRequest();
            this.isRespEnc = encrypt.isResponse();
        }
        this.mType = type;

    }

    @Override
    public RequestBody convert(T value) throws IOException {
//        Gson gson = new Gson();
//        TypeAdapter<T> adapter = (TypeAdapter<T>) gson.getAdapter(TypeToken.get(mType));
//        Buffer buffer = new Buffer();
//        Writer writer = new OutputStreamWriter(buffer.outputStream(),UTF_8);
//        JsonWriter jsonWriter = gson.newJsonWriter(writer);
//        adapter.write(jsonWriter,value);
//        jsonWriter.flush();
        if (value instanceof Request) {
            Request req = (Request) value;
            req.isReqEnc = isReqEnc;
            req.isRespEnc = isRespEnc;
        }
        //...others
        return null;
    }
}
