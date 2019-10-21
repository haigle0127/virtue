package com.haigle.around.common.interceptor;

import com.haigle.around.common.interceptor.permission.annotation.Permissions;
import com.haigle.around.common.interceptor.exception.IllegalRequestException;
import com.haigle.around.common.interceptor.exception.NoPermissionAccessException;
import com.haigle.around.common.interceptor.exception.NoTokenException;
import com.haigle.around.common.interceptor.exception.TokenExpiredException;
import com.haigle.around.common.interceptor.permission.authentication.PowerPermission;
import com.haigle.around.common.util.JwtUtils;
import com.haigle.around.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 *  权限拦截器
 * @author haigle
 * @date 2018/8/2 12:43
 */
public class PermissionInterceptor implements HandlerInterceptor {

    private static final String OPTION = "OPTIONS";

    @Autowired
    private PowerPermission powerPermission;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        /*
         * 如果是预处理请求直接过
         */
        String method = request.getMethod();
        if (OPTION.equalsIgnoreCase(method)) {
            return true;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Permissions methodPermission = handlerMethod.getMethodAnnotation(Permissions.class);
            Permissions classPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Permissions.class);

            /*
             * 判断在方法或者类上有没有加权限，如果都有以方法上为准
             */
            Permissions permission = Optional.ofNullable(methodPermission).orElse(classPermission);
            Optional.ofNullable(permission).ifPresent(p -> {
                String token = request.getHeader(Constant.TOKEN);

                /*
                 * 没有token说明未登录
                 */
                Optional.ofNullable(token)
                        .filter(t -> !t.isEmpty())
                        .orElseThrow(NoTokenException::new);

                /*
                 * 没有获取到uid说明token过期或者不是token
                 */
                Long uid;
                try {
                    uid = JwtUtils.getSubject(token);
                } catch (Exception e) {
                    throw new TokenExpiredException();
                }

                /*
                 * 判断是否拥有访问权限
                 */
                if(permission.type() && !powerPermission.hasPermission(uid, permission.value())) {
                    throw new NoPermissionAccessException();
                }
            });
            return true;
        }else{
            throw new IllegalRequestException();
        }
    }
}
