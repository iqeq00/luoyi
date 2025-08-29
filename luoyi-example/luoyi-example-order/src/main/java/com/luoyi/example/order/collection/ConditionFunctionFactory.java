package com.luoyi.example.order.collection;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
/**
 * 条件验证函数工厂
 *
 * @author yaojinchi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConditionFunctionFactory {

    // 存储条件类型与验证函数的映射
    private static final Map<ConditionType, BiFunction<Object, List<String>, Boolean>> FUNCTION_MAP = new ConcurrentHashMap<>();

    // 静态初始化默认验证函数
    static {
        registerDefaultFunctions();
    }

    /**
     * 注册默认验证函数
     */
    private static void registerDefaultFunctions() {

        registerFunction(ConditionType.EQUAL, ConditionFunctionFactory::checkEqual);
        registerFunction(ConditionType.NOT_EQUAL, ConditionFunctionFactory::checkNotEqual);
        registerFunction(ConditionType.RANGE, ConditionFunctionFactory::checkRange);
        registerFunction(ConditionType.GREATER_THAN, ConditionFunctionFactory::checkGreaterThan);
        registerFunction(ConditionType.LESS_THAN, ConditionFunctionFactory::checkLessThan);
        registerFunction(ConditionType.GREATER_THAN_OR_EQUAL, ConditionFunctionFactory::checkGreaterThanOrEqual);
        registerFunction(ConditionType.LESS_THAN_OR_EQUAL, ConditionFunctionFactory::checkLessThanOrEqual);
    }

    /**
     * 注册自定义验证函数
     */
    public static void registerFunction(ConditionType type, BiFunction<Object, List<String>, Boolean> function) {

        Optional.ofNullable(type).ifPresent(t -> Optional.ofNullable(function).ifPresent(f -> FUNCTION_MAP.put(t, f)));
    }

    /**
     * 获取条件对应的验证函数
     */
    public static BiFunction<Object, List<String>, Boolean> getFunction(ConditionType type) {

        return FUNCTION_MAP.get(type);
    }

    // ------------------------------ 条件验证逻辑 ------------------------------

    /**
     * equal条件：判断字段值是否在values列表中
     */
    private static boolean checkEqual(Object fieldValue, List<String> values) {

        return Optional.ofNullable(values).filter(CollectionUtil::isNotEmpty).flatMap(vals -> Optional.ofNullable(fieldValue)
            .map(fieldVal -> vals.stream().anyMatch(value -> isValueMatch(fieldVal, value)))).orElse(false);
    }

    /**
     * not equal条件：判断字段值是否不在values列表中
     */
    private static boolean checkNotEqual(Object fieldValue, List<String> values) {

        return !checkEqual(fieldValue, values);
    }

    /**
     * range条件：判断字段值是否在values[0]和values[1]之间（包含边界）
     */
    public static boolean checkRange(Object fieldValue, List<String> values) {

        return Optional.ofNullable(values).filter(vals -> vals.size() >= 2)
            .flatMap(vals -> compareWithValue(fieldValue, vals.get(0), val -> val >= 0)
            .flatMap(gteMin -> compareWithValue(fieldValue, vals.get(1), val -> val <= 0)
            .map(lteMax -> gteMin && lteMax))).orElse(false);
    }

    /**
     * gt条件：判断字段值是否大于values[0]
     * 特殊处理：时间类型字段且值为数字时，转换为区间校验
     */
    private static boolean checkGreaterThan(Object fieldValue, List<String> values) {

        return handleTimeTypeGreaterThan(fieldValue, values).orElseGet(() -> checkComparison(fieldValue, values, val -> val > 0));
    }

    /**
     * gt特殊逻辑: 处理时间类型字段
     */
    private static Optional<Boolean> handleTimeTypeGreaterThan(Object fieldValue, List<String> values) {

        return resolveTimeTypeAndDays(fieldValue, values).map(ConditionFunctionAdapter::calculateTimeRangeGt).map(range -> checkRange(fieldValue, range));
    }

    /**
     * lt条件：判断字段值是否小于values[0]
     * 特殊处理：时间类型字段且值为数字时，转换为区间校验
     */
    private static boolean checkLessThan(Object fieldValue, List<String> values) {

        return handleTimeTypeLessThan(fieldValue, values).orElseGet(() -> checkComparison(fieldValue, values, val -> val < 0));
    }

    /**
     * lt特殊逻辑: 处理时间类型字段
     */
    private static Optional<Boolean> handleTimeTypeLessThan(Object fieldValue, List<String> values) {

        return resolveTimeTypeAndDays(fieldValue, values).map(ConditionFunctionAdapter::calculateTimeRangeLt).map(range -> checkRange(fieldValue, range));
    }

    /**
     * ge条件：判断字段值是否大于等于values[0]
     */
    private static boolean checkGreaterThanOrEqual(Object fieldValue, List<String> values) {

        return checkComparison(fieldValue, values, val -> val >= 0);
    }

    /**
     * le条件：判断字段值是否小于等于values[0]
     */
    private static boolean checkLessThanOrEqual(Object fieldValue, List<String> values) {

        return checkComparison(fieldValue, values, val -> val <= 0);
    }

    // ------------------------------ 通用工具方法 ------------------------------

    /**
     * 公共的时间类型处理前置逻辑
     */
    private static Optional<Integer> resolveTimeTypeAndDays(Object fieldValue, List<String> values) {

        return Optional.of(fieldValue).filter(ConditionFunctionAdapter::isTimeType).filter(val -> ConditionFunctionAdapter.isParsableAsDays(values)).flatMap(val -> ConditionFunctionAdapter.parseDays(values.get(0)));
    }

    /**
     * 通用比较逻辑
     */
    private static boolean checkComparison(Object fieldValue, List<String> values, Function<Integer, Boolean> comparator) {

        return Optional.ofNullable(values).filter(vals -> CollectionUtil.isNotEmpty(vals)).flatMap(vals -> compareWithValue(fieldValue, vals.get(0), comparator)).orElse(false);
    }

    /**
     * 与单个值比较
     */
    private static Optional<Boolean> compareWithValue(Object fieldValue, String value, Function<Integer, Boolean> comparator) {

        return getComparisonResult(fieldValue, value).map(comparator);
    }

    /**
     * 获取比较结果
     *
     * 数字比较 -> 时间比较 -> 字符串比较
     */
    private static Optional<Integer> getComparisonResult(Object fieldValue, String value) {

        return compareAsNumbers(fieldValue, value).or(() -> compareAsDates(fieldValue, value)).or(() -> compareAsStrings(fieldValue, value));
    }

    /**
     * 检查字段值与单个条件值是否匹配
     *
     * 检查非空 -> 数字匹配 -> 时间匹配 -> 字符串匹配
     */
    private static boolean isValueMatch(Object fieldValue, String value) {

        return Optional.ofNullable(fieldValue).flatMap(fieldVal -> Optional.ofNullable(value).flatMap(val -> compareAsNumbers(fieldVal, val).map(v -> v == 0)
            .or(() -> compareAsDates(fieldVal, val).map(v -> v == 0)).or(() -> Optional.of(fieldVal.toString().equals(val)))))
            .orElse(false);
    }

    /**
     * 数字比较
     */
    private static Optional<Integer> compareAsNumbers(Object fieldValue, String value) {

        return convertToBigDecimal(fieldValue).flatMap(fieldNum -> convertToBigDecimal(value).map(valueNum -> fieldNum.compareTo(valueNum)));
    }

    /**
     * 时间比较
     */
    private static Optional<Integer> compareAsDates(Object fieldValue, String value) {

        return Optional.ofNullable(fieldValue).map(Object::toString).flatMap(fieldStr -> parseDate(fieldStr)
            .flatMap(fieldDate -> parseDate(value).map(valueDate -> fieldDate.compareTo(valueDate))));
    }

    /**
     * 字符串比较
     */
    private static Optional<Integer> compareAsStrings(Object fieldValue, String value) {

        return Optional.ofNullable(fieldValue).map(Object::toString).map(fieldStr -> null != value ? fieldStr.compareTo(value) : 1);
    }

    /**
     * 解析日期字符串为DateTime
     */
    private static Optional<DateTime> parseDate(String dateStr) {

        try {
            return Optional.of(DateUtil.parse(dateStr));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * 转换为BigDecimal
     */
    private static Optional<BigDecimal> convertToBigDecimal(Object value) {

        return Optional.ofNullable(value).map(Object::toString).filter(str -> StrUtil.isNotBlank(str.trim())).flatMap(str -> {
            try {
                return Optional.of(new BigDecimal(str));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        });
    }

}