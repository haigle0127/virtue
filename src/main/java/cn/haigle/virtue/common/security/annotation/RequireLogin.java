package cn.haigle.virtue.common.security.annotation;

import java.lang.annotation.*;

/**
 * 标识需要登录才能访问的接口或类
 * <p>标注在类上表示该类所有接口都需要登录</p>
 * <p>标注在方法上表示该方法需要登录，优先级高于类上的标注</p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireLogin {
}
