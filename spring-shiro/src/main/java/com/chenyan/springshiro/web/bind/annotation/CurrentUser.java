package com.chenyan.springshiro.web.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.chenyan.springshiro.Constants;

/**
 * <p>绑定当前登陆的用户</p>
 *
 * @author chenyan
 * @date 2019年8月28日
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default Constants.CURRENT_USER;
}
