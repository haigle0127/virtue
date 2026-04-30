package cn.haigle.virtue.common.security.context;

/**
 * 安全上下文，存储当前登录用户信息
 * @author haigle
 * @date 2026/4/29 15:10
 */
public class SecurityContext {

    private static final ThreadLocal<Long> CURRENT_USER = new ThreadLocal<>();
    private static final ThreadLocal<String> CURRENT_LOGIN_TYPE = new ThreadLocal<>();

    /**
     * 设置当前登录用户ID
     */
    public static void setUserId(Long userId) {
        CURRENT_USER.set(userId);
    }

    /**
     * 获取当前登录用户ID
     */
    public static Long getUserId() {
        return CURRENT_USER.get();
    }

    /**
     * 设置当前登录类型
     */
    public static void setLoginType(String loginType) {
        CURRENT_LOGIN_TYPE.set(loginType);
    }

    /**
     * 获取当前登录类型
     */
    public static String getLoginType() {
        return CURRENT_LOGIN_TYPE.get();
    }

    /**
     * 清除当前线程的用户信息
     */
    public static void clear() {
        CURRENT_USER.remove();
        CURRENT_LOGIN_TYPE.remove();
    }
}
