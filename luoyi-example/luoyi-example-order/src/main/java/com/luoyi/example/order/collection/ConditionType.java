package com.luoyi.example.order.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 条件枚举类
 *
 * @author yaojinchi
 */
@Getter
@AllArgsConstructor
public enum ConditionType {

    EQUAL("equal", "等于（包含）"),            // 字段值在values列表中
    NOT_EQUAL("ne", "不等于（不包含）"),        // 字段值不在values列表中
    RANGE("range", "区间（between）"),        // 字段值在values[0]和values[1]之间
    GREATER_THAN("gt", "大于"),              // 字段值 > values[0]
    LESS_THAN("lt", "小于"),                 // 字段值 < values[0]
    GREATER_THAN_OR_EQUAL("ge", "大于等于"),  // 字段值 >= values[0]
    LESS_THAN_OR_EQUAL("le", "小于等于");     // 字段值 <= values[0]

    private final String code;
    private final String description;

    /**
     * 根据编码获取条件类型
     */
    public static Optional<ConditionType> getInstance(String code) {

        return Optional.ofNullable(code).filter(c -> !c.trim().isEmpty()).flatMap(c -> Arrays.stream(values())
            .filter(type -> type.code.equalsIgnoreCase(c)).findFirst());
    }

}