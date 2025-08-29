package com.luoyi.example.order.collection;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.NumberUtil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 选品集条件验证时间处理适配器
 *
 * @author yaojinchi
 */
public class ConditionFunctionAdapter {

    /**
     * 判断字段是否为时间类型
     */
    public static boolean isTimeType(Object fieldValue) {

        return fieldValue instanceof LocalDateTime || fieldValue instanceof java.util.Date || fieldValue instanceof DateTime;
    }

    /**
     * 判断条件值是否为单个可解析为整数的字符串
     */
    public static boolean isParsableAsDays(List<String> values) {

        return values.size() == 1 && NumberUtil.isInteger(values.get(0));
    }

    /**
     * 解析天数
     */
    public static Optional<Integer> parseDays(String value) {

        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * 计算时间区间，小于
     */
    public static List<String> calculateTimeRangeLt(int days) {

        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusDays(days);
        return Arrays.asList(startTime.toString(), endTime.toString());
    }

    /**
     * 计算时间区间，大于
     */
    public static List<String> calculateTimeRangeGt(int days) {

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusDays(days);
        return Arrays.asList(startTime.toString(), endTime.toString());
    }

}