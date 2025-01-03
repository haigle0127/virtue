package cn.haigle.virtue.common.constants;

/**
 * 数据权限枚举
 * @author haigle
 * @date 2024/11/14 14:28 下午
 */
public enum RoleDataScopeEnum {

    ONE("1", "全部数据权限"),
    TWO("2", "自定数据权限"),
    THREE("3", "本部门数据权限"),
    FOUR("4", "本部门及以下数据权限");

    private final String value;

    private final String name;

    RoleDataScopeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String value() {
        return this.value;
    }


}
