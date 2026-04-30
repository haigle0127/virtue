package cn.haigle.virtue.common.enums;

/**
 * 登录类型枚举
 * <p>区分不同端口的登录类型，不同登录类型可对应不同用户表或同一张用户表的不同逻辑</p>
 *
 * @author haigle
 * @date 2026/4/30
 */
public enum LoginTypeEnum {

    ADMIN("admin", "管理端登录"),
    APP("app", "移动端登录"),
    PC("pc", "PC端登录");

    private final String code;
    private final String desc;

    LoginTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据 code 获取枚举
     */
    public static LoginTypeEnum fromCode(String code) {
        if (code == null) {
            return null;
        }
        for (LoginTypeEnum type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
