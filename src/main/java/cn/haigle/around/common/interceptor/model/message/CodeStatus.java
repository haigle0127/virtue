package cn.haigle.around.common.interceptor.model.message;

/**
 * 枚举类型
 * @author haigle
 * @date 2020/11/7 9:31 下午
 */
public enum CodeStatus {

    /**
     * {@code 200 OK}.
     * 一切正确的都用这个
     */
    OK(200, "OK"),
    USER_ERROR(400, "账号或密码错误"),
    TOKEN_ERROR(401, "TOKEN错误"),
    TOKEN_EXPIRED(402, "登录过期, 请重新登录"),
    FORM_VALIDATION_ERROR(901, "FORM_VALIDATION_ERROR"),
    NOT_PERMISSION_EXPIRED(403, "无权限访问"),
    EMAIL_EXIST(405, "邮箱已注册"),
    CAPTCHA_NOT_SENT(405, "未获取验证码"),
    CAPTCHA_ERROR(405, "验证码不正确"),
    EMAIL_FORMAT_ERROR(405, "邮箱格式不正确"),
    EXIST(500, "服务错误，各种错误，你就找吧"),
    REDIS_EXPIRED(501, "Redis数据库错误"),
    FILE_UPLOAD_FAIL_EXPIRED(521, "上传失败"),

    /**
     * {@code 401 Unauthorized}.
     * 权限错误
     */
    UNAUTHORIZED(401, "Unauthorized");

    private final int value;

    private final String name;

    CodeStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */

}
