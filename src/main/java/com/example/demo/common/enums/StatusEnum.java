package com.example.demo.common.enums;

/**
 * 状态枚举：有效/无效
 */
public enum StatusEnum {
    /**
     * 无效
     */
    INVALID(0, "无效"),

    /**
     * 有效
     */
    VALID(1, "有效");

    private final int code;
    private final String desc;

    StatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据code获取枚举值
     *
     * @param code 状态码
     * @return 对应的枚举值，如果没有找到则返回null
     */
    public static StatusEnum getByCode(int code) {
        for (StatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    /**
     * 判断状态码是否有效
     * @param code 状态码
     * @return 是否有效
     */
    public static boolean isValid(int code) {
        return code == VALID.getCode();
    }
}

