package cn.haigle.virtue.common.security.context;

/**
 * 安全上下文，存储当前登录用户信息
 * @author haigle
 * @date 2026/4/29 15:10
 */
public class SecurityContext {

    private static final ThreadLocal<Long> CURRENT_USER = new ThreadLocal<>();

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
     * 清除当前线程的用户信息
     */
    public static void clear() {
        CURRENT_USER.remove();
    }
}
