package cn.haigle.virtue.common.exception;

/**
 * 业务错误码
 *
 * @author haigle
 */
public enum CodeConstants {

    OK(200, "OK"),
    FAIL(500, "系统异常"),
    USER_ERROR(406, "账号或密码错误"),
    USER_DISABLED(406, "账号已停用"),
    TOKEN_ERROR(401, "登录状态无效或已过期"),
    TOKEN_EXPIRED(401, "登录已过期，请重新登录"),
    NOT_PERMISSION_EXPIRED(403, "无权限访问"),
    FORM_VALIDATION_ERROR(901, "参数校验失败"),
    EMAIL_EXIST(405, "邮箱已注册"),
    CAPTCHA_NOT_SENT(405, "未获取验证码"),
    CAPTCHA_ERROR(405, "验证码不正确"),
    EMAIL_FORMAT_ERROR(405, "邮箱格式不正确"),
    REDIS_EXPIRED(501, "Redis 数据库错误"),
    FILE_UPLOAD_FAIL_EXPIRED(521, "上传失败"),
    VERIFY_FIELD_ERROR(901, "验证错误"),
    UNAUTHORIZED(401, "Unauthorized");

    private final Integer value;
    private final String name;

    CodeConstants(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
