package com.haigle.around.common.interceptor.annotation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 只读事务
 * @author haigle
 * @date 2019/3/6 19:08
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
public @interface ReadOnly {

}
