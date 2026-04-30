package cn.haigle.virtue.common.security.interceptor;

import cn.haigle.virtue.common.interceptor.exception.ServiceException;
import cn.haigle.virtue.common.interceptor.exception.enums.CodeConstants;
import cn.haigle.virtue.common.security.annotation.RequireLogin;
import cn.haigle.virtue.common.security.context.SecurityContext;
import cn.haigle.virtue.common.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 * <p>拦截带有 {@link RequireLogin} 注解的类或方法，校验用户是否已登录</p>
 * @author haigle
 * @date 2026/4/29 15:10
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        // 先检查方法上是否有注解，方法优先级高于类
        RequireLogin methodAnnotation = handlerMethod.getMethodAnnotation(RequireLogin.class);
        if (methodAnnotation != null) {
            checkLogin(request);
            return true;
        }

        // 再检查类上是否有注解
        RequireLogin classAnnotation = handlerMethod.getBeanType().getAnnotation(RequireLogin.class);
        if (classAnnotation != null) {
            checkLogin(request);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SecurityContext.clear();
    }

    private void checkLogin(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            throw new ServiceException(CodeConstants.TOKEN_ERROR);
        }

        String token = authHeader.substring(BEARER_PREFIX.length());
        Long userId = JwtUtil.parseToken(token);
        String loginType = JwtUtil.parseLoginType(token);
        SecurityContext.setUserId(userId);
        SecurityContext.setLoginType(loginType);
    }
}
