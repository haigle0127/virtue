package com.haigle.around.common.interceptor.annotation;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.SQLException;

/**
 * 常见事务配置（支持当前事务，如果当前没有事务，就新建一个事务）
 * @author haigle
 * @date 2019/3/6 19:07
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.READ_COMMITTED,rollbackFor={Exception.class, RuntimeException.class, SQLException.class})
public @interface Commit {

}
