package cn.haigle.virtue.common.interceptor.permission.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 权限控制
 * 此注解 value 值与 power值相对应
 * 当此注解加在类上时，权限判断时会默认类里所有的方法都需要访问权限，在再
 * 此类的方法中加入 type 并设置成 false时，所有角色均可访问该类
 * @author haigle
 * @date 2018/11/9 9:17
 */
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface Permissions {

    /**
     * 权限标识
     */
    String value() default "";

    /**
     * 方法有无权限
     */
    boolean type() default true;
}
