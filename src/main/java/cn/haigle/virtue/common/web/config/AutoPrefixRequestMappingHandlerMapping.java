package cn.haigle.virtue.common.web.config;

import cn.haigle.virtue.common.web.Constant;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 自动前缀路由映射处理器
 * <p>根据 Controller 所在包路径自动添加 API 前缀：</p>
 * <ul>
 *   <li>{@code controller.admin} 包下自动添加 {@code /api/admin}</li>
 *   <li>{@code controller.app} 包下自动添加 {@code /api/app}</li>
 * </ul>
 * @author haigle
 * @date 2026/4/29 15:10
 */
public class AutoPrefixRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
        if (info == null) {
            return null;
        }

        // 如果类上显式写了完整路径（以 /api 开头），不自动加前缀，保持向后兼容
        RequestMapping classMapping = AnnotatedElementUtils.findMergedAnnotation(handlerType, RequestMapping.class);
        if (classMapping != null && classMapping.value().length > 0) {
            String classPath = classMapping.value()[0];
            if (classPath.startsWith("/api")) {
                return info;
            }
        }

        String prefix = resolvePrefix(handlerType);
        if (prefix != null) {
            RequestMappingInfo prefixInfo = RequestMappingInfo.paths(prefix).build();
            return prefixInfo.combine(info);
        }

        return info;
    }

    private String resolvePrefix(Class<?> handlerType) {
        String packageName = handlerType.getPackage().getName();
        // admin 包下除了 login 子包（登录/验证码为公共接口）外自动加前缀
        if (packageName.contains(".controller.admin.") && !packageName.contains(".controller.admin.login")) {
            return Constant.ADMIN;
        } else if (packageName.contains(".controller.app.")) {
            return Constant.APP;
        }
        return null;
    }
}
