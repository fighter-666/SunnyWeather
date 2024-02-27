package com.example.sunnyweather.base;



import android.util.Xml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;

import kotlinx.serialization.json.Json;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/12/29 17:07
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class MyConverters extends Converter.Factory {


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        for (Annotation annotation : annotations) {
            if (annotation instanceof Json) {
                return new JsonResponseBodyConverter<>(type);
            }
            if (annotation instanceof Xml) {
                return new XmlResponseBodyConverter<>(type);
            }
        }
        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        Encrypt encrypt = null;
        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof Encrypt) {
                encrypt = (Encrypt) annotation;
                break;
            }
        }

        setUrlPartIfNeeded(methodAnnotations, retrofit.baseUrl());
        setProxyIfNeeded(methodAnnotations, retrofit.callFactory());
        setTimeoutIfNeeded(methodAnnotations, retrofit.callFactory());

        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof Json) {
                return new JsonRequestBodyConverter(encrypt, type);
            }
            if (annotation instanceof Xml) {
                return new XmlRequestBodyConverter(encrypt, type);
            }
        }
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    /**
     * 说明：动态修改host和port
     * 注解Url 和 注解Host/Post 互斥
     *
     * @作者 linwen@ffcs.cn
     * @创建时间 2018/1/5 17:09
     * @版本
     * @------修改记录-------
     * @修改人
     * @版本
     * @修改内容
     */
    private static void setUrlPartIfNeeded(Annotation[] methodAnnotations, HttpUrl httpUrl) {
        boolean isUrl = false;
        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof Url) {
                isUrl = true;
                break;
            }
        }
        for (Annotation annotation : methodAnnotations) {
            if (isUrl) {
                if (annotation instanceof Url) {
                    Url url = (Url) annotation;
                    setField(httpUrl, "host", url.host());
                    setField(httpUrl, "port", url.port());
                    break;
                }
            } else {
                if (annotation instanceof Host) {
                    Host host = (Host) annotation;
                    setField(httpUrl, "host", host.value());
                } else if (annotation instanceof Port) {
                    Port port = (Port) annotation;
                    setField(httpUrl, "port", port.value());
                }
            }
        }
    }

    private static void setProxyIfNeeded(Annotation[] methodAnnotations, Call.Factory client) {
        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof Proxy) {
                Proxy proxyConfig = (Proxy) annotation;
                if (client instanceof OkHttpClient) {
                    java.net.Proxy proxy = new java.net.Proxy(proxyConfig.type(),
                            new InetSocketAddress(proxyConfig.host(), proxyConfig.port()));
                    setField(client, "proxy", proxy);
                }
                break;
            }
        }
    }

    private static void setTimeoutIfNeeded(Annotation[] methodAnnotations, Call.Factory client) {
        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof Timeout) {
                Timeout timeout = (Timeout) annotation;
                if (client instanceof OkHttpClient) {
                    setField(client, "connectTimeout", Integer.valueOf(timeout.connect()+""));
                    setField(client, "readTimeout", Integer.valueOf(timeout.read()+""));
                    setField(client, "writeTimeout", Integer.valueOf(timeout.write()+""));
                }
                break;
            }
        }
    }

    private static  <T>void setField(T target, String fieldName, Object newValue) {
        try {
            Class<?> raw = target.getClass();
            Field field = raw.getDeclaredField(fieldName);
            field.setAccessible(true);

//            Field modifiers = field.getClass().getDeclaredField("modifiers");
//            modifiers.setAccessible(true);
//            modifiers.setInt(field, hostF.getModifiers() & ~Modifier.FINAL);

            field.set(target, newValue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
