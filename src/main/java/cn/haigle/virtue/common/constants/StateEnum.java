package cn.haigle.virtue.common.constants;

/**
 * 对错枚举 遵循0否1是的原则
 * @author haigle
 * @date 2020/11/7 9:31 下午
 */
public enum StateEnum {

    ONE("1", "是"),
    ZERO("0", "否");

    private final String value;

    private final String name;

    StateEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String value() {
        return this.value;
    }


}
