package com.example.sunnyweather.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2018/1/4 15:33
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Encrypt {
    boolean isRequest() default true;
    boolean isResponse() default true;
}
