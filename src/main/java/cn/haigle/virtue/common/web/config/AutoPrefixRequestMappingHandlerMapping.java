package cn.haigle.virtue.common.web.config;

import cn.haigle.virtue.common.web.Constant;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 自动前缀路由映射处理器
 * <p>根据 Controller 所在包路径自动添加 API 前缀：</p>
 * <ul>
 *   <li>{@code controller.admin} 包下自动添加 {@code /admin-api}</li>
 *   <li>{@code controller.app} 包下自动添加 {@code /app-api}</li>
 *   <li>{@code controller.pc} 包下自动添加 {@code /pc-api}</li>
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

        String prefix = resolvePrefix(handlerType);
        if (prefix != null) {
            RequestMappingInfo prefixInfo = RequestMappingInfo.paths(prefix).build();
            return prefixInfo.combine(info);
        }

        return info;
    }

    private String resolvePrefix(Class<?> handlerType) {
        String packageName = handlerType.getPackage().getName();
        if (packageName.contains(".controller.admin.")) {
            return Constant.ADMIN_API;
        } else if (packageName.contains(".controller.app.")) {
            return Constant.APP_API;
        } else if (packageName.contains(".controller.pc.")) {
            return Constant.PC_API;
        }
        return null;
    }
}
