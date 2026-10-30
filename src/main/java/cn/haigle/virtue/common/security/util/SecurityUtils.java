package cn.haigle.virtue.common.security.util;

import cn.haigle.virtue.common.security.jwt.JwtConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

/**
 * 当前登录用户工具类
 *
 * @author haigle
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * 当前用户ID，未登录返回 null
     */
    public static Long getUserId() {
        JwtAuthenticationToken authentication = getJwtAuthentication();
        if (authentication == null) {
            return null;
        }
        String subject = authentication.getToken().getSubject();
        return subject == null ? null : Long.valueOf(subject);
    }

    /**
     * 当前账号，未登录返回 null
     */
    public static String getUsername() {
        JwtAuthenticationToken authentication = getJwtAuthentication();
        return authentication == null ? null : authentication.getToken().getClaimAsString(JwtConfig.CLAIM_USERNAME);
    }

    /**
     * 当前登录类型 admin/app/pc，未登录返回 null
     */
    public static String getLoginType() {
        JwtAuthenticationToken authentication = getJwtAuthentication();
        return authentication == null ? null : authentication.getToken().getClaimAsString(JwtConfig.CLAIM_LOGIN_TYPE);
    }

    /**
     * 当前登录会话ID，未登录返回 null
     */
    public static String getSessionId() {
        JwtAuthenticationToken authentication = getJwtAuthentication();
        return authentication == null ? null : authentication.getToken().getClaimAsString(JwtConfig.CLAIM_SESSION_ID);
    }

    /**
     * 当前用户权限标识，格式 ROLE_{mark}
     */
    public static List<String> getAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return List.of();
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    private static JwtAuthenticationToken getJwtAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            return jwtAuthenticationToken;
        }
        return null;
    }

}
