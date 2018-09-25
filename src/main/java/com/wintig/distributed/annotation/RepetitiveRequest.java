package com.wintig.distributed.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Despriction 限制重复请求注解头
 *
 * @Author wintig
 * @Create 2018-09-25 上午7:43
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepetitiveRequest {

    /**
     * 限制时间，单位毫秒
     */
    int limitTime() default 500;

}
